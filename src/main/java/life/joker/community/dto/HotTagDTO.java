package life.joker.community.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author joker
 * @date 2023/03/15 18:46
 **/
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    private Integer count;
    private Integer commentCount;

    @Override
    public int compareTo(@NotNull Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}