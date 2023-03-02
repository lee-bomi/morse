package com.zerobase.morse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyUpdate {

	Long studyNo;
	String title;
	String category;	//대분류
	String subCategory;	//중분류
	String keyword; //키워드(소분류)
	String description;
	String dueDt;
	int numPeople;

}
