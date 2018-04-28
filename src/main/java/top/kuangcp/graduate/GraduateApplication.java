package top.kuangcp.graduate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * @author kcp
 */
@SpringBootApplication
public class GraduateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateApplication.class, args);
    }

//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory()
//    {
//        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
//        factory.setPort(8889);
//        factory.setContextPath("/api");
////        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//        return factory;
//    }
}
