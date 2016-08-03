package model;

import java.io.Serializable;
import java.util.Date;

public class itemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemID;
	private int typeID;
	private String itemName;
	private int ISBN;
	private String publisher;
	private Date publishedYear;
	private String author;
	private String status;
	private int locID;

	public itemDTO(String itemID, int typeID, String itemName, int iSBN, String publisher, Date publishedYear,
			String author, String status, int locID) {
		super();
		this.itemID = itemID;
		this.typeID = typeID;
		this.itemName = itemName;
		this.ISBN = iSBN;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.author = author;
		this.status = status;
		this.locID = locID;
	}

	public itemDTO() {
		super();
	}


	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Date publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLocID() {
		return locID;
	}

	public void setLocID(int locID) {
		this.locID = locID;
	}

	@Override
	public String toString() {
		return "itemDTO [itemID=" + itemID + ", typeID=" + typeID + ", itemName=" + itemName + ", ISBN=" + ISBN
				+ ", publisher=" + publisher + ", publishedYear=" + publishedYear + ", author=" + author + ", status="
				+ status + ", locID=" + locID + "]";
	}

}
