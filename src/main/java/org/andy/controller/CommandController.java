package org.andy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.andy.service.CommandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CommandController {

	private static final Logger LOGGER = Logger.getLogger(CommandController.class);

	@Autowired
	private CommandService commandService;
	
	/*
	 * 终端命令
	 * 控制列表
	 * 
	 */
	
	@RequestMapping(value="/commandList")
	@ResponseBody
	public ModelAndView commandList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav =new ModelAndView("command");
		LOGGER.info("终端_服务器_数据接口");
		return mav;
	}
	/*
	 * 发送命令到终端
	 * 控制终端的状态
	 */
	@RequestMapping(value="/sendCommand",method=RequestMethod.POST)
	@ResponseBody
	public boolean sendCommand(HttpServletRequest request,HttpServletResponse response){
		String command = request.getParameter("command");//获取前端命令指令
		commandService.findCommand(command);//查找命令
		System.err.println("command________________"+command);
		return true;
	}
	

}
