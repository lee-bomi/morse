package com.zerobase.morse.entity;

import com.zerobase.morse.model.MemberInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    private LocalDateTime regDt;
    private boolean emailYn;
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
