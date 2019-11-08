package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Post;
import online.vidacademica.services.entities.Test;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.entities.enums.PostType;

import java.io.Serializable;
import java.time.Instant;

public class PostDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String body;
    private Long postFather;
    private Instant date;
    private PostType postType;

    private User author;

    public PostDTO(){}

    private PostDTO(Long id, String body, Long postFather, Instant date, PostType postType, User author) {
        super();
        this.id = id;
        this.body = body;
        this.postFather = postFather;
        this.date = date;
        setPostType(postType);
        this.author = author;
    }

    public PostDTO(Post entity){
        this.id = entity.getId();
        this.body = entity.getBody();
        this.postFather = entity.getPostFather();
        this.date = entity.getDate();
        this.postFather = entity.getPostFather();
        this.author = entity.getAuthor();

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

    public Long getPostFather() {
        return postFather;
    }

    public void setPostFather(Long postFather) {
        this.postFather = postFather;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post toEntity() {
        return new Post(id,body,postFather,date, postType, author);
    }
}
