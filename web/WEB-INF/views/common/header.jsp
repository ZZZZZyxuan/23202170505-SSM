<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    <div class="container">
        <div class="header-content">
            <a href="${pageContext.request.contextPath}/" class="logo">
                <span class="logo-icon">🎬</span>
                <span class="logo-text">淘票票影评社区</span>
            </a>
            <nav class="nav">
                <a href="${pageContext.request.contextPath}/" class="nav-link">首页</a>
                <c:choose>
                    <c:when test="${sessionScope.currentUser != null}">
                        <a href="${pageContext.request.contextPath}/user/profile" class="nav-link">个人中心</a>
                        <span class="user-info">
                            <span class="user-avatar">👤</span>
                            <span class="user-name">${sessionScope.currentUser.nickname}</span>
                        </span>
                        <a href="javascript:void(0)" onclick="logout()" class="nav-link logout-btn">退出</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/user/login" class="nav-link">登录</a>
                        <a href="${pageContext.request.contextPath}/user/register" class="nav-link btn-register">注册</a>
                    </c:otherwise>
                </c:choose>
            </nav>
        </div>
    </div>
</header>
<script>
function logout() {
    fetch('${pageContext.request.contextPath}/api/user/logout')
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                window.location.href = '${pageContext.request.contextPath}/';
            }
        });
}
</script>

