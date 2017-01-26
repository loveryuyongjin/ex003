package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleController {

	/* @RestController
	 *  JSP와 같은 뷰를 만들어 내지 않는 대신에 데이터 자체를 반환하는데, 이때 주로 사용되는 것은 
	 *  단순 문자열과 JSON ,XML 등으로 나누어 볼 수 있습니다.
	 *  
	 *  1. 단순 문자열의 경우
	 *  @RestController에서 문자열 데이터는 기본적으로 브라우저에는 'text/html'타입으로 처리됩니다.]
	 *  
	 */
	
	@RequestMapping("/hello")
	public String sayHello(){
		
		return "Hello World";
	}
	/*
	 *  클래스 선언부에 @RestController 애노테이션을 이용하고 있는 것이 보인다.
	 *  이것은 해당 컨트롤러의 모든 뷰 처리가 JSP가 아니라는 것을 의미한다.
	 *  @RestController 애노테이션이 사용된 클래스의 모든 메소드는  @ResponseBody가 없어도 동일하게 동작합니다.(생략되었다고 생각해도 무방하다.)
	 *  
	 *  sayHello()는 문자열을 결과로 반환하게 되어 있습니다. 특이한 점은 결과로 반환하는 문자열이 JSP의 경로가 아닌 일반 문자열이라는 점이다.
	 */

	
	
	/*
	 *  객체를 JSON으로 반환하는 경우
	 *  @RestController의 경우 별도의 처리가 없어도 객체는 자동으로 JSON으로 처리될수 있습니다.
	 *  
	 *  경로 호출 시 406 Not Accepatable 상태 에러가 발생하면 라이브러리 추가  jackson-databind
	 *  jackson-databind 라이브러리는 객체를 JSON 타입의 데이터로 변환하거나, 반대의 작업할 때사용 
	 *  개발자 도구 에서 content-type  allplication/json 으로 되어있다 확인해봄
	 */
	@RequestMapping("/sendVO")
	public SampleVO sendVO(){
		
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);
		
		return vo;
	}
	
	/*
	 * 컬렉션 타입의 객체를 반환하는 경우
	 * 결과 데이터로 List나 Map 타입은 흔시 사용되므로 이에 대한 확인 작업이 필요하다.
	 * JDK1.7 이상의 경우  List<SampleVO> list new ArrayList<>(); 와 같이 클래스에 제네릭 타입을 명시하지 않아도 됩니다.
	 */
	
	@RequestMapping("sendList")
	public List<SampleVO> sendList(){
		
		List<SampleVO> list = new ArrayList();
		for(int i=0; i<20; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		
		return list;
	}
	
	/*
	 *  Map의 경우는  javascript 의 JSON형식으로 보여지가 된다. 키 와 값으로 구성되어 {} 로 표현된다.
	 * 
	 */
	
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		
		Map<Integer, SampleVO> map = new HashMap<>();
		for(int i=0; i<20; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}
	
	/*
	 * ResponseEntity 타입
	 */
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendlistAuth(){
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		
		List<SampleVO> list = new ArrayList();
		for(int i=0; i<10; i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
}
