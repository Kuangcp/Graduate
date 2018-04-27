package top.kuangcp.graduate.service.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.CoreConfig;
import top.kuangcp.graduate.service.role.RoleService;

import java.util.ArrayList;

/**
 * @author kcp
 * 自定义身份认证验证组件
 */
@Log4j2
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private RoleService roleService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String username = name.split(CoreConfig.DELIMITER)[0];
        String role = name.split(CoreConfig.DELIMITER)[1];
        String password = authentication.getCredentials().toString();
        boolean result = false;
        log.info("roleService "+roleService);
        // 查询登录
        switch (role){
            case "student" :
                result = roleService.loginWithStudent(username, password);
                break;
            case "teacher" :
                result = roleService.loginWithTeacher(username, password);
                break;
            case "admin":
                result = roleService.loginWithAdmin(username, password);
                break;
            default:
                break;
        }
        // TODO 授权， 权限需要 ROLE_ 开头
        if(result){
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            switch (role){
                case "student" :
                    authorities.add( new GrantedAuthorityImpl("ROLE_STUDENT") );
                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                case "teacher" :
                    authorities.add( new GrantedAuthorityImpl("ROLE_TEACHER") );
//                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                case "admin" :
                    authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
//                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                default:
                    break;
            }
            log.info("用户登录成功 "+username+" | "+password+" | "+role);
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }else{
            log.info("用户登录失败 "+username+" | "+password+" | "+role);
            throw new BadCredentialsException("用户名或密码错误！");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}