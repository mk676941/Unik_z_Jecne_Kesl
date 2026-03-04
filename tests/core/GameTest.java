package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Trida pro testovani startCommand metody ve tride Game
 * Testuje zda metoda vraci ocekavany boolean
 */
class GameTest {

    private Game game;

    @BeforeEach
    /**
     * Vytvori instanci tridy Game pred kazdym testem
     */
    void setUp() {
        game = new Game();
    }

    @Test
    /**
     * Testuje zda metoda vraci true kdyz uzivatel zda platny command
     */
    void startVaidCommand() {
        String[] commands = {"backpack", "help"};
        for (String cmd : commands) {
            boolean result = game.startCommand(cmd);
            assertTrue(result, cmd + " Command by měl vracet true.");
        }
    }
//TODO rozsirit test na vsechny commandy

    @Test
    /**
     * Testuje zda metoda vraci false kdyz uzivatel zada exit command
     */
    void startExitCommand() {
        boolean result = game.startCommand("exit");
        assertFalse(result, "Exit command by měl vracet false.");
    }

    @Test
    /**
     * Testuje zda metoda vraci true kdyz uzivatel zada neplatny command
     */
    void startInvalidCommand() {
        boolean result = game.startCommand("neexistuje");
        assertTrue(result, "Neplatný command by měl vracet true.");
    }

    @Test
    /**
     * Testuje zda metoda vyplni mapu commandu
     */
    void registerCommands() {
        game.registerCommands();
        String[] expectedCommands = {"go", "take", "put", "talk", "explore", "backpack", "help", "exit", "map"};
        for (String cmd : expectedCommands) {
            assertTrue(game.getCommands().containsKey(cmd), "Mapa příkazů musí obsahovat: " + cmd);
        }
    }
}