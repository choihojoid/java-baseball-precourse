package baseball;

import camp.nextstep.edu.missionutils.Console;

public final class Player {

    private String inputStr;

    private Player() {}

    public static Player getInstance() {
        return new Player();
    }

    public String getInputStr() {
        return this.inputStr;
    }

    public void enter(int digits) {
        final String inputStr = Console.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr, digits);

        this.inputStr = inputStr;
    }

    public boolean isQuit() {
        String quitStr = null;

        do {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

            quitStr = Console.readLine();
        } while (!checkOneOrTwo(quitStr));

        return quitStr.equals("2");
    }

    private boolean checkOneOrTwo(final String inputStr) {
        return inputStr.equals("1") || inputStr.equals("2");
    }

    private void validateInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값을 입력하셨습니다.");
        }
    }

    private void validateDigits(final String str, int digits) {
        if (str.length() != digits) {
            throw new IllegalArgumentException("자릿수가 일치하지 않습니다.");
        }
    }

}
