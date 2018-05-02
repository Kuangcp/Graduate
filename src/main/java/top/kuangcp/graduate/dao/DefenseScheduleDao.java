package top.kuangcp.graduate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.DefenseSchedule;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午11:20
 */
@Repository
public interface DefenseScheduleDao extends JpaRepository<DefenseSchedule, Long> {

    List<DefenseSchedule> findAllByTeamId(Long teamId);

    // TODO 左外连接？
    @Query("from DefenseSchedule as ds, Teacher as t, Student as s where ds.teamId = t.teamId and s.year = ds.year  and s.teacherId = t.teacherId and s.studentId = ?1")
    DefenseSchedule selectByStudentId(Long studentId);

}
