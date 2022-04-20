# 스페이스 인베이더 리마스터(SpaceInvader Remaster)

------

**요약 : 갤러그 게임의 시초인 스페이스 인베이더의 리마스터링**

**프로젝트 기간 : 2017/09 ~ 2017/12**

**Skils : Java, MySQL**

---

## Description



### Summary

![1](https://user-images.githubusercontent.com/87848564/160090600-56b0f295-1adc-478d-a7e1-ad27e3cc269b.JPG)

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

![160087216-1d6c7df7-1f2d-4ef1-88b9-4f9e4ba7e17e](https://user-images.githubusercontent.com/87848564/160090732-5ccaeaf3-be8b-493a-9416-293081262201.gif)

![160087304-777fde23-99e1-4cf9-bb1a-b098c6eb1ad8](https://user-images.githubusercontent.com/87848564/160090817-49e147c2-22f2-46bc-849d-89dd6422de23.gif)



### 로그인/회원가입 화면

![160088397-ad22de5f-ddc0-4933-9bdf-07a0cc3a1972](https://user-images.githubusercontent.com/87848564/160090843-84fbfe32-1b8a-4d27-9ab4-7b41a0119f9b.jpeg)

![160088406-43b4672f-2180-42f1-83e2-5d070c7b0dba](https://user-images.githubusercontent.com/87848564/160090866-a1413c51-8937-4853-8ae7-e21ec2645e4b.jpeg)



### 환경설정


![160088401-a0081fd8-d999-45d2-af95-72f029c7676d](https://user-images.githubusercontent.com/87848564/160090852-73dc5842-a5f6-45c7-8cd1-27e67cbb0221.jpeg)



(※ 멀티플레이 게임은 DB가 없어서 플레이를 진행할수가 없었습니다...😭)
