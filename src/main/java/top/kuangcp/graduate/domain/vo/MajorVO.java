package top.kuangcp.graduate.domain.vo;

import lombok.Data;
import top.kuangcp.graduate.domain.doc.Major;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午10:05
 */
@Data
public class MajorVO {
    private Long majorId;
    private String name;
    private Long collegeId;
    private String leader;

    public static MajorVO of(Major major) {
        MajorVO majorVO = new MajorVO();
        majorVO.setCollegeId(major.getCollegeId());
        majorVO.setName(major.getName());
        return majorVO;
    }
}
