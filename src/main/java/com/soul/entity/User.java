package com.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "country_id")
    private int countryId;


//    @OneToMany(targetEntity = Article.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Set<Article> articles;
}
