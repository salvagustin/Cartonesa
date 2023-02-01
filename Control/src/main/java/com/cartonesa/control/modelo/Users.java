package com.cartonesa.control.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Users {

	//CAMPOS, SETTER Y GETTER DE LA TABLA USUARIOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "USER_NAME")
	private String username;
	private String password;
	private boolean enabled;
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_ID")
	private Authority authority;

	public Users() {

	}

	public Users(int id, String username, String password, boolean enabled, Authority authority) {
		super();
		
		 this.id = id;
		 this.username = username;
		 this.password = password;
		 this.enabled = enabled;
		 this.authority = authority;
		 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}