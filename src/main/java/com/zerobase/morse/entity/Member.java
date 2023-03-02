package com.zerobase.morse.entity;

import java.util.Collection;
import java.util.Collections;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member implements UserDetails {

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
    private List<ApplicantList> participantLists;

    @OneToMany(mappedBy = "member")
    private List<ChatContent> chatContents;

    @OneToMany(mappedBy = "member")
    private List<Study> study;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
