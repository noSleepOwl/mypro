package demo_327;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Strings;

public class Jsoum {
	public static final String BASEURL = "http://all.kanshu.com/";
	public static final String SAVEFILE="C:\\Users\\uigsw\\Desktop\\test";
	public static void main(String[] args) throws IOException {
		Jsoum js = new Jsoum();
		 js.getInfo().forEach(o->{
			 System.out.println(o.toString());
		 });;
	}
	
	void saveBook(String path , book bo){
		File file = new File(path);
		if(!(file.exists() && file.isDirectory())){
			file.mkdirs();
		}
		BufferedWriter bw = null;
		try {
			File bookFile = new File(SAVEFILE+"\\"+bo.getName()+".txt");
			if(!(bookFile.exists()&&bookFile.isFile()))
				bookFile.createNewFile();
			bw = new BufferedWriter(new FileWriter(bookFile));
			bw.write(bo.getContent().toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void getContentUrl(book bo){
		 try {
			 bo.setUrl( BASEURL+this.getDoc(bo.getUrl()).select("#readedA").attr("href"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void getContent(book bo){
		bo.getUrl();
		 try {
//			 bo.setUrl( BASEURL+this.getDoc(bo.getUrl()).select("#readedA").attr("href"));
			Document doc = this.getDoc(bo.getUrl());
			
			Elements els = doc.select(".yd_butp1");
			bo.appendContent(doc.select(".yd_text2").text());
			if(els.size() !=0){
				String url=	els.select("a").attr("href");
				bo.setUrl(url);
				getContent(bo);
			}else{
				return ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	Document getDoc(String url) throws IOException{
		Request req = Jsoup.connect(url).request();
//		System.out.println(req.toString());
		System.out.println(req.cookies().toString());
		System.out.println(req.headers().toString());
		System.out.println(req.requestBody());
		return Jsoup.connect(url).get();
//		{Accept-Encoding=gzip, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36}
	}
	Elements parseElement(Document doc ,String select){
		Elements elem = doc.select(select);
		return elem;
	}
	
	
	List<book> getBooks(Elements elements){
		List<book> books = new ArrayList<>();
		for (Element element : elements) {
			book boo = new book();
			if(element.attr("class").equals("li_one")) continue;
			Elements el = element.select("span");
			for(int i = 0 ; i<el.size();i++)
			{
				Element inf = el.get(i);
				if(i==0) boo.setIndex(Integer.valueOf(inf.text()));
				else if(i==1) boo.setType(inf.select("a").text());
				else if(i==2) {boo.setName(inf.select("a").text()); boo.setUrl(inf.select("a").attr("href"));}
				else if(i==3) boo.setWritter(inf.select("a").text());
				else if(i==4) boo.setUpDateTime(getDate(inf.text()));
				else if(i==5) boo.setTouchCount(Integer.valueOf(inf.text()));
			}
			books.add(boo);
		}
		return books;
	}
	List<book> getInfo() throws IOException{
		Document doc =getDoc(BASEURL);
		Elements ele = parseElement(doc, ".sk_cont>ul>li");
		List<book> ls = getBooks(ele);
		for (book bo: ls) {
			getContentUrl(bo);
		}
		for(book bo:ls){
			getContent(bo);
		}
		for(book bo:ls){
			saveBook(SAVEFILE,bo);
		}
		return ls;
	}
	Date getDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过url 获取
	 * @param url
	 * @return
	 */
	Html getHtml(Html html){
		Document document = null;
		try {
			if(html.isHaveDoc()){
				document = html.getDoc();
			}else if(Strings.isNullOrEmpty(html.getUrl())){
				document = Jsoup.connect(html.getUrl()).get();
				html.setDoc(document);
				html.setHaveDoc(true);
			}
			if(!Strings.isNullOrEmpty(html.getSelector())&&html.isHaveDoc()){
				document.select(html.getSelector());
				html.setElement(document.select(html.getSelector()));
			}else if(html.getSelectors() != null && html.getSelectors().size()>0){
				for (String select:html.getSelectors()) {
					Elements elements =document.select(select);
					html.putSelectsResult(select, elements);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			html.setHaveDoc(false);
		}
		return html;
	}
}

class book{
	private String url;
	private int index;
	private String type;
	private String name;
	private String writter;
	private Date upDateTime;
	private int touchCount;
	private StringBuffer content;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWritter() {
		return writter;
	}
	public void setWritter(String writter) {
		this.writter = writter;
	}
	public Date getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(Date upDateTime) {
		this.upDateTime = upDateTime;
	}
	public int getTouchCount() {
		return touchCount;
	}
	public void setTouchCount(int touchCount) {
		this.touchCount = touchCount;
	}
	
	public StringBuffer getContent() {
		return content;
	}
	public void setContent(StringBuffer content) {
		this.content = content;
	}
	public void appendContent(String newContent){
		content = content == null ?new StringBuffer():content;
		content.append(newContent);
	}
	@Override
	public String toString() {
		return "book [网址=" + url + ", 序号=" + index + ", 类型=" + type + ", 书名=" + name + ", 作者=" + writter
				+ ", 更新时间=" + upDateTime + ", 点击量=" + touchCount + "]";
	}
	
}


abstract class Html{
	private List<String> selectors;
	/**
	 * 选择器
	 */
	private String selector;
	/**
	 * 有无doc
	 */
	private boolean haveDoc = false;
	 /**
	 * url
	 */
	private String url ;
	/**
	 * 获取的正文
	 */
	private Document doc;
	/**
	 * 获取的url 或者是正文
	 */
	private Elements element;
	private int eleSize;
	private Map<String, String> infoUrl;
	private Map<String, Elements> selectResult;
	
	public boolean isHaveDoc() {
		return haveDoc;
	}
	public void setHaveDoc(boolean haveDoc) {
		this.haveDoc = haveDoc;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean setInfo(String name, String url) {
		infoUrl = infoUrl==null?new HashMap<>():infoUrl;
		return infoUrl.put(name, url) != null;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public boolean selectorsAdd(String selector){
		selectors = selectors == null? new ArrayList<>():selectors;
		return selectors.add(selector);
	}
	public boolean putSelectsResult(String selector,Elements elements){
		selectResult = selectResult == null ? new HashMap<>():selectResult;
		if(elements.size() == 0)
			return false;
		else
			selectResult.put(selector,elements);
		return true ;
	}
	public Elements getElement() {
		return element;
	}
	public void setElement(Elements element) {
		eleSize =element.size();
		this.element = element;
	}
	public int getEleSize() {
		return eleSize;
	}
	public List<String> getSelectors() {
		return selectors;
	}
	public void setSelectors(List<String> selectors) {
		this.selectors = selectors;
	}
	public Map<String, String> getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(Map<String, String> infoUrl) {
		this.infoUrl = infoUrl;
	}
	public Map<String, Elements> getSelectResult() {
		return selectResult;
	}
	public void setSelectResult(Map<String, Elements> selectResult) {
		this.selectResult = selectResult;
	}
	public void setEleSize(int eleSize) {
		this.eleSize = eleSize;
	}
	
}

class HtmlExtend extends Html{
	
}