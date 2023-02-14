package com.zerobase.morse.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private int studyNo;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipant> chatParticipant;

    @ManyToOne
    private ChatContent chatContent;

    @OneToMany(mappedBy = "chatRoom")
    private List<ParticipantList> participantList;
}
