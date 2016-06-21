# CubicLineGraph
이번 플젝을 하면서 꼭지점이 완만한 라인 그래프를 만들기 위해서 간단히 만들어 봄.

그래프 라이브러리의 지존 MAC(MPandroidChart)안에서 부드럽게 해주는 부분을 찾아봤더디 Path의 cubicTo() method를 사용해서 그리고 있어서
그려봄.

# 설명
단순하게 등급을 보여주기 위한 그래프로 0~5등급 정도로 보여줄 수 있다.

이 것은 CubicLineView안에 HEIGHT과 RADIUS를 조절해서 조절할 수 있다.

그리고 MAC에서는 베지에 곡선 공식을 넣어 더 부드럽게 했고 여기는 간단히 기준 X의 +20, -20 정도에서 Y값을 계산해서 보여준다.

각 값은 graphArray에 적용하면 볼 수 있다.


# 참고
[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
[Android Path 사용법](http://aroundck.tistory.com/293)