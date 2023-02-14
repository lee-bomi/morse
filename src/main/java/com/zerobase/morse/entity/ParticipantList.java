package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ParticipantList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_no")
    private int applyNo;
    @Column(name = "study_no")
    private int studyNo;
    private String email;
    @Column(name = "apply_status")
    private String applyStatus;
    @Column(name = "apply_dt")
    private LocalDateTime applyDt;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChatRoom chatRoom;
}
