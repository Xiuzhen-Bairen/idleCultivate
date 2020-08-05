function register() {
    if ($('#username').val().length < 6 || $('#username').val().length > 20) {
        alert('用户名长度应为6-20位');
        return;
    }

    if ($('#password').val().length < 6 || $('#password').val().length > 20) {
        alert('密码长度应为6-20位');
        return;
    }

    if ($('#password').val() !== $('#password2').val()) {
        alert('两次密码输入不一致！');
        return;
    }

    $.ajax({
        url: '/game/register',
        type: 'post',
        data: $('#form-register').serialize(),
        success: function (data) {
            if (data.success) {
                alert("注册成功！");
                location.href = "/index.jsp";
            } else {
                alert(data.message);
            }
        },
        error: function () {
            alert('请求失败！');
        }
    });
}