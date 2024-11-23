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

    // 获取用户的交易记录
    $.ajax({
        url: `http://localhost:8082/history/${username}`,
        type: 'GET',
        contentType: 'application/json',
        success: function(response) {
            if (response && response.length > 0) {
                const transactionList = document.getElementById('transaction-list');
                transactionList.innerHTML = '';  // 清空当前列表

                response.forEach(transaction => {
                    const li = document.createElement('li');
                    li.classList.add('transaction-item'); // 添加样式类

                    li.classList.add('transaction-item');
                    li.innerHTML = `
                    <strong>交易时间:</strong> ${transaction.transactionTime}<br>
                    <strong>交易类型:</strong> ${transaction.transactionType}<br>
                    <strong>金额:</strong> ￥${transaction.amount}<br>
                    <strong>余额：</strong> ￥${transaction.balanceAfterTransaction}<br>
                    <strong>描述：</strong> ${transaction.description}<br>
                    <strong>状态：</strong> ${transaction.transactionStatus}<br>
                    <hr>  <!-- 分隔线，增加视觉上的区分 -->
                `;
                    transactionList.appendChild(li);
                });
            } else {
                alert('没有交易记录');
            }
        },
        error: function() {
            alert('获取交易记录失败');
        }
    });

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
                alert('存款成功！存入金额: ￥' + depositAmount); // 这里显示人民币
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

    const username = sessionStorage.getItem('username');
    const withdrawAmount = parseFloat(document.getElementById('withdraw-amount').value);

    // 验证取款金额
    if (!withdrawAmount || withdrawAmount <= 0) {
        alert('请输入有效的取款金额');
        return;
    }

    // 如果没有用户名，提示用户登录
    if (!username) {
        alert('请先登录');
        return;
    }

    // 发送取款请求
    $.ajax({
        url: 'http://localhost:8082/withdraw',  // 假设后端有处理取款的接口
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ username: username, balance: withdrawAmount }),    // 取款金额作为 balance 参数
        success: function(response) {
            if (response.success) {
                alert(`取款成功！取出金额: ￥${withdrawAmount}`); // 显示人民币
            } else {
                alert('取款失败，请稍后再试！');
            }
        },
        error: function() {
            alert('取款请求失败');
        }
    });
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

// 监听查询余额按钮点击事件
document.getElementById('check-balance')?.addEventListener('click', function() {
    // 获取登录用户的用户名
    const username = sessionStorage.getItem('username');
    if (!username) {
        alert('请先登录');
        return;
    }

    // 发起 AJAX 请求查询账户余额
    $.ajax({
        url: `http://localhost:8082/getBalance?username=${encodeURIComponent(username)}`,  // 使用查询字符串传递用户名
        type: 'GET',
        contentType: 'application/json',
        success: function(response) {
            if (response.success) {
                // 假设后端返回余额字段为 `newBalance`
                document.getElementById('balance-amount').textContent = `￥${response.newBalance}`; // 显示人民币
            } else {
                alert('余额获取失败，请稍后再试！');
            }
        },
        error: function() {
            alert('余额获取请求失败');
        }
    });
});
