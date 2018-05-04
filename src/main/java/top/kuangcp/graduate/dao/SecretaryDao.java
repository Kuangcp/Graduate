package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Secretary;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-4  下午6:00
 */
@Repository
public interface SecretaryDao extends JpaRepository<Secretary, Long> {
}
