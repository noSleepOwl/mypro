package mytools.features.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FilesOperationTools implements FileOperationInterface {

	// 创建ArrayList对象保存数量及查找到的路径
	private static ArrayList<String> list = null;
	// 创建intb变量保存查找数量
	private int sum = 0;

	public FilesOperationTools() {
		super();
		list = new ArrayList<>();
		list.add(0, "-1");
	}

	@Override
	public boolean copyFils(String sorFile, String toFile) throws IOException {
		boolean flag = false;
		// 封装字符路径为File对象
		File sorfile = new File(sorFile);
		File tofile = new File(toFile);
		// 声明字节流
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		if (sorfile.isFile()) {
			// 创建FileInputStream字节流读取文件对象
			fileInputStream = new FileInputStream(sorfile);
			// 创建FileOutputStream字节流写入文件对象
			File temp = new File(tofile.getAbsolutePath().concat(File.separator).concat(sorfile.getName()));
			fileOutputStream = new FileOutputStream(temp);
			// 定义字节数组变量，每次都读进字节数组
			byte[] by = new byte[1024 * 1000 * 2];
			// 定义每次真实读取到的字节长度变量
			int len = 0;
			// 循环复制文件
			while ((len = fileInputStream.read(by)) != -1) {
				fileOutputStream.write(by, 0, len);
			}
			// 关闭流
			fileInputStream.close();
			fileOutputStream.close();
			// 如果源文件长度等于复制后的文件长度，则设置返回变量 true
			return true;
		} else {
			// 获取源文件夹下的所有文件包含文件夹的File列表
			File[] files = sorfile.listFiles();
			// 判断是否获取到的List对象为null,null说明是特殊文件或没权限访问
			if (files != null) {
				// 拼接新的文件夹路径并创建
				File temp = new File(tofile.getAbsolutePath().concat(File.separator).concat(sorfile.getName()));
				temp.mkdir();
				// 循环调用方法本身
				for (File file : files) {
					flag = copyFils(file.getAbsolutePath(), temp.getAbsolutePath());
				}
			} else {
				System.out.println("文件夹下有特殊文件夹！可能是隐藏的垃圾桶文件。 " + sorfile.getAbsolutePath());
			}
		}
		return flag;
	}

	@Override
	public boolean deleteFiles(String delFile) throws IOException {
		boolean flag = false;
		File delfile = new File(delFile);
		if (delfile.isFile()) {
			return delfile.delete();
		} else {
			File[] files = delfile.listFiles();
			if (files != null) {
				for (File file : files) {
					flag = deleteFiles(file.getAbsolutePath());
				}
				delfile.delete();
			} else {
				System.out.println("文件夹下有特殊文件!可能是隐藏的垃圾桶文件, 无法删除" + delfile.getAbsolutePath());
			}
		}

		return flag;
	}

	@Override
	public boolean renameFile(String oldFile, String newName) throws IOException {
		boolean flag = false;
		File oldfile = new File(oldFile);
		File newfile = new File(oldfile.getParent().concat(File.separator).concat(newName));
		if (oldfile.isFile()) {
			flag = oldfile.renameTo(newfile);
		} else {
			System.out.println("指定的文件路径有错误" + oldFile);
		}
		return flag;
	}

	@Override
	public boolean renameFile(String files, String type, String name) throws IOException {
		boolean flag = false;
		final String typ = type.contains(".") ? type : ".".concat(type);
		File file = new File(files);
		File[] fil = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().toLowerCase().endsWith(typ.toLowerCase());
			}
		});
		int i = 1;
		for (File fi : fil) {
			flag = fi.renameTo(new File(fi.getParent().concat(File.separator).concat(name).concat("_") + (i++) + typ));
		}
		return flag;
	}

	@Override
	public boolean clipFile(String file, String toFiles) throws IOException {
		boolean flag = false;
		File sfile = new File(file);
		File tofile = new File(toFiles);
		if (sfile.isFile()) {
			flag = sfile.renameTo(new File(toFiles.concat(File.separator).concat(sfile.getName())));
		} else {
			flag = copyFils(sfile.getAbsolutePath(), tofile.getAbsolutePath());
			if (flag) {
				flag = deleteFiles(sfile.getAbsolutePath());
			}
		}
		return flag;
	}

	@Override
	public ArrayList<String> findFileType(String findFiles, String type) throws IOException {
		final String typ = type.contains(".") ? type : ".".concat(type);
		File findfiles = new File(findFiles);
		File[] findfile = findfiles.listFiles();
		if (findfiles != null) {
			for (File find : findfile) {
				if (find.isDirectory()) {
					findFileType(find.getAbsolutePath(), type);
				} else {
					if (find.getName().toLowerCase().endsWith(type.toLowerCase())) {
						list.add(find.getAbsolutePath());
						sum++;
					}
				}
			}
		}
		list.set(0, String.valueOf(sum));
		return list;
	}

	@Override
	public ArrayList<String> findFile(String findFiles, String file) throws IOException {
		File findfiles = new File(findFiles);
		if (".".equals(file.substring(0, 2))) {
			findFileType(findFiles, file.substring(1));
		} else {
			getFile(findfiles, file);
		}
		list.remove(0);
		return list;
	}

	private void getFile(File findfiles, String file) {
		File[] files = findfiles.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isFile() && f.getName().toLowerCase().contains(file.toLowerCase())) {
					list.add(f.getAbsolutePath());
				}
				getFile(f, file);
			}
		}

	}

}
