### 요구사항

- [x]  특정 도메인(ex. okestro.com)의 사용자만 이용 가능하다.
- [ ]  로그인을 하면 (room 컬럼을 확인해서) (FE)
    - [ ] 참여 중인 방이 없다면 메인 화면으로 이동한다.
    - [ ] 참여 중인 방이 있다면 방 화면으로 이동한다.
- [ ]  메인 화면에서는 현재 생성되어 있는 방 목록이 보여진다. 
- [ ]  메인 화면에서는
    - [ ]  내가 참여 중인 방으로 바로 이동할 수 있다.
    - [ ]  현재 방 목록이 마감 임박 순, 참여 순 등으로 정렬이 가능하다.
    - [ ]  음식 카테고리나 제목으로 방을 검색할 수 있다.
        - [ ]  음식 카테고리나 음식점을 미설정하면 랜덤(? 표시)으로 설정된다.
    - [ ]  방 정보(제목, 현재 참여 인원/최대 인원, 음식점 이름, 음식 카테고리, 비밀방 여부, 마감 시간(마감 10분 전 빨간색 강조, 마감 시 마감 표시)를 확인할 수 있다.
    - [ ]  방을 만드는 버튼을 클릭하여 방을 만들 수 있다.
    - [ ]  (옵션) 대화가 가능한 채팅창이 있다.
- [ ]  방 만들기 화면에서는 POST /rooms
    - [ ]  음식점을 검색할 수 있다.
    - [ ]  방 제목을 설정할 수 있다.
    - [ ]  비밀방을 만들어 비밀번호를 설정할 수도 있다.
    - [ ]  방 최대 인원을 설정할 수 있다.
- [ ]  방 화면에서는
    - [x]  나가기 버튼으로 나갈 수 있다. PATCH /rooms/{roomId}/participants/{userId}
    - [ ]  마감 버튼을 눌러 마감 시간 전/최대 인원 전에도 마감을 할 수 있다. PATCH /rooms/{roomId} -???
    - [ ]  (옵션)방장은 사용자를 선택해 추방시킬 수 있다. PATCH /rooms/{roomId} -???
    - [ ]  (옵션)방장은 방 정보를 수정할 수 있다. PATCH /rooms/{roomId} -???
    - [ ]  방 참여자들 간 대화가 가능한 채팅창이 있다.

