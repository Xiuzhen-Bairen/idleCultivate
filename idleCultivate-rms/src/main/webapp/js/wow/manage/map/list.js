layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'name', title: '地图名称'}
        , {field: 'occupy', title: '地图归属'}
        // , {
        //     field: 'occupy', title: '地图归属', templet: function (row) {
        //         var occupy = dataDict.occupy(row.occupy);
        //         return '<img src="/images/wow/occupy/' + occupy + '.gif" title="" />' + occupy;
        //     }
        // }
        , {
            title: '操作', templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑地图\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/map/list');
    crud.upload('/manage/map/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/map/delete/' + id);
}