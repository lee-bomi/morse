package com.zerobase.morse.entity;

import com.zerobase.morse.model.MemberInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "MEMBER")
public class Member {

    @Id
    private String email;
    private String password;
    private String name;
    private String nickname;
    private char gender;
    private Date birth;
    private String address;
    private String phone;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;
    @Column(name = "email_yn")
    private boolean emailYn;
    private boolean status;
    @Column(name = "report_count")
    private  int reportCount;
    @Column(name = "sns_login")
    private  String snsLogin;

    @OneToMany(mappedBy = "member")
    private List<ChatParticipant> chatParticipant;

    @OneToMany(mappedBy = "member")
    private List<ParticipantList> participantLists;

    @OneToMany(mappedBy = "member")
    private List<ChatContent> chatContents;

    @OneToMany(mappedBy = "member")
    private List<Study> study;

}
