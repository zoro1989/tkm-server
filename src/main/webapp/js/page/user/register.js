require(['../../base/config'],function (config) {
	require(['jquery','bootstrap','cookie','MD5','supersized','supersized-init','layer'],function () {

		var baseurl = $("#baseurl").val();
		//验证码
		$("#vcode").on("click",'img',function(){
			/**动态验证码，改变地址，多次在火狐浏览器下，不会变化的BUG，故这样解决*/
			var i = new Image();
			i.src = baseurl + '/common/getVCode.tkm?'  + Math.random();
			$(i).replaceAll(this);
		});
		$('.register').click(function(){
			var form = $('#_form');
			var error= form.find(".error");
			var tops = ['27px','96px','165px','235px','304px','372px'];
			var inputs = $("form :text,form :password");
			for(var i=0;i<inputs.length;i++){
				var self = $(inputs[i]);
				if(self.val() == ''){
					error.fadeOut('fast', function(){
						$(this).css('top', tops[i]);
					});
					error.fadeIn('fast', function(){
						self.focus();
					});
					return !1;
				}
			}
			var re_password = $("#re_password").val();
			var password = $("#password").val();
			if(password != re_password){
				return layer.msg('2次密码输出不一样！',function(){}),!1;
			}

			if($('[name=vcode]').val().length !=4){
				return layer.msg('验证码的长度为4位！',function(){}),!1;
			}
			var password = $("#password").val();
			var username = $("#email").val();
			var nickname = $("#nickname").val();
			var vcode = $(".vcode").val();
			var data = {nickname:nickname,email:username,pswd:password,vcode:vcode};
			var load = layer.load();
			$.post(baseurl+"/user/subRegister.tkm",data ,function(result){
				layer.close(load);
				if(result && result.status!= 200){
					return layer.msg(result.message,function(){}),!1;
				}else{
					layer.msg('注册成功！' );
					window.location.href= result.back_url || baseurl + "/";
				}
			},"json");

		});
		$("form :text,form :password").keyup(function(){
			$(this).parent().find('.error').fadeOut('fast');
		});
		//跳转
		$("#login").click(function(){
			window.location.href="user/login.tkm";
		});
		$("#register").click(function(){
			window.location.href="user/register.tkm";
		});
	})
});
