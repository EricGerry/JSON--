package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//lombok包的注解
@Setter
@Getter
@ToString
public class User {

    private Integer id;
    private String name;
    //java.util.Date
    private Date createTime;
}
