<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心 - 淘票票影评社区</title>
    <link rel="icon" href="data:;base64,=">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main class="main-content">
        <div class="container">
            <!-- 用户信息 -->
            <section class="profile-header">
                <div class="profile-avatar">
                    <img src="${pageContext.request.contextPath}/static/images/avatars/default_avatar.png"
                         alt="头像" class="profile-avatar-img">
                </div>
                <div class="profile-info">
                    <h2 class="profile-name">${user.nickname}</h2>
                    <p class="profile-username">@${user.username}</p>
                    <p class="profile-join">
                        注册时间：<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/>
                    </p>
                </div>
                <div class="profile-stats">
                    <div class="stat-item">
                        <span class="stat-value">${myReviews.size()}</span>
                        <span class="stat-label">评价数</span>
                    </div>
                </div>
            </section>

            <!-- 我的评论 -->
            <section class="my-reviews">
                <h2 class="section-title">我的评价</h2>

                <c:choose>
                    <c:when test="${empty myReviews}">
                        <div class="empty-state">
                            <div class="empty-icon">📝</div>
                            <p>你还没有评价过任何电影</p>
                            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">去看看电影</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="review-list profile-reviews">
                            <c:forEach items="${myReviews}" var="review">
                                <div class="review-item with-movie">
                                    <div class="review-movie-info"
                                         onclick="location.href='${pageContext.request.contextPath}/movie/${review.movie.id}'">
                                        <div class="review-movie-poster">
                                            <c:choose>
                                                <c:when test="${not empty review.movie.poster}">
                                                    <img src="${pageContext.request.contextPath}${review.movie.poster}"
                                                         alt="${review.movie.title}">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="poster-placeholder small">
                                                        <span>🎬</span>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="review-movie-detail">
                                            <h4 class="movie-title">${review.movie.title}</h4>
                                            <p class="movie-meta">
                                                ${review.movie.genre} | ${review.movie.region}
                                            </p>
                                        </div>
                                    </div>
                                    <div class="review-content-section">
                                        <div class="review-rating my-rating">
                                            <span class="rating-label">我的评分：</span>
                                            <c:forEach begin="1" end="5" var="i">
                                                <span class="star ${review.rating >= i ? 'filled' : ''}">★</span>
                                            </c:forEach>
                                        </div>
                                        <div class="review-content">
                                            <p>${review.content}</p>
                                        </div>
                                        <div class="review-footer">
                                            <span class="review-time">
                                                评价于 <fmt:formatDate value="${review.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                                            </span>
                                            <div class="review-actions">
                                                <a href="${pageContext.request.contextPath}/movie/${review.movie.id}"
                                                   class="btn btn-small btn-secondary">修改评价</a>
                                                <button class="btn btn-small btn-danger"
                                                        onclick="deleteReview(${review.id})">删除</button>
                                            </div>
                                        </div>
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

    <script>
    function deleteReview(reviewId) {
        if (!confirm('确定要删除这条评价吗？')) {
            return;
        }

        fetch('${pageContext.request.contextPath}/api/review/' + reviewId, {
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                alert('删除成功');
                location.reload();
            } else {
                alert(data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('删除失败，请稍后重试');
        });
    }
    </script>
</body>
</html>

