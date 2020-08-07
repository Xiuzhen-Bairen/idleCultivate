layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: '门派ID'}
        , {field: 'name', title: '门派名称'}
        , {field: 'type', title: '门派类别'}
        , {
            field: 'level', title: '门派等级', templet: function (row) {
                return row.level;
            }
        }
        , {
            field: 'alchemy', title: '炼丹', templet: function (row) {
                return row.alchemy;
            }
        }
        , {
            field: 'refiner', title: '炼器', templet: function (row) {
                return row.refiner;
            }
        }
        , {
            title: '操作', width: 150, templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑物品\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/sect/list');
    crud.upload('/manage/sect/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        level: $('select[name="level"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/sect/delete/' + id);
}