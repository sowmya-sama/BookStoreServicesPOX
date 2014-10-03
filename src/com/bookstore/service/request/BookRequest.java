package com.bookstore.service.request;

public class BookRequest {
	public enum SearchBy {ISBN, Title, Author}
	 private String key;
	 private SearchBy searchBy;
	 
	 
	 public String getKey(){
		 return this.key;
	 }
	 public void setKey(String key){
		 this.key = key;
	 }
	 
	 public SearchBy getSearchBy(){
		 return this.searchBy;
	 }
	 public void setSearchBy(SearchBy searchBy){
		 this.searchBy = searchBy;
	 }
}
