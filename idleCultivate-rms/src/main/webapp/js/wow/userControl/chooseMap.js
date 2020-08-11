layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'name', title: '地图名称'}
        , {
            field: 'occupy', title: '地图归属', templet: function (d) {
                return enumUtil.occupyImage(d.occupy) + enumUtil.occupy(d.occupy);
            }
        }
        , {
            title: '操作', width: 150, templet: function (d) {
                return '<button class="layui-btn layui-btn-xs"  onclick="choose(\'' + d.id + '\',\'' + d.name + '\')" type="button"><i class="layui-icon">&#xe642;</i>选择</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/map/list');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        levelStart: $('input[name="levelStart"]').val(),
        levelEnd: $('input[name="levelEnd"]').val(),
        race: $('select[name="race"]').val(),
        job: $('select[name="job"]').val(),
    };

    crud.search(data);
}

function choose(id, name) {
    if (confirm("确认选择吗？")) {
        if (window.opener) {
            var idTag = _querystring['idTag'];
            var nameTag = _querystring['nameTag'];
            window.opener.userControl.chooseMapCallBack(id, name, idTag, nameTag);
        }

        window.close();
    }
}