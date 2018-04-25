package top.kuangcp.graduate.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:31
 */
public class TokenAuthenticationService {
    private static Logger log = LoggerFactory.getLogger(TokenAuthenticationService.class);
    private static final long EXPIRATION_TIME = 432_000_000;     // 5天 432_000_000
    private static final String SECRET = "?jiushi?123@";            // JWT密码
    private static final String TOKEN_PREFIX = "Mythos";        // Token前缀，自定义，解析时要去除
    private static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    /**
     * 认证成功后 生成Token
     * @param response HttpServletResponse
     * @param auth Authentication 认证对象
     */
    public static void addAuthentication(HttpServletResponse response, Authentication auth) {
        StringBuilder builder = new StringBuilder();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            builder.append(authority.getAuthority()).append(",");
        }
        String role = builder.toString().substring(0, builder.length() - 1);

        // 生成JWT
        String jwt = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", role)
                // 用户名写入标题
                .setSubject(auth.getName())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        // 将 JWT 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JsonBuilder.buildResult(0, "", jwt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        log.info("获取认证信息，Token ：" + token);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
//            // 判断时间过期
//            Date targetDate = claims.getExpiration();
//            if(targetDate.getTime() < System.currentTimeMillis()){
//                log.info("过期 ");
//                return null;
//            }
            String user = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities)
                    : null;
        }
        return null;
    }
}
