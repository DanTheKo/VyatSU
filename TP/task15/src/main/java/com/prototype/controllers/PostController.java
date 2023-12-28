package com.prototype.controllers;

import com.prototype.entities.Post;
import com.prototype.services.PostService;
import com.prototype.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private UserService userService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    String text;
    LocalDate start;
    LocalDate end;
//    @GetMapping
//    public String showPostsList(Model model) {
//        Post post = new Post();
//        model.addAttribute("posts", postService.getAllPosts());
//        model.addAttribute("post", post);
//        return "posts";
//    }

    @GetMapping("")
    public String showPostsList(Principal principal, Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        if(principal != null){
            Page<Post> postPage = postService.getAllPostsUser(pageable, userService.getUserByUserName(principal.getName()));
            model.addAttribute("posts", postPage.getContent());
            model.addAttribute("post", new Post());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", postPage.getTotalPages());
            List<Post> topPosts = postService.getTopPosts();
            model.addAttribute("topPosts", topPosts);
            text = null;
            start = null;
            end = null;

        }

        return "posts";
    }
    @PostMapping("/posts/addOrUpdate/add")
    public String addPost(@ModelAttribute(value = "post")Post post,@RequestParam("username") String username){
        post.setViews(0);
        post.setUser(userService.getUserByUserName(username));
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
//        return "redirect:/";
    }
    @GetMapping("/posts/addOrUpdate/add")
    public String getAddPost(Principal principal, Model model) {
        if(principal != null){
            Page<Post> postPage = postService.getAllPostsUser(PageRequest.of(0, 5), userService.getUserByUserName(principal.getName()));
            model.addAttribute("posts", postPage.getContent());
            model.addAttribute("post", new Post());
        }
        return "addOrUpdate";
    }

    @GetMapping("/posts/addOrUpdate/edit/{id}")
    public String editPostGet(Model model, @PathVariable(value = "id") Integer id) {
        Post post = postService.getById(id);
        model.addAttribute("post", post);
        return "addOrUpdate";
    }
    @PostMapping("/posts/addOrUpdate/edit/update")
    public String editPost(@ModelAttribute(value = "post") Post updated) {
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
//        return "redirect:/";
    }

    @GetMapping("/posts/show/{id}")
    public String showOnePost(Model model, @PathVariable(value = "id") Integer id) {
        Post post = postService.getById(id);
        postService.incrementViews(post);
        model.addAttribute("post", post);
        return "post-info";


    }

    @GetMapping("/posts/filter")
    public String filterPost(Principal principal, Model model,
                             @RequestParam(value = "text", required = false)String text,
                             @RequestParam(value = "start", required = false) LocalDate startDate,
                             @RequestParam(value = "end", required = false) LocalDate endDate,
                             @RequestParam(defaultValue = "0") int page) throws ParseException {
        Post post = new Post();
        Pageable pageable = PageRequest.of(page, 5);
        Page<Post> postPage;
        if(principal != null){
            postPage = postService.FilterAndGetAllPosts(text, startDate, endDate, userService.getUserByUserName(principal.getName()),pageable);
            if(text!= null){
                this.text = text;
            }
            if(startDate!= null){
                this.start = startDate;
            }
            if(endDate != null){
                this.end = endDate;
            }

            model.addAttribute("posts", postPage.getContent());
            model.addAttribute("post", post);
            model.addAttribute("text", text);
            model.addAttribute("start", startDate);
            model.addAttribute("end", endDate);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", postPage.getTotalPages());

            List<Post> topPosts = postService.getTopPosts();
            model.addAttribute("topPosts", topPosts);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/posts/filter");
            if (text != null && !text.isEmpty()) uriBuilder.queryParam("text", text);
            if (startDate != null) uriBuilder.queryParam("start", startDate);
            if (endDate != null) uriBuilder.queryParam("end", endDate);
            model.addAttribute("filterUrl", uriBuilder.build().toString());
        }




        return "posts";
    }

    @PostMapping("/authenticateTheUser")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            String storedPassword = userDetails.getPassword();
            if (password.equals(storedPassword)) {
                model.addAttribute("username", username);
                return "redirect:/posts";
            }
        }
        return "posts";
    }
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Integer id) {
        Post post = postService.getById(id);
        postService.delete(post);
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
}