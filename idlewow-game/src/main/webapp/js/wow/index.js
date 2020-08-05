function login() {
    $.ajax({
        url: '/game/login',
        type: 'post',
        data: $('#form-login').serialize(),
        success: function (data) {
            if (data.success) {
                location.href = "/game/characters";
            } else {
                alert(data.message);
            }
        },
        error: function () {
            alert('请求失败！');
        }
    });
}