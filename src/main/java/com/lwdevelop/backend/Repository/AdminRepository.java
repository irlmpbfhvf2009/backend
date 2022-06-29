package com.lwdevelop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lwdevelop.backend.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
    public Admin findByEmail(String member);
    public Admin findByPassword(String password);
    public Admin findByUsername(String username);
    public void deleteByEmail(String email);
    public void deleteByUsername(String Username);
}
