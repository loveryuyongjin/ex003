package org.zerock.domain;

public class Criteria {
	//Criteria  뜻  : 검색기준, 분류기준
	//계산공식 :  시작데이터 번호 = (페이지번호-1) * 페이지 당 보여지는 개수
	
	private int page;
	private int perPageNum;
	
	public Criteria(){
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page){
		if(page <= 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){
		
		if(perPageNum <=0 || perPageNum >100){
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage(){
		return page;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPageStart(){
		return (this.page - 1) * perPageNum;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPerPageNum(){
		return this.perPageNum;
	}
	
	@Override
	public String toString(){
		return "Criteria [page=" + page + ", " + "perPageNum=" + perPageNum + "]";
	}
}
