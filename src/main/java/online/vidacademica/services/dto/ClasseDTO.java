package online.vidacademica.services.dto;

import java.time.Instant;

import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Subject;

public class ClasseDTO {

	private Long id;
	private String name;
	private Instant startDate;
	private Instant endDate;
	private boolean active;
	private Instant creationDate;
    
	private Long subjectId;
    
    public ClasseDTO () {}


    
    public ClasseDTO(Long id, String name, Instant startDate, Instant endDate, boolean active, Instant creationDate,
			Long subjectId) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
		this.creationDate = creationDate;
		this.subjectId = subjectId;
	}



	public ClasseDTO(Classe entity) {
    	this.id = entity.getId();
		this.name = entity.getName();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.active = entity.isActive();
		this.creationDate = entity.getCreationDate();
		this.subjectId = entity.getSubject().getId();
    }

	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Instant getStartDate() {
		return startDate;
	}



	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}



	public Instant getEndDate() {
		return endDate;
	}



	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public Instant getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}






	public Long getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}



	public Classe toEntity(){
		Subject subject = new Subject();
		subject.setId(subjectId);
		return new Classe(null, name, startDate, endDate, active, creationDate, subject);
	}
    
}
