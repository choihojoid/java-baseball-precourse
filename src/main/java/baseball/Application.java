package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Application {

    private static final int minNum = 100;
    private static final int maxNum = 999;

    public static void main(String[] args) throws IOException {
        int randNum = Randoms.pickNumberInRange(minNum, maxNum);
        int inputNum = receiveNumber();
    }

    private static int receiveNumber() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String inputStr = bufferedReader.readLine();
        int inputNum = Integer.parseInt(inputStr);

        return inputNum;
    }

}
