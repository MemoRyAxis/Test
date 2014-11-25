package test.test.test.test.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MRSFTP {
	
	

	public static void main(String[] args) {
		
		MRSFTP my = new MRSFTP();
		my.authorize("D:\\userkeyprivate", "mqz.13");
		my.connect("userKey", "", "127.0.0.1", 22);
		my.getChannel();
//		
//		JSch jSch = new JSch();
//		try {
//			jSch.addIdentity("D:\\userkeyprivate", "mqz.13");
////			jSch.setKnownHosts("D:\\Program Files\\freeSSHd\\privatekey.dsa");
//			Session session = jSch.getSession("userkey", "127.0.0.1", 22);
//			Properties config = new java.util.Properties(); 
//			config.put("StrictHostKeyChecking", "no");
//			session.setConfig(config);
//			System.out.println("--->\t session create");
////			session.setPassword("memoi");
//			session.connect();
//			System.out.println("--->\t session connected");
//			
//			
//			Channel channel = session.openChannel("sftp");
//			System.out.println("--->\t opening channel");
//			channel.connect();
//			
//			System.out.println("---> \t channel connected");
//			channel.disconnect();
//			session.disconnect();
//		} catch (JSchException e) {
//			e.printStackTrace();
//		}
	}
	
	private JSch jSch;
	private Session session;
	private Properties config;
	private static Map<String, Object> cache = new HashMap<>();
	
	public MRSFTP() {
		jSch = new JSch();
		config = new Properties();
	}
	
	public void authorize(String prvKey) {
		authorize(prvKey, null);
	}
	
	public void authorize(String prvKey, String passPhrase) {
		try {
			jSch.addIdentity(prvKey, passPhrase);
			config.put("StrictHostKeyChecking", "no");
			
			cache.put("prvKey", prvKey);
			cache.put("passPhrase", passPhrase);
		} catch (JSchException e) {
			System.out.println("===>\t authorize fail! please check prvKey and the passphrase.");
			e.printStackTrace();
		}
	}
	
	public void connect(String userName, String password, String host, int port) {
		try {
			session = jSch.getSession(userName, host, port);
			session.setConfig(config);
			session.setPassword(password);
			session.connect();
			
			cache.put("userName", userName);
			cache.put("password", password);
			cache.put("host", host);
			cache.put("port", port);
		} catch (JSchException e) {
			System.out.println("===>\t connect fail! please check user info and host info.");
			e.printStackTrace();
		}
	}
	
	public void connect() {
		// TODO cache 
	}
	
	public ChannelSftp getChannel() {
		ChannelSftp channel = null;
		try {
			channel = (ChannelSftp) session.openChannel("sftp");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}
	
	public void disConnect() {
		session.disconnect();
	}
}
