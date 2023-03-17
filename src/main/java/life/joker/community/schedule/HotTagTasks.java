package life.joker.community.schedule;

import life.joker.community.cache.HotTagCache;
import life.joker.community.mapper.QuestionMapper;
import life.joker.community.model.Question;
import life.joker.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author joker
 * @date 2023/03/15 17:23
 **/
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 6)
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;
        List<Question> list = new ArrayList<>();
        Map<String, Integer> priorities = new HashMap<>(16);
        Map<String, Integer> counts = new HashMap<>(16);
        Map<String, Integer> commentCounts = new HashMap<>(16);
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    Integer count = counts.get(tag);
                    Integer commentCount = commentCounts.get(tag);
                    if (priority != null) {
                        //权重值设置
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                    if (count != null) {
                        //问题数设置
                        counts.put(tag, count + 1);
                    } else {
                        counts.put(tag, 1);
                    }
                    if (commentCount != null) {
                        //回复数设置
                        commentCounts.put(tag, commentCount + question.getCommentCount());
                    } else {
                        commentCounts.put(tag, question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities, counts, commentCounts);
    }
}