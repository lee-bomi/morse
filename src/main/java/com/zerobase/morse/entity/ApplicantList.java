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
public class ApplicantList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyNo;

    @ManyToOne
    @JoinColumn(name = "stduyNo")
    private Study studyNo;

    private String applyStatus;
    private LocalDateTime applyDt;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    @ManyToOne
    private ChatRoom chatRoom;


}
