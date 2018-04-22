package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.role.Student;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:35
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    Student findByUsername(String username);
}
