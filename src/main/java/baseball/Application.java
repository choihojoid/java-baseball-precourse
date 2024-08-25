package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final int minNum = 1;
    private static final int maxNum = 9;

    private static final int wrongMinNum = 100;
    private static final int wrongMaxNum = 999;

    private static final int digits = 3;

    public static void main(String[] args) {
        do {
            playBaseBall();
        } while (!checkQuit());
    }

    private static void playBaseBall() {
        final String randStr = getRandomNumberAsString();

        int strikeCnt = 0;
        int ballCnt = 0;

        do {
            final String inputStr = receiveNumberAsString();

            final int[] counts = calculateStrikeAndBall(randStr, inputStr);
            strikeCnt = counts[0];
            ballCnt = counts[1];

            printGameResult(strikeCnt, ballCnt);
        } while (!checkEnd(strikeCnt));
    }

    private static String getRandomNumberAsString() {
        return Stream.generate(() -> Randoms.pickNumberInRange(minNum, maxNum))
                .distinct()
                .limit(digits)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    // TODO: 1자리씩 뽑는 것이 아니라 한번에 3자리 수 뽑아서 반환하면 테스트할 때 timeout 발생하는 이유를 분석한다.
    private static String wrongGetRandomNumberAsString() {
        return Stream.generate(() -> Randoms.pickNumberInRange(wrongMinNum, wrongMaxNum))
                // .peek(System.out::println)
                .map(String::valueOf)
                .filter(Application::checkIncludeZero)
                .filter(Application::checkDifferentDigits)
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private static boolean checkIncludeZero(final String str) {
        return !str.contains("0");
    }

    private static boolean checkDifferentDigits(final String str) {
        final int size = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet())
                .size();

        return size == digits;
    }

    private static String receiveNumberAsString() {
        final String inputStr = Console.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr);

        return inputStr;
    }

    private static void validateInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값을 입력하셨습니다.");
        }
    }

    private static void validateDigits(final String str) {
        if (str.length() != digits) {
            throw new IllegalArgumentException("자릿수가 일치하지 않습니다.");
        }
    }

    private static int[] calculateStrikeAndBall(final String randStr, final String inputStr) {
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

    private static void printGameResult(final int strikeCnt, final int ballCnt) {
        if (strikeCnt + ballCnt == 0) {
            System.out.println("낫싱");
            return;
        }

        if (strikeCnt == digits) {
            System.out.println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return;
        }

        final StringBuilder stringBuilder = new StringBuilder();

        if (ballCnt != 0) {
            stringBuilder.append(String.format("%d볼", ballCnt)).append(" ");
        }

        if (strikeCnt != 0) {
            stringBuilder.append(String.format("%d스트라이크", strikeCnt));
        }

        System.out.println(stringBuilder);
    }

    private static boolean checkQuit() {
        String inputStr = null;

        do {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

            inputStr = Console.readLine();
        } while (!checkOneOrTwo(inputStr));

        return inputStr.equals("2");
    }

    private static boolean checkOneOrTwo(final String inputStr) {
        return inputStr.equals("1") || inputStr.equals("2");
    }

    private static boolean checkEnd(int strikeCnt) {
        return strikeCnt == digits;
    }

}
