<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>淘票票影评社区 - 首页</title>
    <link rel="icon" href="data:;base64,=">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main class="main-content">
        <div class="container">
            <!-- 筛选区域 -->
            <section class="filter-section">
                <form id="filterForm" action="${pageContext.request.contextPath}/" method="get" class="filter-form">
                    <div class="filter-group">
                        <label>类型：</label>
                        <select name="genre" class="filter-select" onchange="document.getElementById('filterForm').submit();">
                            <option value="">全部类型</option>
                            <c:forEach items="${genres}" var="g">
                                <option value="${g}" ${currentGenre eq g ? 'selected' : ''}>${g}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="filter-group">
                        <label>地区：</label>
                        <select name="region" class="filter-select" onchange="document.getElementById('filterForm').submit();">
                            <option value="">全部地区</option>
                            <c:forEach items="${regions}" var="r">
                                <option value="${r}" ${currentRegion eq r ? 'selected' : ''}>${r}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="filter-group search-group">
                        <input type="text" name="keyword" placeholder="搜索电影名、导演、演员"
                               value="${keyword}" class="search-input">
                        <button type="submit" class="btn btn-primary">搜索</button>
                    </div>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">重置</a>
                </form>
            </section>

            <!-- 电影列表 -->
            <section class="movie-section">
                <h2 class="section-title">
                    <c:choose>
                        <c:when test="${not empty keyword}">搜索结果：${keyword}</c:when>
                        <c:when test="${not empty currentGenre || not empty currentRegion}">筛选结果</c:when>
                        <c:otherwise>热门电影</c:otherwise>
                    </c:choose>
                    <span class="movie-count">共 ${movies.size()} 部</span>
                </h2>

                <c:choose>
                    <c:when test="${empty movies}">
                        <div class="empty-state">
                            <div class="empty-icon">🎬</div>
                            <p>暂无符合条件的电影</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="movie-grid">
                            <c:forEach items="${movies}" var="movie">
                                <div class="movie-card" onclick="location.href='${pageContext.request.contextPath}/movie/${movie.id}'">
                                    <div class="movie-poster">
                                        <c:choose>
                                            <c:when test="${not empty movie.poster}">
                                                <img src="${pageContext.request.contextPath}${movie.poster}" alt="${movie.title}"
                                                     onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">
                                                <div class="poster-placeholder" style="display:none;">
                                                    <span class="poster-icon">🎬</span>
                                                    <span class="poster-title">${movie.title}</span>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="poster-placeholder">
                                                    <span class="poster-icon">🎬</span>
                                                    <span class="poster-title">${movie.title}</span>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="movie-rating">
                                            <span class="rating-star">★</span>
                                            <span class="rating-score">${movie.avgRating}</span>
                                        </div>
                                    </div>
                                    <div class="movie-info">
                                        <h3 class="movie-title">${movie.title}</h3>
                                        <p class="movie-meta">
                                            <span class="movie-genre">${movie.genre}</span>
                                            <span class="movie-region">${movie.region}</span>
                                        </p>
                                        <p class="movie-director">导演：${movie.director}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>
</body>
</html>

