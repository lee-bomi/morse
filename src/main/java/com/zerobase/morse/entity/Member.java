package com.zerobase.morse.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "MEMBER")
public class Member {

    @Id
    private String email;
    private String password;
    private String name;
    private String nickname;
    private char gender;
    private String birth;
    private String address;
    private String phone;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;
    @Column(name = "email_yn")
    private boolean emailYn;
    @Column(name = "email_auth_key")
    private String emailAuthKey;
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
