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

    private Game(Status status, Bot bot, Player player) {
        this.status = status;
        this.bot = bot;
        this.player = player;
    }

    public static Game getInstance(Bot bot, Player player) {
        return new Game(Status.PLAY, bot, player);
    }

    private enum Status { PLAY, QUIT }

    public void play() {
        bot.setRandStr(minNum, maxNum, digits);

        do {
            player.enter(digits);
            String inputStr = player.getInputStr();

            int[] counts = bot.calculateStrikeAndBall(inputStr);

            this.strikeCnt = counts[0];
            this.ballCnt = counts[1];

            printResult();
        } while (!isEnd());
    }

    public void printResult() {
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

    public void ask() {
        if (player.isQuit()) {
            this.status = Status.QUIT;
        }
    }

    private boolean isEnd() {
        return this.strikeCnt == digits;
    }

    public boolean isQuit() {
        return this.status == Status.QUIT;
    }

}
