
SET foreign_key_checks=0;

-- 사용자
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
                          `email`        VARCHAR(100) NOT NULL PRIMARY KEY COMMENT '이메일', -- 이메일
                          `password`     VARCHAR(255) NOT NULL COMMENT '비밀번호', -- 비밀번호
                          `name`         VARCHAR(30)  NOT NULL COMMENT '이름', -- 이름
                          `nickname`     VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
                          `gender`       CHAR(1)      NOT NULL COMMENT '성별', -- 성별
                          `birth`        DATE         NOT NULL COMMENT '생년월일', -- 생년월일
                          `address`      VARCHAR(255) NOT NULL COMMENT '주소', -- 주소
                          `phone`        VARCHAR(15)  NOT NULL COMMENT '휴대전화', -- 휴대전화
                          `reg_dt`       DATETIME     NOT NULL COMMENT '가입일', -- 가입일
                          `email_yn`     BIT(1)       NOT NULL COMMENT '이메일인증여부', -- 이메일인증여부
                          `email_auth_key` VARCHAR(255) NOT NULL COMMENT '이메일인증키', --이메일인증키
                          `status`       BIT(1)       NOT NULL COMMENT '회원상태', -- 회원상태
                          `report_count` INT          NOT NULL COMMENT '신고누적수', -- 신고누적수
                          `sns_login`    VARCHAR(30)  NOT NULL COMMENT '간편로그인' -- 간편로그인
)
    COMMENT '사용자';

