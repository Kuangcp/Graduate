package top.kuangcp.graduate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-27  下午3:20
 */
@Entity
public class Topic implements Serializable {

    @Id
    @GeneratedValue
    private Long topicId;
    private String name;
    private String attention;
    private int publishYear;
    private Long studentId;
    private double guideScore;
    private String guideAdvise;
    private double judgeScore;
    private String judgeAdvise;
    private Long teacherId;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public double getGuideScore() {
        return guideScore;
    }

    public void setGuideScore(double guideScore) {
        this.guideScore = guideScore;
    }

    public String getGuideAdvise() {
        return guideAdvise;
    }

    public void setGuideAdvise(String guideAdvise) {
        this.guideAdvise = guideAdvise;
    }

    public double getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(double judgeScore) {
        this.judgeScore = judgeScore;
    }

    public String getJudgeAdvise() {
        return judgeAdvise;
    }

    public void setJudgeAdvise(String judgeAdvise) {
        this.judgeAdvise = judgeAdvise;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                ", attention='" + attention + '\'' +
                ", publishYear=" + publishYear +
                ", studentId=" + studentId +
                ", guideScore=" + guideScore +
                ", guideAdvise='" + guideAdvise + '\'' +
                ", judgeScore=" + judgeScore +
                ", judgeAdvise='" + judgeAdvise + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}
