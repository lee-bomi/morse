package com.zerobase.morse.entity;

import javax.persistence.*;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyNo;
    private String category;
    private String subCategory;
    private String keyword;

    @ManyToOne
    private Study study;
}
