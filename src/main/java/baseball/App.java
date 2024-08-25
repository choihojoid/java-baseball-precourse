package baseball;

public class App {

    public static void main(String[] args) {
        Bot bot = Bot.getInstance();
        Player player = Player.getInstance();

        Game game = Game.getInstance(bot, player);

        do {
            game.play();
            game.ask();
        } while (!game.isQuit());
    }

}
