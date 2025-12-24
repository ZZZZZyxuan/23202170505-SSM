<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</html>
</body>
    </script>
    });
        });
            errorMsg.textContent = '登录失败，请稍后重试';
            console.error('Error:', error);
        .catch(error => {
        })
            }
                errorMsg.textContent = data.message;
            } else {
                window.location.href = '${pageContext.request.contextPath}/';
            if (data.code === 200) {
        .then(data => {
        .then(response => response.json())
        })
            body: JSON.stringify({ username, password })
            },
                'Content-Type': 'application/json'
            headers: {
            method: 'POST',
        fetch('${pageContext.request.contextPath}/api/user/login', {

        }
            return;
            errorMsg.textContent = '请输入密码';
        if (!password.trim()) {
        }
            return;
            errorMsg.textContent = '请输入用户名';
        if (!username.trim()) {

        const errorMsg = document.getElementById('errorMsg');
        const password = document.getElementById('password').value;
        const username = document.getElementById('username').value;

        e.preventDefault();
    document.getElementById('loginForm').addEventListener('submit', function(e) {
    <script>

    <%@ include file="../common/footer.jsp" %>

    </main>
        </div>
            </div>
                </div>
                    </div>
                        <p>还没有账号？<a href="${pageContext.request.contextPath}/user/register">立即注册</a></p>
                    <div class="auth-footer">
                    </form>
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                        <div class="form-error" id="errorMsg"></div>
                        </div>
                                   placeholder="请输入密码" class="form-input" required>
                            <input type="password" id="password" name="password"
                            <label for="password">密码</label>
                        <div class="form-group">
                        </div>
                                   placeholder="请输入用户名" class="form-input" required>
                            <input type="text" id="username" name="username"
                            <label for="username">用户名</label>
                        <div class="form-group">
                    <form id="loginForm" class="auth-form">
                    <h2 class="auth-title">用户登录</h2>
                <div class="auth-box">
            <div class="auth-container">
        <div class="container">
    <main class="main-content">

    <%@ include file="../common/header.jsp" %>
<body>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <title>登录 - 淘票票影评社区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
<head>
<html lang="zh-CN">
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

