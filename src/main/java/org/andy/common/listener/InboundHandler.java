package org.andy.common.listener;

import java.util.Date;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.jdy.connecter.MysqlConnecter;
import org.andy.common.listener.CommondLine;
public class InboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel active");
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {//对于每个传入的消息都要调用
		
		String txt = msg.toString();// 获取终端收到的信息
		System.out.println("server:________________" + txt + " received " + new Date());
		

		MysqlConnecter mysql = new MysqlConnecter();
		String sql = "select cid,merchantid,deviceid,commond,status from watch_commonds where status=0";
		Map<String,Object> lod = mysql.find(sql);
		if(lod!=null) {
			System.out.println("cid:");
			String resultData = lod.get("commond").toString();
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			
			ctx.writeAndFlush(buf_out);
			sql = "update watch_commonds set status=1 where cid="+lod.get("cid").toString();
			mysql.update(sql);
		}
		sql = "";
		CommondLine commodline = this.parseCommond(txt);
		String commondName = commodline.getcommond();
		String merchantid = commodline.getmerchantid();
		String deviceid = commodline.getdeviceid();
		System.out.println("commodname:"+commondName);
		if (commondName.equals("LK")) {//链路保持数据，回复
			String resultData = "["+merchantid+"*"+deviceid+"*0002*LK]";
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
			ctx.writeAndFlush(buf_out);
		} else if (commondName.equals("AL")) {//报警数据上报，回复
			String resultData = "["+merchantid+"*"+deviceid+"*0002*AL]";
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
			ctx.writeAndFlush(buf_out);
		} else if (commondName.equals("TKQ")) {//报警数据上报，回复
			String resultData = "["+merchantid+"*"+deviceid+"*0003*TKQ]";
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
			ctx.writeAndFlush(buf_out);
		} else if (commondName.equals("TKQ2")) {//报警数据上报，回复
			String resultData = "["+merchantid+"*"+deviceid+"*0004*TKQ2]";
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
			ctx.writeAndFlush(buf_out);
		} else if (commondName.equals("UD")) {//位置上传，无回复
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
		}  else if (commondName.equals("UD2")) {//位置补齐，无回复
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			
		} else {//这里应该是手表对服务器发送的很多设置命令的回复了，暂时不分析。
			String resultData = "["+merchantid+"*"+deviceid+"*0002*LK]";
			byte[] datas = resultData.getBytes();
			ByteBuf buf_out = Unpooled.copiedBuffer(datas);
			sql = "insert into watch_log (`merchantid`,`deviceid`,`logdata`,`status`) values ('"+merchantid+"','"+deviceid+"','"+txt+"','0')";
			ctx.writeAndFlush(buf_out);
		}
		mysql.insert(sql);
		
		mysql.release();
	}
	
	public CommondLine parseCommond(String message){
		CommondLine cmd = new CommondLine();
		String[] comArray = message.replace("[", "").replace("]", "").replace("*", " ").split(" ");
		
		cmd.setmerchantid(comArray[0]);
		cmd.setdeviceid(comArray[1]);
		if(comArray[3].indexOf(",") != -1){
			String[] strs= comArray[3].split(",");
			cmd.setcommond(strs[0]);
		}else{
			cmd.setcommond(comArray[3]);
		}
		
		return cmd;
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {//通知ChannelInboundHandler最后一次对channel-Read()的调用时当前批量读取中的最后一条消息
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
