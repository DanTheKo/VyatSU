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
    public Post(String post_text, LocalDateTime created_at) {
        this.post_text = post_text;
        this.created_at = created_at;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "post_id")
    private long post_id;

    @Getter
    @Column(name  = "post_text")
    private String post_text;

    @Getter
    @Column(name  = "created_at")
    private LocalDateTime created_at;

    @Column(name = "views")
    private Integer views;

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

}
