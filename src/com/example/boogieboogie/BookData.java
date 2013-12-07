package com.example.boogieboogie;

public class BookData {
	private String title = "";
	private String isbn = "";
	private String image = "";
	private String author = "";
	private String publisher = "";
	private String pubdate = "";
	private String description = "";
	private String memo = "";
	private String date = "";
	
	public BookData() {
		// TODO Auto-generated constructor stub
	}
	
	public BookData(String title, String bs, String author, String memo) {
		this.title = title;
		this.image = bs;
		this.author = author;
		this.memo = memo;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public String getPubdate() {
		return pubdate;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
