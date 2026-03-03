package commands;

import core.Player;
import core.World;

public class MapCommand implements Command {

    private World world;
    private Player player;

    public MapCommand(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    /**
     * Vypise mapu sveta
     * @param args - commandid
     * @return boolean
     */
    @Override
    public boolean execute(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        System.out.println("   +-------------------+   +-----------------+   +-------------------+\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   |      Kabinet      |   | Kmenová učebna  |   | Počítačová učebna |\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   |                 JV|   |        J        |   |JZ                 |\n" +
                "   +-------------------+   +-----------------+   +-------------------+\n" +
                "                       \\            |             /\n" +
                "                         \\          |           /\n" +
                "                           \\        |         /\n" +
                "+-----------------+        +-----------------+        +-----------------+\n" +
                "|                 |        |SZ      S      SV|        |                 |\n" +
                "|                 |        |                 |        |                 |\n" +
                "|      Dílna     V|--------|Z     Chodba    V|--------|Z    Laborka     |\n" +
                "|                 |        |                 |        |                 |\n" +
                "|                 |        |JZ      J      JV|        |                 |\n" +
                "+-----------------+        +-----------------+        +-----------------+\n" +
                "                          /         |         \\\n" +
                "                        /           |           \\\n" +
                "                      /             |             \\\n" +
                "   +-------------------+   +-----------------+   +-------------------+\n" +
                "   |                 SV|   |        S        |   |SZ                 |\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   |     Vrátnice      |   |  Hlavní vchod   |   | Serverová učebna  |\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   |                   |   |                 |   |                   |\n" +
                "   +-------------------+   +-----------------+   +-------------------+");
        System.out.println();
        //kde je hrac
        System.out.println("Nacházíš se v: " + world.getRoom(player.getCurrentRoom()).getName());
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        return true;
    }
}
