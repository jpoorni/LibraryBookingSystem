package model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {

	/**
	 * 
	 */
	// attribute
	private static final long serialVersionUID = 1L;
	private String loginid;
	private String password;
	private int roleid;
	private String name;
	private String email;
	private int phoneno;
	private Date createdon;
	private String status;
	private String roleName;

	// constructor
	public Users(String loginid, String password, int roleid, String name, String email, int phoneno, Date createdon,
			String status) {
		super();
		this.loginid = loginid;
		this.password = password;
		this.roleid = roleid;
		this.name = name;
		this.email = email;
		this.phoneno = phoneno;
		this.createdon = createdon;
		this.status = status;
	}

	public Users() {
		super();
	}

	// getter and setter
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	@Override
	public String toString() {
		return "Users [loginid=" + loginid + ", password=" + password + ", roleid=" + roleid + ", name=" + name
				+ ", email=" + email + ", phoneno=" + phoneno + ", createdon=" + createdon + ", status=" + status
				+ ", roleName=" + roleName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdon == null) ? 0 : createdon.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((loginid == null) ? 0 : loginid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + phoneno;
		result = prime * result + roleid;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (createdon == null) {
			if (other.createdon != null)
				return false;
		} else if (!createdon.equals(other.createdon))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loginid == null) {
			if (other.loginid != null)
				return false;
		} else if (!loginid.equals(other.loginid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneno != other.phoneno)
			return false;
		if (roleid != other.roleid)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
