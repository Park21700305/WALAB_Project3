# 🖥 WalabProject3
## 💻 PC방 시간 추가 콘솔 프로그램
## ⚠️ branch: springBoot branch 
*👾 PC방 시간 추가 및 좌석 관리 콘솔 프로그램.* <br>
*👥 유저와 관리자 모드를 통해 PC방의 좌석 관리 및 시간 추가 기능을 제공합니다.*

### 🚀 프로그램 흐름
- 프로그램 시작 시 `admin` 계정이 자동으로 생성됩니다.
- 프로그램 시작 시 빈 좌석이 자동으로 생성됩니다.
- `admin` 계정으로 로그인 시 유저의 좌석 변경 및 모든 유저 정보 조회가 가능합니다.
- 유저 로그인 시 좌석 선택과 시간 추가가 가능합니다.
- `@Scheduled(fixedRate = 60000)`를 사용하여 시간이 자동으로 감소합니다.

### 📊 엔티티 관계
- `@OneToOne`을 사용하여 유저와 좌석을 일대일 관계로 매핑하였습니다.
![엔티티 관계 다이어그램](https://github.com/Park21700305/WALAB_Project3/assets/93187535/d6c8570a-0a65-48e4-87b8-8e6dd97a2d79)

### 📚 사용한 라이브러리
- 🌱 SpringBoot
- ✂️ Lombok
- 💾 MysqlConnector

### ⚠️ 이슈 
- ORM과 JPA 사용: SQLite3 사용 시 다이얼렉트의 부재로 인해 MySQL을 사용하였습니다.

### 📸 프로그램 실행 캡쳐
![프로그램 실행 캡쳐1](https://github.com/Park21700305/WALAB_Project3/assets/93187535/0033ba9c-8216-4206-ae5f-fab1a02ff07c)
![프로그램 실행 캡쳐2](https://github.com/Park21700305/WALAB_Project3/assets/93187535/d0669de0-a878-417f-b886-8fd303753070)
![프로그램 실행 캡쳐3](https://github.com/Park21700305/WALAB_Project3/assets/93187535/d794ab93-59d8-429b-8994-dd6039eaa864)
![프로그램 실행 캡쳐4](https://github.com/Park21700305/WALAB_Project3/assets/93187535/3567bbda-7c96-4cd8-b6d7-c249b4435edd)
![프로그램 실행 캡쳐5](https://github.com/Park21700305/WALAB_Project3/assets/93187535/33b84841-5673-4111-874b-7b52803d87c1)
