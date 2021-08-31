package com.soul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "article")
@IdClass(ArticlePrimaryKey.class)
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

//    @Id
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    @Id
    @Column(name = "article_id", nullable = false)
    private Integer articleId;

    @Column(name = "article_name")
    private String articleName;




}
