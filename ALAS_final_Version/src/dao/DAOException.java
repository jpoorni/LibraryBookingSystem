package dao;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
	}

	public DAOException(String s) {
		super(s);
	}

	public DAOException(Exception e) {
		super(e);
	}
}
