package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChatParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participantNo;

    private LocalDateTime participantDt;

    private boolean chatStatus;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;
}
