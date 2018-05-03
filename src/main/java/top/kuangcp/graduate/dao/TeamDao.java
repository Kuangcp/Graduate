package top.kuangcp.graduate.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Team;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-3  上午10:08
 */
@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
    Page<Team> findByNameContaining(String name, Pageable of);
}
