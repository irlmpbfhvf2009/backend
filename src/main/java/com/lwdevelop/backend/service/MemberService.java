package com.lwdevelop.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.repository.MemberRepository;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.utils.CommUtils;
import com.lwdevelop.backend.utils.JwtUtils;
import com.lwdevelop.backend.utils.RedisUtils;
import com.lwdevelop.backend.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @Autowired
    RedisUtils redisUtils;

    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findByPassword(String password) {
        return memberRepository.findByPassword(password);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member saveFlush(Member member) {
        return memberRepository.saveAndFlush(member);
    }

    public ResponseEntity<String> register(HttpServletRequest request, MemberVO memberVO) {
        String email = memberVO.getEmail();
        String password = memberVO.getPassword();
        String username = memberVO.getUsername();

        if (!StringUtils.hasText(email)) {
            log.info("MemberService ==> register ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
        if (!StringUtils.hasText(password)) {
            log.info("MemberService ==> register ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
        if (!StringUtils.hasText(username)) {
            log.info("MemberService ==> register ........... [ {} ]", "?????????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("?????????????????????");
        }
        if (password.length() < 7) {
            log.info("MemberService ==> register ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
        if (!email.contains("@") || !email.contains(".com")) {
            log.info("MemberService ==> register ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }

        log.info("MemberService ==> register ... ?????????????????????????????? [ {} ]", email);
        Member userEmail = findByEmail(email);
        Member userName = findByUsername(username);

        if (userEmail != null) {
            log.info("MemberService ==> register ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }

        if (userName != null) {
            log.info("MemberService ==> register ........... [ {} ] {}", username, "?????????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("?????????????????????");
        }

        try {
            log.info("MemberService ==> register ... {}", "???????????????");
            Member member = new Member();
            List<String> roles = Arrays.asList(new String[] { "USER" });
            member.setEmail(email);
            member.setPassword(password);
            member.setUsername(username);
            member.setRoles(roles);
            member.setPlatform(CommUtils.getClientDevice(request));
            member.setEnable(true);
            member.setGender(memberVO.getGender());
            member.setConstellation(memberVO.getConstellation());
            member.setAge(memberVO.getAge());
            member.setRegIp(CommUtils.getClientIP(request));
            member.setLastLoginIP(CommUtils.getClientIP(request));
            saveFlush(member);
            log.info("MemberService ==> register ... {}", "????????????");
            return ResponseEntity.status(HttpStatus.OK).body("????????????");

        } catch (Exception e) {
            log.info("MemberService ==> register ... ???????????? Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("????????????");
        }

    }

    /*
     * ????????????
     */
    public ResponseEntity<String> memberLogin(HttpServletRequest request, MemberVO memberVO) {

        if (!StringUtils.hasText(memberVO.getEmail())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "???????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("???????????????");
        }
        if (!StringUtils.hasText(memberVO.getPassword())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "???????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("???????????????");
        }

        Member member = findByEmail(memberVO.getEmail());

        if (member == null) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "???????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("???????????????");
        }
        if (!member.getEnable()) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
        if (!member.getPassword().equals(memberVO.getPassword())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "????????????");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("????????????");
        }

        try {

            String userAgent = request.getHeader("User-Agent");

            if (userAgent != null && userAgent.length() > 255) {
                userAgent.substring(0, 255);
            }

            member.setLastLoginIP(CommUtils.getClientIP(request));
            member.setPlatform(CommUtils.getClientDevice(request));
            save(member);

            log.info("MemberService ==> memberLogin ........... [ {} ]", "????????????");

            JwtUtils jwtToken = new JwtUtils();
            String token = jwtToken.generateToken(member); // ??????token

            return ResponseEntity.status(HttpStatus.OK).body(token);

        } catch (Exception e) {
            log.info("Member237Service ==> memberLogin Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
    }

    /*
     * ????????????
     */
    public ResponseEntity<Member> memberInfo(MemberVO memberVO) {
        JwtUtils token = new JwtUtils();
        String email = token.verifyToken(memberVO.getToken());
        Member member = findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    /*
     * ????????????(?????????)
     */
    /* public ResponseEntity<List<String>> searchFriend(SearchFriendVO searchFriendVO) {
        List<Member> memberList = findByUsername(searchFriendVO.getUsername());
        List<String> usernameList = new ArrayList<>();
        for (Member list : memberList) {
            usernameList.add(memberList.get(memberList.indexOf(list)).getUsername());
        }
        try {
            log.info("MemberService ==> searchFriend ........... [ {} ]", searchFriendVO.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(usernameList);
        } catch (Exception e) {
            log.info("Member237Service ==> searchFriend Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usernameList);
        }
    } */

    /*
     * ????????????
     */
    public ResponseEntity<String> addFriend(MemberVO memberVO) {
        try {
            Member member = findByEmail(memberVO.getLoginEmail());
            Member friend = findByEmail(memberVO.getEmail());

            if (member == null) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "????????????");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("????????????");
            }
            if (friend == null) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "???????????????");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("???????????????");
            }

            String memberEmail = member.getEmail();
            String friendEmail = friend.getEmail();
            String friendId = String.valueOf(friend.getId());
            if (memberEmail.equals(friendEmail)) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "???????????????????????????");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("???????????????????????????");
            }
            List<String> memberList = member.getFriendId();
            if (memberList == null) {
                memberList = new ArrayList<>();
            }
            if (memberList.contains(friendId)) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "?????????????????????");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("?????????????????????");
            }

            redisUtils.addFriend(memberEmail, friendId, friend.getUsername());
            memberList.add(friendId);
            member.setFriendId(memberList);
            save(member);

            log.info("MemberService ==> addFriend ........... [ {} ]", "??????????????????");
            return ResponseEntity.status(HttpStatus.OK).body("??????????????????");
        } catch (Exception e) {
            log.info("Member237Service ==> addFriend Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("??????????????????");
        }
    }

    /*
     * ????????????
     */
    public ResponseEntity<List<Map<String, String>>> myFriend(MemberVO memberVO) {
        /*
         * Member member = findByEmail(memberVO.getLoginEmail());
         * List<String> memberList = member.getFriendId();
         * List<Map<String,String>> myList=new ArrayList<>();
         * for (String m : memberList) {
         * Map<String, String> map = new LinkedHashMap<String,String>();
         * Optional<Member> friend =
         * findById(Integer.parseInt(memberList.get(memberList.indexOf(m))));
         * map.put("id", String.valueOf(friend.get().getId()));
         * map.put("username", friend.get().getUsername());
         * myList.add(map);
         * }
         */

        List<Map<String, String>> myList = new ArrayList<>();
        String friends = redisUtils.getFriend(memberVO.getLoginEmail());
        if (friends != null) {
            String[] friend = friends.split(",");
            for (int i = 0; i < friend.length; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                map.put("id", friend[i].split(":")[0]);
                map.put("username", friend[i].split(":")[1]);
                myList.add(map);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(myList);

    }

}
