package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Leader;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-4  下午4:49
 */
@Repository
public interface LeaderDao extends JpaRepository<Leader, Long> {

    Optional<Leader> findByTeacherId(Long teacherId);
}
