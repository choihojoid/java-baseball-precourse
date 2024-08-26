package baseball;

public final class Game {

    private final int minNum = 1;
    private final int maxNum = 9;
    private final int digits = 3;
    private final Bot bot;
    private final Player player;

    private Status status;
    private int strikeCnt;
    private int ballCnt;

    private Game(final Status status, final Bot bot, final Player player) {
        this.status = status;
        this.bot = bot;
        this.player = player;
    }

    public static Game getInstance() {
        Bot bot = Bot.getInstance();
        Player player = Player.getInstance();
        return new Game(Status.PLAY, bot, player);
    }

    private enum Status { PLAY, QUIT }

    public void play() {
        do {
            startSet();
            askQuit();
        } while (!isQuit());
    }

    private void startSet() {
        bot.setRandStr(minNum, maxNum, digits);

        do {
            player.enter(digits);
            final String inputStr = player.getInputStr();

            final int[] counts = bot.calculateStrikeAndBall(inputStr);

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
        if (player.askQuit()) {
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
