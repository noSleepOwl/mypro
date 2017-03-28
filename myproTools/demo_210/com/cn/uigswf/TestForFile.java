package com.cn.uigswf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestForFile {
	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param sourceDir
	 *            要复制的目标
	 * @param targetDir
	 *            复制到的目标文件夹
	 * @throws IOException 
	 */
	public static void coppyFile(String sourceDir, String targetDir) throws IOException {
		File source = new File(sourceDir);
		File target = new File(targetDir);

		if (source.exists() && source.isDirectory()) {
			coppyFile(sourceDir, targetDir);
		} else if (source.exists() && source.isFile()) {
		File file = new File(sourceDir);
			coppyFile(file, targetDir,"");
		} else {
			System.out.println("文件不存在");
		}
	}

	public static void coppyFile(File sourceDir, String targetDir,String parentFolder) throws IOException {
		char lastChar = targetDir.charAt(targetDir.length() - 1);
		if (lastChar != '/' || lastChar != '\\') {
			targetDir += File.separator;
		}
		String targetfile = targetDir +parentFolder+ sourceDir.getName();
		FileInputStream inputStream = new FileInputStream(sourceDir);
		FileOutputStream outputStream = new FileOutputStream(new File(targetfile));
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		byte[] b = new byte[2048];
		int read = 0;
		while ((read = bufferedInputStream.read(b)) != -1) {
			bufferedOutputStream.write(b);
		}
		bufferedOutputStream.flush();
		if(bufferedOutputStream != null) bufferedOutputStream.close();
		if(bufferedInputStream != null) bufferedInputStream.close();
		if(outputStream != null) outputStream.close();
		if(inputStream != null) inputStream.close();
	}

}
