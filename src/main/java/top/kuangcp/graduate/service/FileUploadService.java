package top.kuangcp.graduate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  下午8:43
 */
@Service
public class FileUploadService {

    /**
     * 上传 文件
     * @param file
     * @param request
     * @return
     */
    public String uploadFile(MultipartFile file, HttpServletRequest request){
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            // 根据Tomcat的上下文得到上传文件的绝对路径, 在我Linux电脑上路径为 /tmp/tomcat-docbase.4713101443107034093.8889/upload
            // 中间的应该是随机数和端口号
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
//            log.info("绝对路径  "+saveFile.getAbsolutePath());

            try {
                if (!saveFile.getParentFile().exists()) {
                    boolean result = saveFile.getParentFile().mkdirs();
                    if(!result){
                        return JsonBuilder.buildResult(1, "create ", "");
                    }
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return JsonBuilder.buildSuccessResult(saveFile.getName()+" upload sucess", "");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return JsonBuilder.buildResult(1, saveFile.getName()+" not found", "");
            } catch (IOException e) {
                e.printStackTrace();
                return JsonBuilder.buildResult(1, " eroor", "");
            }
        } else {
            return JsonBuilder.buildResult(203, "", "");
        }
    }
}
