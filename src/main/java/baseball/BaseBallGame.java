package baseball;

public final class BaseBallGame implements Playable {

    private final int minNum = 1;
    private final int maxNum = 9;
    private final int digits = 3;

    private final String STRIKE_MSG = "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private final String NOTHING_MSG = "낫싱";

    private final String BALL = "볼";
    private final String STRIKE = "스트라이크";

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
            resetSet();
            startSet();

            if (askQuit()) {
                status = Status.QUIT;
            }
        } while (status == Status.PLAY);
    }

    private void resetSet() {
        strikeCnt = 0;
        ballCnt = 0;

        baseBallBot.reset();
        baseBallPlayer.reset();
    }

    private void startSet() {
        baseBallBot.setRandStr(minNum, maxNum, digits);
        final String randStr = baseBallBot.getRandStr();

        do {
            baseBallPlayer.enter(digits);
            final String inputStr = baseBallPlayer.getInputStr();

            calculateStrikeAndBall(randStr, inputStr);

            printResult();
        } while (!isEnd());
    }

    private void calculateStrikeAndBall(final String randStr, final String inputStr) {
        for (int i = 0; i < randStr.length(); i++) {
            if (randStr.charAt(i) == inputStr.charAt(i)) {
                strikeCnt++;
                continue;
            }

            if (inputStr.contains(String.valueOf(randStr.charAt(i)))) {
                ballCnt++;
            }
        }
    }

    private void printResult() {
        if (strikeCnt == digits) {
            System.out.println(STRIKE_MSG);
            return;
        }

        if (strikeCnt == 0 && ballCnt == 0) {
            System.out.println(NOTHING_MSG);
            return;
        }

        final String resultStr = buildResultMessage();

        System.out.println(resultStr);
    }

    private String buildResultMessage() {
        final StringBuilder resultBuilder = new StringBuilder();

        if (ballCnt > 0) {
            resultBuilder.append(ballCnt).append(BALL).append(" ");
        }

        if (strikeCnt > 0) {
            resultBuilder.append(strikeCnt).append(STRIKE);
        }

        return resultBuilder.toString();
    }

    private boolean askQuit() {
        return baseBallPlayer.askQuit();
    }

    private boolean isEnd() {
        return strikeCnt == digits;
    }

}
