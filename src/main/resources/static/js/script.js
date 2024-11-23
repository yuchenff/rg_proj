// script.js

// 检查是否登录
window.onload = function() {
    const username = sessionStorage.getItem('username'); // 使用 sessionStorage 来存储用户名
    const loginLink = document.getElementById('login-link');
    const usernameSpan = document.getElementById('username');
    const logoutLink = document.getElementById('logout-link'); // 退出链接

    if (username) {
        // 如果存在用户名，显示用户名并隐藏登录链接
        usernameSpan.textContent = `欢迎, ${username}`;
        usernameSpan.style.display = 'inline'; // 显示用户名
        loginLink.style.display = 'none'; // 隐藏登录按钮
        logoutLink.style.display = 'inline'; // 显示退出登录按钮
    } else {
        // 如果没有登录，显示登录按钮
        usernameSpan.style.display = 'none'; // 隐藏用户名
        loginLink.style.display = 'inline'; // 显示登录按钮
        logoutLink.style.display = 'none'; // 隐藏退出登录按钮
    }
};

// 退出登录功能
function logout() {
    sessionStorage.removeItem('username'); // 清除 sessionStorage 中的用户名
    window.location.reload(); // 重新加载页面，更新登录状态
}

// 存款操作
document.getElementById("deposit-form")?.addEventListener("submit", function(event) {
    event.preventDefault();
    checkLogin(); // 确保登录后才能存款

    const username = sessionStorage.getItem('username');
    const depositAmount = parseFloat(document.getElementById('deposit-amount').value);
    sessionStorage.setItem('depositAmount', depositAmount);

    // 验证存款金额
    if (!depositAmount || depositAmount <= 0) {
        alert('请输入有效的存款金额');
        return;
    }

    // 如果没有用户名，提示用户登录
    if (!username) {
        alert('请先登录');
        return;
    }

    // 发送存款请求
    $.ajax({
        url: 'http://localhost:8082/deposit',  // 假设后端有处理存款的接口
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ username: username, balance: depositAmount }),    //这里为了方便  取名balance，对应user变量名
        success: function(response) {
            if (response.success) {
                alert('存款成功');
            } else {
                alert('存款失败，请稍后再试！');
            }
        },
        error: function() {
            alert('存款请求失败');
        }
    });

});

// 取款操作
document.getElementById("withdraw-form")?.addEventListener("submit", function(event) {
    event.preventDefault();
    checkLogin(); // 确保登录后才能取款

    const withdrawAmount = document.getElementById("withdraw-amount").value;
    alert(`取出金额: $${withdrawAmount}`);
});

// 检查是否登录
function checkLogin() {
    const username = sessionStorage.getItem("username"); // 使用 sessionStorage 检查登录状态
    if (!username) {
        alert("您必须先登录才能执行此操作！");
        window.location.href = "../login.html"; // 如果未登录，跳转到登录页面
    }
}

// 页面切换功能
function navigateTo(page) {
    const pages = document.querySelectorAll('.page');
    pages.forEach(p => p.classList.remove('active'));
    document.getElementById(page).classList.add('active');
}

// 初始显示首页
document.addEventListener('DOMContentLoaded', function() {
    navigateTo('home');
});

// 创建账户表单提交事件
document.getElementById('create-account-form')?.addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('customer-name').value;
    const accountType = document.getElementById('account-type').value;
    alert(`成功创建 ${name} 的 ${accountType} 账户！`);
    navigateTo('home');
});

// 存款成功后更新余额
document.getElementById('deposit-form')?.addEventListener('submit', function(e) {
    e.preventDefault();
    const amount = document.getElementById('deposit-amount').value;
    document.getElementById('balance-amount').textContent = `$${amount}`;
    alert(`存款成功！金额：$${amount}`);
    navigateTo('home');
});

// 取款成功后更新余额
document.getElementById('withdraw-form')?.addEventListener('submit', function(e) {
    e.preventDefault();
    const amount = document.getElementById('withdraw-amount').value;
    document.getElementById('balance-amount').textContent = `$${amount}`;
    alert(`取款成功！金额：$${amount}`);
    navigateTo('home');
});
