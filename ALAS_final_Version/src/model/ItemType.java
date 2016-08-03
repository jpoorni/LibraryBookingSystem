package model;

import java.io.Serializable;

public class ItemType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeID;
	private String typeName;

	public ItemType(int typeID, String typeName) {
		super();
		this.typeID = typeID;
		this.typeName = typeName;
	}

	public ItemType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "ItemType [typeID=" + typeID + ", typeName=" + typeName + "]";
	}

}
