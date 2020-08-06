layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: '物品ID'}
        , {field: 'name', title: '物品名称'}
        , {field: 'value', title: '物品属性'}
        , {field: 'description', title: '物品描述'}
        , {
            field: 'pile', title: '可否堆叠', templet: function (row) {
                return row.pile;
            }
        }
        , {field: 'max_count', title: '堆叠最大值'}
        , {
            field: 'sell', title: '可否出售', templet: function (row) {
                return row.sell;
            }
        }
        , {
            field: 'level', title: '物品等级', templet: function (row) {
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

    crud.list(cols, '/manage/item/list');
    crud.upload('/manage/item/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        pile: $('input[name="pile"]').val(),
        sell: $('input[name="sell"]').val(),
        level: $('select[name="level"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/item/delete/' + id);
}