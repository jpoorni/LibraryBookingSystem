package model;

import java.util.Date;

public class Items {
	
	private String itemId;
	private int typeId;
	private int isbn;
	private String	status;
	private int	locId;
	private Date publishedYear;
	private String itemName;
	private String author;
	private String publisher;
	
	
	
	
	
	@Override
	public String toString() {
		return "Items [itemId=" + getItemId() + ", typeId=" + getTypeId() + ", isbn="
				+ getIsbn() + ", status=" + getStatus() + ", locId=" + getLocId()
				+ ", publishedYear=" + getPublishedYear() + ", itemName=" + getItemName()
				+ ", author=" + getAuthor() + ", publisher=" + getPublisher()
				+  "]";
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public Date getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(Date publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	
}
