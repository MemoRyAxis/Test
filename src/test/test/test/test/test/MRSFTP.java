package test.test.test.test.test;

import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MRSFTP {

	public static void main(String[] args) {
		JSch jSch = new JSch();
		try {
			jSch.addIdentity("D:\\userkeyprivate", "mqz.13");
//			jSch.setKnownHosts("D:\\Program Files\\freeSSHd\\privatekey.dsa");
			Session session = jSch.getSession("userkey", "127.0.0.1", 22);
			Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			System.out.println("--->\t session create");
//			session.setPassword("memoi");
			session.connect();
			System.out.println("--->\t session connected");
			
			Channel channel = session.openChannel("sftp");
			System.out.println("--->\t opening channel");
			channel.connect();
			
			System.out.println("---> \t channel connected");
			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}
}
