package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.ArrayList;
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
    private final TopicDao topicDao;

    @Autowired
    public CrudTopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public String listAll(){
        return JsonBuilder.buildSuccessResult("", topicDao.findAll());
    }

    // 未选择， 审核通过
    public String listNoSelected(){
        List<Topic> list = topicDao.findAllByStudentIdIsNull();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getStatus()!=2){
                list.remove(i);
            }
        }
        return JsonBuilder.buildSuccessResult("", list);
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

    public String listByTeacher(Long teacherId) {
        List<Topic> list = topicDao.findAllByTeacherId(teacherId);
        if(list == null || list.size()==0){
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }else{
            return JsonBuilder.buildSuccessResult(" ", list);
        }
    }
    public String getOne(Long topicId){
        return CrudServiceCommon.getOne(topicId, topicDao);
    }

    public String listAllNoValidate() {
        List<Topic> list = topicDao.findAll();
        final List<Topic> result = new ArrayList<>();
        if(list.size()>0){
            for (Topic item : list) {
                if (item != null && item.getStatus()!=null &&item.getStatus() == 1) {
                    item.setAttention("");
                    result.add(item);
                }
            }
        }
        return JsonBuilder.buildSuccessResult("", result);
    }
}
