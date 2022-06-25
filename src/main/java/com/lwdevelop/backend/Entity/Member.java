package com.lwdevelop.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    //private String name;
    private String email;
    private String password;
    
    //@CreatedDate
    //private Date createTime;
    //@LastModifiedDate
    //private Date updateTime;
}
