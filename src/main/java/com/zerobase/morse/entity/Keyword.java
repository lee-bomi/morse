package com.zerobase.morse.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyword_no;
    private String category;
    private String subCategory;
    private String keyword;

//    @OneToOne
//    private Study study;
}
