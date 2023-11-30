package org.example.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name  = "profile")
public class Profile {
    public Profile() {
    }
    public Profile(ProfileStatus profile_status, long user_id) {
        this.profile_status = profile_status;
        this.user_id = user_id;
    }
    @Transient
    public long user_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "profile_id")
    private long profile_id;
    @Enumerated(EnumType.STRING)
    @Column(name  = "profile_status")
    private ProfileStatus profile_status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Post> posts;

    public void setProfile_status(ProfileStatus profile_status) {
        this.profile_status = profile_status;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public long getProfile_id() {
        return profile_id;
    }

    public ProfileStatus getProfile_status() {
        return profile_status;
    }

    public User getUser() {
        return user;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
