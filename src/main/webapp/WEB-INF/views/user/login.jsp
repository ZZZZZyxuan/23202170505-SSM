<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 - 淘票票影评社区</title>
    <link rel="icon" href="data:;base64,=">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main class="main-content">
        <div class="container">
            <div class="auth-container">
                <div class="auth-box">
                    <h2 class="auth-title">用户登录</h2>
                    <form id="loginForm" class="auth-form">
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" id="username" name="username"
                                   placeholder="请输入用户名" class="form-input" required>
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" id="password" name="password"
                                   placeholder="请输入密码" class="form-input" required>
                        </div>
                        <div class="form-error" id="errorMsg"></div>
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                    </form>
                    <div class="auth-footer">
                        <p>还没有账号？<a href="${pageContext.request.contextPath}/user/register">立即注册</a></p>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>

    <script>
    document.getElementById('loginForm').addEventListener('submit', function(e) {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const errorMsg = document.getElementById('errorMsg');

        if (!username.trim()) {
            errorMsg.textContent = '请输入用户名';
            return;
        }
        if (!password.trim()) {
            errorMsg.textContent = '请输入密码';
            return;
        }

        fetch('${pageContext.request.contextPath}/api/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                window.location.href = '${pageContext.request.contextPath}/';
            } else {
                errorMsg.textContent = data.message;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            errorMsg.textContent = '登录失败，请稍后重试';
        });
    });
    </script>
</body>
</html>

