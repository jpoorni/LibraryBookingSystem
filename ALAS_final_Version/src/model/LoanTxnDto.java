package model;

import java.util.Date;

public class LoanTxnDto {
	
	private int LoanId;
	private String ItemId;
	private String StuLoginId;
	private Date IssueDate;
	private Date ActualReturnDate;
	private int IssuedBy;
	private String itemStatus;
	
	public LoanTxnDto(){
		
		
	}

	
	
	@Override
	public String toString() {
		return "LoanTxnDto [getLoanId()=" + getLoanId() + ", getItemId()="
				+ getItemId() + ", getStuLoginId()=" + getStuLoginId()
				+ ", getIssueDate()=" + getIssueDate()
				+ ", getActualReturnDate()=" + getActualReturnDate()
				+ ", getIssuedBy()=" + getIssuedBy() + ", getitemStatus()="
				+ getitemStatus() + "]";
	}



	public int getLoanId() {
		return LoanId;
	}

	public void setLoanId(int loanId) {
		LoanId = loanId;
	}

	public String getItemId() {
		return ItemId;
	}

	public void setItemId(String itemId) {
		ItemId = itemId;
	}

	public String getStuLoginId() {
		return StuLoginId;
	}

	public void setStuLoginId(String stuLoginId) {
		StuLoginId = stuLoginId;
	}

	public Date getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(Date date) {
		IssueDate =  date;
	}

	public Date getActualReturnDate() {
		return ActualReturnDate;
	}

	public void setActualReturnDate(Date actualReturnDate) {
		ActualReturnDate = actualReturnDate;
	}

	public int getIssuedBy() {
		return IssuedBy;
	}

	public void setIssuedBy(int issuedBy) {
		IssuedBy = issuedBy;
	}

	public String getitemStatus() {
		return itemStatus;
	}

	public void setitemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	
	
}

