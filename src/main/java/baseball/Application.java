package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Application {

    private static final int minNum = 100;
    private static final int maxNum = 999;
    private static final int digits = 3;

    public static void main(String[] args) throws IOException {
        String randStr = generateNumberAsString();
        String inputStr = receiveNumberAsString();

        int[] counts = calculateStrikeAndBall(randStr, inputStr);
        int strikeCnt = counts[0];
        int ballCnt = counts[1];

        printGameResult(strikeCnt, ballCnt);
    }

    private static String generateNumberAsString() {
        String randStr = null;

        do {
            randStr = getRandomNumberAsString();
        } while (!checkDifferentDigits(randStr));

        return randStr;
    }

    private static String getRandomNumberAsString() {
        int randNum = Randoms.pickNumberInRange(minNum, maxNum);

        return String.valueOf(randNum);
    }

    private static boolean checkDifferentDigits(final String str) {
        final Set<Character> characterSet = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            characterSet.add(str.charAt(i));
        }

        return characterSet.size() == digits;
    }

    private static String receiveNumberAsString() throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final String inputStr = bufferedReader.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr);

        return inputStr;
    }

    private static void validateInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("a value other than a number has been entered.");
        }
    }

    private static void validateDigits(final String str) {
        if (str.length() != digits) {
            throw new IllegalArgumentException("the digits do not match.");
        }
    }

    private static int[] calculateStrikeAndBall(final String randStr, final String inputStr) {
        int[] counts = new int[2];

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

    private static void printGameResult(int strikeCnt, int ballCnt) {
        if (strikeCnt + ballCnt == 0) {
            System.out.println("낫싱");
            return;
        }

        if (strikeCnt == digits) {
            System.out.println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return;
        }

        System.out.printf("%d볼 %d스트라이크", ballCnt, strikeCnt);
    }

}
