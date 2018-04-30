package top.kuangcp.graduate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.kuangcp.graduate.domain.College;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-28  下午5:57
 */
@Repository
public interface CollegeDao extends PagingAndSortingRepository<College, Long> {


//    Page<College> pageListAll(Pageable pageable);
}
