package baseball;

import camp.nextstep.edu.missionutils.Console;

public final class BaseBallPlayer {

    private final String GUIDE_MSG = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private final String NOT_NUMBER_MSG = "숫자가 아닌 값을 입력하셨습니다.";
    private final String INVALID_DIGITS_MSG = "자릿수가 일치하지 않습니다.";

    private String inputStr;
    private Status status;

    private enum Status { INVALID, REPLAY, QUIT }

    private BaseBallPlayer() {}

    // 플레이어 객체는 자기 자신을 생성해서 반환한다.
    public static BaseBallPlayer getInstance() {
        return new BaseBallPlayer();
    }

    // 플레이어 객체는 입력한 숫자 문자열 반환한다.
    public String getInputStr() {
        return inputStr;
    }

    // 플레이어 객체는 digits 자릿수의 숫자 문자열을 입력한다.
    public void enter(final int digits) {
        final String inputStr = Console.readLine();

        validateInteger(inputStr);
        validateDigits(inputStr, digits);

        this.inputStr = inputStr;
    }

    // 숫자 문자열이 맞는지 확인한다.
    private void validateInteger(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }

    // 자릿수가 맞는지 확인한다.
    private void validateDigits(final String str, final int digits) {
        if (str.length() != digits) {
            throw new IllegalArgumentException(INVALID_DIGITS_MSG);
        }
    }

    // 플레이어 객체는 1 혹은 2를 입력받아 게임을 재시작할지 결정한다.
    public boolean askQuit() {
        String quitStr = null;

        do {
            System.out.println(GUIDE_MSG);
            quitStr = Console.readLine();

            status = fromValue(quitStr);
        } while (status == Status.INVALID);

        return status == Status.QUIT;
    }

    // 입력한 문자열로부터 Status를 반환한다.
    private Status fromValue(String quitStr) {
        switch (quitStr) {
            case "1":
                return Status.REPLAY;
            case "2":
                return Status.QUIT;
            default:
                return Status.INVALID;
        }
    }

    // 플레이어 객체는 상태로 가지고 있는 입력 문자열을 초기화한다.
    public void reset() {
        inputStr = null;
    }

}
