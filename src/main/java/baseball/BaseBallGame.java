package baseball;

public final class BaseBallGame implements Playable {

    private final int minNum = 1;
    private final int maxNum = 9;
    private final int digits = 3;
    private final BaseBallBot baseBallBot;
    private final BaseBallPlayer baseBallPlayer;

    private Status status;
    private int strikeCnt;
    private int ballCnt;

    private BaseBallGame(final Status status, final BaseBallBot baseBallBot, final BaseBallPlayer baseBallPlayer) {
        this.status = status;
        this.baseBallBot = baseBallBot;
        this.baseBallPlayer = baseBallPlayer;
    }

    public static BaseBallGame getInstance() {
        BaseBallBot baseBallBot = BaseBallBot.getInstance();
        BaseBallPlayer baseBallPlayer = BaseBallPlayer.getInstance();
        return new BaseBallGame(Status.PLAY, baseBallBot, baseBallPlayer);
    }

    private enum Status { PLAY, QUIT }

    public void play() {
        do {
            startSet();
            askQuit();
        } while (!isQuit());
    }

    private void startSet() {
        baseBallBot.setRandStr(minNum, maxNum, digits);

        do {
            baseBallPlayer.enter(digits);
            final String inputStr = baseBallPlayer.getInputStr();

            final int[] counts = baseBallBot.calculateStrikeAndBall(inputStr);

            this.strikeCnt = counts[0];
            this.ballCnt = counts[1];

            printResult();
        } while (!isEnd());
    }

    private void printResult() {
        if (strikeCnt == digits) {
            System.out.println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return;
        }

        if (strikeCnt == 0 && ballCnt == 0) {
            System.out.println("낫싱");
            return;
        }

        final String resultStr = buildResultMessage();

        System.out.println(resultStr);
    }

    private String buildResultMessage() {
        final StringBuilder resultBuilder = new StringBuilder();

        if (ballCnt > 0) {
            resultBuilder.append(String.format("%d볼", ballCnt)).append(" ");
        }

        if (strikeCnt > 0) {
            resultBuilder.append(String.format("%d스트라이크", strikeCnt));
        }

        return resultBuilder.toString();
    }

    private void askQuit() {
        if (baseBallPlayer.askQuit()) {
            this.status = Status.QUIT;
        }
    }

    private boolean isEnd() {
        return this.strikeCnt == digits;
    }

    private boolean isQuit() {
        return this.status == Status.QUIT;
    }

}
