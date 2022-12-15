package com.masai.build;

import java.util.Objects;

public class Admin {
	private String adminName;
	private String adminPassword;
	
	public Admin(String name, String password) {
		super();
		this.adminName = name;
		this.adminPassword = password;
	}

	public String getName() {
		return adminName;
	}

	public void setName(String name) {
		this.adminName = name;
	}

	public String getPassword() {
		return adminPassword;
	}

	public void setPassword(String password) {
		this.adminPassword = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminName, adminPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adminName, other.adminName) && Objects.equals(adminPassword, other.adminPassword);
	}
	

	@Override
	public String toString() {
		return "Admin name=" + adminName + ", password=" + adminPassword;
	}
	
}
