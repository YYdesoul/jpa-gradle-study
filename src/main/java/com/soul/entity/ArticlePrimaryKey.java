package com.soul.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticlePrimaryKey implements Serializable {

//    private User user;
    private Integer userId;
    private Integer articleId;
}
