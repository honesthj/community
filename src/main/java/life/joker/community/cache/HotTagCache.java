package life.joker.community.cache;

import life.joker.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author joker
 * @date 2023/03/15 18:01
 **/
@Component
@Data
public class HotTagCache {

    private List<HotTagDTO> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags, Map<String, Integer> counts, Map<String, Integer> commentCounts) {
        int max = 5;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            hotTagDTO.setCount(counts.get(name));
            hotTagDTO.setCommentCount(commentCounts.get(name));
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagDTO);
            } else {
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        List<HotTagDTO> sortedTags = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            sortedTags.add(0, priorityQueue.poll());
        }
        hots = sortedTags;
    }
}