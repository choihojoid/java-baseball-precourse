package baseball;

public class App {

    public static void main(String[] args) {
        final Bot bot = Bot.getInstance();
        final Player player = Player.getInstance();

        final Game game = Game.getInstance(bot, player);

        do {
            game.play();
            game.ask();
        } while (!game.isQuit());
    }

}
