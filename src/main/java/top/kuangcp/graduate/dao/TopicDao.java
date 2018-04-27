package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Topic;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-27  下午3:31
 */

@Repository
@RepositoryRestResource(path = "topic")
public interface TopicDao extends JpaRepository<Topic, Long> {
}
