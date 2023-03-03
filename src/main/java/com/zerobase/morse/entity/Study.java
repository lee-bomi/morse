package com.zerobase.morse.entity;
import com.zerobase.morse.model.StudyInput;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyNo;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member email;
    private String title;
    private String category;
    private String subCategory;
    private String keyword;
    private String description;
    private boolean status;
    private boolean cancelStatus;
    private LocalDateTime writeDt;
    private LocalDateTime editDt;
    private Date dueDt;
    private int applicantNums;
    private int numPeople;

//    @OneToOne
//    private ChatRoom chatRoom;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "keyword_no")
//    private Keyword keyword;

//    @OneToMany(mappedBy = "applyNo")
//    private List<ParticipantList> participantList;
//
}
