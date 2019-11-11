/**
 * 修改用户信息
 * @param userId 用户ID
 */
function updateUserInfo(userId,img){

	var path = $("#photoPath").val();

	if(oldMobile==null||oldMobile==""){
		var mobileVal=$("#u-mobile").val();
		if(mobileVal==""){//验证手机是否为空
			dialog('提示信息',"请输入用户手机号！",1);
			return;
		}
		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
		if(reg.test(mobileVal)==false){//格式不正确
			dialog('提示信息',"请输入正确的手机！",1);
			return;
		}
	}
	if(oldEmail==null||oldEmail==""){
		var emailVal=$("#u-email").val();
		if(emailVal==""){//验证邮箱是否为空
			dialog('提示信息',"请输入邮箱！",1);
			return;
		}
		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
		if(reg.test(emailVal)==false){//格式不正确
			dialog('提示信息',"请输入正确的邮箱！",1);
			return;
		}
	}
	var changeShowName = $("#changeShowName").val();
	if (changeShowName==""){
		dialog('提示信息',"请输入一个昵称!",1);
		return;
	}

	var oldPhotoPath=$("#oldPhotoPath").val();
	//新上传图片后，修改图片
	if(path!=oldPhotoPath){
		var params = "photoPath=" + path + "&txt_width=" + $("#picture_width").val()
			+ "&txt_height=" + $("#picture_height").val() + "&txt_top="
			+ $("#txt_top").val() + "&txt_left=" + $("#txt_left").val()
			+ "&txt_DropWidth=" + $("#txt_DropWidth").val()
			+ "&txt_DropHeight=" + $("#txt_DropHeight").val();
		$.getJSON(uploadServerUrl + "/image/saveface?" + params,function(json) {
				uploadImageCallback(json.src);
			}
		);
	}else {
		uploadImageCallback(oldPhotoPath);
	}

}

/**
 * 修改个人头像 切图后回调
 * @param photoUrl
 */
function uploadImageCallback(photoUrl){
	var params='';
	$("#updateForm input,#updateForm select").each(function(){
		params+=$(this).serialize()+"&";
	});

	params+="user.picImg="+photoUrl+"&";
	$.ajax({
		url:baselocation+'/uc/updateUser',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				showUserInfo();
				msgshow(result.message,"success");//弱提示
			}else{
				msgshow(result.message,"false");//弱提示
			}
		}
	});
}

/**
 * 选项控制显示
 */
function showTab(_in){
	$(".c-tab-title > a.clickAvailable").click(function(){
		$(".c-tab-title > a.clickAvailable").removeClass('current');
		$(this).addClass('current');
		var _index = $(this).index()-1;
		$("#p_tCont > div").hide();
		$($("#p_tCont > div")[_index]).show();
	});
	
	$(".c-tab-title > a.clickAvailable").removeClass('current');
	$($(".c-tab-title > a.clickAvailable")[_in]).addClass('current');
	$("#p_tCont > div").hide();
	$($("#p_tCont > div")[_in]).show();
}

/**
 * 修改密码
 */
