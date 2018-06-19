package org.andy.common.listener;

public class CommondLine extends Object {
	private String merchantid;
	private String deviceid;
	private String commond;
	private String options;
	
	public String  getmerchantid() {
		return merchantid;
	}
	
	public void  setmerchantid(String merchantID) {
		merchantid = merchantID;
	}
	
	public String  getdeviceid() {
		return deviceid;
	}
	
	public void  setdeviceid(String deviceID) {
		deviceid = deviceID;
	}
	
	public String getcommond() {
		
		return commond;
	}
	
	public void setcommond(String Commond){
		
		commond = Commond;
	}

	public String getoptions() {
		
		return options;
	}
	
	public void setoptions(String Options){
		
		options = Options;
	}
	
	public String toCommond(){
		String cmd = "";
		cmd = "["+merchantid+"*"+deviceid+"*"+String.format("%04d", commond.length())+"*"+commond+options+"]";
		
		return cmd;
	}
}