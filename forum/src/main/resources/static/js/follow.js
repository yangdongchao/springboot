
function getLove(id) {
    var followerId = $("#userId").val();
    if(id===1){
        $.ajax({
            async: false,
            type: 'POST',
            dataType: 'json',
            data:{'followerId':followerId},
            url:"follower/add",
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            },
            success: function (data) {
                     if(data.rspCode='000000'){
                         $("#follow").attr("class", "btn btn-default");
                         $("#follow").attr("onclick", "getLove(2)");
                         $("#follow").text("取消关注");
                         toastr.success("成功关注!")
                     }
                     else{
                         toastr.fail("关注失败!")
                     }
            }
        })
    }
    else{
        //alert(id);
        $.ajax({
            async: false,
            type: 'POST',
            dataType: 'json',
            data:{'followerId':followerId},
            url:"follower/delete",
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            },
            success: function (data) {
                if(data.rspCode='000000'){
                    $("#follow").attr("class", "btn btn-success");
                    $("#follow").attr("onclick", "getLove(1)");
                    $("#follow").text("关注");
                    toastr.success("取消关注成功!")
                }
                else{
                    toastr.fail("取消关注失败!")
                }
            }
        })
    }
}