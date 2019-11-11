//解绑
function removeBinding(id){
	if(confirm("是否解绑此账号?")){
		$.ajax({
			url:baselocation+"/uc/ajax/removeBinding",
			data:{"id":id},
			type:"post",
			dataType:"json",
			cache : false,
			async:false,
			success:function(result){
				//关闭上次弹窗
				$(".dialog-shadow").remove();
				if(result.success){
					dialog('提示',result.message,0);

				}else{
					dialog('提示',result.message,1);
				}
				$(".order-submit").click(function(){
					window.location.href="/uc/threelogin";
				});
			}
		});
	}


}