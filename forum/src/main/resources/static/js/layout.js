/*---LEFT BAR ACCORDION----*/


var flag = 1;
var gfavorites;
var gfollows;
var gconfig;
var page=1;
var standardStr = "standard";
$(function() {
	//loadFavorites();
	//loadConfig();
	//loadFollows();
	//myrefresh();
	$("#passwordError").hide();
	$("#nicknameError").hide();
	$("#noticeNum").hide();
});



/**
 * js修改密码
 */
function updatePwd() {
    var ok = $('#updatePwdForm').parsley().isValid({force: true});
	if(!ok){
		return;
	}
	var url = '/userSetPassword';
	$.ajax({
		async: false,
		url : url,
		data : 'oldPassword='+$("#oldPassword").val()+'&newPassword='+$("#newPassword").val(),
		type : 'POST',
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		},
		success : function(data, textStatus) {
			if(data.rspCode == '000000'){
				$("#passwordError").hide();
				$("#updatePwdBtn").attr("aria-hidden","true");
				$("#updatePwdBtn").attr("data-dismiss","modal");
				$("#updatePwdForm")[0].reset();
				toastr.success('密码修改成功！', '操作成功');
  	    	}else{
				toastr.success("失败");
  	    		$("#passwordError").show();
  	    		$("#passwordError").html(data.rspMsg);
  	    		$("#updatePwdBtn").removeAttr("aria-hidden");
				$("#updatePwdBtn").removeAttr("data-dismiss");
  	    	}
		}
	});
}

/**
 * js修改个性签名
 */
function updateIntroduction() {
    var ok = $('#updateIntroductionForm').parsley().isValid({force: true});
	if(!ok){
		return;
	}
	var url = '/userSetSignature';
	$.ajax({
		async: false,
		url : url,
		data : {'introduction':$("#introduction").val()},
		type : 'POST',
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
            toastr.success('操作失败', '操作失败');
		},
		success : function(data, textStatus) {
			if(data.rspCode == '000000'){
				$("#updateIntroductionBtn").attr("aria-hidden","true");
				$("#updateIntroductionBtn").attr("data-dismiss","modal");
				$("#updateIntroductionForm")[0].reset();
				if(data.data.length>10){
					$("#leftIntroduction").html(data.data.substring(0,20)+'...');
				}else{
					$("#leftIntroduction").html(data.data);
				}
				$("#userIntroduction").html(data.data);
				toastr.success('个人简介修改成功！', '操作成功');
  	    	}else{
  	    		toastr.error(data.rspMsg, '操作失败');
  	    	}
		}
	});
}

/**
 * js修改用户名
 */

function updateNickname() {
    var ok = $('#updateNicknameForm').parsley().isValid({force: true});
	if(!ok){
		return;
	}
	var url = '/updateUserName';
	$.ajax({
		async: false,
		url : url,
		data : 'userName='+$("#newNickname").val(),
		type : 'POST',
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		},
		success : function(data, textStatus) {
			if(data.rspCode == '000000'){
				$("#nicknameError").hide();
				$("#updateNicknameBtn").attr("aria-hidden","true");
				$("#updateNicknameBtn").attr("data-dismiss","modal");
				$("#updateNicknameForm")[0].reset();
				if(data.data.length>10){
					$("#leftUserName").html("欢迎  "+data.data.substring(0,10)+'...');
				}else{
					$("#leftUserName").html("欢迎  "+data.data);
				}
				$("#userUserName").html(data.data);
				toastr.success('昵称修改成功！', '操作成功');
  	    	}else{
  	    		$("#nicknameError").show();
  	    		$("#nicknameError").html(data.rspMsg);
  	    		$("#updateNicknameBtn").removeAttr("aria-hidden");
				$("#updateNicknameBtn").removeAttr("data-dismiss");
  	    	}
		}
	});
   }

function showNotice(type){

	if(type === "letter"){
		locationUrl('/letter/me','letterMe');
	}else if(type == "praise"){
		locationUrl('/notice/praiseMe','praiseMe');
	}else if(type == "comment"){
		locationUrl('/notice/commentMe','commentMe');
	}else if(type == "at"){
		locationUrl('/notice/atMe','atMe');
	}

}

