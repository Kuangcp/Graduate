package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.Topic;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-27  下午3:31
 */

@Repository
public interface TopicDao extends JpaRepository<Topic, Long> {

    List<Topic> findAllByStudentId(Long studentId);
    List<Topic> findAllByTeacherId(Long teacherId);

    List<Topic> findAllByNameContaining(String name);

    List<Topic> findAllByStudentIdIsNull();
}
