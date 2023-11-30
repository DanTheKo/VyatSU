package org.example;

import org.example.Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import java.util.List;

public class DataAccess {

    public Configuration configuration;
    public SessionFactory sessionFactory;
    public DataAccess(Configuration configuration){
        this.configuration = configuration;
        sessionFactory = configuration.buildSessionFactory();
    }
    public void CreateTables(Class<?>... entities) {
        for (Class<?> entity : entities) {
            if (entity.isAnnotationPresent(Entity.class)) {
                configuration.addAnnotatedClass(entity);
            }
        }
        configuration.configure();
    }
    public void AddUsers(User ... users){
        try(Session session = sessionFactory.openSession()) {
            for (User user: users){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            }
            session.close();
        }
    }
    public void AddProfiles(Profile ... profiles){
        try(Session session = sessionFactory.openSession()) {
            for (Profile profile: profiles){
                session.beginTransaction();
                User user = session.find(User.class, profile.user_id);
                profile.setUser(user);
                session.persist(profile);
                session.getTransaction().commit();
            }
            session.close();
        }
    }
    public void AddMessages(Message... messages) {
        try(Session session = sessionFactory.openSession()) {
            for (Message message: messages){
                session.beginTransaction();
                User user_target = session.find(User.class, message.target_user_id);
                message.setTarget_user(user_target);
                User user_source = session.find(User.class, message.source_user_id);
                message.setSource_user(user_source);
                session.persist(message);
                session.getTransaction().commit();
            }
            session.close();
        }
    }
    public void AddPosts(Post... posts) {
        try(Session session = sessionFactory.openSession()) {
            for (Post post: posts){
                session.beginTransaction();
                Profile profile = session.find(Profile.class, post.profile_id);
                post.setProfile(profile);
                session.persist(post);
                session.getTransaction().commit();
            }
            session.close();
        }
    }

    public void DeleteUser(long user_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, user_id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        }
    }
    public void ChangeUserName(long user_id, String new_name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, user_id);
            user.setFirstname(new_name);
            session.update(user);
            session.getTransaction().commit();
            session.close();
        }
    }
    public void ChangePostText(long post_id, String new_text){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Post post = session.find(Post.class, post_id);
            post.setPost_text(new_text);
            session.update(post);
            session.getTransaction().commit();
            session.close();
        }
    }
    public User[] GetUsers(){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User ORDER BY user_id", User.class).getResultList();
            for (User user: users){
                System.out.println(user.getUser_id() + " | " + user.getFirstname()+ " | " + user.getLastname() + " | "  + user.getPassword());
            }
            return users.toArray(new User[0]);
        }
    }
    public void GetUserByPostID(long post_id){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Post post = session.get(Post.class, post_id);
            User user = post.getProfile().getUser();
            System.out.println(user.getUser_id() + " | " + user.getFirstname()+ " | " + user.getLastname() + " | "  + user.getPassword());
            session.close();
        }
    }

    public void GetMessagesByText(String text){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Message> messages = session.createQuery("FROM Message", Message.class).getResultList();
            for (Message m: messages){
                if(m.getMessage_text().contains(text)) {
                    System.out.println(m.toString());
                }
            }
            session.close();
        }
    }

    public void GetMessagesByUserID(long user_id){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user  = session.get(User.class, user_id);
            System.out.println(user.toString());
            session.close();
        }
    }
}
