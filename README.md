# 스페이스 인베이더 리마스터(SpaceInvader Remaster)

------

**요약 : 갤러그 게임의 시초인 스페이스 인베이더의 리마스터링**

**프로젝트 기간 : 2017/09 ~ 2017/12**

**Skils : Java, MySQL**

---

## Description



### Summary

![1](../ComebackQueen.github.io/assets/images/README/160075736-21f3df81-45e9-4068-a7e5-bdfc6a17c94a.jpeg)

- 싱글 플레이는 스테이지 10까지 있으며 난이도(쉬움, 보통, 어려움), 속도(x1.0, x1.5, x2.0, x2.5, x3.0), 방어벽(ON, OFF)와 같은 옵션을 설정하여 플레이 가능
- 멀티 플레이는 2명의 플레이어가 협동을 통해 보스를 제거하는 협동모드와 2명의 플레이어가 마주선상에서 서로 맞대결하는 PVP모드가 있음



### Jaein's Role

- GUI 구현
- 사용자 및 적 개체 알고리즘 구현
- 데이터베이스 설계 및 적용
- 네트워크 통신 구현 및 로그인/로그아웃 구현



---

## About Project



### GUI

- AWT 패키지, Swing 패키지 활용

### Single Play

- 좌표 계산을 통해 사용자 및 적 개체 움직임 구현
- 플레이 중간에 아이템(폭탄, 고속연사, 헬퍼)을 제공하도록 구현
- 게임 시작 전 옵션을 설정하여 사용자가 원하는 환경에서 게임하도록 구현

### Multi Play

- 서버 프로젝트를 따로 만들어서 구현
- 소켓 통신을 활용한 네트워크 구현
- MySql을 통한 DB 구현
- DB에 회원 정보와 회원의 전적을 업로드하여 랭킹 구현
- PVP, 협동 모드는 Queue를 활용하여 사용자들간 매칭되도록 구현
- 플레이어 간 원할한 데이터 교환을 위해 subString을 활용하여 식별자를 두어 데이터 교환이 이루어지도록 구현



---

## Results

### 게임 플레이 화면

![2](../ComebackQueen.github.io/assets/images/README/160087216-1d6c7df7-1f2d-4ef1-88b9-4f9e4ba7e17e.gif)

![3](../ComebackQueen.github.io/assets/images/README/160087304-777fde23-99e1-4cf9-bb1a-b098c6eb1ad8.gif)



### 로그인/회원가입 화면

![5](../ComebackQueen.github.io/assets/images/README/160088406-43b4672f-2180-42f1-83e2-5d070c7b0dba.jpeg)

![6](../ComebackQueen.github.io/assets/images/README/160088397-ad22de5f-ddc0-4933-9bdf-07a0cc3a1972.jpeg)



### 환경설정

![4](../ComebackQueen.github.io/assets/images/README/160088401-a0081fd8-d999-45d2-af95-72f029c7676d.jpeg)



(※ 멀티플레이 게임은 DB가 없어서 플레이를 진행할수가 없었습니다...😭)
