package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participantNo;
    private int roomId;
    private String email;
    private LocalDateTime participantDt;
    private boolean chatStatus; //

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChatRoom chatRoom;
}
