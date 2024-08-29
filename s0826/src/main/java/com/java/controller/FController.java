package com.java.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.ScrapDto;

@Controller
public class FController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
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
	
	@RequestMapping("/daum")
	public String daum() {
		return "daum";
	}
	
	@RequestMapping("/kakaomap")
	public String kakaomap() {
		return "kakaomap";
	}
	
}
