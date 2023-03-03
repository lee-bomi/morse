INSERT INTO member
    (email, password, name, nickname,
     gender, birth, address, phone,
     reg_dt, email_yn,email_auth_key, status, report_count, sns_login)
    VALUES ('kim@naver.com', '"$2a$10$HpPHhzop7HjJVW9dYHncwuklRTc7AP.b1f.Rqz14lnim68uwqk0fm"', '김모스', '김나방',
        '남', '1995-01-23', '서울 청와대', '010-1234-5678',
        now(), true,'a', false, 0, 'email'), -- password : 12345
           ('lee@naver.com', '$2a$10$n4k0MT4Okd.ld6SY1kzzAuB7XHHqx6jTE/jstyUOEb1luafXgng1y', '김스모', '김씨름',
        '남', '1999-01-01', '부산 해운대', '010-1111-1111',
        now(), true,'a', false, 0, 'kakao'), -- password : 11111
            ('park@naver.com', '$2a$10$NyTo3NccOstHxA4cHgPxJeTOKQ0N/.2NwQUnexrjrivoPHeE/LXm6', '박버거', '박빵',
        '여', '1998-01-01', '서울 강남구', '010-2222-2222',
        now(), true,'a', false, 0, 'google'); -- password : 22222

INSERT INTO study
    (study_no, email, title, category, sub_category, keyword,
     description, status, cancel_status, write_dt,
     edit_dt, due_dt, applicant_nums, num_people)
    VALUES (1, 'kim@naver.com', '토익공부할 사람', '취업', '토익', 'LC',
        '토익 900점 이상 (생략)', false, false, now(),
        now(), date_add(now(), interval 10 day), 3, 10);


INSERT INTO keyword
    (keyword_no, study_no, category, sub_category, keyword)
    VALUES
    (1, 1, '외국어', '영어', '토익');

INSERT INTO chat_room
    (room_id, study_no, room_type)
    VALUES
    (1, 1,'inquiry');

INSERT INTO applicant_list
    (apply_no, study_no, email, room_id, apply_status, apply_dt)
    VALUES
    (1, 1, 'park@naver.com', 1, '신청중', now());



INSERT INTO chat_participant
    (participant_no, room_id, email, participant_dt, chat_status)
    VALUES
    (1, 1, 'kim@naver.com', now(), true),
    (2, 1, 'park@naver.com',now(), true);

INSERT INTO chat_content
    (chat_no, room_id, email, content, write_dt)
    VALUES
    (1, 1, 'park@naver.com', '안녕하세요 저 토익 공부하려고 신청했는데요.', now()),
    (2, 1, 'kim@naver.com', '현재 토익 점수가 어떻게 되시나요?', now());

INSERT INTO jwt_token
    (email,refresh_token)
    VALUES
    ('kim@naver.com',"");
