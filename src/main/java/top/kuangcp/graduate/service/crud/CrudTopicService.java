package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.Calendar;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午8:36
 */
@Log4j2
@Service
public class CrudTopicService {
    @Autowired
    TopicDao topicDao;

    // TODO 分页
    public String listAll(){
        return JsonBuilder.buildSuccessResult("", topicDao.findAll());
    }

    public String findByName(String name) {
        List<Topic> list =  topicDao.findAllByNameContaining(name);
        if(list.size() == 0){
            return JsonBuilder.buildCodeResult(ResponseCode.NOT_FOUND);
        }
        return JsonBuilder.buildSuccessResult(" ", list);
    }

    public String save(Topic topic){
        try {
            topic.setPublishYear(Calendar.getInstance().get(Calendar.YEAR));
            topic.setStatus(0);
            log.info(topic.toString());
            topicDao.save(topic);
            return JsonBuilder.buildSuccessCodeResult();
        }catch (Exception e){
            log.error("保存课题失败", e);
            return JsonBuilder.buildCodeResult(ResponseCode.SAVE_TOPIC_ERROR);
        }
    }

}
