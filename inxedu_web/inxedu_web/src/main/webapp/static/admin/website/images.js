
var imageFilehtml;
	$(function(){
		imageFilehtml =$("#imageFile").parent().html();
		/*根据选项的value判断图片类型判断变图片大小*/
		var w;
        var h;
        var imgType = $("#imgType").val();
        if (imgType==1||imgType==16||imgType==17||imgType==25){
			$(".htmlImgSize").html("(请上传 1920*460(长X宽)像素 的图片)");
			w = 1920;
            h = 460;
        }else if (imgType==11){
			$(".htmlImgSize").html("(请上传 140*140(长X宽)像素 的图片)");
			w = 140;
            h = 140;
        }else if (imgType==18||imgType==20){
			$(".htmlImgSize").html("(请上传 1920*400(长X宽)像素 的图片)");
			w = 1920;
            h = 400;
        }else if (imgType==19){
			$(".htmlImgSize").html("(请上传 1920*456(长X宽)像素 的图片)");
			w = 1920;
            h = 456;
        }else if (imgType==23){
			$(".htmlImgSize").html("(请上传 220*500(长X宽)像素 的图片)");
			w = 220;
			h = 500;
		}
        else if (imgType==26){
			$(".htmlImgSize").html("(请上传 1903*460(长X宽)像素 的图片)");
			w = 1903;
			h = 460;
		}else {
			$(".htmlImgSize").html("(请选择图片类型)")
		}
		//初始化图片上传
        initSimpleImageUpload("imageFile","image",imgCallback,'',"true",w,h);
		//初始化略缩图片上传
		initSimpleImageUpload("previewFile","image",previewCallback,'',"true",w,h);

	});


var num=1;
	/*判断图片尺寸*/
	function checkImgWH(obj) {
		var imgType = $(obj).val();
		var w= "";
		var h= "";
		/*根据选项的value判断图片类型判断变图片大小*/
		if (imgType==1||imgType==16||imgType==17||imgType==25){
			w = 1920;
			h = 460;
			$(".htmlImgSize").html("(请上传 1920*460(长X宽)像素 的图片)")
		}else if (imgType==11){
			w = 140;
			h = 140;
			$(".htmlImgSize").html("(请上传 140*140(长X宽)像素 的图片)")
		}else if (imgType==18||imgType==20){
			w = 1920;
			h = 400;
			$(".htmlImgSize").html("(请上传 1920*400(长X宽)像素 的图片)")

		}else if (imgType==19){
			w = 1920;
			h = 456;
			$(".htmlImgSize").html("(请上传 1920*456(长X宽)像素 的图片)")
		}else if (imgType==23){
			w = 220;
			h = 500;
			$(".htmlImgSize").html("(请上传 220*500(长X宽)像素 的图片)")
		} else if (imgType==26){
			$(".htmlImgSize").html("(请上传 1903*460(长X宽)像素 的图片)");
			w = 1903;
			h = 460;
		}else {
			$(".htmlImgSize").html("(请选择图片类型)")
		}
		//imageFilehtml=imageFilehtml.replace("imageFile","imageFile1")
		//$("#imageFile").parent().html(imageFilehtml);
		$(".ke-inline-block").remove();
		initSimpleImageUpload("imageFile","image",imgCallback,'',"true",w,h);
	}
	//图片上传回调
	function imgCallback(imgUrl){
		$("input[name='websiteImages.imagesUrl']").val(imgUrl);
		$("#imagesUrl").attr('src',staticServer+imgUrl);
	}
	//略缩图片上传回调
	function previewCallback(imgUrl){
		$("input[name='websiteImages.previewUrl']").val(imgUrl);
		$("#previewUrl").attr('src',staticServer+imgUrl);
	}
	
	/**
	 * 提交信息
	 */
	function saveImage(){
		var imagesUrl =$("input[name='websiteImages.imagesUrl']").val();
		if(imagesUrl==null || $.trim(imagesUrl)==''){
            layer.msg("请上传图片", {icon: 5, shift: 6});
			return;
		}
		$("#saveImagesForm").submit();
	}
