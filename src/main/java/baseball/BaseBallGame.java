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

    private enum Status { PLAY, QUIT }

    private BaseBallGame(final Status status, final BaseBallBot baseBallBot, final BaseBallPlayer baseBallPlayer) {
        this.status = status;
        this.baseBallBot = baseBallBot;
        this.baseBallPlayer = baseBallPlayer;
    }

    // 게임 객체는 플레이어 객체와 봇 객체를 가져와서 참조하고 자기 자신을 반환한다.
    public static BaseBallGame getInstance() {
        BaseBallBot baseBallBot = BaseBallBot.getInstance();
        BaseBallPlayer baseBallPlayer = BaseBallPlayer.getInstance();
        return new BaseBallGame(Status.PLAY, baseBallBot, baseBallPlayer);
    }

    // 게임 객체는 게임을 진행시킨다.
    public void play() {
        do {
            resetSet();
            startSet();

            if (askQuit()) {
                status = Status.QUIT;
            }
        } while (status == Status.PLAY);
    }

    // 세트 시작 전에 초기화를 수행한다.
    private void resetSet() {
        strikeCnt = 0;
        ballCnt = 0;

        baseBallBot.reset();
        baseBallPlayer.reset();
    }

    // 세트를 시작한다.
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

    // 스트라이크, 볼을 계산한다.
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

    // 결과를 출력한다.
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

    // 세트가 끝났는지 확인한다.
    private boolean isEnd() {
        return strikeCnt == digits;
    }

    // 3스크라이크나 낫싱이 아닌 경우 문자열을 빌더로 구성한다.
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

    // 플레이어 객체에게 게임을 종료 여부를 물어본다.
    private boolean askQuit() {
        return baseBallPlayer.askQuit();
    }

}
