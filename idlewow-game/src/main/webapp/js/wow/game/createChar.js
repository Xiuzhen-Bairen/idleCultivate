function createChar() {
    $.ajax({
        url: '/game/createChar',
        type: 'post',
        data: $('#form-createChar').serialize(),
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