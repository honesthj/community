package life.joker.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joker
 * @date 2023/02/26 11:49
 **/
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
    private Integer totalPage;
    static final Integer DEFAULT_PAGE = 3;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages = new ArrayList<Integer>();
        pages.add(page);
        for (int i = 1; i <= DEFAULT_PAGE; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page.equals(totalPage)) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}