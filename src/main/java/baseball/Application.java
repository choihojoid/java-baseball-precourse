package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class Application {

    private static final int minNum = 100;
    private static final int maxNum = 999;

    public static void main(String[] args) {
        int randNum = Randoms.pickNumberInRange(minNum, maxNum);
    }

}
