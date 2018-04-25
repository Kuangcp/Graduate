package top.kuangcp.graduate.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import top.kuangcp.graduate.service.security.TokenAuthenticationService;
import top.kuangcp.graduate.util.JsonBuilder;

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
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = null;
        try {
            authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
        } catch (Exception e) {
            log.info("鉴权失败 ", e.getMessage());
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JsonBuilder.buildResult(1, "auth error", "  "));
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
