package online.vidacademica.services.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String token;
	private String role;
	public TokenDTO() {}

	public TokenDTO(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}

	public TokenDTO(String email, String token,String role) {
		super();
		this.email = email;
		this.token = token;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
