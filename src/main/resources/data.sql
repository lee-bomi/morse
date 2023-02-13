INSERT INTO MEMBER
    (email, password, name, nickname,
     gender, birth, address, phone,
     reg_dt, email_yn, status, report_count, sns_login)
    VALUES ('kim@naver.com', '12345', '김모스', '김나방',
        '남', '1995-01-23', '서울 청와대', '010-1234-5678',
        now(), true, false, 0, 'email'),
           ('lee@naver.com', '11111', '김스모', '김씨름',
        '남', '1999-01-01', '부산 해운대', '010-1111-1111',
        now(), true, false, 0, 'kakao'),
            ('park@naver.com', '22222', '박버거', '박빵',
        '여', '1998-01-01', '서울 강남구', '010-2222-2222',
        now(), true, false, 0, 'google');

INSERT INTO STUDY
    (study_no, email, title, purpose,
     description, status, cancel_status, write_dt,
     edit_dt, due_dt, applicant_nums, num_people, category)
    VALUES (1, 'kim@naver.com', '토익공부할 사람', '토익',
        '토익 900점 이상 (생략)', false, false, now(),
        now(), date_add(now(), interval 10 day), 3, 10, '외국어');


INSERT INTO KEYWORD
    (keyword_no, study_no, category, sub_category, keyword)
    VALUES
    (1, 1, '외국어', '영어', '토익');

INSERT INTO APPLICANT_LIST
    (apply_no, study_no, email, apply_status, apply_dt)
    VALUES
    (1, 1, 'park@naver.com', '신청중', now());

INSERT INTO CHAT_ROOM
    (room_id, study_no)
    VALUES
    (1, 1);

INSERT INTO CHAT_PARTICIPANT
    (participant_no, room_id, email, participant_dt, chat_status)
    VALUES
    (1, 1, 'kim@naver.com', now(), true),
    (2, 1, 'park@naver.com',now(), true);

INSERT INTO CHAT_CONTENT
    (chat_no, room_id, email, content, write_dt)
    VALUES
    (1, 1, 'park@naver.com', '안녕하세요 저 토익 공부하려고 신청했는데요.', now()),
    (2, 1, 'kim@naver.com', '현재 토익 점수가 어떻게 되시나요?', now());
