package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.TopicFile;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-9  下午12:53
 */
@Repository
public interface TopicFileDao extends JpaRepository<TopicFile, Long> {
}
