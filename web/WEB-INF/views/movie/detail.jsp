<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${movie.title} - 淘票票影评社区</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main class="main-content">
        <div class="container">
            <!-- 电影详情 -->
            <section class="movie-detail">
                <div class="movie-detail-header">
                    <div class="movie-poster-large">
                        <c:choose>
                            <c:when test="${not empty movie.poster}">
                                <img src="${pageContext.request.contextPath}${movie.poster}" alt="${movie.title}">
                            </c:when>
                            <c:otherwise>
                                <div class="poster-placeholder large">
                                    <span class="poster-icon">🎬</span>
                                    <span class="poster-title">${movie.title}</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="movie-detail-info">
                        <h1 class="movie-detail-title">${movie.title}</h1>
                        <div class="movie-rating-large">
                            <span class="rating-label">评分</span>
                            <span class="rating-score-large">${movie.avgRating}</span>
                            <span class="rating-count">${movie.ratingCount} 人评价</span>
                        </div>
                        <div class="movie-meta-detail">
                            <p><strong>导演：</strong>${movie.director}</p>
                            <p><strong>主演：</strong>${movie.actors}</p>
                            <p><strong>类型：</strong>${movie.genre}</p>
                            <p><strong>地区：</strong>${movie.region}</p>
                            <p><strong>片长：</strong>${movie.duration} 分钟</p>
                            <p><strong>上映日期：</strong>
                                <fmt:formatDate value="${movie.releaseDate}" pattern="yyyy-MM-dd"/>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="movie-synopsis">
                    <h3>剧情简介</h3>
                    <p>${movie.synopsis}</p>
                </div>
            </section>

            <!-- 评分评论区 -->
            <section class="review-section">
                <h2 class="section-title">影评区</h2>

                <!-- 发表评论表单 -->
                <c:choose>
                    <c:when test="${sessionScope.currentUser == null}">
                        <div class="login-prompt">
                            <p>登录后即可发表评论</p>
                            <a href="${pageContext.request.contextPath}/user/login" class="btn btn-primary">立即登录</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="review-form-container">
                            <h3>
                                <c:choose>
                                    <c:when test="${myReview != null}">修改我的评价</c:when>
                                    <c:otherwise>发表评价</c:otherwise>
                                </c:choose>
                            </h3>
                            <form id="reviewForm" class="review-form">
                                <input type="hidden" name="movieId" value="${movie.id}">
                                <div class="rating-input">
                                    <label>我的评分：</label>
                                    <div class="star-rating" id="starRating">
                                        <c:forEach begin="1" end="5" var="i">
                                            <span class="star ${myReview != null && myReview.rating >= i ? 'active' : ''}"
                                                  data-value="${i}">★</span>
                                        </c:forEach>
                                    </div>
                                    <input type="hidden" name="rating" id="ratingInput"
                                           value="${myReview != null ? myReview.rating : ''}">
                                </div>
                                <div class="form-group">
                                    <label for="content">我的评论：</label>
                                    <textarea name="content" id="content" rows="4"
                                              placeholder="写下你对这部电影的看法..."
                                              class="form-textarea">${myReview != null ? myReview.content : ''}</textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">
                                    ${myReview != null ? '更新评价' : '发表评价'}
                                </button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>

                <!-- 评论列表 -->
                <div class="review-list">
                    <h3>全部评论 (${reviews.size()})</h3>
                    <c:choose>
                        <c:when test="${empty reviews}">
                            <div class="empty-state">
                                <p>暂无评论，快来发表第一条评论吧！</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${reviews}" var="review">
                                <div class="review-item">
                                    <div class="review-header">
                                        <div class="reviewer-info">
                                            <span class="reviewer-avatar">👤</span>
                                            <span class="reviewer-name">${review.user.nickname}</span>
                                        </div>
                                        <div class="review-rating">
                                            <c:forEach begin="1" end="5" var="i">
                                                <span class="star ${review.rating >= i ? 'filled' : ''}">★</span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="review-content">
                                        <p>${review.content}</p>
                                    </div>
                                    <div class="review-footer">
                                        <span class="review-time">
                                            <fmt:formatDate value="${review.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                                        </span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>

    <script>
    // 星级评分交互
    document.addEventListener('DOMContentLoaded', function() {
        const stars = document.querySelectorAll('#starRating .star');
        const ratingInput = document.getElementById('ratingInput');

        stars.forEach(star => {
            star.addEventListener('click', function() {
                const value = this.dataset.value;
                ratingInput.value = value;
                stars.forEach((s, index) => {
                    if (index < value) {
                        s.classList.add('active');
                    } else {
                        s.classList.remove('active');
                    }
                });
            });

            star.addEventListener('mouseenter', function() {
                const value = this.dataset.value;
                stars.forEach((s, index) => {
                    if (index < value) {
                        s.classList.add('hover');
                    } else {
                        s.classList.remove('hover');
                    }
                });
            });

            star.addEventListener('mouseleave', function() {
                stars.forEach(s => s.classList.remove('hover'));
            });
        });

        // 提交评论
        const reviewForm = document.getElementById('reviewForm');
        if (reviewForm) {
            reviewForm.addEventListener('submit', function(e) {
                e.preventDefault();

                const movieId = parseInt(this.querySelector('[name="movieId"]').value);
                const rating = parseInt(ratingInput.value);
                const content = this.querySelector('[name="content"]').value;

                if (!rating || rating < 1 || rating > 5) {
                    alert('请选择评分（1-5星）');
                    return;
                }
                if (!content.trim()) {
                    alert('请输入评论内容');
                    return;
                }

                fetch('${pageContext.request.contextPath}/api/review/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ movieId, rating, content })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        alert('评论成功！');
                        location.reload();
                    } else if (data.code === 401) {
                        alert('请先登录');
                        location.href = '${pageContext.request.contextPath}/user/login';
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('提交失败，请稍后重试');
                });
            });
        }
    });
    </script>
</body>
</html>

