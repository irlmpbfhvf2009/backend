package com.lwdevelop.backend.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //private String name;
    private String email;
    private String password;
    
    //@CreatedDate
    //private Date createTime;
    //@LastModifiedDate
    //private Date updateTime;
}
