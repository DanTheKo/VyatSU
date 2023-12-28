package com.prototype.controllers;

import com.prototype.entities.Post;
import com.prototype.services.PostService;
import org.springframework.cglib.core.Local;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostService postService;
    String text;
    LocalDate start;
    LocalDate end;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public String showPostsList(Model model) {
        Post post = new Post();
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("post", post);
                text = null;
        start = null;
        end = null;
        return "posts";
    }
    @PostMapping("/add")
    public String addPost(@ModelAttribute(value = "post")Post post) {
        postService.add(post);
        String str = "redirect:/posts/filter?text=";
        if(text!= null){
            str += text;
        }
        str += "&start=";
        if(start != null){
            str +=  start.toString();
        }
        str += "&end=";
        if(end != null){
            str += end.toString();
        }
        return str;
    }
    @GetMapping("/show/{id}")
    public String showOnePost(Model model, @PathVariable(value = "id") Long id) {

        Post post = postService.getById(id);
        model.addAttribute("post", post);
        return "post-info";
    }
    @GetMapping("/delete/{id}")
    public String deletePost(Model model, @PathVariable(value = "id") Long id) {
        Post post = postService.getById(id);
//        text = null;
//        start = null;
//        end = null;
        postService.delete(post);
        return "redirect:/posts";
    }
    @GetMapping("/add")
    public String test(Model model) {
        Post post = new Post();
        Post.id_++;
        post.setPost_id(Post.id_);
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("post", post);
        return "add";
    }
    @GetMapping("/filter")
    public String filterPost(Model model,
                             @RequestParam(value = "text", required = false)String text,
//                             @RequestParam(value = "user", required = false) String username,
//                             @RequestParam(value = "likes", required = false) Long likesCount,
                             @RequestParam(value = "start", required = false) LocalDate startDate,
                             @RequestParam(value = "end", required = false) LocalDate endDate) throws ParseException {
        Post post = new Post();
        if(text!= null){
            this.text = text;
        }
        if(startDate!= null){
            this.start = startDate;
        }
        if(endDate != null){
            this.end = endDate;
        }
        model.addAttribute("posts", postService.getAllPosts(text, startDate, endDate));
        model.addAttribute("post", post);
        model.addAttribute("text", text);
        model.addAttribute("start", startDate);
        model.addAttribute("end", endDate);
//        model.addAttribute("likes", likesCount);
//        model.addAttribute("user", username);

        return "posts";
    }
    @GetMapping("/edit/{id}")
    public String editPost(Model model, @PathVariable(value = "id") Long id) {
        Post post = postService.getById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/edit/update")
    public String updatePost(@ModelAttribute(value = "post") Post updated) {
        Post post = postService.getById(updated.getPost_id());
        postService.update(post.getPost_id(), updated);
        String str = "redirect:/posts/filter?text=";
        if(text!= null){
            str += text;
        }
        str += "&start=";
        if(start != null){
            str +=  start.toString();
        }
        str += "&end=";
        if(end != null){
            str += end.toString();
        }
        return str;
//        return "redirect:/posts";
    }
}