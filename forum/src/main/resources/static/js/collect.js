
var page=1;

function showAt(name){
	var text = $("#cremark").val();
	$("#cremark").focus().val(text + "@" +name + " ");
	$(".dropdown-menu").hide();
}

function onCollect(id,user){
	 $('#modal-remove').modal('show');
	 $("#ttopicId").val(id);
	 $("#userCheck").val(user);
}

function delCollect(){
	 $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:"",
			url: '/topic/delete/'+$("#ttopicId").val(),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(response){
				$('#modal-remove').modal('hide');
			}
		});
}



function getCollect(id,user){
    var userId = document.getElementById("userId").value;
    if(userId != "0"){
        $.ajax({
            async: false,
            type: 'POST',
            dataType: 'json',
            data:"",
            url: '/love/detail/'+id,
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            },
            success: function(data){
               if(data.rspCode === '000000'){
               		toastr.success("收藏成功");
			   }
			   else{
               	 toastr.success("该话题已收藏!");
			   }
            }
        });
    }else{
    	window.location.href="/login";
    }
}




function ChangePraise(id) {
    var userId = document.getElementById("userId").value;
    //console.log(userId);
    //alert(userId);
    console.log(Object.prototype.toString.call(userId));
    if (userId != "0") {
        $.ajax({
            async: false,
            type: 'POST',
            dataType: 'json',
            data: "",
            url: '/topic/like/' + id,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            },
            success: function (like) {
                if ($("#like" + id).is(":hidden")) {
                    $("#like" + id).show();
                    var praiseCount = parseInt($("#praiseC" + id).val()) - 1;
                    $("#praiseC" + id).val(praiseCount);
                    $("#likeS" + id).html("点赞(" + praiseCount + ")");
                    $("#likel" + id).show();
                    $("#unlike" + id).hide();
                    $("#unlikel" + id).hide();
                } else {
                    $("#like" + id).hide();
                    $("#likel" + id).hide();
                    $("#unlike" + id).show();
                    $("#unlikel" + id).show();
                    var praiseCount = parseInt($("#praiseC" + id).val()) + 1;
                    $("#praiseC" + id).val(praiseCount);
                    $("#unlikeS" + id).html("取消点赞(" + praiseCount + ")");
                }
            }
        });
    } else {
        //toast.fail("请先登录");
        window.location.href = "/login";
    }
}

function search(){
	 $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:"",
			url: '/collect/delete/'+$("#collectId").val(),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(response){
				locationUrl($("#forward").val(),"home");
				$('#modal-remove').modal('hide');
			}
		});
}


function switchComment(collectId){
     var userId = $("#userId").val();
     //alert(userId);
     if(userId != "0"){
         if($("#collapse"+collectId).hasClass('in')){
         	//alert("has");
             $("#collapse"+collectId).removeClass('in');
         }else{
              showComment(collectId);
         }
     }else{
         //toast.fail("请先登录");
         window.location.href="/login";
     }
}

function showComment(collectId){
	  $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:'',
			url: '/post/list/'+collectId,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(comments){
				initComment(comments,collectId);
	    	    $("#collapse"+collectId).addClass('in');
			}
		});
}

