
# Project Board ( 게시판 작성 )

LongTermFieldExperience at MCST (22.09 ~ 22.12)

- board_project -

DEV PERIOD : 22.09.01 ~ 22.10.17

SPRING : eGovFrameWorkDev ver.3.10.0 (eclipse)
DB : Cubrid 11.0
JDK : ver. 1.18.0_202
Server : Tomcat v8.5

FUNCTION
+ 게시판 CRUD 기능
+ ===================
+ Paging
+ Search
+ ===================
+ 파일 첨부
+ 다중 파일 첨부
+ ===================
+ 로컬에 파일 저장(UUID.~)
+ 게시글 첨부 파일 조회
+ ===================
+ 다중 파일 
+ ===================
+ 첨부 파일 개별 삭제
+ 첨부 파일 개별 추가
+ ===================
+ 첨부 파일 전체 삭제(글 삭제 시)
+ 게시글 리스트 엑셀화.( 다운로드 )
+ ===================
+ 댓글 기능
+ 댓글 개별 삭제
+ ===================
+ 댓글 전체 삭제(글 삭제 시)
+ 로그인/로그아웃
+ 회원가입
+ ===================
+ 피드백
+ link 뒤 간결화
+ return referer => redirect로
( 왠만하면 referer 미사용)
+ 상세 페이지 parameter 파일 번호로만 받도록
+ 목록 버튼 클릭 시 paging 정보 불러오기(main, 등록, 수정 페이지)
+ ===================

-메인페이지
![메인페이지](https://user-images.githubusercontent.com/33487961/194021946-cf0f2386-e8b3-43a8-85ad-15fd3d762de8.jpg)
-글 작성 페이지
![글 작성 페이지](https://user-images.githubusercontent.com/33487961/194021985-f7bac4f5-35af-41f2-a083-6301d54970fb.jpg)
- 상세 페이지
![상세 페이지](https://user-images.githubusercontent.com/33487961/194021996-6a90ec64-bebd-47ec-82c0-9b86222878bc.jpg)
- 수정 페이지
![수정 페이지](https://user-images.githubusercontent.com/33487961/194022014-1f9577bd-81e8-4326-a9bb-1801eba949d8.jpg)
- 글 내용 수정
![글 내용 수정](https://user-images.githubusercontent.com/33487961/194022028-e24b522e-2a65-4490-9850-c8277bddf2fc.jpg)
- 첨부 파일 추가 & 삭제
![파일 추가 삭제](https://user-images.githubusercontent.com/33487961/194022048-360fd325-c918-431f-9c36-91328462b232.jpg)
- 글 수정 완료
![수정 완료](https://user-images.githubusercontent.com/33487961/194022063-12947d22-ad25-4146-9686-93b5b22842e2.jpg)
- 글 삭제 ( 첨부 파일 포함 )
![글 삭제](https://user-images.githubusercontent.com/33487961/194022071-d137bc3b-efd9-44a0-92b8-604ceb1a2fde.jpg)
- 게시판 리스트 excel 다운로드
![전체 정보 저장](https://user-images.githubusercontent.com/33487961/194022141-1a3f0252-99d9-4fb1-80c3-df774a9a30ef.jpg)
- 제목, 작성자, 아이디에 따른 검색 기능
![검색 기능](https://user-images.githubusercontent.com/33487961/194022153-36f6bae4-aec5-483c-9d64-3a66c47f6a4b.jpg)
- 검색된 데이터만 excel 
![검색 결과 따른 엑셀 저장](https://user-images.githubusercontent.com/33487961/194022199-d660d28d-79b5-4dff-a0ae-895ccc8b7653.jpg)
