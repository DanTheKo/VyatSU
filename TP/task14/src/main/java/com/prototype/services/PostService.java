package com.prototype.services;

import com.prototype.entities.Post;
import com.prototype.repositories.PostRepository;
import com.prototype.repositories.PostRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepositoryClass repository;

    @Autowired
    public PostService(PostRepositoryClass repository) {
        this.repository = repository;
    }

//    @Autowired
//    public PostService(PostRepository repository) {
//        this.repository = repository;
//    }
    public Post getById(Long id) {
        return repository.findById(id);
    }
    public List<Post> getAllPosts() {
        return repository.findAll();
    }
    public List<Post> getAllPosts(String text, LocalDate startDate, LocalDate endDate) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date sDate;
//        Date eDate;
//        if(!startDate.isBlank() && !endDate.isBlank()){
//            sDate = format.parse(startDate);
//            eDate = format.parse(endDate);
//        } else {
//            eDate = new Date();
//            sDate = new Date();
//        }

        return repository.findAll().stream()
                .filter(a -> text.isBlank()|| a.getPost_text().contains(text))
                .filter(a -> startDate == null || a.getCreated_at().isAfter(startDate.atStartOfDay()))
                .filter(a -> endDate == null || a.getCreated_at().isBefore(endDate.atTime(23, 59, 59)))
//                .filter(a -> likesCount.toString().isBlank() || a.getLikes().toString().contains(likesCount.toString()))
//                .filter(a -> username.isBlank()|| a.getUser().getUsername().contains(username))
                .collect(Collectors.toList());
    }

    public void add(Post post) {
        repository.findAll().add(post);
        //repository.save(post);
    }
    public void delete(Post post) {
        repository.findAll().remove(post);
        //repository.delete(post);
    }

    public Post getPostById(Long id) {
        return repository.findById(id);
    }
    public void update(Long id, Post updated) {
        Post post = getPostById(id);
        if (post != null) {
            post.setPost_text(updated.getPost_text());
//            post.setLikes(updated.getLikes());
//            post.setUser(updated.getUser());
            post.setCreated_at(updated.getCreated_at());
            post = updated;
            //repository.save(post);
        }
    }
}