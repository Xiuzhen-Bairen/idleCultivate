layui.use(['upload', 'table', 'form'], function () {
    var cols = [[
        {field: 'id', width: 50, title: 'id'}
        , {field: 'name', title: '怪物名称'}
        , {field: 'mapName', title: '地图名称'}
        , {
            field: 'faction', title: '阵营', templet: function (row) {
                return dataDict.faction(row.faction);
            }
        }
        , {
            field: 'mobClass', title: '怪物种类', templet: function (row) {
                return dataDict.mobClass(row.mobClass);
            }
        }
        , {
            field: 'mobType', title: '怪物类型', templet: function (row) {
                return dataDict.mobType(row.mobType);
            }
        }
        , {field: 'level', title: '等级'}
        , {field: 'hp', title: '生命值'}
        , {field: 'damage', title: '伤害'}
        , {field: 'armour', title: '护甲'}
        , {
            title: '操作', width: 150, templet: function (row) {
                return '<button class="layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑怪物\',\'edit/' + row.id + '\', 500, 500)" type="button"><i class="layui-icon">&#xe642;</i>编辑</button>' +
                    '<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="remove(this, \'' + row.id + '\')" type="button"><i class="layui-icon">&#xe640;</i>删除</button>';
            }
        }
    ]];

    crud.list(cols, '/manage/map_mob/list');
    crud.upload('/manage/map_mob/importExcel', 'xls|xlsx');
});

function search() {
    var data = {
        name: $('input[name="name"]').val(),
        levelStart: $('input[name="levelStart"]').val(),
        levelEnd: $('input[name="levelEnd"]').val(),
        faction: $('select[name="faction"]').val(),
        mobClass: $('select[name="mobClass"]').val(),
        mobType: $('select[name="mobType"]').val()
    };

    crud.search(data);
}

function remove(obj, id) {
    crud.remove(obj, '/manage/map_mob/delete/' + id);
}