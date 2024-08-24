package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static final int minNum = 100;
    private static final int maxNum = 999;

    public static void main(String[] args) throws IOException {
        int randNum = Randoms.pickNumberInRange(minNum, maxNum);
        String randStr = String.valueOf(randNum);

        String inputStr = receiveNumber();

        int strikeCnt = calculateStrike(randStr, inputStr);
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

    private static String receiveNumber() throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final String inputStr = bufferedReader.readLine();

        return inputStr;
    }

}
