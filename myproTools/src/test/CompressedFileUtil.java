package test;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * @project: Test
 * @author chenssy
 * @date 2013-7-28
 * @Description: 文件压缩工具类 将指定文件/文件夹压缩成zip、rar压缩文件
 */
public class CompressedFileUtil {
	/**
	 * 默认构造函数
	 */
	public CompressedFileUtil() {

	}

	/**
	 * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
	 * @param resourePath
	 *            源文件/文件夹
	 * @param targetPath
	 *            目的压缩文件保存路径
	 * @return void
	 * @throws Exception
	 */
	public String compressedFile(String resourcesPath, String targetPath) throws Exception {
		File resourcesFile = new File(resourcesPath); // 源文件
		File targetFile = new File(targetPath); // 目的
		// 如果目的路径不存在，则新建
		if (!targetFile.exists()) {
			if (targetFile.mkdirs()) {
			}

		}

		String targetName = "jw.zip"; // 目的压缩文件名
		File file = new File(targetPath + "\\" + targetName);
		if (file.exists()) {
			if (file.delete()) {
				JOptionPane.showMessageDialog(null, "旧文件删除成功", "提示:", JOptionPane.WARNING_MESSAGE);
				System.out.println("旧文件删除成功");

			}
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
		createCompressedFile(out, resourcesFile, "");
		out.close();
		return file.getAbsolutePath();
	}

	/**
	 * @desc 生成压缩文件。 如果是文件夹，则使用递归，进行文件遍历、压缩 如果是文件，直接压缩
	 * @param out
	 *            输出流
	 * @param file
	 *            目标文件
	 * @return void
	 * @throws Exception
	 */
	public void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
		// 如果当前的是文件夹，则进行进一步处理
		if (file.isDirectory()) {
			// 得到文件列表信息
			File[] files = file.listFiles();
			// 将文件夹添加到下一级打包目录
			out.putNextEntry(new ZipEntry(dir + "/"));

			dir = dir.length() == 0 ? "" : dir + "/";

			// 循环将文件夹中的文件打包
			for (int i = 0; i < files.length; i++) {
				createCompressedFile(out, files[i], dir + files[i].getName()); // 递归处理
			}
		} else { // 当前的是文件，打包处理
			// 文件输入流
			FileInputStream fis = new FileInputStream(file);

			out.putNextEntry(new ZipEntry(dir));
			// 进行写操作
			int j = 0;
			byte[] buffer = new byte[2048];
			while ((j = fis.read(buffer)) > 0) {
				out.write(buffer, 0, j);
			}
			// 关闭输入流
			fis.close();
		}
	}

	public String getSystemClipboard() {// 获取系统剪切板的文本内容[如果系统剪切板复制的内容是文本]
		Clipboard sysClb = null;
		sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = sysClb.getContents(null);
		// Transferable t =
		// Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		// //跟上面三行代码一样
		try {
			if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String text = (String) t.getTransferData(DataFlavor.stringFlavor);
				return text;
			}
		} catch (UnsupportedFlavorException e) {
			// System.out.println("Error tip: "+e.getMessage());
		} catch (IOException e) {
		} // System.out.println("Error tip: "+e.getMessage());
		return null;
	}

	public void setSystemClipboard(String refContent) {
		String vc = refContent.trim();
		StringSelection ss = new StringSelection(vc);

		Clipboard sysClb = null;
		sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
		sysClb.setContents(ss, null);

		// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,
		// null); //跟上面三行代码效果相同
	}

	public static void main(String[] args) {
		CompressedFileUtil compressedFileUtil = new CompressedFileUtil();

		try {
			String filePath = compressedFileUtil.compressedFile("O:\\orcaldriver\\njust-jw\\webapp", "O:\\");
			compressedFileUtil.setSystemClipboard(filePath);

			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

			Transferable contents = new Transferable() {
				DataFlavor[] dataFlavors = new DataFlavor[] { DataFlavor.javaFileListFlavor };

				@Override
				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
					List<File> files = new ArrayList<File>();
					files.add(new File(filePath));
					return files;
				}

				@Override
				public DataFlavor[] getTransferDataFlavors() {
					return dataFlavors;
				}

				@Override
				public boolean isDataFlavorSupported(DataFlavor flavor) {
					for (int i = 0; i < dataFlavors.length; i++) {
						if (dataFlavors[i].equals(flavor)) {
							return true;
						}
					}
					return false;
				}
			};

			clipboard.setContents(contents, null);

			JOptionPane.showMessageDialog(null, "压缩文件已经生成...\n 文件已复制到剪切板", "提示:", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}