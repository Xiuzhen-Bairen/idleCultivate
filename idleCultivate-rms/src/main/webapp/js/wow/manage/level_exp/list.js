layui.use(['table', 'form', 'upload'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'level', title: '等级'}
        , {field: 'exp', title: '经验'}
        , {field: 'exp_speed', title: '经验获取速度'}
        , {
            fixed: 'right', width: 160, align: 'center', title: '操作', templet: function (d) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑配置\',\'edit/' + d.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + d.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/level_exp/list');
    crud.upload('/manage/level_exp/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        level: $('input[name="level"]').val()
    };
    crud.search(data);
}

function reset(){
    $('#queryForm').reset();
}

function remove(obj, id) {
    crud.remove(obj, '/manage/level_exp/delete/' + id);
}