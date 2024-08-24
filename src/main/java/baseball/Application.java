package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static final int minNum = 100;
    private static final int maxNum = 999;
    private static final int digits = 3;

    public static void main(String[] args) throws IOException {
        String randStr = generateNumber();
        String inputStr = receiveNumber();

        int strikeCnt = calculateStrike(randStr, inputStr);
    }

    private static String generateNumber() {
        final int randNum = Randoms.pickNumberInRange(minNum, maxNum);

        final String randStr = String.valueOf(randNum);

        return randStr;
    }

    private static String receiveNumber() throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final String inputStr = bufferedReader.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr);

        return inputStr;
    }

    private static void validateInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("a value other than a number has been entered.");
        }
    }

    private static void validateDigits(String str) {
        if (str.length() != digits) {
            throw new IllegalArgumentException("the digits do not match.");
        }
    }

    private static int calculateStrike(final String randStr, final String inputStr) {
        int cnt = 0;

        for (int i = 0; i < randStr.length(); i++) {
            if (randStr.charAt(i) == inputStr.charAt(i)) {
                cnt++;
            }
        }

        return cnt;
    }

}
