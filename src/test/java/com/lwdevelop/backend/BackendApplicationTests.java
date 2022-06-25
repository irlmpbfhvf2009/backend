package com.lwdevelop.backend;

import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.Repository.MemberRepository;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BackendApplicationTests {
	
    @Autowired
    MemberRepository memberRepository;

	@Test
    public void test1() throws SQLException {
		Member member = new Member();
		member.setEmail("email");
		member.setPassword("password");
		member.setName("name");
		memberRepository.save(member);
    }
	@Test
	void contextLoads() {
	}

}
