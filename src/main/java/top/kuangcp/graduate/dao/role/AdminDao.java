package top.kuangcp.graduate.dao.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.role.Admin;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-26  上午8:46
 */
@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
