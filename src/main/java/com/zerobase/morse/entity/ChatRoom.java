package com.zerobase.morse.entity;

import javax.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @ManyToOne
    @JoinColumn(name = "studyNo")
    private Study studyNo;
    //private int studyNo;

    private String roomType;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipant> chatParticipant;


    //@ManyToOne
    //private ChatContent chatContent;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatContent> chatContents;

    @OneToMany(mappedBy = "chatRoom")
    private List<ApplicantList> participantList;
}
