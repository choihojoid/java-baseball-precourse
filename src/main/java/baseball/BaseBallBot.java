package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BaseBallBot {

    private String randStr;

    private BaseBallBot() {}

    public static BaseBallBot getInstance() {
        return new BaseBallBot();
    }

    private String getRandomNumberAsString(final int minNum, final int maxNum, final int digits) {
        return Stream.generate(() -> Randoms.pickNumberInRange(minNum, maxNum))
                .distinct()
                .limit(digits)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String getRandStr() {
        return randStr;
    }

    public void reset() {
        randStr = null;
    }

    public void setRandStr(final int minNum, final int maxNum, final int digits) {
        randStr = getRandomNumberAsString(minNum, maxNum, digits);
    }

}
