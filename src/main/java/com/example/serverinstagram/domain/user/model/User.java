package com.example.serverinstagram.domain.user.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name= "name")
    private String name;

    @Column(name = "username" , nullable = false, unique = true)
    private String username;


    @Column(name = "password")
    private String password;


    @Column(name ="image_path")
    private String imagePath;


    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    @JsonManagedReference
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;



}