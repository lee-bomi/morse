package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_no")
    private int studyNo;
    private String email;
    private String title;
    private String purpose;
    private String description;
    private boolean status;
    @Column(name = "cancel_status")
    private boolean cancelStatus;
    @Column(name = "write_dt")
    private LocalDateTime writeDt;
    @Column(name = "edit_dt")
    private LocalDateTime editDt;
    @Column(name = "due_dt")
    private LocalDateTime dueDt;
    @Column(name = "applicant_nums")
    private int applicantNums;
    @Column(name = "num_people")
    private int numPeople;
    private String category;

    @ManyToOne
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "study")
    private List<Keyword> keyword;

    @OneToMany(mappedBy = "chatRoom")
    private List<ParticipantList> participantList;

    @ManyToOne
    private Member member;
}
