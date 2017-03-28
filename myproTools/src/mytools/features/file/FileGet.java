package mytools.features.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import myproTools.readConfig;

public class FileGet {
	private File file;
	private String result;

	public String getResult() {
		return result;
	}

	public FileGet() {
	};

	public FileGet(String path) {
		this.file = new File(path);
		if (file.exists() && file.isFile()) {
			this.result = readFile() == null ? null : readFile();
		}
	}

	private String readFile() {
		StringBuffer sb = new StringBuffer();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			int i = 0;
			while (i != -1) {
				i = fileReader.read();
				sb.append((char) i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return sb.toString();
	}

	public void createFolder(String path) {
		File file = new File(path);
		if (file.exists()) {
			System.out.println(path + "文件已经存在");
		} else {
			file.mkdirs();
			System.out.println(path + "创建成功");
		}
	}

	/**
	 * 读取 user的jsp文件并根据 路径和新的名称 生成 limst 什么的路径
	 * 
	 * @param writePath
	 * @param fileNewName
	 */
	public void readFile(String writePath, String fileNewName) {
//		readConfig aa = readConfig.getConfig();
		String Readpath = "O:/orcaldriver/njust-jw/webapp/app/jsp/sys/user/";
		readFile(Readpath, writePath, fileNewName);
	}

	public void readFile(String Readpath, String writePath, String fileNewName) {

		File file = new File(Readpath);
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				String newFileName = writePath + fileNewName + file2.getName().substring(4);
				File newFile = new File(newFileName);
				System.out.println(file2.getAbsolutePath());
				if (newFile.exists()) {
					System.out.println("文件已经存在");
				} else {
					try {

						fileInputStream = new FileInputStream(file2);
						fileOutputStream = new FileOutputStream(newFile);
						int len = 0;
						byte[] b = new byte[1024 * 1000 * 2];
						while ((len = fileInputStream.read(b)) != -1) {
							fileOutputStream.write(b, 0, len);
						}
						fileOutputStream.close();
						fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}