function initComment(comments,collectId){
	var comment='';
	 $("#commentList"+collectId).html("");
	for(var i=0;i<comments.length;i++){
		var item ='<div class=\"media bb p\"><small class=\"pull-right text-muted\">'+comments[i].createTime+'</small>';
		item=item+'<div class=\"pull-left\"><img class=\"media-object img-circle thumb32\" src=\"/'+comments[i].userPhoto+ '\" /></div> ';
		item=item+'<div class=\"media-body\">  <span class=\"media-heading\">  <p class=\"m0\"> '
		item=item+"<a href=\"javascript:void(0);\" onclick=\"locationUrl('/user/" + comments[i].userId + "/0')\">"+comments[i].userName+"</a>";
		item=item+'</p> <p class=\"m0 text-muted\">';
		if(!isEmpty(comments[i].replyUserName)){
			item=item+'回复@'+comments[i].replyUserName+':'+comments[i].content+'<small>';
		}else{
			item=item+comments[i].content+'<small>';
		}

		if($("#loginUser").length > 0){
			if(comments[i].userId==$("#loginUser").val()){
				item=item+"<a href=\"javascript:void(0);\" onclick=\"deleteComment('"+comments[i].postId+"','"+collectId+"')\" >    删除</a>";
			}else{
				item=item+"<a href=\"javascript:void(0);\" onclick=\"replyComment('"+comments[i].userName+"','"+collectId+"')\" class=\"replyComment\" >    回复</a>";
			}
		}else{
			if(comments[i].userId==$("#topicUser").val()){
				item=item+"<a href=\"javascript:void(0);\" onclick=\"deleteComment('"+comments[i].postId+"','"+collectId+"')\" >    删除</a>";
			}else{
				item=item+"<a href=\"javascript:void(0);\" onclick=\"replyComment('"+comments[i].userName+"','"+collectId+"')\" class=\"replyComment\" >    回复</a>";
			}
		}
		item=item+'</small></p></span></div></div>';
		comment=comment+item;
	}
	$("#commentList"+collectId).append(comment);

    // if($("#loginUserInfo").val()==null||$("#loginUserInfo").val()==''){
    //     $(".replyComment").hide();
    // }

}


function comment(collectId){
	var id = "commentContent"+collectId;
	//alert($("#commentContent"+collectId).val());
	 $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:{'topicId':collectId,'text':$("#commentContent"+collectId).val()},
			url: '/post/add',
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(response){
				var commentCount=parseInt($("#commentC"+collectId).val())+1;
				$("#commentC"+collectId).val(commentCount);
				$("#commentS"+collectId).html("评论("+commentCount+")");
				$("#commentContent"+collectId).val('');
				showComment(collectId);
			}
		});
}

//添加浏览记录,没有添加相应数据库接口。
function saveLookRecord(collectId){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'',
        url: '/lookRecord/save/'+collectId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){

        }
    });
}


function deleteComment(id,collectId){
	 $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:'',
			url: '/post/delete/'+id+'/'+collectId,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(response){
				var commentCount=parseInt($("#commentC"+collectId).val())-1;
				$("#commentC"+collectId).val(commentCount);
				$("#commentS"+collectId).html("评论("+commentCount+")");
				showComment(collectId);
			}
		});
}

function replyComment(name,collectId){
	var text = $("#commentContent"+collectId).val();
	$("#commentContent"+collectId).focus().val(text + "@" +name + " ");
}


function loadStandardMore(){
	var url='';
	if($("#userFavoritesId").length > 0){
		url = '/collect/standard/'+$("#pageType").val()+"/" + $("#userFavoritesId").val() ;
	}else{
		url = '/collect/standard/'+$("#pageType").val()+"/0";
	}
	if($("#userId").length > 0){
		url = url + "/" + $("#userId").val();
	}else{
		url = url + "/0";
	}
	if($("#pageType").val() == "lookAround"){
	    url = url + "/" + $("#category").val();
	}else{
	    url = url + "/NO";
	}
	 $.ajax({
			async: false,
			type: 'POST',
			dataType: 'json',
			data:'page='+page,
			url: url,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest);
				console.log(textStatus);
				console.log(errorThrown);
			},
			success: function(collects){
				if(collects.length==0){
					$("#loadStandardMore").text('没有更多了');
				}
				if($("#userContent").val()== 'usercontent'){
					listStandardCollect(collects,'collectStandardList','usercontent');
				}else{
					listStandardCollect(collects,'collectStandardList','');
				}
				page++;
			}
		});
}

