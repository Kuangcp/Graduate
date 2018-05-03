package top.kuangcp.graduate.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.kuangcp.graduate.domain.role.Teacher;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-3  上午9:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVO {
    private long teacherId;
    private String username;
    private String teacherNo;
    private String password;
    private String email;
    private String team;
    private String major;

    public static TeacherVO of(Teacher teacher){
        TeacherVO  teacherVO = new TeacherVO();
        teacherVO.setTeacherId(teacher.getTeacherId());
        teacherVO.setPassword(teacher.getPassword());
        teacherVO.setUsername(teacher.getUsername());
        teacherVO.setTeacherNo(teacher.getTeacherNo());
        teacherVO.setEmail(teacher.getEmail());
        return teacherVO;
    }

}
