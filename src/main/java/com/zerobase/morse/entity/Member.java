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
    private LocalDateTime regDt;
    private boolean emailYn;
    private String emailAuthKey;
    private boolean status;
    private  int reportCount;
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
