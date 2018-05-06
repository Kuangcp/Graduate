package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.TopicComment;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  下午2:05
 */
@Repository
public interface TopicCommentDao extends JpaRepository<TopicComment, Long> {
}
