layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'map_id', title: '地图Id'}
        , {field: 'name', title: '怪物名称'}
        , {field: 'mapName', title: '地图名称'}
        , {
            field: 'faction', title: '种族', templet: function (row) {
                return dataDict.faction(row.faction);
            }
        }
        , {
            field: 'mobClass', title: '职业', templet: function (row) {
                return dataDict.mobClass(row.mobClass);
            }
        }
        , {field: 'level', title: '等级'}
        , {field: 'hp', title: '生命'}
        , {field: 'att', title: '攻击'}
        , {field: 'def', title: '防御'}
        , {field: 'crit', title: '暴击率'}
        , {field: 'dodge', title: '闪避率'}
        , {field: 'coordinate', title: '所在坐标'}
        , {
            field: 'is_hide', title: '隐身', templet: function (row) {
                return row.is_hide;
            }
        }
        , {field: 'extra_info', title: '扩展信息'}
        , {
            title: '操作', width: 150, templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑怪物\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/monster/list');
    crud.upload('/manage/monster/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        level: $('input[name="level"]').val(),
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/monster/delete/' + id);
}