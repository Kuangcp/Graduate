package top.kuangcp.graduate.service.curd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.util.JsonBuilder;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午8:36
 */
@Service
public class CurdTopicService {
    @Autowired
    TopicDao topicDao;

    // TODO 分页
    public String listAll(){
        return JsonBuilder.buildSuccessResult("", topicDao.findAll());
    }
}