//用于加载分页显示各类话题
function lookAroundMore(){
	var boardId = $("#type1").val();
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/topic/lookAround/'+boardId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listStandardCollect(collects,'collectStandardList','');
            page++;
        }
    });
}
//用于分页加载全部话题
function lookAllMore(){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/topic/lookAllMore/',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listStandardCollect(collects,'collectStandardList','');
            page++;
        }
    });
}
function loadMore2(){
    //alert("loading");
    var boardId = $("#type1").val();
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/userContent/more/',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listStandardCollect(collects,'collectStandardList','');
            page++;
        }
    });
}
function loadMoreMyArticle() {
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data: 'page=' + page,
        url: '/myArticle/more/',
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function (collects) {
            if (collects.length == 0) {
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listStandardCollect(collects, 'collectStandardList', '');
            page++;
        }
    });
}
//我的收藏分页加载
function loadMoreLove(){
    //var boardId = $("#type1").val();
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/userLove/more',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listStandardCollect(collects,'collectStandardList','');
            page++;
        }
    });
}


function listStandardCollect(collects,listId,user){
	var collectStandardList='';
	var collect = '';
    var collectorUserId='';
	for(var i=0;i<collects.length;i++){
        collectorUserId=collects[i].userId;
		if($("#userId").val() != collects[i].userId){
			collect = "				  <if> "+
			"				     | "+
			"				  </if> "+
			"                  <a class=\"sharing-action-button\" onclick=\"getCollect("+collects[i].id+")\">"+
			"                     <span class=\"fa fa-spoon\"></span>"+
			"                   	    收藏"+
			"                  </a>";
		}
		var item =
		"<li>"+
		"<a style=\"background-image:url("+(collects[i].user.photo=='' ? 'img/favicon.png' : '/'+collects[i].user.photo )+")\" class=\"hidden-xs timeline-badge sharing-user-avatar\" href=\"javascript:void(0);\" onclick=\"locationUrl(\'/userIndex/"+collects[i].userId+"/0\',\'\');\" ></a>"+
		"<div class=\"timeline-panel\">"+
		"   <div class=\"popover right\">"+
		"      <div class=\"arrow\"></div>"+
		"      <div class=\"popover-content\">"+
		"         <div class=\"table-grid\">"+
		"            <div class=\"col\">"+
		"               <div class=\"pull-right dropdown dropdown-list\">"+
		"                  ";
		if($("#userId").val() == collects[i].userId){
			item=item+		"   <a href=\"#\" data-toggle=\"dropdown\" class=\"sharing-more-button\"  >"+
			"                                             <span class=\"fa fa-angle-down\"></span>"+
			"                                          </a>";
		}
		item=item+
		"                  "+
		"                  <ul class=\"dropdown-menu animated bounceIn\">"+
		"                     <li>"+
		"                        <div class=\"list-group\">"+
		"                           <a onclick=\"getCollect("+collects[i].topicId+",'"+user+"');\" class=\"list-group-item\" href=\"javascript:void(0);\">"+
		"                              <div class=\"media-box\">"+
		"                                 <div class=\"pull-left\">"+
		"                                    <em class=\"fa fa-pencil-square-o fa-2x fa-fw text-info\"></em>"+
		"                                 </div>"+
		// "                                 <div class=\"media-box-body clearfix\">"+
		// "                                    <p class=\"m0\">修改收藏</p>"+
		// "                                    <p class=\"m0 text-muted\">"+
		// "                                       <small>修改收藏的各种属性</small>"+
		// "                                    </p>"+
		// "                                 </div>"+
		"                              </div>"+
		"                           </a>"+
		"                           <a onclick=\"onCollect("+collects[i].topicId+",'"+user+"');\" class=\"list-group-item\" href=\"javascript:void(0);\">"+
		"                              <div class=\"media-box\">"+
		"                                 <div class=\"pull-left\">"+
		"                                    <em class=\"fa fa-trash fa-2x fa-fw text-danger\"></em>"+
		"                                 </div>"+
		"                                 <div class=\"media-box-body clearfix\">"+
		"                                    <p class=\"m0\">删除</p>"+
		"                                    <p class=\"m0 text-muted\">"+
		"                                       <small>该话题会永久删除</small>"+
		"                                    </p>"+
		"                                 </div>"+
		"                              </div>"+
		"                           </a>"+
		"                        </div>"+
		"                     </li>"+
		"                  </ul>"+
		"               </div>"+
		"               <div class=\"m0\">"+
		"                  <a onclick=\"locationUrl(\'/user/"+collects[i].userId+"/0\',\'\');\" class=\"text-muted\" href=\"javascript:void(0);\">"+collects[i].user.userName+"</a>"+
		"                  ";
		// if($("#userId").val() == collects[i].userId){
		// 	item=item+" <a onclick=\"changePrivacy("+collects[i].topicId+",\'PRIVATE\');\" style=\"display:"+(collects[i].type=='PRIVATE' ? 'none' : 'inline-block')+"\" id=\"private"+collects[i].topicId+"\" href=\"javascript:void(0);\" title=\"设为私密\" class=\"deco-none\">"+
		// 	"                <span style=\"color: #eee;\" class=\"fa fa-lock\"></span>"+
		// 	"              </a>";
		// 	item=item+" <a onclick=\"changePrivacy("+collects[i].topicId+",\'PUBLIC\');\" style=\"display:"+(collects[i].type=='PUBLIC' ? 'none' : 'inline-block')+"\" id=\"public"+collects[i].topicId+"\" href=\"javascript:void(0);\" title=\"设为公开\" class=\"deco-none\">"+
		// 	"                <span class=\"fa fa-lock text-warning\"></span>"+
		// 	"              </a>";
		// }
		//if($("#pageType").val() != 'explore'){
			item=item+
			"                  "+
			"                  <small class=\"ml-sm text-muted\">"+collects[i].createTime+"</small>"
		//}
		item=item+
		/*"                  "+
		"                  <small class=\"ml-sm text-muted\">"+collects[i].collectTime+"</small>"+*/
		"               </div>"+
		"            </div>"+
		"         </div>"+
		"         <div class=\"media resource-card-thumbnail\">"+
		"            <a href=\""+collects[i].url+"\" onclick=\"saveLookRecord("+collects[i].topicId+");\" target=\"_blank\" class=\"pull-left\">"+
		"               <div style=\"background-image:url("+(collects[i].logoUrl=='' ? 'img/favicon.png' : collects[i].logoUrl )+")\" class=\"media-object resource-card-image\"></div>"+
		"            </a>"+
		"            <div class=\"media-body\">"+
		"               <h4 class=\"visible-xs media-heading resource-card-title-xs\">"+
		"                  <a onclick=\"saveLookRecord("+collects[i].topicId+");\" href=\""+collects[i].url+"\" target=\"_blank\">"+collects[i].topicTitle+"</a>"+
		"               </h4>"+
		"               <h3 class=\"hidden-xs media-heading resource-card-title\">"+
		"                  <a onclick=\"saveLookRecord("+collects[i].topicId+");\" href=\""+collects[i].url+"\" target=\"_blank\">"+collects[i].topicTitle+"</a>"+
		"               </h3>"+
		"               <div class=\"hidden-xs resource-card-content\">"+
		"                  <p>"+collects[i].decription+"</p>"+
		"               </div>"+
		"            </div>"+
		"         </div>"+
		"         <div class=\"m0\">"

            item=item+"            <span class=\"icon-folder mr-sm\"></span>"
                // "  <a onclick=\"locationUrl(\'/standard/"+collects[i].favoritesId+"/"+collects[i].userId+"\',\'"+collects[i].favoritesId+"\');\" class=\"normal-color-a ng-binding\" href=\"javascript:void(0);\">"+collects[i].favoriteName+"</a>"
        item=item+
		"            <div class=\"pull-right hidden-xxs\">"+
		"                  <a onclick=\"ChangePraise("+collects[i].topicId+");\" style=\"display:"+(collects[i].isPraise==1? 'none' : 'inline-block')+"\" id=\"like"+collects[i].topicId+"\" class=\"sharing-action-button btn-praise\">"+
		"                     <span class=\"fa fa-thumbs-o-up\"></span>"+
		"                     <show id=\"likeS"+collects[i].topicId+"\">点赞("+collects[i].praise+")</show>"+
		"                  </a>"+
		"                   <if  style=\"display:none\"> "+
		"				     | "+
		"				  </if> "+
		"                  <a onclick=\"ChangePraise("+collects[i].topicId+");\" style=\"display:"+(collects[i].isPraise==1? 'inline-block' : 'none')+"\" id=\"unlike"+collects[i].topicId+"\" class=\"sharing-action-button\">"+
		"                     <span class=\"fa fa-thumbs-up\"></span>"+
		"                  	 <show id=\"unlikeS"+collects[i].topicId+"\">取消点赞("+collects[i].praise+")</show>"+
		"                  </a>"+
		"                  <input type=\"hidden\" value=\""+collects[i].praise+"\" id=\"praiseC"+collects[i].topicId+"\" name=\"praiseC\">"+
		"                  <input type=\"hidden\" value=\""+collects[i].topicReplies+"\" id=\"commentC"+collects[i].topicId+"\" name=\"commentC\">"+
		"                  | "+
		"                  <a onclick=\"switchComment("+collects[i].topicId+");\" href=\"javascript:void(0);\" class=\"sharing-action-button btn-comment\">"+
		"                     <span class=\"fa fa-comment-o\"></span>"+
		"                     <show id=\"commentS"+collects[i].topicId+"\">评论("+collects[i].topicReplies+")</show>"+
		"                  </a>";
		if($("#userId").val() != collects[i].userId){
			item=item+"	 <if> "+
			"				     | "+
			"				  </if> "+
			"                  <a class=\"sharing-action-button\" onclick=\"getCollect("+collects[i].topicId+")\">"+
			"                     <span class=\"fa fa-spoon\"></span>"+
			"                   	    收藏"+
			"                  </a>";
		}
        // if($("#loginUserInfo").val()){
        //     item=item+"	 <if> "+
        //         "				     | "+
        //         "				  </if> "+
        //         "                  <a class=\"sharing-action-button\" onclick=\"getCollect("+collects[i].id+")\">"+
        //         "                     <span class=\"fa fa-spoon\"></span>"+
        //         "                   	    收藏"+
        //         "                  </a>";
        // }
			item=item+		"               </small>"+
		"            </div>"+
		"         </div>"+
		"         <div id=\"collapse"+collects[i].topicId+"\" class=\"collapse\">"+
		"            <comments id=\"commentList"+collects[i].topicId+"\"></comments>"+
		"            <div id=\"comment"+collects[i].topicId+"\" class=\"media p0\">"+
		"               <div class=\"media-body\">"+
		"                  <form>"+
		"                     <div class=\"input-group\">"+
		"                        <input type=\"text\" id=\"commentContent"+collects[i].topicId+"\" class=\"form-control\" placeholder=\"输入评论...\">"+
		"                        <span class=\"input-group-btn\">"+
		"                           <button onclick=\"comment("+collects[i].topicId+");\" type=\"button\" class=\"btn btn-default\">发送</button>"+
		"                        </span>"+
		"                     </div>"+
		"                  </form>"+
		"               </div>"+
		"            </div>"+
		"         </div>"+
		"      </div>"+
		"   </div>"+
		"</div>"+
		"</li>";
		collectStandardList=collectStandardList+item;
	}
	$("#"+listId).append(collectStandardList);
	if($("#loginUserInfo").val()==""){
        $(".sharing-action-button.btn-praise").removeAttr("onclick");
        $(".input-group").hide();
    }
    if($("#collector").val()){
        if($("#loginUserInfo").val() != collectorUserId){
            $(".pull-right.dropdown.dropdown-list").hide();
            $(".deco-none").hide();
        }
    }
}


