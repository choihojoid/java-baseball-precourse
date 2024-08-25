package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Bot {

    private String randStr;

    private Bot() {}

    public static Bot getInstance() {
        return new Bot();
    }

    private String getRandomNumberAsString(final int minNum, final int maxNum, final int digits) {
        return Stream.generate(() -> Randoms.pickNumberInRange(minNum, maxNum))
                .distinct()
                .limit(digits)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public void setRandStr(final int minNum, final int maxNum, final int digits) {
        this.randStr = getRandomNumberAsString(minNum, maxNum, digits);
    }

    public int[] calculateStrikeAndBall(final String inputStr) {
        final int[] counts = new int[2];

        for (int i = 0; i < randStr.length(); i++) {
            if (randStr.charAt(i) == inputStr.charAt(i)) {
                counts[0]++;
                continue;
            }

            if (inputStr.contains(String.valueOf(randStr.charAt(i)))) {
                counts[1]++;
            }
        }

        return counts;
    }

}
