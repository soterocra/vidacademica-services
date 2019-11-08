package online.vidacademica.services.entities;

import online.vidacademica.services.entities.enums.PostType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Long postFather;
    private Instant date;
    private Integer postType;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Post() {
    }

    public Post(Long id, String body, Long postFather, Instant date, PostType postType, User author) {
        super();
        this.id = id;
        this.body = body;
        this.postFather = postFather;
        this.date = date;
        setPostType(postType);
        this.author = author;
    }

    public Post(String body, Instant date, User author) {
        this.id = null;
        this.body = body;
        this.postFather = null;
        this.date = date;
        setPostType(PostType.POST);
        this.author = author;
    }

    public Post(String body, Long postFather, Instant date, User author) {
        this.id = null;
        this.body = body;
        this.postFather = postFather;
        this.date = date;
        setPostType(PostType.COMMENT);
        this.author = author;
    }

    public Post(Long id, String body, Instant date) {
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

    public PostType getPostType() {
        return PostType.valueOf(postType);
    }

    public void setPostType(PostType postType) {
        this.postType = postType.getCode();
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Post other = (Post) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
