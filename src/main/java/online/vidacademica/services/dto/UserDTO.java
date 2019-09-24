package online.vidacademica.services.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import online.vidacademica.services.entities.Phone;
import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.User;

public class UserDTO {

	private Long id;
    private String name;
    private String email;
    private Instant dateOfBirth;
    private String socialId;
    private String registration;
    
    private List<Post> posts = new ArrayList<>();
    private Set<Phone> phones = new HashSet<>();
    
    public UserDTO () {}

	public UserDTO(Long id, String name, String email, Instant dateOfBirth, String socialId, String registration) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.socialId = socialId;
		this.registration = registration;
	}
    
    public UserDTO(User entity) {
    	this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.dateOfBirth = entity.getDateOfBirth();
		this.socialId = entity.getSocialId();
		this.registration = entity.getRegistration();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
    
	public User toEntity(){
		return new User(null, name, email, dateOfBirth, socialId, registration, null, null);
	}
    
}
