layui.use(['table', 'form', 'upload'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'code', title: '编码'}
        , {field: 'parentCode', title: '父编码'}
        , {field: 'value', title: '值'}
        , {field: 'remark', title: '备注'}
        , {
            fixed: 'right', width: 160, align: 'center', title: '操作', templet: function (d) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑配置\',\'edit/' + d.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + d.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/data_dict/list');
    crud.upload('/manage/data_dict/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        code: $('input[name="code"]').val(),
        parentCode: $('input[name="parentCode"]').val()
    };
    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/data_dict/delete/' + id);
}