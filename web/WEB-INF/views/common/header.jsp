<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</script>
}
        });
            }
                window.location.href = '${pageContext.request.contextPath}/';
            if (data.code === 200) {
        .then(data => {
        .then(response => response.json())
    fetch('${pageContext.request.contextPath}/api/user/logout')
function logout() {
<script>
</header>
    </div>
        </div>
            </nav>
                </c:choose>
                    </c:otherwise>
                        <a href="${pageContext.request.contextPath}/user/register" class="nav-link btn-register">注册</a>
                        <a href="${pageContext.request.contextPath}/user/login" class="nav-link">登录</a>
                    <c:otherwise>
                    </c:when>
                        <a href="javascript:void(0)" onclick="logout()" class="nav-link logout-btn">退出</a>
                        </span>
                            <span class="user-name">${sessionScope.currentUser.nickname}</span>
                            <span class="user-avatar">👤</span>
                        <span class="user-info">
                        <a href="${pageContext.request.contextPath}/user/profile" class="nav-link">个人中心</a>
                    <c:when test="${sessionScope.currentUser != null}">
                <c:choose>
                <a href="${pageContext.request.contextPath}/" class="nav-link">首页</a>
            <nav class="nav">
            </a>
                <span class="logo-text">淘票票影评社区</span>
                <span class="logo-icon">🎬</span>
            <a href="${pageContext.request.contextPath}/" class="logo">
        <div class="header-content">
    <div class="container">
<header class="header">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

