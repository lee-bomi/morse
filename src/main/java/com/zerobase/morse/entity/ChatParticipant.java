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
    private Long participantNo;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    private LocalDateTime participantDt;

    private boolean chatStatus;

}
