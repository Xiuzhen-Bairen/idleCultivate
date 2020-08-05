layui.use(['form', 'layer'],
    function () {
        var form = layui.form;
        form.verify({});
        crud.add('/manage/data_dict/add');
    });