$(function() {
	var loadingFlag = true;

	$(window).scroll(function() {
		if ($(window).scrollTop() == $(document).height() - $(window).height()) {
            if ($('#lookAround').length >= 1) {
                if ($('#standard').is(':visible')) {
                    if ($('#loadStandardMore').text() == '加载更多') {
                        if (loadingFlag) {
                            loadingFlag = false;
                            $('#loadingStandard').show();
                            lookAroundMore();
                            $('#loadingStandard').hide();
                            loadingFlag = true;
                        }
                    }
                }
             } else if ($('#standard').length >= 1 && $('#simple').length >= 1) {
					if ($('#standard').is(':visible')) {
						if ($('#loadStandardMore').text() == '加载更多') {
							if (loadingFlag) {
								loadingFlag = false;
								$('#loadingStandard').show();
								loadStandardMore();
								$('#loadingStandard').hide();
								loadingFlag = true;
							}
						}
			} else if ($('#simple').is(':visible')) {
					if ($('#loadSimpleMore').text() == '加载更多') {
						if (loadingFlag) {
							loadingFlag = false;
							$('#loadingSimple').show();
							loadSimpleMore();
							$('#loadingSimple').hide();
							loadingFlag = true;
						}
					}
				}
			} else if ($('#standard').length >= 1 && $('#simple').length == 0) {
				if ($('#loadStandardMore').text() == '加载更多') {
					if (loadingFlag) {
						loadingFlag = false;
						$('#loadingStandard').show();
						loadStandardMore();
						$('#loadingStandard').hide();
						loadingFlag = true;
					}
				}
			} else if ($('#standard').length == 0 && $('#simple').length >= 1) {
				if ($('#loadSimpleMore').text() == '加载更多') {
					if (loadingFlag) {
						loadingFlag = false;
						$('#loadingSimple').show();
						loadSimpleMore();
						$('#loadingSimple').hide();
						loadingFlag = true;
					}
				}
			}
		}
	});
});
//为管理者查阅添加数据
//by: xiuwenli
function loadForMange(){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/topic/lookAllMore/',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listManageCollect(collects,'manageContent');
            page++;
        }
    });
}

