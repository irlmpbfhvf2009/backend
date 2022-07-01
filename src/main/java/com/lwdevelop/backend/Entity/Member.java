package com.lwdevelop.backend.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.lwdevelop.backend.converter.StringListConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member implements UserDetails {
//public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String username;

    private String platform; // 行動裝置
    private Boolean enable; // 是否啟用
    private String regIp; // 註冊IP

    @Column(length = 255)
    private String lastLoginIP;

    @Column(nullable = false, length = 30)
    @Convert(converter = StringListConverter.class)
    private List<String> roles;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList()); 
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

}
