package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.TopicTask;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-8  下午7:25
 */
@Repository
public interface TopicTaskDao extends JpaRepository<TopicTask, Long> {
}
