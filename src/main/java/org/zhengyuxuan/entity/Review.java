package org.zhengyuxuan.entity;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable { // 评论实体类

    private static final long serialVersionUID = 1L;

    private Integer id; // 评论ID
    private Integer userId; // 用户ID
    private Integer movieId; // 电影ID
    private Integer rating; // 评分(1-5)
    private String content; // 评论内容
    private Date createTime; // 评论时间
    private User user; // 关联用户信息
    private Movie movie; // 关联电影信息

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

