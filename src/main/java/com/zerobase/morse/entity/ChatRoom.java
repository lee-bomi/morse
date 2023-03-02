package com.zerobase.morse.entity;

import javax.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Study study;
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipant> chatParticipant;

    @ManyToOne
    private ChatContent chatContent;

    @OneToMany(mappedBy = "chatRoom")
    private List<ParticipantList> participantList;
}
