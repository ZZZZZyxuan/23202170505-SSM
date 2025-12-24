package org.zhengyuxuan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 电影实体类
 * 对应数据库表 t_movie
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 电影ID */
    private Integer id;

    /** 电影名称 */
    private String title;

    /** 导演 */
    private String director;

    /** 主演 */
    private String actors;

    /** 类型 */
    private String genre;

    /** 地区 */
    private String region;

    /** 上映日期 */
    private Date releaseDate;

    /** 片长(分钟) */
    private Integer duration;

    /** 海报图片 */
    private String poster;

    /** 剧情简介 */
    private String synopsis;

    /** 平均评分 */
    private BigDecimal avgRating;

    /** 评分人数 */
    private Integer ratingCount;

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", region='" + region + '\'' +
                ", avgRating=" + avgRating +
                '}';
    }
}

