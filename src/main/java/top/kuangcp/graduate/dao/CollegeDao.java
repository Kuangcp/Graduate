package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.College;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-28  下午5:57
 */
@Repository
//@RepositoryRestResource(path = "college")
public interface CollegeDao extends JpaRepository<College, Long> {
}
