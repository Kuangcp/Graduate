package top.kuangcp.graduate.service.curd;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.CoreConfig;
import top.kuangcp.graduate.dao.CollegeDao;
import top.kuangcp.graduate.domain.College;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  上午9:01
 */
@Log4j2
@Service
public class CurdCollegeService {

    @Autowired
    CollegeDao collegeDao;

    public String listAll(int page, int limit){
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if(checkPageNum != null){
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List<College> list = collegeDao.findAll(pages).getContent();
        return JsonBuilder.buildTableResult(0, " ", (int) collegeDao.count(), list);
    }


    public String delete(String idList){
        log.info("收到参数 "+idList);
        try {
            for(String id : idList.split(CoreConfig.DELIMITER_NUM)){
                collegeDao.deleteById(Long.valueOf(id));
            }
        }catch(Exception e){
            log.error("删除失败 ", e);
            return JsonBuilder.buildResult(1, " ", " ");
        }
        return JsonBuilder.buildResult(0, " ", " ");
    }
}