function LoadUserList(){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'page='+page,
        url:'/userManage/lookAllMore',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(collects){
            if(collects.length==0){
                $("#loadStandardMore").text('没有更多了');
                $("#loadStandardMore").show();
            }
            listUserCollect(collects,'UserContent');
            page++;
        }
    });
}

function listUserCollect(collects,listId){
	var display = $("#checkUser").val();
	var num = Number($("#checkUserId").val());
	//alert(num);
    var totalContent="";
    var userId="", topicId ="";
    var userName = "", userCredits="",userType;
    for(var i=0;i<collects.length;++i){
        userId = collects[i].userId;
        userName = collects[i].userName;
        userType = collects[i].user_type;
        userCredits = collects[i].credits;
        var typeName = collects[i].typeName;
		//alert(userType);
        var item = "<tr>\n"+
            "<td>\n"+
            "<div style=\"padding-bottom: 5%\">\n"+
            "       <input type=\"checkbox\" name=\"selectflag\" id="+userId+">"+
            "    </div>"+
            "</td>"+
            "<td><a text="+userName+"></a>"+userName+"</td>"+
            "<td><a text="+userCredits+"></a>"+userCredits+"</td>"+
            "<td text="+typeName+">"+typeName+"</td>"+
            "<td align=\"center\">";
        if(userType!==0&&userType<2){
            item+= "<button class=\"btn btn-danger\"  name=\"ban\" onclick="+"lock(" + userId + ")>封禁<tton>";
        }
        if(userType ===0&&userType<2){
            item+= "<button class=\"btn btn-warning\"  name=\"ban\" onclick="+"unlock(" + userId + ")>解除封禁<tton>";
        }


        if(num===3) {
        	//alert(3);
            if (userType === 1) {
            	//alert(1);
                //alert(userType);
                item += "<div>" +
                    "<button    class=\"btn btn-green\"  name=\"manger\" onclick="+"manager(" + userId + ")>设为管理员<tton>" +
                    "</div>";
            }
        }

            if(userType===2&&num===3){
                item += "<div><button  class=\"btn btn-default\"  name=\"ban\" onclick="+"unmanager(" + userId + ")>取消管理员<tton></div>";
            }
        item += "</td>";
        item += "</tr>";
        totalContent += item;
    }
    console.log(totalContent);
    $("#"+listId).append(totalContent);
}


