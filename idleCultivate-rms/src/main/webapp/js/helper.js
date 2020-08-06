/* 网站根目录 */
var _webroot = "http://" + location.host;
/* url参数 */
var _querystring = {};
if (location.search.length > 0) {
    var arr = location.search.substring(1).split('&');
    for (var item in arr) {
        var key = arr[item].split('=')[0];
        var value = arr[item].split('=')[1];
        _querystring[key] = value;
    }
}
/* 数据字典缓存 */
var _cache = {
    version: new Date().getTime(),
    configmap: null
};

loadCache();

/* 读取缓存 */
function loadCache() {
    if (_cache.configmap == null || (new Date().getTime() - _cache.version) > 1000 * 60 * 30) {
        var localConfigMap = localStorage.getItem("configmap");
        if (localConfigMap) {
            _cache.configmap = JSON.parse(localConfigMap);
        } else {
            /* 读取数据字典缓存 */
            $.ajax({
                url: '/manage/data_dict/configMap',
                type: 'post',
                success: function (data) {
                    _cache.configmap = data;
                    localStorage.setItem("configmap", JSON.stringify(_cache.configmap));
                },
                error: function () {
                    alert('ajax error');
                }
            });
        }
    }
}

/* 数据字典Key */
var DataType = {
    "Occupy": "10100",  // 领土归属
    "Faction": "10110", // 阵营
    "Race": "10200",    // 种族
    "Job": "10250",     // 职业
    "MobType": "10300", // 怪物类型
    "MobClass": "10310" // 怪物种类
};

var CRUD = function () {
};
var DataDict = function () {
};
var UserControl = function () {
};

function formatDateTime(val) {
    if (val === null || val === '')
        return '';
    var datetime = new Date(val);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

function formatDate(val) {
    if (val === null || val === '')
        return '';
    var datetime = new Date(val);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    return year + "-" + month + "-" + date;
}

CRUD.prototype = {
    list: function (cols, url) {
        var table = layui.table;
        table.render({
            elem: '#datatable'
            , url: url
            , method: 'post'
            , cellMinWidth: 80
            , cols: cols
            , page: {
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , limits: [10, 20, 30, 40, 50]
                , groups: 3 //只显示 1 个连续页码
                , first: '首页'
                , last: '尾页'
            }
        });
    },
    upload: function (url, extension) {
        layui.upload.render({
            elem: '#btnSelectFile',
            url: url,
            accept: 'file',
            exts: extension,
            auto: false,
            bindAction: '#btnImport',
            done: function (result) {
                if (result.code === 1) {
                    layer.alert(result.message, {icon: 6},
                        function () {
                            layui.layer.closeAll();
                            layui.table.reload('datatable');
                        });
                } else {
                    layer.alert(result.message, {icon: 5});
                }
            }
        });
    },
    search: function (data) {
        var table = layui.table;
        table.reload('datatable', {
            where: data,
            page: {
                curr: 1
            }
        });
    },
    add: function (url) {
        var form = layui.form;
        form.on('submit(add)',
            function (data) {
                $.ajax({
                    url: url,
                    type: 'post',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(data.field),
                    success: function (result) {
                        if (result.code === 1) {
                            layer.alert(result.message, {icon: 6},
                                function () {
                                    xadmin.close();
                                    xadmin.father_reload();
                                });
                        } else {
                            layer.alert(result.message, {icon: 5});
                        }
                    },
                    error: function () {
                        layer.alert("请求失败", {icon: 5});
                    }
                });
            });
    },
    edit: function (url) {
        var form = layui.form;
        form.on('submit(edit)',
            function (data) {
                $.ajax({
                    url: url + '/' + data.field.id,
                    type: 'post',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(data.field),
                    success: function (result) {
                        if (result.code === 1) {
                            layer.alert(result.message, {icon: 6},
                                function () {
                                    xadmin.close();
                                    xadmin.father_reload();
                                });
                        } else {
                            layer.alert(result.message, {icon: 5});
                        }
                    },
                    error: function () {
                        layer.alert("请求失败", {icon: 5});
                    }
                });
            });
    },
    remove: function (obj, url) {
        layer.confirm('确认要删除吗？', function () {
            $.ajax({
                url: url,
                type: 'post',
                success: function (result) {
                    if (result.code === 1) {
                        $(obj).parents("tr").remove();
                        layer.msg('删除成功', {icon: 1, time: 1000});
                    } else {
                        layer.alert("删除失败", {icon: 5});
                    }
                },
                error: function () {
                    layer.alert("请求失败", {icon: 5});
                }
            });
        });
    }
};

DataDict.prototype = {
    occupy: function (value) {
        return _cache.configmap[DataType.Occupy][value];
    },
    job: function (value) {
        return _cache.configmap[DataType.Job][value];
    },
    level: function (value) {
        return _cache.configmap[DataType.level][value];
    },
    mobClass: function (value) {
        return _cache.configmap[DataType.MobClass][value];
    },
    mobType: function (value) {
        return _cache.configmap[DataType.MobType][value];
    }
};

UserControl.prototype = {
    chooseMap: function (idTag, nameTag) {
        var url = _webroot + '/userControl/chooseMap?single=1';
        if (idTag != null && idTag != '') {
            url += '&idTag=' + idTag;
        }

        if (nameTag != null && nameTag != '') {
            url += '&nameTag=' + nameTag;
        }

        window.open(url, '_blank', 'height=500,width=720,top=200,left=300,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,z-look=yes');
    },
    chooseMapCallBack: function (id, name, idTag, nameTag) {
        if (idTag != null && idTag != '') {
            $('#' + idTag).val(id);
        } else {
            $('#mapId').val(id);
        }

        if (nameTag != null && nameTag != '') {
            $('#' + nameTag).val(name);
        } else {
            $('#mapName').val(name);
        }
    }
};

window.crud = new CRUD();
window.dataDict = new DataDict();
window.userControl = new UserControl();