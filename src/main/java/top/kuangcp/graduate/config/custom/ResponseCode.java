package top.kuangcp.graduate.config.custom;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  下午10:37
 */
public interface ResponseCode {

    // 100 - 200 CRUD
    int DELETE_ERROR = 101; // 删除失败
    int ADD_ERROR = 102; // 新增失败
    int UPDATE_ERROR = 103;// 更新失败
    int NOT_FOUND = 104; // 记录找不到，基础表

    // 200 - 300 系统异常
    int CREATE_FOLDER_ERROR = 201; // 创建文件夹失败
    int FILE_DAMAGE = 202; // 文件损坏,格式不对
    int FILE_EMPTY = 203; // 文件为空
    int FILE_FORMAT_ERROR = 204; // 文件后缀不一致

    int SERIALIZATION_EROOR = 210; // JSON序列化失败

    // 300 - 400 权限异常

    // 400 - 500 业务异常
    int POJO_NOT_FOUND = 401; // 查询不到记录
    int ALREADY_SELECT = 402; // 已经选择

    int SAVE_TOPIC_ERROR = 410; // 保存课题异常

    int PASSWORD_VALID = 411; // 密码错误

    int SUBMIT_ERROR = 415; // 提交审核失败
}
