package model;

import java.util.Date;

public class LoanTxnDetails {

	private String itemName;
	private String category;
	private Date issueOn;
	private String borrower;
	private String issuedBy;
	private int loanId;     //added new 
	private String itemId; //added new
	private String itemStatus; //added new
	
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public LoanTxnDetails(){}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getIssueOn() {
		return issueOn;
	}

	public void setIssueOn(Date issueOn) {
		this.issueOn = issueOn;
	}

	//new
	public String getItemId() {
		return itemId;
	}

	//new
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	//new 
	public int getLoanId() {
		return loanId;
	}

	//new
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	//new
	
	@Override
	public String toString() {
		return "LoanTxnDetails [itemName=" + itemName + ", category=" + category + ", issueOn=" + issueOn
				+ ", borrower=" + borrower + ", issuedBy=" + issuedBy + ", loanId=" + loanId + ", itemId=" + itemId
				+ ", itemStatus=" + itemStatus + "]";
	}
	
}

