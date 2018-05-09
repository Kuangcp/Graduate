package top.kuangcp.graduate.dao.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.role.Student;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:35
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    Page<Student> findByUsernameContaining(String name, Pageable pageable);
    Student findByUsername(String username);

    Student findByStudentNo(String studentNo);

    Page<Student> findAllByClassGroupId(Long classGroupId, Pageable pageable);
    List<Student> findAllByClassGroupId(Long classGroupId);

    List<Student> findAllByOpenId(String openId);

    List<Student> findAllByTeacherId(Long teacherId);

}
