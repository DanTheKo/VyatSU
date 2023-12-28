package com.prototype.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column
    private String password;

    @OneToMany()
    @JoinColumn(name = "post_id")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Authority> authorityList;
}

//http://localhost:8189/demo/posts