package top.kuangcp.graduate.dao.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.role.Teacher;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-26  上午8:45
 */
@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);
}
