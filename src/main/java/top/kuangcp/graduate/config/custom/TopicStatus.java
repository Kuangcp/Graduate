package top.kuangcp.graduate.config.custom;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  上午10:57
 */
public interface TopicStatus {
    int START_NODE = 0;
    int SUBMIT_NODE = 1;
    int SUBMIT_PASS_NODE = 2;
    int SUBMIT_REJECT_NODE = 3;
    int TOPIC_SELECTED_NODE = 4;
    int TOPIC_VACANT_NODE = 5;
    int FIRST_REJECT_NODE = 6;
    int FIRST_PASS_NODE = 7;
    int SECOND_REJECT_NODE = 8;
    int SECOND_PASS_NODE = 9;
    int THIRD_REJECT_NODE = 10;
    int THIRD_PASS_NODE = 11;
    int END_NODE = 20;
}
