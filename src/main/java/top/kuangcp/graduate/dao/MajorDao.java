package top.kuangcp.graduate.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.doc.Major;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午8:04
 */
@Repository
public interface MajorDao extends JpaRepository<Major, Long> {

    Page<Major> findByNameContaining(String name, Pageable pageable);

    Page<Major> findAllByCollegeId(Long college, Pageable pageable);
    List<Major> findAllByCollegeId(Long college);
}
