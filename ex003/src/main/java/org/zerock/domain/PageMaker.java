package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 *  1.외부에서 입력되는 데이터 (Criteria에 이미 존재)
 *  - page: 현재 조회하는 페이지의 번호
 *  - perPageNum : 한 페이지당 출력하는 데이터의 개수
 *  
 *  2.DB에서 계산되는 데이터
 *  - totalCount : SQL의 결과로 나온 데이터의 전체 개수
 *  
 *  3.계산을 통해서 만들어지는 데이터
 *  - startpage
 *  - endPage
 *  - prev
 *  - next
 */
public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	// 화면에 보여지는 페이지 번호의 숫자를 의미 하는 변수  값이 10인 경우에 10개의 페이지 번호가 출력되는 것을 알 수있다
	private int displayPageNum = 10; 
	
	private Criteria cri; // page : 현재 조회하는 페이지의 번호 , perPageNum : 한 페이지당 출력하는 데이터의 개수
	
	public void setCri(Criteria cri){
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData(){
		
		endPage  = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum );
		
		startPage  = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public String makeQuery(int page) {

	    UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
	        .queryParam("perPageNum", cri.getPerPageNum()).build();

	    return uriComponents.toUriString();
	  }
	  
	  
	  public String makeSearch(int page){
	    
	    UriComponents uriComponents =
	              UriComponentsBuilder.newInstance()
	              .queryParam("page", page)
	              .queryParam("perPageNum", cri.getPerPageNum())
	              .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	              .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
	              .build();             
	    
	    return uriComponents.toUriString();
	  } 
	
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}
	
	
}
