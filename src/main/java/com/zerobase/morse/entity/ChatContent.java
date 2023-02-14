package com.zerobase.morse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ChatContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatNo;
    private int roomId;
    private String email;
    private String content;
    private LocalDateTime writeDt;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "chatContent")
    private List<ChatRoom> chatRoom;
}
