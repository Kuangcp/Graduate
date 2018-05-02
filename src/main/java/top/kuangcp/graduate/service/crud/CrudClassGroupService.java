package top.kuangcp.graduate.service.crud;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.ClassGroupDao;
import top.kuangcp.graduate.domain.doc.ClassGroup;
import top.kuangcp.graduate.service.FileUploadService;
import top.kuangcp.graduate.service.crud.base.BaseCrud;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  下午2:08
 */
@Log4j2
@Service
public class CrudClassGroupService implements BaseCrud {
    private final ClassGroupDao classGroupDao;
    private final FileUploadService uploadService;


    public String listByMajor(int page, int limit, Long majorId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List list = classGroupDao.findAllByMajorId(majorId, pages).getContent();
        return JsonBuilder.buildTableResult(0, " ", classGroupDao.findAllByMajorId(majorId).size(), list);
    }

    public String findByName(String name, int page, int limit, Long majorId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Page<ClassGroup> list = classGroupDao.findByNameContaining(name, PageRequest.of(page, limit));
        final List<ClassGroup> result = new ArrayList<>();
        // 过滤出相等的并添加到集合
        list.filter(item -> item.getMajorId().equals(majorId)).iterator().forEachRemaining(result::add);
        if (list.getTotalElements() == 0) {
            return JsonBuilder.buildSuccessTableResult("", 0, "");
        }
        return JsonBuilder.buildSuccessTableResult(" ", (int) list.getTotalElements(), result);
    }

    public String listTotal(){
        return CrudServiceCommon.listTotal(classGroupDao);
    }
    @Autowired
    public CrudClassGroupService(ClassGroupDao classGroupDao, FileUploadService uploadService) {
        this.classGroupDao = classGroupDao;
        this.uploadService = uploadService;
    }

    @Override
    public String listAll(int page, int limit) {
        return CrudServiceCommon.listAll(page, limit, classGroupDao);
    }

    @Override
    public String delete(String idList) {
        return CrudServiceCommon.delete(idList, classGroupDao);
    }

    @Override
    public String getOne(Long id) {
        return CrudServiceCommon.getOne(id, classGroupDao);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return CrudServiceCommon.uploadFile(uploadService, file, request, classGroupDao, ClassGroup.class);
    }

    @Override
    public String saveOne(ExcelTransform target) {
        return CrudServiceCommon.saveOne(target, classGroupDao);
    }

    public String listTotalByMajor(Long majorId) {
        List<ClassGroup> list = classGroupDao.findAllByMajorId(majorId);
        if(list == null){
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }else{
            return JsonBuilder.buildSuccessResult(" ", list);
        }
    }
}
