package top.kuangcp.graduate.service.crud.base;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by https://github.com/kuangcp
 * 导入Excel 的 CRUD
 *
 * @author kuangcp
 * @date 18-5-2  上午8:24
 */
public interface BaseCrud {
    String listAll(int page, int limit);

    String delete(String idList);

    String getOne(Long id);

//    String findByName(String name, int page, int limit);

    String uploadFile(MultipartFile file, HttpServletRequest request);

    String saveOne(ExcelTransform target);
}
