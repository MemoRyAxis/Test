package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MrSocket {

	public static void main(String[] args) throws IOException {
		String ip = "127.0.0.1";
		int port = 38080;
		new Server(port, "D:\\vm\\save\\").start();
		System.out.println(new Client().sendFile(ip, port, "D:\\vm\\1-1.vm.dat"));
	}
}

/**
 * the server, that will receive file from client
 * @author ma_qz
 */
class Server {

	// listen port
	private int listenPort;
	// the directory to save received files
	private String directory;

	public Server(int listenPort, String directory) throws IOException {
		this.listenPort = listenPort;
		this.directory = directory;
		File file = new File(directory);
		if (!file.exists() && !file.mkdirs()) {
			throw new IOException("--->\tcan't create directory!\t\t" + directory);
		}
	}

	/**
	 *	start server
	 * @author ma_qz
	 * @date 2014年11月6日 上午1:51:43
	 */
	public void start() {
		new ListenThread().start();
	}

	/**
	 * thread to listen
	 * @author ma_qz
	 */
	private class ListenThread extends Thread {
		
		@Override
		public void run() {
			try (ServerSocket server = new ServerSocket(listenPort);) {
				while (true) {
					Socket socket = server.accept();
					new HandleThread(socket).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * thread to handle input and save the file
	 * @author ma_qz
	 */
	private class HandleThread extends Thread {

		private Socket socket;

		public HandleThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (InputStream in = socket.getInputStream();
					OutputStream re = socket.getOutputStream()) {
				readAndSave(in, re);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * write received data to file that will be created if is not exist    
		 * @author ma_qz
		 * @date 2014年11月6日 上午2:02:46
		 */
		private void readAndSave(InputStream in, OutputStream re) throws IOException {
			String fileName = getFileName(in);
			int fileLen = readInteger(in);
			System.out.println("--->\t get ready to receive file '" + directory + fileName + "', length: " + fileLen);
			FileOutputStream out = getFileOut(directory + fileName);
			readAndWrite(in, out, fileLen);
			out.close();
			System.out.println("--->\t receive success!");
			
			// TODO send result 
			re.write("success".getBytes());
		}

		/**
		 * gain file name
		 * @author ma_qz
		 * @date 2014年11月6日 上午2:09:53
		 */
		private String getFileName(InputStream in) throws IOException {
			int nameLen = readInteger(in);
			byte[] bytes = new byte[nameLen];
			in.read(bytes);
			return new String(bytes);
		}

		/**
		 * decode header to get length of file name and file
		 * @author ma_qz
		 * @date 2014年11月6日 上午2:10:21
		 */
		private int readInteger(InputStream in) throws IOException {
			byte[] bytes = new byte[7];
			in.read(bytes);
			return Integer.parseInt((new String(bytes).trim()));
		}

		/**
		 * write file
		 * @author ma_qz
		 * @date 2014年11月6日 上午2:11:38
		 */
		private FileOutputStream getFileOut(String filePath) throws IOException {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			return new FileOutputStream(file);
		}

		/**
		 * read and write at the same time
		 * @author ma_qz
		 * @date 2014年11月6日 上午2:04:27
		 */
		private void readAndWrite(InputStream in, FileOutputStream out, int fileLen) throws IOException {
			byte[] buffer = new byte[2048];
			int count = 0;
			while (count < fileLen) {
				int n = in.read(buffer);
				out.write(buffer, 0, n);
				count += n;
			}
		}

	}
}

/**
 * the client that will send file to server
 * @author ma_qz
 */
class Client {

	/**
	 * send the file to server(ip:prot) 
	 * @author ma_qz
	 * @date 2014年11月6日 上午2:15:36
	 */
	public String sendFile(String ip, int port, String filePath) throws IOException {
		String result = "";
		File file = new File(filePath);
		try (FileInputStream in = new FileInputStream(filePath);
				Socket socket = new Socket(ip, port);
				OutputStream out = socket.getOutputStream();
				InputStream re = socket.getInputStream()) {
			int len = (int) file.length();
			System.out.println("--->\t get ready to send file '" + filePath + "', length: " + len);
			writeFileName(file, out);
			writeFileContent(in, out, len);
			System.out.println("--->\t send success!");
			
			// TODO receive result
			byte[] bytes = new byte[7];
			re.read(bytes);
			result = new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * write file name
	 * @author ma_qz
	 * @date 2014年11月6日 上午2:16:46
	 */
	private void writeFileName(File file, OutputStream out) throws IOException {
		byte[] bytes = file.getName().getBytes();
		writeHeader(bytes.length, out);
		out.write(bytes);
	}

	/**
	 * write file content
	 * @author ma_qz
	 * @date 2014年11月6日 上午2:19:43
	 */
	private void writeFileContent(InputStream in, OutputStream out, int len) throws IOException {
		writeHeader(len, out);
		byte[] buffer = new byte[2048];
		int size;
		while ((size = in.read(buffer)) != -1) {
			out.write(buffer, 0, size);
		}
	}

	/**
	 * write 7 bits header that include data length and space
	 * @author ma_qz
	 * @date 2014年11月6日 上午2:20:19
	 */
	private void writeHeader(int len, OutputStream out) throws IOException {
		StringBuffer header = new StringBuffer();
		header.append(len);
		int spaceLen = 7 - header.length();
		for (int i = 0; i < spaceLen; i++) {
			header.append(" ");
		}
		out.write(header.toString().getBytes());
	}
}