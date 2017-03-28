package demo_328;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

public class PoiWorld {
	public static void main(String[] args) {
			String wordPath = "C:/Users/uigsw/Desktop/test/test.docx";
			
			XWPFDocument doc =	new PoiWorld().openDocument(wordPath);
			String res = new PoiWorld().conTrolDoc(doc);
		
	}
	public String conTrolDoc(XWPFDocument doc){
		String res = doc.toString();
		
//		doc.getParagraph(p)
		System.out.println(res);
		return "";
	}
	public void testWrite() throws Exception {
	      String templatePath = "C:/Users/uigsw/Desktop/test/test.docx";
	      String outPutPath = "C:/Users/uigsw/Desktop/test/test_ok.docx";
	      InputStream is = new FileInputStream(templatePath);
	      OutputStream out = new FileOutputStream(outPutPath);
	      XWPFDocument docx = new XWPFDocument(is);
	      XWPFRun run = docx.getParagraphs().get(0).getRuns().get(0);
	      run.setText("modify");
	      docx.write(out);
	      docx.close();
	   }
	public XWPFDocument openDocument(String saveFile) {
        return null;
    }
}
