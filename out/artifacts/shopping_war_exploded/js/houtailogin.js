        $(function()
        {
        	// $("#username").on('blur', function(){});
        	$("#username").blur(function()
        	{
        		// 当用户名为空时  展示tooltip提示框
        		if ($("#username").val().trim() == '')
        		{
        			// 显示提示框    $().tooltip(json的options对象).tooltip('show');
        			$("#username").tooltip(
        			{
        				'placement' : 'top',   // 提示框的位置  top|buttom|left|right|auto
        				'trigger'   : 'manual', // 手动触发
        				'title' :  '用户名不能为空'
        			}).tooltip('show');
        			
        			$("#username").parent().parent().addClass('has-error');  // 添加错误样式
        		}else
        		{
        			$("#username").parent().parent().removeClass('has-error');  // 移除错误样式
        		}
        	});
        	
        	
        	//  当用户信息(用户名、密码、身份)校验通过  之后   3秒钟之后关闭提示框tooltip
        	$('[data-toggle="tooltip"]').each(function()
        	{
        		// this是一个dom对象   将dom对象 转换成了 jQuery对象      $(dom对象)
        		// 绑定事件: 当tooltip显示之后触发
        		$(this).on('shown.bs.tooltip', function()
        		{
        			var _this  = this;
        			setTimeout(function()
  		        	{
  		        		// 隐藏提示框    $().tooltip(json的options对象).tooltip('hide');
  		        		$(_this).tooltip('hide').addClass('has-error');
  		        	},3000);
        		});
        	});
        	
        
        	// 当用户名、密码为空时 我们要阻止表单的默认提交行为  return false
        	$("#loginFrm").submit(function()
        	{
        		if ($("#username").val().trim() == '')
        		{
        		
        			// 显示提示框    $().tooltip(json的options对象).tooltip('show');
        			$("#username").tooltip(
        			{
        				'placement' : 'top',   // 提示框的位置  top|buttom|left|right|auto
        				'trigger'   : 'manual', // 手动触发
        				'title' :  '用户名不能为空'
        			}).tooltip('show');
        			 
        			$("#username").parent().parent().addClass('has-error');  // 添加错误样式
        			return false;  // 阻止表单的默认提交行为
        		} else
		        {
		            $("#username").parent().parent().removeClass('has-error');
		        }
        	});
        	
        });