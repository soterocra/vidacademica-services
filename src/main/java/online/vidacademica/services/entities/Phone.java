package online.vidacademica.services.entities;

import java.io.Serializable;

public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String ddi;
	private String ddd;
	private String number;
	
	public Phone() {
	}

	public Phone(int id, String ddi, String ddd, String number) {
		super();
		this.id = id;
		this.ddi = ddi;
		this.ddd = ddd;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
