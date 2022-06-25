package com.lwdevelop.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lwdevelop.backend.Repository.MemberRepository;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BackendApplicationTests {
	
    @Autowired
    MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

}
