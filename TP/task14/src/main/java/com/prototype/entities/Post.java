package com.prototype.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Post")
public class Post {

    @Transient
    static public Long id_ = 0L;
    public Post( String post_text, LocalDateTime created_at, User user) {

        this.post_text = post_text;
        this.created_at = created_at;
        this.user = user;
        post_id = ++id_;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "post_id")
    private Long post_id;

    @Getter
    @Column(name  = "post_text")
    private String post_text;

    @Getter
    @Column(name  = "created_at")
    private LocalDateTime created_at;

//    @Getter
//    @Column(name  = "likes")
//    private Long likes;

    @ManyToOne
    @JoinColumn(name = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Long getPost_id() {
        return post_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public User getUser() {
        return user;
    }
}
