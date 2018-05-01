package top.kuangcp.graduate.service;

import com.kuangcp.mythpoi.excel.ExcelImport;
import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  下午8:43
 */
@Service
@Log4j2
public class FileUploadService {

    /**
     * 上传 文件
     *
     * @param file    上传的文件对象
     * @param request 请求对象
     * @param target  目标实体类 类对象
     * @param <T>     类对象的泛型约束
     * @param dao Dao对象, 用于保存实体
     * @return JSON 结果
     */
    public <T extends ExcelTransform> String uploadFile(MultipartFile file, HttpServletRequest request, Class<T> target, CrudRepository dao) {
        if (file.isEmpty()) {
            return JsonBuilder.buildCodeResult(ResponseCode.FILE_EMPTY);
        } else {
            try {
                String saveFileName = file.getOriginalFilename();
                // 根据Tomcat的上下文得到上传文件的绝对路径, 在我Linux电脑上路径为 /tmp/tomcat-docbase.4713101443107034093.8889/upload
                // 中间的应该是随机数和端口号
                File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
                log.info(" 上传文件绝对路径  " + saveFile.getAbsolutePath());
                if (!saveFile.getParentFile().exists()) {
                    boolean result = saveFile.getParentFile().mkdirs();
                    if (!result) {
                        return JsonBuilder.buildCodeResult(ResponseCode.CREATE_FOLDER_ERROR);
                    }
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                List<T> list = ExcelImport.importExcel(saveFile.getAbsolutePath(), target);
//                list.forEach(item ->{
//                    System.out.println(item.toString());
//                });
                list.forEach(dao::save);
                log.info(saveFile.getName() + "上传文件并保存成功");
                return JsonBuilder.buildSuccessCodeResult();
            }catch (NotOLE2FileException e){
                log.info("文件类型不正确", e);
                return JsonBuilder.buildCodeResult(ResponseCode.FILE_DAMAGE);
            }catch (Exception e) {
                log.info("文件上传失败", e);
                return JsonBuilder.buildCodeResult(ResponseCode.FILE_DAMAGE);
            }
        }
    }
}
