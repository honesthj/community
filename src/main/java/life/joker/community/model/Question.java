package life.joker.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.ID
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TITLE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMT_CREATE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.CREATOR
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TAG
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    private String tag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.ID
     *
     * @return the value of QUESTION.ID
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.ID
     *
     * @param id the value for QUESTION.ID
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TITLE
     *
     * @return the value of QUESTION.TITLE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TITLE
     *
     * @param title the value for QUESTION.TITLE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.DESCRIPTION
     *
     * @return the value of QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.DESCRIPTION
     *
     * @param description the value for QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMT_CREATE
     *
     * @return the value of QUESTION.GMT_CREATE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMT_CREATE
     *
     * @param gmtCreate the value for QUESTION.GMT_CREATE
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMT_MODIFIED
     *
     * @return the value of QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMT_MODIFIED
     *
     * @param gmtModified the value for QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.CREATOR
     *
     * @return the value of QUESTION.CREATOR
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.CREATOR
     *
     * @param creator the value for QUESTION.CREATOR
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.COMMENT_COUNT
     *
     * @return the value of QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.COMMENT_COUNT
     *
     * @param commentCount the value for QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.LIKE_COUNT
     *
     * @return the value of QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.LIKE_COUNT
     *
     * @param likeCount the value for QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.VIEW_COUNT
     *
     * @return the value of QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.VIEW_COUNT
     *
     * @param viewCount the value for QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TAG
     *
     * @return the value of QUESTION.TAG
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TAG
     *
     * @param tag the value for QUESTION.TAG
     *
     * @mbg.generated Wed Mar 15 17:58:45 CST 2023
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}