package top.kuangcp.graduate.service.crud;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.dao.MajorDao;
import top.kuangcp.graduate.domain.doc.Major;
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
 * @date 18-5-2  上午8:05
 */
@Log4j2
@Service
public class CrudMajorService implements BaseCrud {
    private final MajorDao majorDao;
    private final FileUploadService uploadService;

    @Autowired
    public CrudMajorService(MajorDao majorDao, FileUploadService uploadService) {
        this.majorDao = majorDao;
        this.uploadService = uploadService;
    }

    /**
     * 模糊查询姓名, 并按专业id来
     *
     * @return JSON
     */
    public String findByName(String name, int page, int limit, Long collegeId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Page<Major> list = majorDao.findByNameContaining(name, PageRequest.of(page, limit));
        final List<Major> result = new ArrayList<>();
        // 过滤出相等的并添加到集合
        list.filter(item -> item.getCollegeId().equals(collegeId)).iterator().forEachRemaining(result::add);
        if (list.getTotalElements() == 0) {
            return JsonBuilder.buildSuccessTableResult("", 0, "");
        }
        return JsonBuilder.buildSuccessTableResult(" ", (int) list.getTotalElements(), result);
    }

    public String listByCollege(int page, int limit, Long collegeId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List list = majorDao.findAllByCollegeId(collegeId, pages).getContent();
        return JsonBuilder.buildTableResult(0, " ", majorDao.findAllByCollegeId(collegeId).size(), list);
    }

    @Override
    public String listAll(int page, int limit) {
        return CrudServiceCommon.listAll(page, limit, majorDao);
    }

    @Override
    public String delete(String idList) {
        return CrudServiceCommon.delete(idList, majorDao);
    }

    @Override
    public String getOne(Long majorId) {
        return CrudServiceCommon.getOne(majorId, majorDao);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return CrudServiceCommon.uploadFile(uploadService, file, request, majorDao, Major.class);
    }

    @Override
    public String saveOne(ExcelTransform target) {
        return CrudServiceCommon.saveOne(target, majorDao);
    }
}
