package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.OAuthToken;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoController {
	@Autowired HttpSession session;
	
	@GetMapping("/kakao/oauth")
	public String oauth(String code) {
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
		// header오브젝트, MultiValueMap을 1개 오브젝트로 묶음 
		HttpEntity<MultiValueMap<String,String>> tokenRequest = 
				new HttpEntity<>(params,headers);
		// http 전송 : post 방식 응답은 response
		ResponseEntity<String> response = rt.exchange(tokenUrl, 
				HttpMethod.POST, tokenRequest, String.class);
		
		System.out.println("response : "+ response);
		// 카카오에서 response 로 받은 json 데이터를 저장 
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		
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
		
		return "redirect:/";
	}
	@GetMapping("/kakao/logout")
	public String kakologout() {
		session.invalidate();
		return "redirect:/";
	}
}
