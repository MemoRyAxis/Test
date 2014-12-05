package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MrCompress {

	public static final String SEP = System.getProperty("file.separator");
	ZipOutputStream zos = null;
	String zipPath;
	String zipName;

	public MrCompress(String zipPath, String zipName)
			throws FileNotFoundException {
		this.zipPath = zipPath;
		this.zipName = zipName;
		zos = new ZipOutputStream(new FileOutputStream(zipPath + zipName));
	}
	
	/**
	 * compress given files by ZIP 
	 * 
	 * @author ma_qz
	 * @date 2014年12月5日 下午1:54:54
	 */
	public void compressByZip(String[] files) throws IOException {
		for (String filePath : files) {
			File file = new File(filePath);
			zos.putNextEntry(new ZipEntry(file.getName()));
			try (FileInputStream fis = new FileInputStream(filePath)) {
				System.out.println(filePath);
				byte[] buffer = new byte[1024];
				int size = 0;
				while ((size = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, size);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * compress given file or directory to ZIP file
	 * 
	 * @author ma_qz
	 * @date 2014年12月5日 下午1:55:22
	 */
	public void compressByZip(String filePath, String fileName) throws IOException {
		addZipEntry(filePath, fileName);
		writeZipFile(filePath + fileName);
	}
	private void addZipEntry(String filePath, String fileName) throws IOException {
		String realPath = filePath + fileName;
		File file = new File(realPath);
		if (file.isDirectory()) {
			System.out.println(fileName);
			zos.putNextEntry(new ZipEntry(fileName + SEP));
			for (File f : file.listFiles()) {
				addZipEntry(filePath, f.getPath().replace(filePath, ""));
			}
		} else {
			System.out.println(fileName);
			zos.putNextEntry(new ZipEntry(fileName));
		}
	}
	private void writeZipFile(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			System.out.println(file.getPath());
			for (File f : file.listFiles()) {
				writeZipFile(f.getPath());
			}
		} else {
			try (FileInputStream fis = new FileInputStream(file)) {
				System.out.println(file.getPath());
				byte[] buffer = new byte[1024];
				int size = 0;
				while ((size = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, size);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void close() {
		try {
			if (zos != null) {
				zos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// ZipOutputStream zos = null;
		// FileOutputStream fos = null;
		String filePath = "D:\\1-1.vm.dat";
//		String dirPath = "D:\\vm";

		File file = new File(filePath);
		File zipFile = new File("D:\\vm.zip");
		
		System.out.println("--->\t gen vm.zip");

		try (FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);) {
			zos.putNextEntry(new ZipEntry("1-1.vm.dat"));
			byte[] buffer = new byte[1024];
			int size = 0;
			while ((size = fis.read(buffer)) != -1) {
				zos.write(buffer, 0, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--->\t gen zip.zip");
		
		MrCompress comp = new MrCompress("D:\\", "zip.zip");
		comp.compressByZip("D:\\", "vm");
		comp.close();
		
		System.out.println("--->\t gen comp.zip");
		
		comp = new MrCompress("D:\\", "comp.zip");
		String file1 = "D:\\vm.zip";
		String file2 = "D:\\zip.zip";
		String file3 = "D:\\userkeyprivate.ppk";
		comp.compressByZip(new String[]{file1, file2, file3});
		comp.close();
	}
}
