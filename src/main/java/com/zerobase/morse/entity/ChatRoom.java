package com.zerobase.morse.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "study_no")
    private int studyNo;

    @OneToMany(mappedBy = "chatRoom")
    @Column(name = "chat_participant")
    private List<ChatParticipant> chatParticipant;

    @ManyToOne
    private ChatContent chatContent;

    @OneToMany(mappedBy = "chatRoom")
    private List<ParticipantList> participantList;
}
