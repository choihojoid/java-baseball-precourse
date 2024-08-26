package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BaseBallBot {

    private String randStr;

    private BaseBallBot() {}

    // 봇 객체는 자기 자신을 반환한다.
    public static BaseBallBot getInstance() {
        return new BaseBallBot();
    }

    // 봇 객체는 랜덤 숫자 문자열을 반환한다.
    public String getRandStr() {
        return randStr;
    }

    // 봇 객체는 랜덤 숫자 문자열을 초기화한다.
    public void reset() {
        randStr = null;
    }

    // 봇 객체는 랜덤 숫자 문자열을 뽑는다.
    public void setRandStr(final int minNum, final int maxNum, final int digits) {
        randStr = getRandomNumberAsString(minNum, maxNum, digits);
    }

    // 서로 다른 digits 자릿수의 랜덤 숫자 문자열을 [minNum, maxNum] 범위의 숫자를 1개씩, 총 digits 횟수만큼 뽑아서 구성한다.
    private String getRandomNumberAsString(final int minNum, final int maxNum, final int digits) {
        return Stream.generate(() -> Randoms.pickNumberInRange(minNum, maxNum))
                .distinct()
                .limit(digits)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
