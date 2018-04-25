//package top.kuangcp.graduate.controller;
//
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created by https://github.com/kuangcp
// *
// * @author kuangcp
// * @date 18-4-25  下午5:44
// */
//@RestController
//@RequestMapping("/error")
//public class ExceptionController extends BasicErrorController {
//    public ExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
//        super(errorAttributes, errorProperties, errorViewResolvers);
//    }
//
//
////    @RequestMapping("/500")
////    public String error(){
////        return JsonBuilder.buildResult(1, "500 错误", " ");
////    }
//
//}
