package com.prototype.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Authority")
public class Authority {
//    @Id
//    @Column(name = "auth_id")
//    private Long id;

    @Column(insertable=false, updatable=false)
    private String username;

    @Column(name = "authority")
    private String role;

    @Id
    @ManyToOne
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name = "username"))
    private User user;
}