package com.prototype.repositories;

import com.prototype.entities.Post;
import com.prototype.entities.User;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostRepositoryClass {
    private List<Post> posts;

    @PostConstruct
    public void init() {
        posts = new ArrayList<>();
        posts.add(new Post( "Текст 1", LocalDateTime.now(), new User()));
        posts.add(new Post( "Текст 2", LocalDateTime.now(), new User()));
        posts.add(new Post("Текст 3", LocalDateTime.now(), new User()));
        posts.add(new Post( "Текст 4", LocalDateTime.now(), new User()));
    }


    public List<Post> findAll() {
        return posts;
    }

//    public Post findByTitle(String title) {
//        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
//    }
//
    public Post findById(Long id) {
        return posts.stream().filter(p -> p.getPost_id().equals(id)).findFirst().get();
    }
//
//    public void save(Product product) {
//        products.add(product);
//    }
}
