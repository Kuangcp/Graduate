package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Feedback;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午7:52
 */
@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Long> {
}
