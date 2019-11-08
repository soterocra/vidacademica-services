package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.Test;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.entities.enums.PostType;

import java.io.Serializable;
import java.time.Instant;

public class InsertPostDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String body;
    private Instant date;


    public InsertPostDTO(){}

    private InsertPostDTO(Long id, String body, Instant date) {
        super();
        this.id = id;
        this.body = body;
        this.date = date;
    }

    public InsertPostDTO(Post entity){
        this.id = entity.getId();
        this.body = entity.getBody();
        this.date = entity.getDate();


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }




    public Post toEntity() {
        return new Post(id,body,date);
    }
}
