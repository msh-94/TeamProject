drop database if exists 본사;
create database 본사;
use 본사;
set sql_safe_updates = 0; -- mysql workbench : safeMode 해제(끄기 0 / 켜기 1)

create table plan(                          # 1.구독플랜(본사전용)Table
    pno int unsigned auto_increment primary key , -- 구독플랜번호(pk)
    pName varchar(10) not null unique ,           -- 구독플랜명
    pDate int unsigned default 1 ,                -- 구독기간(월)
    pMoney int unsigned default 0                 -- 금액(원)
);

create table Member_head(          # 2.본사회원Table
    mno int unsigned auto_increment primary key,  -- 본사회원번호(pk)
    mCategory tinyint unsigned not null ,         -- 회원유형(1.일반회원/2.택시기사/3.사업자)
    mId varchar(30) not null unique ,             -- 로그인아이디
    mPwd varchar(15) not null ,                   -- 로그인 비밀번호
    mPhone varchar(13) not null ,                 -- 휴대폰번호
    mName varchar(10) not null ,                  -- 이름
    mDate date default(current_date)              -- 가입날짜
);

create table log(                             # 3.구독로그(구독신청내역)Table
    logno int unsigned auto_increment primary key, -- 로그번호(pk)
    pno int unsigned ,                             -- 구독플랜번호(fk)
    mno int unsigned ,                             -- 본사회원번호(fk)
    addDate date default (current_date),           -- 신청날짜
    endDate date not null ,                        -- 종료날짜
    constraint foreign key(pno) references plan(pno),
    constraint foreign key(mno) references Member_head(mno)
);

create table company(                   # 4.회사부가정보(구독자사이트 = 본사 하위사이트)Table
    cno int unsigned auto_increment primary key,  -- 회사번호(pk)
    mno int unsigned unique,                      -- 본사회원번호(fk)
    cName varchar(30) not null unique ,           -- 사이트명
    area varchar(10) not null,                    -- 서비스지역
    service varchar(50),                          -- 서비스내용
    constraint foreign key(mno) references Member_head(mno)
);

create table Member_sub(             # 5.구독자사이트(=본사 하위사이트) 가입회원Table
    mno int unsigned auto_increment primary key, -- 구독자사이트(=본사 하위사이트) 회원번호(pk)
    cno int unsigned ,                           -- 회사번호_(fk)
    mCategory tinyint unsigned not null ,        -- 구독자사이트 회원유형
    mId varchar(30) not null unique ,            -- 구독자사이트 회원아이디
    mPwd varchar(15) not null ,                  -- 구독자사이트 회원비밀번호
    mPhone varchar(13) not null ,                -- 휴대폰번호
    mName varchar(10) not null ,                 -- 이름
    mDate date default(current_date),            -- 가입날짜
    constraint foreign key(cno) references company(cno)
);


# 1. Member_head Table 샘플데이터: 구독플랜(본사 전용메뉴)
INSERT INTO plan (pName) VALUES('무료체험');
INSERT INTO plan (pName,pDate,pMoney) VALUES('베이직',6,490000);
INSERT INTO plan (pName,pDate,pMoney) VALUES('프리미엄',12,950000);

# 2. Member_head Table 샘플데이터: 본사 회원
insert into Member_head( mCategory , mId , mPwd , mPhone , mName , mDate) values
( 3, 'admin', '1111', '010-0000-1111', '관리자', '2024-01-01'),
( 3, 'Adni', 'sdf2342', '010-0000-2222', '강호동', '2024-06-12'),
( 1, 'fff_dd', '234sdfa', '010-0000-3333', '조나단', '2024-05-15'),
( 2, 'a678ff', '123asdfd', '010-0000-4444', '탁재훈', '2024-05-03'),
( 3, 'vbbvv', '5342aaa', '010-0000-5555', '신동엽', '2024-05-12'),
( 3, 'lemonboy', 'ab123456', '010-1111-1111', '유희열', '2024-04-25'),
( 2, 'greenfox', 'cd456789', '010-2222-2222', '정준하', '2024-05-26'),
( 1, 'starfish', 'ef789012', '010-3333-3333', '김용만', '2024-03-20'),
( 1, 'blackdog', 'gh012345', '010-4444-4444', '서경석', '2024-03-28'),
( 3, 'markhani', 'qwe123', '010-4444-4444', '이수근', '2024-03-28'),
( 2, 'orange88', 'ij345678', '010-5555-5555', '이경규', '2024-03-29');

# 3. company Table 샘플데이터: 본사 구독자사이트(=본사 하위사이트) 부가정보
insert into company (mno,cName,area,service) values
( 11, '바로 택시온', '서울', '신규회원 5000point  + 택시요금 3% 적립'),
( 3 , '콜마이택시', '부산', '신규회원 5000point  + 택시요금 3% 적립'),
( 2 , '스마트카택시', '대구', '신규회원 4000point + 택시요금 4% 적립'),
( 4 , '이지고택시', '인천', '신규회원 3000point + 택시요금 5% 적립'),
( 5 , '타요타요콜', '광주', '신규회원 5000point  + 택시요금 3% 적립'),
( 6 , '올웨이콜택시', '대전', '신규회원 4000point + 택시요금 4% 적립'),
( 7 , '하나콜', '울산', '신규회원 3000point + 택시요금 5% 적립'),
( 8 , '스피드온택시', '수원', '신규회원 5000point  + 택시요금 3% 적립'),
( 9 , '택시드림', '제주', '신규회원 4000point + 택시요금 4% 적립'),
( 10 , '더굿택시', '창원', '신규회원 3000point + 택시요금 5% 적립');

