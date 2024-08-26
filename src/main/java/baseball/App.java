package baseball;

public class App {

    public static void main(String[] args) {
        final Playable game = BaseBallGame.getInstance();
        game.play();
    }

}
