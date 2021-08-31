package com.soul.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserArticleDTO {
    private Integer id;

    private String name;

//    private String pwd;

//    private Integer countryId;

    private String articleName;
}
