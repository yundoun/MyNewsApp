# NamuNews

## Fragment

뉴스 앱의 다양한 탭에 공통적으로 사용되는 기능을 추상화 하였습니다.  
이 작업의 목적은 중복 코드를 줄이고, 유지 보수를 용이하게 하며 앱 전반에 걸쳐 일관된 사용자 인터페이스와 경험을 제공하는 것입니다.
<br>

#### BaseNewsFragment의 기능

=> 페이지 버튼 관리, 동적 버튼 생성, 페이지 선택 업데이트, 페이지 데이터 로딩

<br>

## Viewpager2 & FragmentStateAapter

TabLayout 각 페이지를 프래그먼트로 구성하였고, FragmentStateAdapter를 사용하여 관리하였습니다.  
FragmentStateAdapter는 ViewPager2와 함께 사용되며, 사용자가 페이지를 스와이프 할 때마다  
프래그먼트를 효율적으로 관리하는 역할을 합니다.
<br>

FragmentStateAdapter의 핵심은 메모리 사용을 최적화하면서 동시에 사용자 경험을 유지하는 것입니다.  
**이를 위해 FragmentStateAdapter는 필요한 만큼의 인스턴스만을 가지고 있고,  
나머지 페이지에 대해서는 상태값만 들고 있는 구조를 채택하고 있습니다.**

<br>

## RecyclerView

DetailInterface를 생성하고 RecyclerView의 각 항목 클릭 이벤트를 구현하였습니다.  
RecyclerView가 존재하는 Fragment에 DetailInterface를 상속받아 **onItemClick()** 메소드를 구현하였습니다.

<br>

## Retrofit2

Android앱에서 Retrofit2라이브러리를 사용하여 원격 서버로부터 JSON 데이터를 비동기적으로 가져오고,  
가져온 데이터를 리스너를 통해 RecyclerVieew에 표시하는 과정을 학습하였습니다.

<br>

## Glide

Glide 라이브러리를 사용해서 네트워크 이미지를 로드해보았습니다.  
서버에 있는 이미지(URL)를 로드 하기 위해 사용되며 재사용, 디스크 캐싱, 메모리 관점에서 효율적입니다.

<pre><code>Glide.with(context) // Context 제공
     .load(url) // 로드할 이미지 URL
     .into(imageView); // 이미지를 표시할 ImageView
</code></pre>

### 결과

<img src="https://github.com/yundoun/NamuNews/assets/106233376/a3f55721-96d8-4ba7-af21-d77b4fac8ecf" width="400" height="700"/>

<img src="https://github.com/yundoun/NamuNews/assets/106233376/d1780679-0a60-40b5-ad1c-e145ee6cedcc" width="400" height="700"/>



