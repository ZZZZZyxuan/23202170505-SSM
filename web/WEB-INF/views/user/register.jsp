<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册 - 淘票票影评社区</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <main class="main-content">
        <div class="container">
            <div class="auth-container">
                <div class="auth-box">
                    <h2 class="auth-title">用户注册</h2>
                    <form id="registerForm" class="auth-form">
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" id="username" name="username"
                                   placeholder="3-20个字符" class="form-input" required>
                            <span class="form-hint" id="usernameHint"></span>
                        </div>
                        <div class="form-group">
                            <label for="nickname">昵称</label>
                            <input type="text" id="nickname" name="nickname"
                                   placeholder="可选，不填则使用用户名" class="form-input">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" id="password" name="password"
                                   placeholder="至少6个字符" class="form-input" required>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">确认密码</label>
                            <input type="password" id="confirmPassword" name="confirmPassword"
                                   placeholder="请再次输入密码" class="form-input" required>
                        </div>
                        <div class="form-error" id="errorMsg"></div>
                        <button type="submit" class="btn btn-primary btn-block">注册</button>
                    </form>
                    <div class="auth-footer">
                        <p>已有账号？<a href="${pageContext.request.contextPath}/user/login">立即登录</a></p>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="../common/footer.jsp" %>

    <script>
    // 检查用户名是否可用
    let usernameTimeout;
    document.getElementById('username').addEventListener('input', function() {
        const username = this.value;
        const hint = document.getElementById('usernameHint');

        clearTimeout(usernameTimeout);

        if (username.length < 3) {
            hint.textContent = '用户名至少3个字符';
            hint.className = 'form-hint error';
            return;
        }
        if (username.length > 20) {
            hint.textContent = '用户名不能超过20个字符';
            hint.className = 'form-hint error';
            return;
        }

        usernameTimeout = setTimeout(() => {
            fetch('${pageContext.request.contextPath}/api/user/check?username=' + encodeURIComponent(username))
                .then(response => response.json())
                .then(data => {
                    if (data.data.available) {
                        hint.textContent = '用户名可用';
                        hint.className = 'form-hint success';
                    } else {
                        hint.textContent = '用户名已被使用';
                        hint.className = 'form-hint error';
                    }
                });
        }, 500);
    });

    // 提交注册表单
    document.getElementById('registerForm').addEventListener('submit', function(e) {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const nickname = document.getElementById('nickname').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const errorMsg = document.getElementById('errorMsg');

        if (username.length < 3 || username.length > 20) {
            errorMsg.textContent = '用户名长度应为3-20个字符';
            return;
        }
        if (password.length < 6) {
            errorMsg.textContent = '密码长度不能少于6个字符';
            return;
        }
        if (password !== confirmPassword) {
            errorMsg.textContent = '两次输入的密码不一致';
            return;
        }

        fetch('${pageContext.request.contextPath}/api/user/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password, nickname: nickname || username })
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                alert('注册成功！请登录');
                window.location.href = '${pageContext.request.contextPath}/user/login';
            } else {
                errorMsg.textContent = data.message;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            errorMsg.textContent = '注册失败，请稍后重试';
        });
    });
    </script>
</body>
</html>

