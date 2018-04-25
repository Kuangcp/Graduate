package top.kuangcp.graduate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.kuangcp.graduate.controller.filter.JwtAuthenticationFilter;
import top.kuangcp.graduate.controller.filter.JwtLoginFilter;
import top.kuangcp.graduate.service.security.CustomAuthenticationProvider;

/**
 * Created by https://github.com/kuangcp
 * Security的配置类
 * @author kuangcp
 * @date 18-3-28  上午9:29
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // 设置 HTTP 验证规则 TODO 权限的设计
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证  跨站请求伪造, 关掉的话就不能post数据过来了
        // TODO 关于路径规则 ** * 各自标识的意思
        http.csrf().disable()
            // 对请求进行认证
            .authorizeRequests()
            // 所有OPTIONS请求全部放行
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 所有 / 的所有请求 都放行
            .antMatchers("/").permitAll()
            // /login 的POST请求 放行
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            // 权限检查
            .antMatchers("/hello").hasAuthority("AUTH_WRITE")
            // 角色检查
            .antMatchers("/world").hasRole("ADMIN")
            .antMatchers("/hi/*").hasRole("admin")
            // 对Rest请求需要身份认证, 放行OPTIONS
            .antMatchers(HttpMethod.POST).authenticated()
            .antMatchers(HttpMethod.PUT).authenticated()
            .antMatchers(HttpMethod.DELETE).authenticated()
            .antMatchers(HttpMethod.GET).authenticated()
            .and()
            // 添加一个过滤器 所有访问 /login 的请求交给 JwtLoginFilter 来处理 这个类处理所有的JWT相关内容
            .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器验证其他请求的Token是否合法
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }

}

