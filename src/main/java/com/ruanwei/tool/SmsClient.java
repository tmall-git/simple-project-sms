package com.ruanwei.tool;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.simple.common.config.EnvPropertiesConfiger;

public class SmsClient {

	private static final String SEND_URL = EnvPropertiesConfiger.getValue("sendUrl");//"http://120.26.244.194:8888/sms.aspx";//
	private static final String SEND_USER_ID = EnvPropertiesConfiger.getValue("sendUserId");//"7830";//
	private static final String SEND_PASSWORD = EnvPropertiesConfiger.getValue("sendPassword");//"888888";//
	private static final String SEND_ACCOUNT = EnvPropertiesConfiger.getValue("sendAccount");//"ylxx";//;
	
	public static SmsResult sendMsg(String phone,String content) {
		return getMessageFromSms(SmsClientAccessTool.getInstance().doAccessHTTPGet(SEND_URL+"?"+getSendParam(phone,content), null));
	}
	
	private static String getSendParam(String phone,String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("action=send&userid=").append(SEND_USER_ID).append("&account=").append(SEND_ACCOUNT)
		.append("&password=").append(SEND_PASSWORD).append("&mobile=").append(phone)
		.append("&content=").append("【易代理】").append(content).append("退订回n").append("&sendTime=&extno=");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SmsResult sr = SmsClient.sendMsg("18600671341", "注册码：7654");
		System.out.println(sr.getMsg()+">>>"+sr.isSuccess());
	}
	
	private static SmsResult getMessageFromSms(String result) {
		SmsResult sr = new SmsResult();
		try {
			Document document = DocumentHelper.parseText(result);
			Element root = document.getRootElement(); 
			Element status = root.element("returnstatus");
			if ("success".equals(status.getText().toLowerCase())) {
				sr.setSuccess(true);
			}else {
				sr.setSuccess(false);
			}
			Element message = root.element("message");
			sr.setMsg(message.getText());
			return sr;
		} catch (DocumentException e) {
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setMsg(e.getLocalizedMessage());
		}  
		return sr;
	}
	
	
}
