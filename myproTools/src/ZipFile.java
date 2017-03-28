import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
	
	public static void main(String[] args) {
		new ZipFile().fileTozip("C:\\Users\\uigsw\\Desktop\\测试文件\\tewe", "C:\\Users\\uigsw\\Desktop\\test");
	}
	public void fileTozip(String path, String toPath) {

		try {
			File file = new File(path);
			File zipFile = new File(toPath);
			InputStream inputStream = new FileInputStream(file);
			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			int temp = 0;
			while ((temp = inputStream.read()) != -1) {
				zipOutputStream.write(temp);
			}
			inputStream.close();
			zipOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