function updatePwd(){
	$(".u-a-error").html("");
	var oldPwd=$("input[name='nowPassword']").val();
	if(oldPwd.trim()==""){
		$("input[name='nowPassword']").next().html('<em class="u-a-cw icon16">&nbsp;</em>请输入原始密码');
		$("input[name='nowPassword']").parent().addClass("errorts");
		return;
	}else{
		$("input[name='nowPassword']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
		$("input[name='nowPassword']").parent().removeClass("errorts");
	}
	
	var newPassword=$("input[name='newPassword']").val();
	if(newPassword.trim()==""){
		$("input[name='newPassword']").next().html('<em class="u-a-cw icon16">&nbsp;</em>请输入新密码');
		return;
	}else{
		$("input[name='newPassword']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
	}
	
	var confirmPwd=$("input[name='confirmPwd']").val();
	if(confirmPwd.trim()==""){
		$("input[name='confirmPwd']").next().html('<em class="u-a-cw icon16">&nbsp;</em>请输入确认密码');
		return;
	}else{
		$("input[name='confirmPwd']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
	}
	
	var params ='';
	$("#pwdForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	
	$.ajax({
		url:baselocation+'/uc/updatePwd',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				msgshow(result.message,"true","500");
				$("input:password").val('');
                $("input:password").next().html('');
			}else{
				if(result.entity==1){
					$("input[name='nowPassword']").next().html('<em class="u-a-cw icon16">&nbsp;</em>');
				}else {
					$("input[name='confirmPwd']").next().html('<em class="u-a-cw icon16">&nbsp;</em>');
					$("input[name='newPassword']").next().html('<em class="u-a-cw icon16">&nbsp;</em>');
				}
				dialog('提示信息',result.message,1);
			}
		},
		error:function(error){
			dialog('提示信息','系统繁忙，请稍后再操作！',1);
		}
	});
}


/**
 * 修改用户图片
 * @param userId 用户ID
 */
function updateImg(userId) {
    var path = $("#photoPath").val();
	var oldPhotoPath = $("#oldPhotoPath").val();
	if (path==oldPhotoPath){
		document.location='/uc/initUpdateUser/0';
		return;
	}
    if (path==null || path=='') {
        $("#save_message").html('<em class="icon16 u-a-cw">&nbsp;</em><q class="c-orange vam">请上传您要修改的图片</q>');
        return;
    }
    var params = "photoPath=" + path + "&txt_width=" + $("#picture_width").val()
        + "&txt_height=" + $("#picture_height").val() + "&txt_top="
        + $("#txt_top").val() + "&txt_left=" + $("#txt_left").val()
        + "&txt_DropWidth=" + $("#txt_DropWidth").val()
        + "&txt_DropHeight=" + $("#txt_DropHeight").val() + "&cusid="
        + userId;
    $.getJSON(uploadServerUrl + "/image/saveface?" + params,function(json) {
            var photoUrl = json.src;
            $.ajax({
                type : "post",
                url : baselocation+"/uc/updateImg",
                data : {'user.userId':userId,'user.picImg':photoUrl},
                success : function(result) {
                    if(result.success==true){
                        document.location='/uc/initUpdateUser/0'
                    }else{
                        dialog('提示信息',result.message,1);
                    }
                },
                error : function(ex) {
                    dialog('提示信息','系统繁忙，请稍后再操作！',1);
                }
            });
        }
    );
}

/**
 * 初始化上传头像
 * @param btnid 标签ID
 * @param fieldName
 */
function uploadImg(btnid,fieldName){
	KindEditor.create('');
	var uploadbutton = KindEditor.uploadbutton({
		button : KindEditor('#'+btnid+'')[0],
		fieldName : fieldName,
		url : uploadSimpleUrl+"&param=temp&fileType=jpg,gif,png,jpeg",
		afterUpload : function(data) {
			if (data.error == 0) {
				//销毁图片插件
				$("#deleImage").click();
				$("#picture").attr("style","");
				$("#picture").attr("src",baselocation+data.url);
				$(".jcrop-preview").attr("src",baselocation+data.url);
				$(".pictureWrap").attr("src",baselocation+data.url);
				$("#photoPath").val(data.url);

				var img=new Image();
				img.src=baselocation+data.url;
				img.onload=function(){
					var realHeight = img.height;
					var realWidth = img.width;
					if(realHeight>=realWidth&&realHeight>300){
						var height=300;
						var width = height*realWidth/realHeight;
						$("#picture").attr("height",height);
						$("#picture").attr("width",width);
						$("#picture_width").val(Math.ceil(width));
						$("#picture_height").val(height);
					}else if(realHeight<realWidth&&realWidth>300){
						var width=300;
						var height = width*realHeight/realWidth;
						$("#picture").attr("height",height);
						$("#picture").attr("width",width);
						$("#picture_width").val(width);
						$("#picture_height").val(Math.ceil(height));
					}else{
						var height=realHeight;
						var width = realWidth;
						$("#picture").attr("height",height);
						$("#picture").attr("width",width);
						$("#picture_width").val(Math.ceil(width));
						$("#picture_height").val(height);
					}

					editingPhotos();
				};
			}else{
				alert(data.message);
			}
		},
		afterError : function(str){}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
}

/**
 * 编辑图片
 */
function editingPhotos(){
	jQuery(function($){
	    var jcrop_api,
	        boundx,
	        boundy,
	        $preview = $('.preview-pane'),
	        $pcnt2 = $('.preview-pane2 .preview-container'),
	        $pimg2 = $('.preview-pane2 .preview-container img'),
	        $pcnt = $('.preview-pane1 .preview-container'),
	        $pimg = $('.preview-pane1 .preview-container img'),
	        $pcnt3 = $('.preview-pane3 .preview-container'),
	        $pimg3 = $('.preview-pane3 .preview-container img'),
	        xsize = $pcnt.width(),
	        ysize = $pcnt.height();
		    xsize2 = $pcnt2.width(),
	        ysize2 = $pcnt2.height();
		    xsize3 = $pcnt3.width(),
	        ysize3 = $pcnt3.height();

	    $('#picture').Jcrop({
	      onChange: updatePreview,
	      onSelect: updatePreview,
	      allowSelect:false,//是否允许新选框
          minSize: [50,50],//选框最小尺寸
	      aspectRatio: xsize / ysize,
	      aspectRatio: xsize2 / ysize2,
	      aspectRatio: xsize3 / ysize3
	    },function(){
	      var bounds = this.getBounds();
	      boundx = bounds[0];
	      boundy = bounds[1];
	      jcrop_api = this;
	      jcrop_api.animateTo([80,50,80,20]);
	      $preview.appendTo(jcrop_api.ui.holder);
	    });

	    function updatePreview(c){
    	  $('#txt_left').val(c.x);
	      $('#txt_top').val(c.y);
	      $('#x2').val(c.x2);
	      $('#y2').val(c.y2);
	      $('#txt_DropWidth').val(c.w);
	      $('#txt_DropHeight').val(c.h);
	      if (parseInt(c.w) > 0){
	        var rx = xsize / c.w;
	        var ry = ysize / c.h;
	        $pimg.css({
	          width: Math.round(rx * boundx) + 'px',
	          height: Math.round(ry * boundy) + 'px',
	          marginLeft: '-' + Math.round(rx * c.x) + 'px',
	          marginTop: '-' + Math.round(ry * c.y) + 'px'
	        });
	        var rx2 = xsize2 / c.w;
	        var ry2 = ysize2 / c.h;
	        $pimg2.css({
		          width: Math.round(rx2 * boundx) + 'px',
		          height: Math.round(ry2 * boundy) + 'px',
		          marginLeft: '-' + Math.round(rx2 * c.x) + 'px',
		          marginTop: '-' + Math.round(ry2 * c.y) + 'px'
		        });
	        var rx3 = xsize3 / c.w;
	        var ry3 = ysize3 / c.h;
	        $pimg3.css({
		          width: Math.round(rx3 * boundx) + 'px',
		          height: Math.round(ry3 * boundy) + 'px',
		          marginLeft: '-' + Math.round(rx3 * c.x) + 'px',
		          marginTop: '-' + Math.round(ry3 * c.y) + 'px'
		        });
	      }
		}
		$('#deleImage').click(function(e) {
            jcrop_api.destroy();
            return false;
        });
    });
}