package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class MRSFTP {
	
	public static void main(String[] args) throws SftpException {
		
//		MRSFTP my = new MRSFTP();
//		my.setPrvKey("D:\\userkeyprivate");
//		my.setPassPhrase("mqz.13");
//		my.setHost("127.0.0.1");
//		my.setUserName("userKey");
//		my.setPassword("1");
//		
//		my.connect();
//		
//		my.disConnect();
//		
//		MRSFTP me = new MRSFTP();
//		me.setHost("127.0.0.1");
//		me.setUserName("keyuser");
//		me.setPassword("mqz.13");
//		
//		me.connect();
//		ChannelSftp channel = me.getChannel();
//		
//		channel.cd("dir");
//		List<LsEntry> ls = channel.ls(channel.pwd());
//		for (int i = 0; i < ls.size(); i++) {
//			System.out.println(ls.get(i).getFilename());
//		}
//		me.upload("../dir", "D:\\userkeyprivate", ChannelSftp.APPEND);
//		
//		me.disConnect();
		
		MRSFTP sftp = new MRSFTP();
		sftp.setHost("192.168.30.199");
		sftp.setUserName("bank");
		sftp.setPassword("BwZbJmSwZosWYw");
		sftp.connect();
		
//		ChannelSftp channel = sftp.getChannel();
//		System.out.println(channel.pwd());

		sftp.upload("./upload", "D:\\userkeyprivate");
		
		sftp.disConnect();
		
//		try {
//			System.out.println(channle.pwd());
//		} catch (SftpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			my.disConnect();
//		}
		
		
//		my.connect("bank", "BwZbJmSwZosWYw", "192.168.30.199", 22);
//		ChannelSftp cha1 = my.getChannel();
//		
//		
//		
//		try {
//			System.out.println(cha1);
//			
//			cha1.mkdir("/home/bank/1");
//			System.out.println(cha1.pwd());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			my.disConnect();
//		}
		
	}
	
	
	private String userName;
	private String prvKey;
	private String passPhrase;
	private String password;
	private String host;
	private int port;
	private boolean authentication;
	private boolean isConnected;
	
	private JSch jSch;
	private Session session;
	private ChannelSftp channel;
	private Properties config;
//	private static Map<String, Object> cache = new HashMap<>();
//	private static List<ChannelSftp> channelPool = new ArrayList<>();
	
	public MRSFTP() {
		jSch = new JSch();
		
		prvKey = "";
		port = 22;
		authentication = false;
		isConnected = false;
		
		session = null;
		channel = null;
		
		config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		
	}
	
	public void connect() {
		authorize();
		initSession();
		initChannel();
		if (isConnected) {
			String loginAuth = authentication ? "private key" : "user name";
			System.out.println("--->\t connect success!\t" + userName + "@" + host + ":" + port + " by " + loginAuth);
		}
	}
	
	private void authorize() {
		try {
			if (!"".equals(prvKey)) {
				jSch.addIdentity(prvKey, passPhrase);
				authentication = true;
			}
			
		} catch (JSchException e) {
			System.out.println("===>\t authorize fail! ");
			e.printStackTrace();
		}
	}
	
	private void initSession() {
		try {
			session = jSch.getSession(userName, host, port);
			session.setConfig(config);
			if (!authentication) {
				session.setPassword(password);
			}
			session.connect();
			
		} catch (JSchException e) {
			System.out.println("===>\t connect fail! ");
			e.printStackTrace();
		}
	}
	
	private ChannelSftp initChannel() {
		try {
			if (session != null && session.isConnected()) {
				channel = (ChannelSftp) session.openChannel("sftp");
				channel.connect();
				isConnected = true;
			}
		} catch (JSchException e) {
			System.out.println("===>\t channel open fail! ");
			e.printStackTrace();
		}
		return channel;
	}
	
	public ChannelSftp getChannel() {
		return channel;
	}
	
	public void upload(String directory, String uploadFile) {
		upload(directory, uploadFile, ChannelSftp.OVERWRITE);
	}
	public void upload(String directory, String uploadFile, int mode) {
		try {
			channel.cd(directory);
			File file = new File(uploadFile);
			channel.put(new FileInputStream(file), file.getName(), new MrSftpMonitor(), mode);
		} catch (Exception e) {
			System.out.println("===>\t upload file fail! ");
			e.printStackTrace();
		}
	}
	
	private class MrSftpMonitor implements SftpProgressMonitor {
		private long transfered;

		@Override
		public void init(int op, String src, String dest, long max) {
			System.out.println("--->\t transfer start! ");
		}

		@Override
		public boolean count(long count) {
			transfered += count;
			System.out.println("--->\t transfered " + transfered + " bytes already. ");
			return true;
		}

		@Override
		public void end() {
			System.out.println("--->\t transfer end! ");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void disConnect() {
		if (channel != null && channel.isConnected())
			channel.disconnect();
		if (session != null && session.isConnected()) 
			session.disconnect();
	}
	
	
	
	public void addConfig(Object key, Object value) {
		config.put(key, value);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrvKey() {
		// TODO
		return prvKey;
	}
	public void setPrvKey(String prvKey) {
		this.prvKey = prvKey;
	}
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
