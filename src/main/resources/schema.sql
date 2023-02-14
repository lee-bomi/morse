-- 사용자
DROP TABLE member IF EXISTS;
CREATE TABLE `member` (
	`email`        VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
	`password`     VARCHAR(255) NOT NULL COMMENT '비밀번호', -- 비밀번호
	`name`         VARCHAR(30)  NOT NULL COMMENT '이름', -- 이름
	`nickname`     VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
	`gender`       CHAR(1)      NOT NULL COMMENT '성별', -- 성별
	`birth`        DATE         NOT NULL COMMENT '생년월일', -- 생년월일
	`address`      VARCHAR(255) NOT NULL COMMENT '주소', -- 주소
	`phone`        VARCHAR(15)  NOT NULL COMMENT '휴대전화', -- 휴대전화
	`reg_dt`       DATETIME     NOT NULL COMMENT '가입일', -- 가입일
	`email_yn`     BIT(1)       NOT NULL COMMENT '이메일인증여부', -- 이메일인증여부
	`status`       BIT(1)       NOT NULL COMMENT '회원상태', -- 회원상태
	`report_count` INT          NOT NULL COMMENT '신고누적수', -- 신고누적수
	`sns_login`    VARCHAR(30)  NOT NULL COMMENT '간편로그인' -- 간편로그인
)
COMMENT '사용자';

-- 사용자
ALTER TABLE `member`
	ADD CONSTRAINT `PK_member` -- 사용자 기본키
		PRIMARY KEY (
			`email` -- 이메일
		);

-- 스터디모임
DROP TABLE STUDY IF EXISTS;
CREATE TABLE `STUDY` (
	`study_no`       INT          NOT NULL COMMENT '스터디번호', -- 스터디번호
	`email`          VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
	`title`          VARCHAR(150) NOT NULL COMMENT '타이틀', -- 타이틀
	`purpose`        VARCHAR(30)  NOT NULL COMMENT '목적', -- 목적
	`description`    VARCHAR(255) NOT NULL COMMENT '설명', -- 설명
	`status`         BIT(1)       NOT NULL COMMENT '모집상태', -- 모집상태
	`cancel_status`  BIT(1)       NOT NULL COMMENT '취소상태', -- 취소상태
	`write_dt`       DATETIME     NOT NULL COMMENT '작성일', -- 작성일
	`edit_dt`        DATETIME     NOT NULL COMMENT '수정일', -- 수정일
	`due_dt`         DATETIME     NOT NULL COMMENT '만기일', -- 만기일
	`applicant_nums` INT          NOT NULL COMMENT '신청자수', -- 신청자수
	`num_people`     INT          NOT NULL COMMENT '모집인원수', -- 모집인원수
	`category`       VARCHAR(50)  NOT NULL COMMENT '분류' -- 분류
)
COMMENT '스터디모임';

-- 스터디모임
ALTER TABLE `STUDY`
	ADD CONSTRAINT `PK_STUDY` -- 스터디모임 기본키
		PRIMARY KEY (
			`study_no` -- 스터디번호
		);

-- 신청자명단
DROP TABLE APPLICANT_LIST IF EXISTS;
CREATE TABLE `APPLICANT_LIST` (
	`apply_no`     INT          NOT NULL COMMENT '신청번호', -- 신청번호
	`study_no`     INT          NOT NULL COMMENT '스터디번호', -- 스터디번호
	`email`        VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
	`apply_status` VARCHAR(30)  NOT NULL COMMENT '신청상태', -- 신청상태
	`apply_dt`     DATETIME     NOT NULL COMMENT '신청일' -- 신청일
)
COMMENT '신청자명단';

-- 신청자명단
ALTER TABLE `APPLICANT_LIST`
	ADD CONSTRAINT `PK_APPLICANT_LIST` -- 신청자명단 기본키
		PRIMARY KEY (
			`apply_no` -- 신청번호
		);

-- 키워드
DROP TABLE KEYWORD IF EXISTS;
CREATE TABLE `KEYWORD` (
	`keyword_no`   INT         NOT NULL COMMENT '키워드번호', -- 키워드번호
	`study_no`     INT         NOT NULL COMMENT '스터디번호', -- 스터디번호
	`category`     VARCHAR(50) NOT NULL COMMENT '대분류', -- 대분류
	`sub_category` VARCHAR(50) NOT NULL COMMENT '중분류', -- 중분류
	`keyword`      VARCHAR(50) NOT NULL COMMENT '키워드' -- 키워드
)
COMMENT '키워드';

-- 키워드
ALTER TABLE `KEYWORD`
	ADD CONSTRAINT `PK_KEYWORD` -- 키워드 기본키
		PRIMARY KEY (
			`keyword_no` -- 키워드번호
		);

-- 채팅_참가자
DROP TABLE CHAT_PARTICIPANT IF EXISTS;
CREATE TABLE `CHAT_PARTICIPANT` (
	`participant_no` INT          NOT NULL COMMENT '참여번호', -- 참여번호
	`room_id`        INT          NOT NULL COMMENT '방번호', -- 방번호
	`email`          VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
	`participant_dt` DATETIME     NOT NULL COMMENT '참가일', -- 참가일
	`chat_status`    BIT(1)       NOT NULL COMMENT '참여상태' -- 참여상태
)
COMMENT '채팅_참가자';

