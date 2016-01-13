package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class MrCompress {

	public static final String SEP = System.getProperty("file.separator");

	/**
	 * compress given files by ZIP
	 * 
	 * @author ma_qz
	 * @date 2014年12月5日 下午1:54:54
	 */
	public static void compressByZip(String[] files, String zipPath,
			String zipName) throws IOException {
		for (String filePath : files) {
			File file = new File(filePath);
			try (FileInputStream fis = new FileInputStream(filePath);
					FileOutputStream fos = new FileOutputStream(zipPath
							+ zipName);
					ZipOutputStream zos = new ZipOutputStream(fos)) {
				zos.putNextEntry(new ZipEntry(file.getName()));
				System.out.println("--->\t compress file: " + file.getName());
				byte[] buffer = new byte[1024];
				int size = 0;
				while ((size = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, size);
				}
				System.out.println("--->\t write file: " + file.getName());
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
	public static void compressByZip(File file, String zipPath, String zipName)
			throws IOException {
		try (FileOutputStream fos = new FileOutputStream(zipPath + zipName);
				ZipOutputStream zos = new ZipOutputStream(fos);) {
			String rootPath = file.getParent() + SEP;
			addZipEntry(file, rootPath, zos);
			writeZipFile(file, zos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addZipEntry(File file, String rootPath, ZipOutputStream zos)
			throws IOException {
		String relativeName = file.toString().replace(rootPath, "");
		if (file.isDirectory()) {
			System.out.println("--->\t compress dir: " + file.getName());
			zos.putNextEntry(new ZipEntry(relativeName + SEP));
			for (File f : file.listFiles()) {
				addZipEntry(f, rootPath, zos);
			}
		} else {
			System.out.println("--->\t compress file: " + file.getName());
			zos.putNextEntry(new ZipEntry(relativeName));
		}
	}

	private static void writeZipFile(File file, ZipOutputStream zos) {
		if (file.isDirectory()) {
			System.out.println(file.getPath());
			for (File f : file.listFiles()) {
				writeZipFile(f, zos);
			}
		} else {
			try (FileInputStream fis = new FileInputStream(file)) {
				System.out.println("--->\t write file: " + file.getName());
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
	 * unpack the ZIP file and keep it at the same time
	 *
	 * @author ma_qz
	 * @date 2014年12月5日 下午11:48:41
	 */
	public static void decompressByZip(String zipFile, String decomPath) {
		File file = new File(zipFile);

		try (FileInputStream fis = new FileInputStream(file);
				ZipInputStream zis = new ZipInputStream(fis);) {
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				File newFile = new File(decomPath + entry);
				System.out.println(newFile);
				if (entry.getName().endsWith(SEP)) {
					newFile.mkdirs();
				} else {
					newFile.createNewFile();
					writeFile(newFile, zis);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeFile(File file, InputStream is) {
		try (FileOutputStream fos = new FileOutputStream(file);) {
			int size = 0;
			byte[] buffer = new byte[1024];
			while ((size = is.read(buffer)) != -1) {
				fos.write(buffer, 0, size);
			}
		} catch (Exception e) {
			System.out.println("===>\t write file failure! ");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// ZipOutputStream zos = null;
		// FileOutputStream fos = null;
		String filePath = "D:\\1-1.vm.dat";
		// String dirPath = "D:\\vm";

		File file = new File(filePath);
		if (!file.exists()) {
		    file.createNewFile();
		}
		        
		File zipFile = new File("D:\\vm.zip");

		System.out.println("--->*\t gen vm.zip");

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

		System.out.println("--->*\t gen zip.zip");

		MrCompress.compressByZip(new File("D:\\vm"), "D:\\", "zip.zip");

		System.out.println("--->*\t gen comp.zip");

		String file1 = "D:\\vm.zip";
		String file2 = "D:\\zip.zip";
		String file3 = "D:\\userkeyprivate.ppk";
		MrCompress.compressByZip(new String[] { file1, file2, file3 }, "D:\\",
				"comp.zip");

		System.out.println("--->*\t decompress zip.zip");

		MrCompress.decompressByZip("D:\\zip.zip", "D:\\bpm\\");

		// System.out.println("--->\t test write file");
		//
		// File testfile = new File("D:\\txt.txt");
		// FileOutputStream ffos = new FileOutputStream(testfile);
		// ffos.write("keng".getBytes());
		// ffos.close();
		// System.out.println(testfile.createNewFile());
	}
}
