package com.zerobase.morse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatNo;
    //private int roomId;
    //private String email;
    private String content;
    private LocalDateTime writeDt;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    //@OneToMany(mappedBy = "chatContent")
    //private List<ChatRoom> chatRoom;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
}
