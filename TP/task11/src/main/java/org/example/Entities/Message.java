package org.example.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name  = "message")
public class Message {
    public Message() {
    }

    public Message(String message_text, Timestamp created_at, long source_user_id, long target_user_id) {
        this.message_text = message_text;
        this.created_at = created_at;
        this.source_user_id = source_user_id;
        this.target_user_id = target_user_id;
    }
    @Transient
    public long source_user_id;
    @Transient
    public long target_user_id;

    @Override
    public String toString() {
        return "'" + message_text + '\'';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "message_id")
    private long message_id;
    @Column(name  = "message_text")
    private String message_text;
    @Column(name  = "created_at")
    private Timestamp created_at;
    @ManyToOne
    @JoinColumn(name = "source_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User source_user;
    @ManyToOne
    @JoinColumn(name = "target_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User target_user;

    public void setSource_user(User source_user) {
        this.source_user = source_user;
    }

    public void setTarget_user(User target_user) {
        this.target_user = target_user;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public long getMessage_id() {
        return message_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public User getSource_user() {
        return source_user;
    }

    public User getTarget_user() {
        return target_user;
    }
}
