require(['../../base/config'],function (config) {
	require(['jquery','bootstrap','cookie','MD5','supersized','supersized-init','layer'],function () {

		//回车事件绑定
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode==13){
				$('#login').click();
			}
		};

		//登录操作
		$('#login').click(function(){
			var username = $('.username').val();
			var password = $('.password').val();
			if(username == '') {
				$('.error').fadeOut('fast', function(){
					$('.error').css('top', '27px').show();
				});
				$('.error').fadeIn('fast', function(){
					$('.username').focus();
				});
				return false;
			}
			if(password == '') {
				$('.error').fadeOut('fast', function(){
					$('.error').css('top', '96px').show();
				});
				$(this).find('.error').fadeIn('fast', function(){
					$('.password').focus();
				});
				return false;
			}
            var data = {pswd:password,email:username,rememberMe:$(".rememberMe").is(':checked')};
            var load = layer.load();
           var baseurl = $("#baseurl").val();
           $.ajax({
               url : baseurl+"/user/submitLogin.tkm",
               type : "POST",
               data : data,
               dataType: 'json',
               success : function(result) {
                   layer.close(load);
                   if(result && result.status != 200){
                       layer.msg(result.message,function(){});
                       $('.password').val('');
                       return;
                   }else{
                       layer.msg('登录成功！');
                       // setTimeout(function(){
                           //登录返回
                           window.location.href= result.back_url || baseurl+"/";
                       // },1000)
                   }
               }
           });

		});
		$('.page-container form .username, .page-container form .password').keyup(function(){
			$(this).parent().find('.error').fadeOut('fast');
		});
		//注册
		$("#register").click(function(){
			window.location.href="register.tkm";
		});
	})
});
