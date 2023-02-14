package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_no")
    private int participantNo;
    @Column(name = "room_id")
    private int roomId;
    private String email;
    @Column(name = "participant_dt")
    private LocalDateTime participantDt;
    @Column(name = "chat_status")
    private boolean chatStatus; //

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChatRoom chatRoom;
}