function listManageCollect(collects,listId){
    var totalContent="";
    var userId="", topicId ="";
    var userName = "", topicName = "", topicUrl = "";
    for(var i=0;i<collects.length;++i){
        userId = collects[i].userId;
        topicId = collects[i].topicId;
        userName = collects[i].user.userName;
        topicName = collects[i].topicTitle;
        topicUrl = collects[i].url;

        var item = "<tr th:each=\"collect,collectStat : ${topics}\" style=\"width: 100%\">\n" +
            "                                <td>\n" +
            "                                    <div style=\"padding-bottom: 5%\">\n" +
            "                                        <input type=\"checkbox\" name=\"selectflag\" id=\"testname\">\n" +
            "                                    </div>\n" +
            "                                </td>\n" +
            "                                <td>\n" +
            "                                        <span>"+userName +"</span>\n" +
            "                                </td>\n" +
            "\n" +
            "                                <td>\n" +
            "                                    <a href=\""+topicUrl+"\" target= _blank >\n" +
            "                                        <span>"+topicId+"</span>\n" +
            "                                    </a>\n" +
            "                                </td>\n" +
            "\n" +
            "                                <td>\n" +
            "                                    <span>"+topicName+"</span>\n" +
            "                                </td>\n" +
            "\n" +
            "                                <td align=\"center\">\n" +
            "                                    <a onclick="+"deleteCollect(" + topicId + ",\'usercontent\')\n"+
            "                                        <button class=\"btn btn-danger\"  name=\"ban\">删除<tton>\n" +
            "                                    </a>\n" +
            "                                    <a href=\"deleteComment/"+topicId+" \"target=_blank >\n" +
            "                                        <button class=\"btn btn-warning\" name=\"manger\">删评论<tton>\n" +
            "                                    </a>\n" +
            "                                </td>\n" +
            "                            </tr>";
        totalContent += item;
    }
    console.log(totalContent);
    $("#"+listId).append(totalContent);
}



