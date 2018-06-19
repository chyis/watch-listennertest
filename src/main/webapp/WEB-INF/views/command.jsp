<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>终端命令控制</title>
<script type="text/javascript" src="/akeqi/js/jquery.min.js"></script>
<script type="text/javascript">
	
	function sendCommand(command){
		$.ajax({
            type: "post",
            url: "/akeqi/sendCommand.do",
            dataType: "json",
            data: { command: command},
            success: function(data){
                     console.log(data);
               }
         });
		
	}
</script>
</head>
<body>
	<h1>终端控制列表</h1>
	<ul>
		<form action="" method="POST" target="_blank">
			<input type="button" name="ud" value="位置数据上报" onclick="sendCommand('ud');"/>
		    <input type="button" name="step" value="计步功能开关" onclick="sendCommand('step');"/> 
		    <input type="button" name="roll" value="翻滚检测开关" onclick="sendCommand('roll');"/> 
		    <input type="button" name="poweroff" value="关机" onclick="sendCommand('poweroff');"/>
		    <input type="button" name="location" value="定位指令" onclick="sendCommand('location');"/>
		</form>
	</ul>
</body>
</html>