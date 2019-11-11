function addSubmit() {
    var recommendId = $("#recommendId").val();
    if (recommendId < 0) {
        layer.msg("请选择推荐类型", {icon: 5, shift: 6});
        return;
    }
    var imgIds = document.getElementsByName("ids");
    var num = 0;
    var ids = '';
    for (var i = 0; i < imgIds.length; i++) {
        if (imgIds[i].checked == true) {
            num++;
            ids += imgIds[i].value + ",";
        }
    }
    if (num == 0) {
        layer.msg("请选择要推荐的课程", {icon: 5, shift: 6});
        return;
    }
    $.ajax({
        url : baselocation+"/admin/website/addCourseDetail",
        data : {
            "ids" : ids,
            "recommendId" : recommendId
        },
        type : "post",
        dataType : "json",
        success : function(result) {
            if (result.message == 'true') {
                layer.msg("推荐成功", {icon: 1, shift: 6});
                window.opener.addCourseReload();
                window.close();
            } else if (result.message == 'than') {
                var recommendName = $("#recommendId").find(
                    "option:selected").text();
                layer.msg(recommendName + "模块只能添加" + result.entity.courseNum
                    + "个课程", {icon: 5, shift: 6});
            } else {
                layer.msg("操作失败", {icon: 5, shift: 6});
                window.close();
            }
        }
    });

}
function allCheck(th) {
    $("input[name='ids']:checkbox").prop('checked', th.checked);
}