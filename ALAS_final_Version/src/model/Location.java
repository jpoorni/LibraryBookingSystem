package model;

import java.io.Serializable;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int locId;
	private String rackNumber;
	private String shelfId;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(int locId, String rackNumber, String shelfId) {
		super();
		this.locId = locId;
		this.rackNumber = rackNumber;
		this.shelfId = shelfId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getRackNumber() {
		return rackNumber;
	}

	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}

	public String getShelfId() {
		return shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	@Override
	public String toString() {
		return "Location [locId=" + locId + ", rackNumber=" + rackNumber + ", shelfId=" + shelfId + "]";
	}

}
