layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {
            field: 'job', title: '职业', templet: function (row) {
                return dataDict.job(row.job);
            }
        }
        , {field: 'level', title: '等级'}
        , {field: 'hp', title: '生命值'}
        , {field: 'strength', title: '力量'}
        , {field: 'agility', title: '敏捷'}
        , {field: 'intellect', title: '智力'}
        , {field: 'stamina', title: '耐力'}
        , {
            fixed: 'right', width: 160, align: 'center', title: '操作', templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑配置\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/level_prop/list');
    crud.upload('/manage/level_prop/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        job: $('select[name="job"]').val(),
        level: $('input[name="level"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/level_prop/delete/' + id);
}