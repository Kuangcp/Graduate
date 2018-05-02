package top.kuangcp.graduate.service.crud;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.dao.CollegeDao;
import top.kuangcp.graduate.domain.doc.College;
import top.kuangcp.graduate.service.FileUploadService;
import top.kuangcp.graduate.service.crud.base.BaseCrud;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by https://github.com/kuangcp
 * TODO 可以抽象成JPA那样
 * @author kuangcp
 * @date 18-4-30  上午9:01
 */
@Log4j2
@Service
public class CrudCollegeService implements BaseCrud {

    private final CollegeDao collegeDao;

    private final FileUploadService uploadService;

    @Autowired
    public CrudCollegeService(CollegeDao collegeDao, FileUploadService uploadService) {
        this.collegeDao = collegeDao;
        this.uploadService = uploadService;
    }

    /**
     * 名称模糊搜索
     * @param name 姓名
     * @return JSON
     */
    public String findByName(String name, int page, int limit) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Page<College> list =  collegeDao.findByNameContaining(name, PageRequest.of(page, limit));
        if(list.getTotalElements() == 0){
            return JsonBuilder.buildSuccessTableResult("", 0, "");
        }
        return JsonBuilder.buildSuccessTableResult(" ",(int) list.getTotalElements(), list.getContent());
    }

    public String listTotal(){
        return CrudServiceCommon.listTotal(collegeDao);
    }
    @Override
    public String listAll(int page, int limit) {
        return CrudServiceCommon.listAll(page, limit, collegeDao);
    }

    @Override
    public String delete(String idList) {
        return CrudServiceCommon.delete(idList, collegeDao);
    }

    @Override
    public String getOne(Long id) {
        return CrudServiceCommon.getOne(id, collegeDao);
    }

    @Override
    public String saveOne(ExcelTransform target) {
        return CrudServiceCommon.saveOne(target, collegeDao);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return CrudServiceCommon.uploadFile(uploadService, file, request, collegeDao, College.class);
    }
}