-- 스터디모임
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study` (
                         `study_no`       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '스터디번호', -- 스터디번호
                         `email`          VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
                         `title`          VARCHAR(150) NOT NULL COMMENT '타이틀', -- 타이틀
                         `category`       VARCHAR(30)  NOT NULL COMMENT '대분류', -- 대분류
                         `sub_category`   VARCHAR(30)  NOT NULL COMMENT '중분류', -- 중분류
                         `keyword`        VARCHAR(50)  NOT NULL COMMENT '소분류', -- 소분류
                         `description`    VARCHAR(255) NOT NULL COMMENT '설명', -- 설명
                         `status`         BIT(1)       NOT NULL COMMENT '모집상태', -- 모집상태
                         `cancel_status`  BIT(1)       NOT NULL COMMENT '취소상태', -- 취소상태
                         `write_dt`       DATETIME     NOT NULL COMMENT '작성일', -- 작성일
                         `edit_dt`        DATETIME              COMMENT '수정일', -- 수정일
                         `due_dt`         DATETIME     NOT NULL COMMENT '만기일', -- 만기일
                         `applicant_nums` INT          NOT NULL COMMENT '신청자수', -- 신청자수
                         `num_people`     INT          NOT NULL COMMENT '모집인원수' -- 모집인원수
)
    COMMENT '스터디모임';

-- 신청자명단
DROP TABLE IF EXISTS `applicant_list`;
CREATE TABLE `applicant_list` (
                                  `apply_no`     INT          NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '신청번호', -- 신청번호
                                  `study_no`     INT          NOT NULL COMMENT '스터디번호', -- 스터디번호
                                  `email`        VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
                                  `room_id`      INT          NOT NULL COMMENT '방번호', -- 상담방번호
                                  `apply_status` VARCHAR(30)  NOT NULL COMMENT '신청상태', -- 신청상태
                                  `apply_dt`     DATETIME     NOT NULL COMMENT '신청일' -- 신청일
)
    COMMENT '신청자명단';



-- 키워드
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
                           `keyword_no`   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '키워드번호', -- 키워드번호
                           `study_no`     INT         NOT NULL COMMENT '스터디번호', -- 스터디번호
                           `category`     VARCHAR(50) NOT NULL COMMENT '대분류', -- 대분류
                           `sub_category` VARCHAR(50) NOT NULL COMMENT '중분류', -- 중분류
                           `keyword`      VARCHAR(50) NOT NULL COMMENT '키워드' -- 키워드
)
    COMMENT '키워드';


-- 채팅_참가자
DROP TABLE IF EXISTS `chat_participant`;
CREATE TABLE `chat_participant` (
                                    `participant_no` INT          NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '참여번호', -- 참여번호
                                    `room_id`        INT          NOT NULL COMMENT '방번호', -- 방번호
                                    `email`          VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
                                    `participant_dt` DATETIME     NOT NULL COMMENT '참가일', -- 참가일
                                    `chat_status`    BIT(1)       NOT NULL COMMENT '참여상태' -- 참여상태
)
    COMMENT '채팅_참가자';


-- 채팅방
DROP TABLE IF EXISTS `chat_room`;
CREATE TABLE `chat_room` (
                             `room_id`  INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '방번호', -- 방번호
                             `study_no` INT NOT NULL COMMENT '스터디번호', -- 스터디번호
                             `room_type` VARCHAR(30) NOT NULL COMMENT '방종류' -- 방종류
)
    COMMENT '채팅방';


-- 채팅내역
DROP TABLE IF EXISTS `chat_content`;
CREATE TABLE `chat_content` (
                                `chat_no`  INT          NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '채팅번호', -- 채팅번호
                                `room_id`  INT          NOT NULL COMMENT '방번호', -- 방번호
                                `email`    VARCHAR(100) NOT NULL COMMENT '이메일', -- 이메일
                                `content`  VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
                                `write_dt` DATETIME     NOT NULL COMMENT '작성일' -- 작성일
)
    COMMENT '채팅내역';

-- 리프레시_토큰
DROP TABLE IF EXISTS `jwt_token`;
CREATE TABLE `jwt_token` (
                                `email`         VARCHAR(100) NOT NULL PRIMARY KEY COMMENT '이메일', --이메일
                                `refresh_token` VARCHAR(255) NOT NULL COMMENT '리프레시_토큰' --리프레시_토큰
)
    COMMENT '리프레시_토큰';

-- 블랙리스트(로그아웃)
DROP TABLE IF EXISTS `black_list`;
CREATE TABLE `black_list`(
                                `access_token` VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '액세스_토큰' -- 액세스_토큰
)
    COMMENT '블랙리스트';

-- 스터디모임
ALTER TABLE `study`
    ADD CONSTRAINT `FK_member_TO_study` -- 사용자 -> 스터디모임
        FOREIGN KEY (
                     `email` -- 이메일
            )
            REFERENCES `member` ( -- 사용자
                                 `email` -- 이메일
                );

-- 신청자명단
ALTER TABLE `applicant_list`
    ADD CONSTRAINT `FK_member_TO_applicant_list` -- 사용자 -> 신청자명단
        FOREIGN KEY (
                     `email` -- 이메일
            )
            REFERENCES `member` ( -- 사용자
                                 `email` -- 이메일
                );

-- 신청자명단
ALTER TABLE `applicant_list`
    ADD CONSTRAINT `FK_study_TO_applicant_list` -- 스터디모임 -> 신청자명단
        FOREIGN KEY (
                     `study_no` -- 스터디번호
            )
            REFERENCES `study` ( -- 스터디모임
                                `study_no` -- 스터디번호
                );

-- 신청자명단
ALTER TABLE `applicant_list`
    ADD CONSTRAINT `FK_chat_room_TO_applicant_list` -- 채팅방 -> 신청자명단
        FOREIGN KEY (
                    `room_id` -- 방번호
            )
            REFERENCES `chat_room` ( -- 채팅방
                                   `room_id` -- 방번호
                );

-- 키워드
ALTER TABLE `keyword`
    ADD CONSTRAINT `FK_study_TO_keyword` -- 스터디모임 -> 키워드
        FOREIGN KEY (
                     `study_no` -- 스터디번호
            )
            REFERENCES `study` ( -- 스터디모임
                                `study_no` -- 스터디번호
                );

-- 채팅_참가자
ALTER TABLE `chat_participant`
    ADD CONSTRAINT `FK_member_TO_chat_participant` -- 사용자 -> 채팅_참가자
        FOREIGN KEY (
                     `email` -- 이메일
            )
            REFERENCES `member` ( -- 사용자
                                 `email` -- 이메일
                );

-- 채팅_참가자
ALTER TABLE `chat_participant`
    ADD CONSTRAINT `FK_chat_room_TO_chat_participant` -- 채팅방 -> 채팅_참가자
        FOREIGN KEY (
                     `room_id` -- 방번호
            )
            REFERENCES `chat_room` ( -- 채팅방
                                    `room_id` -- 방번호
                );

-- 채팅방
ALTER TABLE `chat_room`
    ADD CONSTRAINT `FK_study_TO_chat_room` -- 스터디모임 -> 채팅방
        FOREIGN KEY (
                     `study_no` -- 스터디번호
            )
            REFERENCES `study` ( -- 스터디모임
                                `study_no` -- 스터디번호
                );

-- 채팅내역
ALTER TABLE `chat_content`
    ADD CONSTRAINT `FK_chat_room_TO_chat_content` -- 채팅방 -> 채팅내역
        FOREIGN KEY (
                     `room_id` -- 방번호
            )
            REFERENCES `chat_room` ( -- 채팅방
                                    `room_id` -- 방번호
                );

-- 채팅내역
ALTER TABLE `chat_content`
    ADD CONSTRAINT `FK_member_TO_chat_content` -- 사용자 -> 채팅내역
        FOREIGN KEY (
                     `email` -- 이메일
            )
            REFERENCES `member` ( -- 사용자
                                 `email` -- 이메일
                );



SET foreign_key_checks=1;