package com.zerobase.morse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ChatContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_no")
    private int chatNo;
    @Column(name = "room_id")
    private int roomId;
    private String email;
    private String content;
    @Column(name = "write_dt")
    private LocalDateTime writeDt;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "chatContent")
    private List<ChatRoom> chatRoom;
}
