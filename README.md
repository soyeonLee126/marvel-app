# marvel-app

Kotlin 언어를 사용하고, UI는 xml 형태가 아닌 Android Jetpack Compose 사용

필요하다면 평소에 사용하시던 open source들은 자유롭게 사용 가능

다양한 Android jetpack 라이브러리 사용 및 보안 적용 시 가산점




## 주제
https://developer.marvel.com/ 에서 API 키 발급하고, characters api를 사용해서 마블 캐릭터들을 화면에 표시

## 요구 사항
- 캐럭터 데이터는 20개씩 가져오고 아래로 스크롤을 할 때마다 20개씩 데이터를 추가로 로딩
- 더이상 가져올 데이터가 없으면 더 없다는 것을 표시
- refresh 기능 추가
- 캐릭터를 보여주는 셀은 자유롭게 구성
  - 캐릭터를 보여주는 셀에 즐겨찾기 버튼 추가
    - 즐겨찾기를 한 캐릭터들만 모아서 볼 수 있는 즐겨찾기 화면 필요
    -  즐겨찾기 했던 캐릭터를 삭제할 수 있어야 함
    -  즐겨찾기를 한 데이터는 로컬에 저장하여 앱 재실행시 유지되어야함
- thumbnail 이미지를 표시
  - thumbnail을 선택하면 해당 이미지를 로컬에 저장
- urls, comics, stories, events, series의 갯수를 표시

참고) thumbnail을 출력하려면 서버에서 받은 데이터 중에서 thumbnail + extension을 해야 다운로드가 가능