-- 채팅_참가자
ALTER TABLE `CHAT_PARTICIPANT`
	ADD CONSTRAINT `PK_CHAT_PARTICIPANT` -- 채팅_참가자 기본키
		PRIMARY KEY (
			`participant_no` -- 참여번호
		);

-- 채팅방
DROP TABLE CHAT_ROOM IF EXISTS;
CREATE TABLE `CHAT_ROOM` (
	`room_id`  INT NOT NULL COMMENT '방번호', -- 방번호
	`study_no` INT NOT NULL COMMENT '스터디번호' -- 스터디번호
)
COMMENT '채팅방';

-- 채팅방
ALTER TABLE `CHAT_ROOM`
	ADD CONSTRAINT `PK_CHAT_ROOM` -- 채팅방 기본키
		PRIMARY KEY (
			`room_id` -- 방번호
		);

-- 채팅내역
DROP TABLE CHAT_CONTENT IF EXISTS;
CREATE TABLE `CHAT_CONTENT` (
	`chat_no`  INT          NOT NULL COMMENT '채팅번호', -- 채팅번호
	`room_id`  INT          NOT NULL COMMENT '방번호', -- 방번호
	`email`    VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
	`content`  VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
	`write_dt` DATETIME     NOT NULL COMMENT '작성일' -- 작성일
)
COMMENT '채팅내역';

-- 채팅내역
ALTER TABLE `CHAT_CONTENT`
	ADD CONSTRAINT `PK_CHAT_CONTENT` -- 채팅내역 기본키
		PRIMARY KEY (
			`chat_no` -- 채팅번호
		);

-- 스터디모임
ALTER TABLE `STUDY`
	ADD CONSTRAINT `FK_member_TO_STUDY` -- 사용자 -> 스터디모임
		FOREIGN KEY (
			`email` -- 이메일
		)
		REFERENCES `member` ( -- 사용자
			`email` -- 이메일
		);

-- 신청자명단
ALTER TABLE `APPLICANT_LIST`
	ADD CONSTRAINT `FK_member_TO_APPLICANT_LIST` -- 사용자 -> 신청자명단
		FOREIGN KEY (
			`email` -- 이메일
		)
		REFERENCES `member` ( -- 사용자
			`email` -- 이메일
		);

-- 신청자명단
ALTER TABLE `APPLICANT_LIST`
	ADD CONSTRAINT `FK_STUDY_TO_APPLICANT_LIST` -- 스터디모임 -> 신청자명단
		FOREIGN KEY (
			`study_no` -- 스터디번호
		)
		REFERENCES `STUDY` ( -- 스터디모임
			`study_no` -- 스터디번호
		);

-- 키워드
ALTER TABLE `KEYWORD`
	ADD CONSTRAINT `FK_STUDY_TO_KEYWORD` -- 스터디모임 -> 키워드
		FOREIGN KEY (
			`study_no` -- 스터디번호
		)
		REFERENCES `STUDY` ( -- 스터디모임
			`study_no` -- 스터디번호
		);

-- 채팅_참가자
ALTER TABLE `CHAT_PARTICIPANT`
	ADD CONSTRAINT `FK_member_TO_CHAT_PARTICIPANT` -- 사용자 -> 채팅_참가자
		FOREIGN KEY (
			`email` -- 이메일
		)
		REFERENCES `member` ( -- 사용자
			`email` -- 이메일
		);

-- 채팅_참가자
ALTER TABLE `CHAT_PARTICIPANT`
	ADD CONSTRAINT `FK_CHAT_ROOM_TO_CHAT_PARTICIPANT` -- 채팅방 -> 채팅_참가자
		FOREIGN KEY (
			`room_id` -- 방번호
		)
		REFERENCES `CHAT_ROOM` ( -- 채팅방
			`room_id` -- 방번호
		);

-- 채팅방
ALTER TABLE `CHAT_ROOM`
	ADD CONSTRAINT `FK_STUDY_TO_CHAT_ROOM` -- 스터디모임 -> 채팅방
		FOREIGN KEY (
			`study_no` -- 스터디번호
		)
		REFERENCES `STUDY` ( -- 스터디모임
			`study_no` -- 스터디번호
		);

-- 채팅내역
ALTER TABLE `CHAT_CONTENT`
	ADD CONSTRAINT `FK_CHAT_ROOM_TO_CHAT_CONTENT` -- 채팅방 -> 채팅내역
		FOREIGN KEY (
			`room_id` -- 방번호
		)
		REFERENCES `CHAT_ROOM` ( -- 채팅방
			`room_id` -- 방번호
		);

-- 채팅내역
ALTER TABLE `CHAT_CONTENT`
	ADD CONSTRAINT `FK_member_TO_CHAT_CONTENT` -- 사용자 -> 채팅내역
		FOREIGN KEY (
			`email` -- 이메일
		)
		REFERENCES `member` ( -- 사용자
			`email` -- 이메일
		);