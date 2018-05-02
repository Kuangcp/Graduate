package top.kuangcp.graduate.service.crud.base;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.config.custom.CoreConfig;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.service.FileUploadService;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 * 公共方法
 * @author kuangcp
 * @date 18-5-2  上午8:34
 */
@Log4j2
public class CrudServiceCommon {

    public static String listTotal(CrudRepository dao){
        List list = new ArrayList();
        for(Object object : dao.findAll()){
            list.add(object);
        }
        if(list.size()==0){
            return JsonBuilder.buildCodeResult(ResponseCode.NOT_FOUND);
        }else{
            return JsonBuilder.buildSuccessResult(" ", list);
        }

    }
    public static String listAll(int page, int limit, PagingAndSortingRepository dao) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List list = dao.findAll(pages).getContent();
        return JsonBuilder.buildTableResult(0, " ", (int) dao.count(), list);
    }

    public static String delete(String idList, CrudRepository  dao) {
        try {
            for (String id : idList.split(CoreConfig.DELIMITER_NUM)) {
                dao.deleteById(Long.valueOf(id));
            }
        } catch (Exception e) {
            log.error("删除失败 ", e);
            return JsonBuilder.buildCodeResult(ResponseCode.DELETE_ERROR);
        }
        return JsonBuilder.buildSuccessCodeResult();
    }

    public static String getOne(Long id, CrudRepository  dao) {
        Optional<Object> college = dao.findById(id);
        return college.map(college1
                -> JsonBuilder.buildSuccessResult(" ", college1)).orElseGet(()
                -> JsonBuilder.buildResult(1, "haven't data", " "));
    }

    public static String saveOne(Object target, CrudRepository dao) {
        try {
            dao.save(target);
        } catch (Exception e) {
            return JsonBuilder.buildCodeResult(ResponseCode.ADD_ERROR);
        }
        return JsonBuilder.buildSuccessCodeResult();
    }


    public static String uploadFile(FileUploadService uploadService, MultipartFile file, HttpServletRequest request, CrudRepository dao, Class target) {
        return uploadService.uploadFile(file, request, target, dao);
    }
    // TODO 如何将两个参数 使用上高阶函数
//    public String findByName(String name, int page, int limit, Function<String, Page> findByName) {
//        String checkPageNum = CheckPage.checkPageNum(page, limit);
//        if (checkPageNum != null) {
//            return checkPageNum;
//        }
////        Page list = findByName.apply(name, PageRequest.of(page, limit));
////        Page<College> list =  dao.findByNameContaining(name, PageRequest.of(page, limit));
//        if(list.getTotalElements() == 0){
//            return JsonBuilder.buildSuccessTableResult("", 0, "");
//        }
//        return JsonBuilder.buildSuccessTableResult(" ",(int) list.getTotalElements(), list.getContent());
//    }


}