//删除帖子
function manageDel(){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/topic/delete/'+$("#ttopicId").val(),
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
        }
    });
}

function deleteCollect(topicId){
    //alert("ysa");
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/topic/delete/'+topicId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                toastr.success("删除成功");
				$("#topicManage").click();
                //alert("删除成功");
            }
            else{
                toastr.fail("操作失败");
                // alert(操作失败);
            }
        }
    });
}
function deleteCollectAll(topicId){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/topic/delete/'+topicId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                //toastr.success("删除成功")
                //$("#topicManage").click();
                //alert("删除成功");
            }
            else{
                toastr.fail("操作失败");
                // alert(操作失败);
            }
        }
    });
}
//管理员删除评论
function managerDeleteComment(id,collectId){
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:'',
        url: '/post/delete/'+id+'/'+collectId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            var commentCount=parseInt($("#commentC"+collectId).val())-1;
            $("#commentC"+collectId).val(commentCount);
            $("#comment"+id).hide();
        }
    });
}
function lock(userId) {
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/userManege/lock/'+userId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                toastr.success("操作成功");

                $("#userManage").click();
            }
            else{
                toastr.fail("操作失败");
            }
        }
    });
}
function unlock(userId) {
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/userManege/unlock/'+userId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                toastr.success("操作成功");

                $("#userManage").click();
            }
            else{
                toastr.fail("操作失败");
            }
        }
    });
}

function manager(userId) {

    // var type = [[${session.USER_CONTEXT.getUserType()}]];
    // if(type!==3){
    //     toastr.fail("只有超级管理员才能进行该操作");
    //     return;
    // }
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/userManege/manager/'+userId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                toastr.success("操作成功");

                $("#userManage").click();
            }
            else{
                toastr.fail("操作失败");
            }
        }
    });
}

function unmanager(userId) {
    // var type = [[${session.USER_CONTEXT.getUserType()}]];
    // if(type!==3){
    //     toastr.fail("只有超级管理员才能进行该操作");
    //     return;
    // }
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data:"",
        url: '/userManege/unmanager/'+userId,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function(response){
            //$('#modal-remove').modal('hide');
            if(response.rspCode==="000000")
            {
                toastr.success("操作成功");
                $("#userManage").click();
            }
            else{
                toastr.fail("操作失败");
            }
        }
    });
}