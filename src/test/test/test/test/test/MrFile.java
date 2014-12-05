package test.test.test.test.test;

import java.io.File;

public class MrFile {

	public static final String SEP = System.getProperty("file.separator");

	public static void main(String[] args) {
		String root = "D:\\vm";
		MrFile.getAllFile(root);

		System.out.println("--->\t *** ***");
		String filePath = "D:\\";
		String fileName = "vm";
		MrFile.getAllFile(filePath, fileName);

		System.out.println("--->\t *** ***");
		
		File f1 = new File("D:\\vm\\save\\");
		System.out.println(f1.exists());
		System.out.println(f1.isDirectory());
		System.out.println(f1.isFile());
		System.out.println(f1.getParent());
		System.out.println(f1.getName());
		File f2 = new File("D:\\vm\\1-1.vm.dat");
		System.out.println(f2.exists());
		System.out.println(f2.isDirectory());
		System.out.println(f2.isFile());
		System.out.println(f2.getParent());
		System.out.println(f2.getName());
		
		System.out.println("--->\t *** ***");
	}

	/**
	 * get all file under the given path
	 * 
	 * @author ma_qz
	 * @date 2014年12月5日 下午1:53:36
	 */
	public static void getAllFile(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			System.out.println("--->\td path: " + file.getPath() + SEP
					+ ", \tname: " + file.getName() + SEP);
			for (File f : file.listFiles()) {
				getAllFile(f.getPath());
			}
		} else {
			System.out.println("--->\tf path: " + file.getPath() + ", \tname: "
					+ file.getName());
		}
	}

	/**
	 * get relative path of all files
	 * 
	 * @author ma_qz
	 * @date 2014年12月5日 下午1:53:36
	 */
	public static void getAllFile(String filePath, String fileName) {
		String path = filePath + fileName;
		File file = new File(path);
		if (file.isDirectory()) {
			System.out.println("--->\td path: " + fileName + SEP + ", \tname: "
					+ file.getName() + SEP);
			for (File f : file.listFiles()) {
				getAllFile(filePath, f.getPath().replace(filePath, ""));
			}
		} else {
			System.out.println("--->\tf path: " + fileName + ", \tname: "
					+ file.getName());
		}
	}
}
