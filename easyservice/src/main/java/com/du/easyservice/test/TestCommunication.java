package com.du.easyservice.test;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCommunication {

	private static String charset = "gbk";//编码格式和java程序本身编码格式要一致(我.java文件编码格式是gbk)
	//	private static String charset1 = "UTF-8";
	// 采用http通讯方式，设置http url
	private static String httpUrl = "http://10.255.2.211:7071/corporbankTest/httpAccess";
	//	private static String httpUrl = "http://172.31.17.115:59080/perbank/b2cOrderquery.do";
	// 采用tcpip通讯方式，设置通讯ip和port
	private static String ip = "10.255.2.211";
	private static int port = 8010;
	//此处的ip端口均为前置机配置的签名ip端口及ssl配置的交易ip端口

	public static void main(String[] args) throws FileNotFoundException, IOException {

//		发送tcpip登录请求，发送到scp进行签名
//		String body = getXmlInfoTcpip();
//		sendTCPIPRequest(body.toString());
//       发送http登录请求,发送到scp，通过SSL发送到银行进行交易
		String body = getXmlInfoHttp();
		String s = sendHttpRequest(body.toString());
		System.out.println(s);


	}
	/**
	 * 直接发送TCPIP请求
	 *
	 * @param message
	 *            上送报文
	 * @return 返回的报文
	 */
	//注意：发送tcpip请求时会在报文头加上5位数字表示报文长度，5位数字不计算在报文长度内
	public static String sendTCPIPRequest(String data) {
		StringBuffer sb = new StringBuffer("");
		Socket socket = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			int length = data.getBytes(charset).length;
			byte[] buf = new byte[length + 5];
			String lenValue = String.valueOf(length);
			System.arraycopy(data.getBytes(charset), 0, buf, 5, length);
			for (int i = 0; i < 5; i++)
				buf[i] = '0';
			int idx = 0;
			for (int i = 5 - lenValue.length(); i < 5; i++) {
				buf[i] = (byte) lenValue.charAt(idx++);
			}
			socket = new Socket(ip, port);
			out = new BufferedOutputStream(socket.getOutputStream());
			//System.out.println("发送数据：" + new String(buf, charset));
			out.write(buf);
			out.flush();
			in = new BufferedInputStream(socket.getInputStream());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len, charset));
			}
		} catch (Exception e) {
			sb = new StringBuffer("-1");
			e.printStackTrace();
		} finally {
			try {
				if (null != socket) {
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("tcpip通讯签名返回数据：" + sb.toString());
		return sb.toString();
	}

	/**
	 * 直接发送HTTP请求
	 *
	 * @param message
	 *            上送报文
	 * @return 返回的报文
	 */
	public static String sendHttpRequest(String data) {
		StringBuffer sb = new StringBuffer("");
		HttpURLConnection connection = null;
		BufferedInputStream in = null;
		BufferedOutputStream o = null;
		String returnValue = null;
//		httpUrl = httpUrl + "?" + data;
		try {
			System.out.println("上送银行数据为："+httpUrl);
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("content-type", "text/xml; charset="
					+ charset);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			o = new BufferedOutputStream(connection.getOutputStream());
			o.write(data.getBytes(charset));
			o.flush();
			in = new BufferedInputStream(connection.getInputStream());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len, charset));
			}
		} catch (Exception e) {
			sb = new StringBuffer("-1");
			e.printStackTrace();
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		try {
			returnValue = URLDecoder.decode(sb.toString(),"UTF-8").toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("http通讯银行端返回的交易数据：" + returnValue.toString());
		return returnValue;

	}
	public static String getXmlInfoTcpip()
	{
		StringBuilder sb = new StringBuilder();
		StringBuffer buffer = new StringBuffer();

//        buffer.append("80081080342668251012|23.00|1");
		buffer.append("201012010100274320|天能电池集团有限公司|6210920302000080388|徐松|50.10");
		sb.append("<?xml version='1.0' encoding='gb2312'?>\r\n");
		sb.append("<msg>\r\n");
		sb.append("<msg_head>\r\n");
		sb.append("<msg_type>0</msg_type>\r\n");
		sb.append("<msg_id>1005</msg_id>\r\n");
		sb.append("<msg_sn>0</msg_sn>\r\n");
		sb.append("<version>1</version>\r\n");
		sb.append("</msg_head>\r\n");
		sb.append("<msg_body>\r\n");
		sb.append("<origin_data_len>");
		sb.append(buffer.toString().trim().getBytes().length);
		sb.append("</origin_data_len>\r\n");
		sb.append("<origin_data>");
		sb.append(buffer.toString().trim());
		sb.append("</origin_data>\r\n");
		sb.append("</msg_body>\r\n");
		sb.append("</msg>");
		System.out.println(sb);
		return sb.toString();
	}
	public static String getXmlInfoHttp()
	{
		StringBuilder sb = new StringBuilder();
		String  aaa = "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";
//        String  bbb = "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";
//        bbb = URLEncoder.encode(bbb);
		sb.append("CBE003|#");
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<QLBankData>\r\n");
		sb.append("<opReq>\r\n");
		sb.append("<bsnCode>CBE003</bsnCode>\r\n");
		sb.append("<cstNo>10004881</cstNo>\r\n");
		sb.append("<reqTime>20160511095802</reqTime>\r\n");
		sb.append("<ReqParam>\r\n");
		sb.append("<serialNo>160000000017</serialNo>\r\n");
		sb.append("<payAccount>201012010100274320</payAccount>\r\n");
		sb.append("<currencyType>CNY</currencyType>\r\n");
		sb.append("<payAccountName>天能电池集团有限公司</payAccountName>\r\n");
		sb.append("<recAccount>6210920302000080388</recAccount>\r\n");
		sb.append("<recAccountName>徐松</recAccountName>\r\n");
		sb.append("<tranAmt>50.10</tranAmt>\r\n");
		sb.append("<payUse>公积金提取</payUse>\r\n");
		sb.append("<remark>是是是</remark>\r\n");
		sb.append("<branchId>5500</branchId>\r\n");
		sb.append("<signDataStr>201012010100274320|天能电池集团有限公司|6210920302000080388|徐松|50.10</signDataStr>\r\n");
		sb.append("<signData>"+aaa+"</signData>\r\n");
		sb.append("</ReqParam>\r\n");
		sb.append("</opReq>\r\n");
		sb.append("</QLBankData>");
		return sb.toString();
	}
}