layui.use(['form', 'layer'],
    function () {
        var form = layui.form;
        form.render();
        form.verify({});
        crud.edit('/manage/map_mob/edit/');
    });
