package top.kuangcp.graduate.config;

/**
 * Created by https://github.com/kuangcp
 * TODO 全局异常对于JWT的使用是无效的， 怀疑就是整体和SpringMVC体系不契合，异常根本没经过这个全局异常
 * @author kuangcp
 * @date 18-4-25  下午5:42
 */

//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
////    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public String notFoundHandler(HttpServletRequest request, Exception e) {
//        return JsonBuilder.buildResult(1, "404", " ");
//    }
//
//
////    @ExceptionHandler(value = RuntimeException.class)
//////    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
////    public String internalServerErrorHandler(HttpServletRequest request, Exception e) {
////        return JsonBuilder.buildResult(1, "500", " ");
////    }
//}

//@EnableWebMvc
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = { NoHandlerFoundException.class })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String noHandlerFoundException(Exception ex) {
//
//        return JsonBuilder.buildResult(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), " ");
////        return RespUtil.make(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
//    }
//
//    @ExceptionHandler(value = { ConstraintViolationException.class })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String constraintViolationException(ConstraintViolationException ex) {
//
//        return JsonBuilder.buildResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), " ");
////        return RespUtil.make(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
//        System.out.println("5 00 ");
//        return JsonBuilder.buildResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), " ");
////        return RespUtil.make(HttpStatus.INTERNAL_SERVER_ERROR.value(),
////                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
//    }
//
//}


//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    /**
//     * 拦截业务异常
//     *
//     * @author fengshuonan
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public String notFount(Exception e) {
//        log.error("业务异常:", e);
//
//        return JsonBuilder.buildResult(500, " ", " ");
//    }

//    SignatureException
//    /**
//     * 用户未登录
//     */
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String unAuth(AuthenticationException e) {
//        log.error("用户未登陆：", e);
//        return "/login.html";
//    }
//
//
//    /**
//     * 拦截未知的运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ErrorTip notFount(RuntimeException e) {
//        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
//        getRequest().setAttribute("tip", "服务器未知运行时异常");
//        log.error("运行时异常:", e);
//        return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
//    }
//}