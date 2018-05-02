package top.kuangcp.graduate.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.doc.ClassGroup;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  下午2:06
 */
@Repository
public interface ClassGroupDao extends JpaRepository<ClassGroup, Long> {
    Page<ClassGroup> findByNameContaining(String name, Pageable pageable);

    Page<ClassGroup> findAllByMajorId(Long majorId, Pageable pageable);
    List<ClassGroup> findAllByMajorId(Long majorId);
}
