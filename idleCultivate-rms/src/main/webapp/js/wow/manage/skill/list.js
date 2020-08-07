layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: '功法ID'}
        , {field: 'name', title: '功法名称'}
        , {field: 'value', title: '功法属性'}
        , {field: 'type', title: '功法类别'}
        , {
            field: 'level', title: '功法等级', templet: function (row) {
                return row.level;
            }
        }
        , {
            title: '操作', width: 150, templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑物品\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/skill/list');
    crud.upload('/manage/skill/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        level: $('select[name="level"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/skill/delete/' + id);
}