# 4. log Table 샘플데이터: 구독로그(구독신청내역)
insert into log (pno , mno , addDate , endDate ) values
( 1 , 1 , '2024-06-01' , '2024-07-01'),
( 3 , 3 , '2024-06-10' , '2025-06-10'),
( 2 , 2 , '2024-12-01' , '2025-06-01'),
( 3 , 4 , '2025-06-01' , '2025-07-01'),
( 2 , 2 , '2025-06-15' , '2025-12-15'),
( 3 , 6 , '2025-07-01' , '2026-07-01'),
( 3 , 7 , '2025-06-01' , '2025-07-01'),
( 2 , 8 , '2025-07-15' , '2026-01-15'),
( 3 , 9 , '2025-07-24' , '2026-07-24'),
( 3 , 10 , '2025-06-01' , '2025-07-01');

# 5. Member_sub 샘플데이터 : 구독자사이트(=본사 하위사이트) 가입회원
INSERT INTO Member_sub (cno,mCategory,mId,mPwd,mPhone,mName,mDate) VALUES
( 3 , 3 , 'admin2', 'sdf2342', '010-0000-2222', '강호동', '2024-06-12'),
( 2 , 1 , 'admin3', '234sdfa', '010-0000-3333', '조나단', '2024-05-15'),
( 4 , 2 , 'admin4', '123asdfd', '010-0000-4444', '탁재훈', '2024-05-03'),
( 5 , 3 ,'admin5', '5342aaa', '010-0000-5555', '신동엽', '2024-05-12'),
( 6 , 3 , 'admin6', 'ab123456', '010-1111-1111', '유희열', '2024-04-25'),
( 7 , 2 , 'admin7', 'cd456789', '010-2222-2222', '정준하', '2024-05-26'),
( 8 , 1 , 'admin8', 'ef789012', '010-3333-3333', '김용만', '2024-03-20'),
( 9 , 1 , 'admin9', 'gh012345', '010-4444-4444', '서경석', '2024-03-28'),
( 10 , 3 , 'admin10', 'qwe123', '010-4444-4444', '이수근', '2024-03-28'),
( 1 , 2 , 'admin11', 'ij345678', '010-5555-5555', '이경규', '2024-03-29'),
( 1 , 1, 'qwerty',  '234234', '010-1111-2222', '김철수', '2025-08-02'),
( 2 , 2 , 'asdfgh', '345345', '010-2222-3333', '이영희', '2025-08-03'),
( 3 , 2 , 'zxcvbn', '456456', '010-3333-4444', '박민수', '2025-08-04'),
( 4 , 1 , 'poiuyt', '567567', '010-4444-5555', '최지우', '2025-08-05'),
( 5 , 1 , 'lkjhgf', '678678', '010-5555-6666', '장동건', '2025-08-06'),
( 6 , 1 , 'mnbvcx', '789789', '010-6666-7777', '한가인', '2025-08-07');

select * from plan;        -- 구독플랜
select * from company;     -- 구독회사 부가정보(=본사 하위사이트 부가정보)
select * from log;         -- 구독로그(구독신청내역)
select * from Member_head; -- 본사 가입회원
select * from Member_sub;  -- 구독회사(=본사 하위사이트) 가입회원

-- 구독신청 테스트_김진숙_250804( log insert )
select * from log; 
-- 1) 최초 구독신청 : 본사 구독신청을 1번도 안한 경우
-- log 테이블 > mno(currentMno) 없는 경우 -->log 레코드 insert //#.case : (신규회원)번호 12)
insert into log( pno , mno , addDate , endDate ) values( 1 , 12 , '2025-08-04' , '2025-09-04');

-- 2) 구독종료 후, 구독신청 : 프리미엄
-- log 테이블 > mno(currentMno) 있지만 레코드 내역 중 종료일(endDate)이 오늘 이전인 경우 //#.case : (기존회원: 3)조나단 : fff_dd , 2025-06-10)
insert into log( pno , mno , addDate , endDate ) values( 3 , 3 , '2025-08-04' , '2026-09-04');

-- 3) 같은플랜 구독연장 : 이미 베이직 상품 구독 중인데,추가 신청(기간 연장 + 6개월)  //#.case : (기존회원: 2)강호동 : Adni ,2025-12-15)
-- log 테이블 > mno(currentMno) 있지만 레코드 내역 중 마지막 종료일(endDate)에 해당신청 플랜 구독기간 추가_베이직
insert into log( pno , mno , addDate , endDate ) values( 2 , 2 , '2025-08-04' , '2026-06-15');

-- 4) 다른 플랜 구독변경 :" 이미 베이직 상품 구독 중인데, 프리미엄 상품으로 갱신하는 경우 //#.case : (기존회원: 8)김용만 : starfish , 2026-01-15)
-- log 테이블 > mno(currentMno) 있지만 레코드 내역 중 마지막 종료일(endDate)에 해당신청 플랜 구독기간 추가_베이직
-- 구독플랜 다운그레이드 불가/ 
insert into log( pno , mno , addDate , endDate ) values( 3 , 8 , '2025-08-04' , '2026-09-04');
















