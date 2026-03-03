package commands;
import core.Game;
import core.Item;
import core.Player;
import core.World;

public class HelpCommand implements Command {

    private Game game;
    private World world;

    public HelpCommand(Game game, World world) {
        this.game = game;
        this.world = world;
    }

    /**
     * Vypise napovedu
     * @param args - commandid
     * @return boolean
     */
    @Override
    public boolean execute(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Aby jsi úspěšně unikl z Ječné, potřebuješ odemknout hlavní vchod pomocí:\n>>> Karty hlavních dveří\n>>> Hesla hlavních dveří");
        System.out.println();
        System.out.println("Některé místnosti jsou nepřístupné. Ke vstupu potřebuješ buď nějaký item, nebo pomoc některého z NPC.");
        System.out.println();
        //vypis commandu
        System.out.println("Seznam všech commandů. Voláš je pomocí command id:");
        System.out.println("---------------------------------------------------");
        for (String commandId : game.getCommands().keySet()) {
            System.out.println(">>> command id: " + commandId);
        }
        System.out.println();
        //vypis itemu
        System.out.println("Seznam všech itemů. Pracuješ s nima pomocí item id:");
        System.out.println("---------------------------------------------------");
        for (String itemId : world.getItems().keySet()) {
            Item item = world.getItems().get(itemId);
            System.out.printf(">>> item id: %-15s item name: %s%n",
                    itemId,
                    item.getName());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        return true;
    }
}