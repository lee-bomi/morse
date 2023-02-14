package com.zerobase.morse.entity;

import javax.persistence.*;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_no")
    private int studyNo;
    private String category;
    @Column(name = "sub_category")
    private String subCategory;
    private String keyword;

    @ManyToOne
    private Study study;
}
