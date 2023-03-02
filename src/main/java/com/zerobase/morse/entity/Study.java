package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studyNo;
    //private String email;
    private String title;
    private String purpose;
    private String description;
    private boolean status;
    private boolean cancelStatus;
    private LocalDateTime writeDt;
    private LocalDateTime editDt;
    private LocalDateTime dueDt;
    private int applicantNums;
    private int numPeople;
    private String category;

    //@ManyToOne
    //private ChatRoom chatRoom;

    @OneToMany(mappedBy = "study")
    private List<Keyword> keyword;

    @OneToMany(mappedBy = "chatRoom")
    private List<ApplicantList> participantList;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;
}
