package top.kuangcp.graduate.wechat.handler;

import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.service.business.student.WxStudentService;
import top.kuangcp.graduate.wechat.builder.TextBuilder;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Log4j2
@Component
public class MsgHandler extends AbstractHandler {

    private final StudentDao studentDao;
    private final WxStudentService studentService;

    public MsgHandler(StudentDao studentDao, WxStudentService studentService) {
        this.studentDao = studentDao;
        this.studentService = studentService;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        String content = "1.回复 绑定，完成和账号的绑定\n2.回复 成绩, 查询答辩成绩\n3.回复 场地， 查询答辩场地 ";
        //TODO 组装回复消息
//        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        log.info("收到消息 |" + wxMessage.getContent() + "|"+ "成绩".equals(wxMessage.getContent()));
        if ("绑定".equals(wxMessage.getContent())) {
            content = "请回复 学号###密码###  进行账号的绑定";
        }

        if("成绩".equals(wxMessage.getContent())){
            content = studentService.queryScoreByWechat(wxMessage.getFromUser());
        }
        if("场地".equals(wxMessage.getContent())){
            content = studentService.queryPlaceByWechat(wxMessage.getFromUser());
        }
        String[] result = wxMessage.getContent().split("###");
        if(result.length>1){
//            log.info(result[0]+ " | "+result[1]);
            Student student = studentDao.findByStudentNo(result[0]);
            if(student != null){
                if(student.getOpenId() != null){
                    content = "不能重复绑定";
                }else if(student.getPassword().equals(result[1])){
                    content = "绑定成功";
                    student.setOpenId(wxMessage.getFromUser());
                    studentDao.save(student);
                }else{
                    content = "账号或密码错误，请重试";
                }
            }
            else{
                content = "账号或密码错误，请重试";
            }
        }

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
