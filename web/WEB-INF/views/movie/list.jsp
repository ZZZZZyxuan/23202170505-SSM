<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</html>
</body>
    <%@ include file="../common/footer.jsp" %>

    </main>
        </div>
            </section>
                </c:choose>
                    </c:otherwise>
                        </div>
                            </c:forEach>
                                </div>
                                    </div>
                                        <p class="movie-director">导演：${movie.director}</p>
                                        </p>
                                            <span class="movie-region">${movie.region}</span>
                                            <span class="movie-genre">${movie.genre}</span>
                                        <p class="movie-meta">
                                        <h3 class="movie-title">${movie.title}</h3>
                                    <div class="movie-info">
                                    </div>
                                        </div>
                                            <span class="rating-score">${movie.avgRating}</span>
                                            <span class="rating-star">★</span>
                                        <div class="movie-rating">
                                        </c:choose>
                                            </c:otherwise>
                                                </div>
                                                    <span class="poster-title">${movie.title}</span>
                                                    <span class="poster-icon">🎬</span>
                                                <div class="poster-placeholder">
                                            <c:otherwise>
                                            </c:when>
                                                <img src="${pageContext.request.contextPath}${movie.poster}" alt="${movie.title}">
                                            <c:when test="${not empty movie.poster}">
                                        <c:choose>
                                    <div class="movie-poster">
                                <div class="movie-card" onclick="location.href='${pageContext.request.contextPath}/movie/${movie.id}'">
                            <c:forEach items="${movies}" var="movie">
                        <div class="movie-grid">
                    <c:otherwise>
                    </c:when>
                        </div>
                            <p>暂无符合条件的电影</p>
                            <div class="empty-icon">🎬</div>
                        <div class="empty-state">
                    <c:when test="${empty movies}">
                <c:choose>

                </h2>
                    <span class="movie-count">共 ${movies.size()} 部</span>
                    </c:choose>
                        <c:otherwise>热门电影</c:otherwise>
                        <c:when test="${not empty currentGenre || not empty currentRegion}">筛选结果</c:when>
                        <c:when test="${not empty keyword}">搜索结果：${keyword}</c:when>
                    <c:choose>
                <h2 class="section-title">
            <section class="movie-section">
            <!-- 电影列表 -->

            </section>
                </form>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">重置</a>
                    </div>
                        <button type="submit" class="btn btn-primary">搜索</button>
                               value="${keyword}" class="search-input">
                        <input type="text" name="keyword" placeholder="搜索电影名、导演、演员"
                    <div class="filter-group search-group">
                    </div>
                        </select>
                            </c:forEach>
                                <option value="${r}" ${currentRegion == r ? 'selected' : ''}>${r}</option>
                            <c:forEach items="${regions}" var="r">
                            <option value="">全部地区</option>
                        <select name="region" class="filter-select">
                        <label>地区：</label>
                    <div class="filter-group">
                    </div>
                        </select>
                            </c:forEach>
                                <option value="${g}" ${currentGenre == g ? 'selected' : ''}>${g}</option>
                            <c:forEach items="${genres}" var="g">
                            <option value="">全部类型</option>
                        <select name="genre" class="filter-select">
                        <label>类型：</label>
                    <div class="filter-group">
                <form action="${pageContext.request.contextPath}/" method="get" class="filter-form">
            <section class="filter-section">
            <!-- 筛选区域 -->
        <div class="container">
    <main class="main-content">

    <%@ include file="../common/header.jsp" %>
<body>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <title>淘票票影评社区 - 首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
<head>
<html lang="zh-CN">
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

