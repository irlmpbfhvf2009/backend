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
import com.lwdevelop.backend.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

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
            log.info("MemberService ==> register ........... [ {} ]", "信箱不可為空");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("信箱不可為空");
        }
        if (!StringUtils.hasText(password)) {
            log.info("MemberService ==> register ........... [ {} ]", "密碼不可為空");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼不可為空");
        }
        if (!StringUtils.hasText(username)) {
            log.info("MemberService ==> register ........... [ {} ]", "用户名不可為空");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用户名不可為空");
        }
        if (password.length() < 7) {
            log.info("MemberService ==> register ........... [ {} ]", "密碼長度不足");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼長度不足");
        }
        if (!email.contains("@") || !email.contains(".com")) {
            log.info("MemberService ==> register ........... [ {} ]", "信箱格式錯誤");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("信箱格式錯誤");
        }

        log.info("MemberService ==> register ... 檢查會員是否已經存在 [ {} ]", email);
        Member userEmail = findByEmail(email);
        Member userName = findByUsername(username);

        if (userEmail != null) {
            log.info("MemberService ==> register ........... [ {} ]", "用戶已經存在");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用戶已經存在");
        }

        if (userName != null) {
            log.info("MemberService ==> register ........... [ {} ] {}", username, "用戶名已經存在");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用戶名已經存在");
        }

        try {
            log.info("MemberService ==> register ... {}", "建立新會員");
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
            log.info("MemberService ==> register ... {}", "註冊成功");
            return ResponseEntity.status(HttpStatus.OK).body("註冊成功");

        } catch (Exception e) {
            log.info("MemberService ==> register ... 註冊失敗 Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("註冊失敗");
        }

    }

    /*
     * 會員登入
     */
    public ResponseEntity<String> memberLogin(HttpServletRequest request, MemberVO memberVO) {

        if (!StringUtils.hasText(memberVO.getEmail())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "未輸入帳號");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("未輸入帳號");
        }
        if (!StringUtils.hasText(memberVO.getPassword())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "未輸入密碼");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("未輸入密碼");
        }

        Member member = findByEmail(memberVO.getEmail());

        if (member == null) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "查無此會員");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("查無此會員");
        }
        if (!member.getEnable()) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "此帳號被停用");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("此帳號被停用");
        }
        if (!member.getPassword().equals(memberVO.getPassword())) {
            log.info("MemberService ==> memberLogin ........... [ {} ]", "密碼錯誤");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼錯誤");
        }

        try {

            String userAgent = request.getHeader("User-Agent");

            if (userAgent != null && userAgent.length() > 255) {
                userAgent.substring(0, 255);
            }

            member.setLastLoginIP(CommUtils.getClientIP(request));
            member.setPlatform(CommUtils.getClientDevice(request));
            save(member);

            log.info("MemberService ==> memberLogin ........... [ {} ]", "登入成功");

            JwtUtils jwtToken = new JwtUtils();
            String token = jwtToken.generateToken(member); // 取得token

            return ResponseEntity.status(HttpStatus.OK).body(token);

        } catch (Exception e) {
            log.info("Member237Service ==> memberLogin Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("會員登入失敗");
        }
    }

    /*
     * 用戶信息
     */
    public ResponseEntity<Member> memberInfo(MemberVO memberVO) {
        JwtUtils token = new JwtUtils();
        String email = token.verifyToken(memberVO.getToken());
        Member member = findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    /*
     * 搜尋好友(未开发)
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
     * 新增好友
     */
    public ResponseEntity<String> addFriend(MemberVO memberVO) {
        try {
            Member member = findByEmail(memberVO.getLoginEmail());
            Member friend = findByEmail(memberVO.getEmail());

            if (member == null) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "請先登入");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("請先登入");
            }
            if (friend == null) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "用戶不存在");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用戶不存在");
            }

            String memberEmail = member.getEmail();
            String friendEmail = friend.getEmail();
            String friendId = String.valueOf(friend.getId());
            if (memberEmail.equals(friendEmail)) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "不能新增自己為好友");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("不能新增自己為好友");
            }
            List<String> memberList = member.getFriendId();
            if (memberList == null) {
                memberList = new ArrayList<>();
            }
            if (memberList.contains(friendId)) {
                log.info("MemberService ==> addFriend ........... [ {} ]", "名單已存在好友");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("名單已存在好友");
            }

            memberList.add(friendId);
            member.setFriendId(memberList);
            save(member);

            log.info("MemberService ==> addFriend ........... [ {} ]", "新增好友成功");
            return ResponseEntity.status(HttpStatus.OK).body("新增好友成功");
        } catch (Exception e) {
            log.info("Member237Service ==> addFriend Exception: {}", e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("新增好友失敗");
        }
    }

    /*
     * 我的好友
     */
    public ResponseEntity<List<Map<String, String>>> myFriend(MemberVO memberVO) {
        
        Member member = findByEmail(memberVO.getLoginEmail());
        List<String> memberList = member.getFriendId();
        List<Map<String,String>> myList=new ArrayList<>();
        for (String m : memberList) {
        Map<String, String> map = new LinkedHashMap<String,String>();
        Optional<Member> friend =
        findById(Integer.parseInt(memberList.get(memberList.indexOf(m))));
        map.put("id", String.valueOf(friend.get().getId()));
        map.put("username", friend.get().getUsername());
        myList.add(map);
        }
        

/*         List<Map<String, String>> myList = new ArrayList<>();
        String friends = redisUtils.getFriend(memberVO.getLoginEmail());
        if (friends != null) {
            String[] friend = friends.split(",");
            for (int i = 0; i < friend.length; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                map.put("id", friend[i].split(":")[0]);
                map.put("username", friend[i].split(":")[1]);
                myList.add(map);
            }
        } */

        return ResponseEntity.status(HttpStatus.OK).body(myList);

    }

}
