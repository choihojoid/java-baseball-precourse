package baseball;

import camp.nextstep.edu.missionutils.Console;

public final class Player {

    private String inputStr;
    private Status status;

    private Player() {}

    private enum Status { INVALID, REPLAY, QUIT }

    public static Player getInstance() {
        return new Player();
    }

    public String getInputStr() {
        return this.inputStr;
    }

    public void enter(final int digits) {
        final String inputStr = Console.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr, digits);

        this.inputStr = inputStr;
    }

    public boolean askQuit() {
        String quitStr = null;

        do {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            quitStr = Console.readLine();

            status = fromValue(quitStr);
        } while (status == Status.INVALID);

        return status == Status.QUIT;
    }

    private Status fromValue(String quitStr) {
        switch (quitStr) {
            case "1": return Status.REPLAY;
            case "2": return Status.QUIT;
            default: return Status.INVALID;
        }
    }

    private void validateInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값을 입력하셨습니다.");
        }
    }

    private void validateDigits(final String str, final int digits) {
        if (str.length() != digits) {
            throw new IllegalArgumentException("자릿수가 일치하지 않습니다.");
        }
    }

}
