package com.zerobase.morse.entity;

import jakarta.persistence.*;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studyNo;
    private String category;
    private String subCategory;
    private String keyword;

    @ManyToOne
    private Study study;
}
