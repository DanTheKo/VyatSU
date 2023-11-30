package org.example.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name  = "post")
public class Post {

    public Post() {
    }

    public Post(String post_text, Timestamp created_at, long profile_id) {
        this.post_text = post_text;
        this.created_at = created_at;
        this.profile_id = profile_id;
    }
    @Transient
    public long profile_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "post_id")
    private long post_id;
    @Column(name  = "post_text")
    private String post_text;
    @Column(name  = "created_at")
    private Timestamp created_at;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getPost_id() {
        return post_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
