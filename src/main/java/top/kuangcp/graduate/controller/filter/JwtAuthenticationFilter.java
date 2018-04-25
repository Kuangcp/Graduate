package top.kuangcp.graduate.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import top.kuangcp.graduate.service.security.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:30
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = null;
        try {
            authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
        } catch (Exception e) {
//            e.printStackTrace();
            log.info("登录失败");
            filterChain.doFilter(request, response);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
