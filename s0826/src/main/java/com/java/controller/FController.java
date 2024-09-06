package com.java.controller;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
import org.jsoup.Jsoup;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.java.dto.ScrapDto;

@Controller
public class FController {
	 private static PythonInterpreter interpreter;
	 
	@RequestMapping("/")
	public String index(Model model) {
		ArrayList<Integer> list = new ArrayList();
		list.add(1);
		list.add(3);
		list.add(15);
		System.out.println(list);
		model.addAttribute("list",list);
		return "index";
	}
	@RequestMapping("/keyTest")
	public String keyTest() {
		return "keyTest";
	}
	@RequestMapping("/pyCall")
	public String pyCall() {
		interpreter = new PythonInterpreter();
	  //  interpreter.execfile("/python/test.py");
	   // interpreter.exec("");
	    
	    try{
//            Properties p = new Properties();
//            p.setProperty("python.path", "PATH OF JYTHON");
//            p.setProperty("python.home", "PATH OF JYTHON");
//            p.setProperty("python.prefix", "PATH OF JYTHON");
//            PythonInterpreter.initialize(System.getProperties(), p, new String[] {});
//            interpreter = new PythonInterpreter();
            interpreter.execfile("/python/test.py");
            interpreter.exec("result = Sum(5, 10)");
            PyObject result = interpreter.get("result");
            System.out.println(result);
            

    }catch(Exception ex){
        ex.printStackTrace();
    }
	    
	    
		return "pyCall";
	}
	/*
	@RequestMapping("/webtest")
	public String webtest() {
		// pom에 jsoup 추가 후 사용 
		
		String URL = "https://news.naver.com/breakingnews/section/105/230";
		List<ScrapDto> scrapers = new ArrayList<>();
        try {
            // timeout을 설정하지 않으면 ReadTimeoutException이 발생할 수 있다.
            Document doc = Jsoup.connect(URL).timeout(50000).get(); 
            Elements elements = doc.select("div[class=sa_item_inner]");
           // System.out.println(elements);
            for( Element element : elements ) {
            	ScrapDto scrap = new ScrapDto();
            	
                // System.out.println(element);
            	scrap.setImgUrl(element.select("img").attr("data-src"));
            	// String imgUrl = element.select("img").attr("data-src");
            	scrap.setTitle(element.select("img").attr("alt"));
            	// String title = element.select("img").attr("alt");
            	scrap.setUrl(element.select("a").attr("href"));
            	// String purl = element.select("a").attr("href");
            	scrap.setDes(element.select("div[class=sa_text_lede]").text());
            	// String content = element.select("div[class=sa_text_lede]").text();
            	scrap.setPress(element.select("div[class=sa_text_press]").text());
//            	String press = element.select("div[class=sa_text_press]").text();
            	System.out.println(scrap);
            } 
           
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		return "webtest";
	}
*/	
	@RequestMapping("/daum")
	public String daum() {
		return "daum";
	}
	
	@RequestMapping("/kakaomap")
	public String kakaomap() {
		return "kakaomap";
	}
	
	
	// -------------------------
		// -------  공공데이터  ------------
		// --------------------------	
	
		@GetMapping("/searchPublic")
		public String searchPublic(String txt) throws Exception{
			System.out.println(txt);
			String key = "5yOr8%2FoR%2FU6nAz5ysFlKnUQ4wayzOogsGSLvrEmu3HET2C43zlryMCB0eAutsCw9wa0xeGjc6BAdQC1YO3LT1A%3D%3D";
			String rows = ""+10; 
			int page = 1; 
			String result= ""; 
			result = galleryList(key, rows, page);
			
			return result;
		}
		public String galleryList(String key, String rows, int page) throws Exception {
 
			int medicineCount = 4800;
			page = 48;//(medicineCount / 100) + 2;
			System.out.println(page);
			String serviceKey = "5yOr8%2FoR%2FU6nAz5ysFlKnUQ4wayzOogsGSLvrEmu3HET2C43zlryMCB0eAutsCw9wa0xeGjc6BAdQC1YO3LT1A%3D%3D";
			String urla = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
			urla += "?serviceKey=" + serviceKey;
			urla += "&numOfRows=" + 100;
			urla += "&pageNo=" + page;
			urla += "&type=xml";
			
			StringBuilder urlBuilder = new StringBuilder(urla); /*URL*/
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
			BufferedReader rd;
			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
				//System.out.println(sb);
			}
			rd.close();
			conn.disconnect();
			//-------------------------------------------------------------------
			//System.out.println(sb.toString());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(sb.toString()));
		    Document xmlDoc = builder.parse(is);
	        
	        System.out.println("첫번째: "+
	        	      xmlDoc.getDocumentElement().getElementsByTagName("entpName").item(0).getTextContent());
			return sb.toString();
		}
		
		
}
