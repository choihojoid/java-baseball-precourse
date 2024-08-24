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
    }

    private static String receiveNumber() throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        final String inputStr = bufferedReader.readLine();

        return inputStr;
    }

}
