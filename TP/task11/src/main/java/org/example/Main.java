package org.example;

import org.example.Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class Main {



    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        DataAccess access = new DataAccess(configuration);
        access.CreateTables(User.class, Message.class, Profile.class, Post.class);

        try (SessionFactory sf = configuration.buildSessionFactory()){
            access.sessionFactory = sf;
            User[] users = {
                    new User("Даниил", "Коковихин", "М","89129456378", "123"),
                    new User("Денис", "Ширшиков", "М","83129656328", "hardpassword"),
                    new User("Павел", "Лутин", "М","89129456378", "1dc3")
            };
            access.AddUsers(users);
            //users = access.GetUsers();
            Profile[] profiles = {
                    new Profile(ProfileStatus.OPEN, 1),
                    new Profile(ProfileStatus.FRIENDS_ONLY, 2),
                    new Profile(ProfileStatus.CLOSED, 3)
            };
            Message[] messages = {
                    new Message("Привет! Как ты?", new Timestamp(124, 1, 1, 13, 23, 15, 0), 1,2),
                    new Message("Здарова, гулять пойдешь?", new Timestamp(124, 1, 1, 13, 23, 15, 0), 3,2),
                    new Message(")))))))?", new Timestamp(124, 1, 1, 13, 23, 15, 0), 2,1),
                    new Message("Привет. У меня все хорошо.", new Timestamp(124, 1, 1, 13, 25, 35, 0),2, 1)
            };
            Post[] posts = {
                    new Post("Ну и погодка",new Timestamp(124, 1, 1, 13, 23, 15, 0), 1),
                    new Post("Отдыхаю",new Timestamp(124, 1, 2, 16, 23, 25, 0), 1),
                    new Post("Пью сок",new Timestamp(124, 1, 5, 17, 27, 10, 0), 1),
                    new Post("Сижу с котом в обнимку",new Timestamp(124, 2, 3, 15, 23, 15, 0), 2)
            };
            access.AddProfiles(profiles);
            access.AddMessages(messages);
            access.AddPosts(posts);

            //access.ChangePostText(2, "Сплю222");
            //access.ChangeUserName(1, "Кто-то111");

            access.GetUserByPostID(4);
            //access.GetUsers();
            access.GetMessagesByText("Привет");
            access.GetMessagesByText("?");

            access.GetMessagesByUserID(2);

            //access.DeleteUser(2);
        }

    }

}