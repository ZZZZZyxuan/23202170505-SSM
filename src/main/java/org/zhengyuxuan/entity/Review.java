package org.zhengyuxuan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论评分实体类
 * 对应数据库表 t_review
 */
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 电影ID */
    private Integer movieId;

    /** 评分(1-5星) */
    private Integer rating;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    private Date createTime;

    /** 关联的用户信息（非数据库字段） */
    private User user;

    /** 关联的电影信息（非数据库字段） */
    private Movie movie;

    public Review() {
    }

    public Review(Integer userId, Integer movieId, Integer rating, String content) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

