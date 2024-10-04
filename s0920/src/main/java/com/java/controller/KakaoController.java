package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.Friends;
import com.java.dto.KakaoDto;
import com.java.dto.Member;
import com.java.dto.OAuthToken;
import com.java.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoController {
	OAuthToken oAuthToken = null;
	@Autowired HttpSession session;
	@Autowired MService mservice;
	
	
	@GetMapping("/kakao/oauth")
	public String oauth(String code, Model model) {
		System.out.println("kakao code : " + code);
		// token 키받기. 
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		// header 에 담을 내용 
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		//--- body 에 담기 -------
		String grant_type="authorization_code";
		String client_id ="7980f70fd5da76a168557ced9cb2c60e";// 본인 코드
		String redirect_uri = "http://localhost:8000/kakao/oauth";
		// 2차 : 토큰 키 요청 
		// http의 post 방식으로 daum 토큰키를 요청함. 
		RestTemplate rt = new RestTemplate();
		// header 오브젝트 생성 
		HttpHeaders headers = new HttpHeaders();  // springframework 임포트
		headers.add("Content-type", content_type);
		// 데이터들을 하나의 묶음으로 처리하기위해서 map 사용 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		params.add("scope", "account_email talk_message friends");
		
		// header오브젝트, MultiValueMap을 1개 오브젝트로 묶음 
		HttpEntity<MultiValueMap<String,String>> tokenRequest = 
				new HttpEntity<>(params,headers);
		// http 전송 : post 방식 응답은 response
		ResponseEntity<String> response = rt.exchange(tokenUrl, 
				HttpMethod.POST, tokenRequest, String.class);
		
		System.out.println("response : "+ response);
		// 카카오에서 response 로 받은 json 데이터를 저장 
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("카카오 토큰키 : " + response);
		System.out.println("카카오 토큰키 getBody : " + response.getBody());
		System.out.println("카카오 엑세스 토큰 : "+ oAuthToken.getAccess_token());
		System.out.println("카카오 엑세스 만료시간(초) : "+ oAuthToken.getExpires_in());
		
		// 3차 : 사용자 정보를 요청
		// 사용자 정보 가져오기 
		// httpheader 오브젝트 생성 
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Authorization","Bearer "+oAuthToken.getAccess_token());
		headers_user.add("Content-type", content_type);
		// 합치는 오브젝트 생성 
		HttpEntity<MultiValueMap<String, String>> tokenRequest_user = 
				new HttpEntity<>(headers_user);
		String userUrl = "https://kapi.kakao.com/v2/user/me";
		ResponseEntity<String> response_user = rt.exchange(userUrl, 
				HttpMethod.POST, tokenRequest_user, String.class);
		
		System.out.println("개인정보 : " + response_user);
		System.out.println("개인정보2 : " + response_user.getBody());
		
		// --- 끝 사용자 정보 받기 성공. 
		// json 데이터를 json 오브젝트로 변환하기 . 
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoDto kdto = null; 
		// json > java object로
		try {
			kdto = objectMapper2.readValue(response_user.getBody(), KakaoDto.class);
		}catch (Exception e) {e.printStackTrace();		}
		System.out.println("카카오 사용자 정보 id : " + kdto.getId());
		System.out.println("카카오 사용자 정보 nickName : " + kdto.getProperties().getNickname());
		// member table에 kakao_id 컬럼. kdto.getId() 있고. 
		// db(member)에 사용자 정보가 있는지 확인 후 로그인을 할 수 있게 해줘야함. 
		
		session.setAttribute("sessionNicName", kdto.getProperties().getNickname() );
		
		if(session.getAttribute("sessionId")!= null) {
			String userid=(String)session.getAttribute("sessionId");
			mservice.updateKakao(userid,code, oAuthToken.getAccess_token());
		}
		
		
		model.addAttribute("kdto",kdto);
		
		return "/join";
	}
	@GetMapping("/kakao/logout")
	public String kakologout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/kakao/message")
	public String kakaoMessageToMe() {
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String id=(String)session.getAttribute("sessionId");
		Member mem = mservice.selectId(id);
		String token = mem.getToken();
		RestTemplate rt = new RestTemplate();
		
		String talkuri = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		
		
		HashMap<String, Object> msg = message(4);
		//System.out.println(msg.get("template_object"));
		
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Authorization","Bearer "+token);
		headers_user.add("Content-type", content_type);
		
		// 합치는 오브젝트 생성 
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("template_object", (String)msg.get("template_object"));
		
		HttpEntity<MultiValueMap<String,String>> tokenRequest_user = 
				new HttpEntity<>(parameters,headers_user);
				//new HttpEntity<>(headers_user,parameters);
		System.out.println("----");
		System.out.println(tokenRequest_user);
		ResponseEntity<String> responseMessage = rt.exchange(talkuri, 
				HttpMethod.POST, tokenRequest_user, String.class);
		
		System.out.println(parameters);
        System.out.println(oAuthToken.getAccess_token());

		
		return "redirect:/";
	}
	@GetMapping("/kakao/friends")
	public String kakaoMessageToFr() throws ParseException {
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String id=(String)session.getAttribute("sessionId");
		Member mem = mservice.selectId(id);
		String token = mem.getToken();
		RestTemplate rt = new RestTemplate();
		
		
		System.out.println("토큰 : "+token);
		
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Authorization","Bearer "+token);
		headers_user.add("Content-type", content_type);
		
		String flistUrl = "https://kapi.kakao.com/v1/api/talk/friends"; //친구 목록 가져오기
		
		HttpEntity<MultiValueMap<String, String>> header = 
				new HttpEntity<>(headers_user);
		ResponseEntity<String> response_user = rt.exchange(flistUrl, 
				HttpMethod.GET, header, String.class);
	
		System.out.println(response_user.getBody());
	
		// string 을 json오브젝트로 변환. 
		JSONParser jsonParser = new JSONParser();
		Object temp = jsonParser.parse(response_user.getBody());
		JSONObject flist =  (JSONObject)temp;
		JSONArray jsonArr = (JSONArray) flist.get("elements");
		ArrayList<String> friendList = new ArrayList<>();
		
		for(int i = 0 ; i < jsonArr.size() ; i ++) {
			JSONObject f = (JSONObject)jsonArr.get(i);
			friendList.add("\""+(String)f.get("uuid")+"\"");
		}
		System.out.println(friendList);
		
		System.out.println(friendList);
		
		String talkuri = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";
		
		HashMap<String, Object> msg = message(4);
		//System.out.println(msg.get("template_object"));
		
		
		
		
		System.out.println(friendList.toString());
		// 합치는 오브젝트 생성 
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("receiver_uuids", friendList.toString());
		parameters.add("template_object", (String)msg.get("template_object"));
		System.out.println(parameters);
		
		HttpEntity<MultiValueMap<String,String>> tokenRequest_user = 
				new HttpEntity<>(parameters,headers_user);
		ResponseEntity<String> responseMessage = rt.exchange(talkuri, 
				HttpMethod.POST, tokenRequest_user, String.class);

		
		
		return "redirect:/";
	}

	private HashMap<String,Object> message(int choice) {
	
		HashMap<String, Object> msg = new HashMap<>();
		String message="test";
		String web_url="http://localhost:8000/";
		String object_type="text";
		String title = "";
		String description = "";
		String image_url = "";
		
		JSONObject template_object = new JSONObject();

		JSONObject linkObj = new JSONObject();
		JSONObject cont = new JSONObject();
		JSONObject hLinkObj = new JSONObject();
		JSONArray content = new JSONArray();
		JSONArray btns = new JSONArray();
		
		
		int size = 2;	
		// 1. 단순 텍스트  
		if(choice == 1) {
			object_type = "text";
			message = "전송되는 메세지 입니다.";
			web_url = "https://www.naver.com/";
			linkObj.put("web_url", web_url);
			
			template_object.put("object_type",  object_type);
			template_object.put("text",message);
			template_object.put("button_title", "바로 확인");
			template_object.put("link", linkObj);
			
		}
		// 2. 리스트 탬플릿
		else if(choice == 2) {
			object_type = "list";
			
			// header 설정 
			String header_title = "초밥 사진";			
			web_url = "https://www.naver.com/";
			hLinkObj.put("web_url", web_url);
			
			// contents 설정 
			// 첫번째 contents
			for(int i = 0 ; i < size ; i ++) {
				linkObj.clear();
				JSONObject cObj1 = new JSONObject();
				title = (i+1)+".제목입니다";
				description = "내용입니다.";
				image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzTDlYYz92T-bTB5jEdkao4gs_onO7PEv4FQ&s" ;
				web_url = "https://www.naver.com/";
				linkObj.put("web_url", web_url);
				
				cObj1.put("title", title);
				cObj1.put("description", description);
				cObj1.put("image_url", image_url);
				cObj1.put("image_width", 50);
				cObj1.put("image_height", 50);
				cObj1.put("link", linkObj);
				content.add(cObj1);
				
				
				JSONObject cObj = new JSONObject();
				linkObj.clear();
				title = "웹으로이동";
				web_url = "https://www.naver.com/";
				linkObj.put("web_url", web_url);
				cObj.put("title",title);
				cObj.put("link", linkObj);
				btns.add(cObj);
				
			}
			
			template_object.put("object_type",  object_type);
			template_object.put("header_title",  header_title);
			template_object.put("header_link",  hLinkObj);
			
			template_object.put("contents",content);
			template_object.put("buttons", btns);
			
		}
		else if(choice == 3) {
			// template 가 feed 
			object_type = "feed";
			// contents 설정 

			title = "제목입니다";
			description = "내용입니다.";
			image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzTDlYYz92T-bTB5jEdkao4gs_onO7PEv4FQ&s" ;
			web_url = "https://www.naver.com/";
			linkObj.put("web_url", web_url);
				
			cont.put("title", title);
			cont.put("description", description);
			cont.put("image_url", image_url);
			cont.put("image_width", 50);
			cont.put("image_height", 50);
			cont.put("link", linkObj);
			
			
			template_object.put("object_type",  object_type);
			template_object.put("content",cont);
			template_object.put("buttons", btns);
			
		}
		else if(choice == 4) {
			// template 가 location
			object_type = "location";
			String address = "경기 성남시 분당구 판교역로 235";
			// contents 설정 
			title = "제목입니다";
			description = "내용입니다.";
			image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzTDlYYz92T-bTB5jEdkao4gs_onO7PEv4FQ&s" ;
			web_url = "https://www.naver.com/";
			linkObj.put("web_url", web_url);
				
			cont.put("title", title);
			cont.put("description", description);
			cont.put("image_url", image_url);
			cont.put("image_width", 50);
			cont.put("image_height", 50);
			cont.put("link", linkObj);
			
			template_object.put("object_type",  object_type);
			template_object.put("address",address);
			template_object.put("content",cont);
		}
		msg.put("template_object", template_object.toString());
		
		
		return msg;
	}
	
	
	
	
}
