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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String username;

    private String gender; // 性別
    private Integer age; // 年齡
    private String constellation; // 星座

    private Byte[] avatarName; // 大頭貼檔名
    private Byte[] avatarData; // 大頭貼資料


    private Integer height;// 身高
    private Integer weight;// 體重
    private String bodyType;// 體型 平均 苗條 運動 曲線 微胖 豐滿
    private String liveArea; // 居住地區 -- 請選擇您所在的地區 -- 台北 新北 基隆 宜蘭 花蓮 桃園 新竹 苗栗 
                                    //台中 彰化 南投 雲林 嘉義 台南 高雄 屏東 台東 金門 連江 澎湖 香港 澳門
    private String selfIntroduction; // 自我介紹 請寫下您的個性和外型品味 你好 我想要找的是(長期/短期/單次)約會對象 我的興趣是XX 喜歡的食物是XX  快來密我喔
    private String myType; // 請寫下您的理想型還有條件  快來密我 不要害羞
    private String profession; // 職業類別 其他 學生 家管 製造業 服務業 金融保險 軍警 政府機關 教育研究 農林漁牧 建造營造 經商 房地產 資訊 醫療 法律相關 流通零售 交通旅遊 娛樂出版 藝術 待業中
    private String education;// 學歷 大學 高中 專科 研究所 博士
    private String language;// 語言 繁體中文 簡體中文 英文 台語 日文
    private String smokes;// 是否有抽菸 完全不吸菸 偶爾有抽菸習慣 經常抽菸
    private String drinking;// 是否有飲酒習慣 完全不喝酒 只喝社交酒 偶爾小酌 經常喝

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
    
    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> friendId; // 好友名单


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
