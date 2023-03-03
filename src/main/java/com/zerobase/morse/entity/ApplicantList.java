package com.zerobase.morse.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ApplicantList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyNo;

    @ManyToOne
    @JoinColumn(name = "studyNo")
    private Study studyNo;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;

    private String applyStatus;
    private LocalDateTime applyDt;

}
