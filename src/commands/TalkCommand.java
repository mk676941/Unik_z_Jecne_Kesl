package commands;
import core.*;

import java.util.Scanner;

/**
 * Command pro interakci s NPC.
 * Obsahuje execute pro interakci a plneni questu NPC.
 * @author Matej Kesl
 */
public class TalkCommand implements Command {

    private Player player;
    private World world;

    public TalkCommand(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Otevre menu interakce s NPC.
     * Obstarava praci s questy.
     * Umoznuje hraci ziskat pomoc od NPC.
     * @param args commandId
     * @return int
     */
    @Override
    public int execute(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        //kontrola prozkoumani
        if (!world.getRoom(player.getCurrentRoom()).isExplored()) {
            System.out.println("Místnost není prozkoumaná.");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            return 0;
        }

        Room room = world.getRoom(player.getCurrentRoom());

        //je v mistnosti npc?
        if (room.getNpcs().isEmpty()) {
            System.out.println("V místnosti: " + room.getName() + " není žádné NPC.");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            return 6;
        } else {
            //interakce s npc
            String keyNPC = room.getNpcs().keySet().iterator().next();

            System.out.println(world.getNPC(keyNPC).getName() + ": " + world.getNPC(keyNPC).getDialogue());;

            Scanner sc = new Scanner(System.in);

            boolean interaction = true;

            //hlavni loop interakce
            while (interaction) {
                System.out.println();
                System.out.println("Menu: " + world.getNPC(keyNPC).getName());
                System.out.println("---------------------------------------------------");
                System.out.println("1. Vypsat inventář");
                System.out.println("2. " + ((world.getNPC(keyNPC).getCanHelp())?"Požádat o pomoc":(world.getNPC(keyNPC).getRequiredItemId()!=null)?"Získat item":"Řešit úkol"));
                System.out.println("3. Exit");
                System.out.println();
                System.out.print("číslo možnosti>");

                String mainInput = sc.nextLine().toLowerCase();

                //vypsani inventare npc
                switch (mainInput) {
                    case "1":
                        System.out.println();
                        if (world.getNPC(keyNPC).getItems().isEmpty()) {
                            System.out.println("Inventář NPC je prázdný.");
                        } else {
                            System.out.println("Obsah inventáře NPC:");
                            System.out.println("---------------------------------------------------");
                            for (String itemId : world.getNPC(keyNPC).getItems().keySet()) {
                                System.out.printf(">>> item id: %-15s item name: %s%n",
                                        itemId,
                                        world.getItem(itemId).getName());
                            }
                        }
                        break;
                    //quest, help
                    case "2":
                        System.out.println();
                        if (world.getNPC(keyNPC).getCanHelp()) {
                            //help
                            if (!player.getHasHelp()) {
                                player.setHasHelp(true);
                                System.out.println("Dobrá, hned jdu odsunout tu skříň, která blokuje dílnu.");
                            } else {
                                System.out.println("Už jsem skříň odsunul.");
                            }
                        } else {
                            //odmena
                            boolean canWork = true;
                            if (player.isInventoryFull() && world.getNPC(keyNPC).getRequiredItemId() == null) {
                                System.out.println("Nemůžeš získat odměnu. Nemáš místo v batohu.");
                                canWork = false;
                            }
                            if (world.getNPC(keyNPC).getItems().isEmpty()) {
                                System.out.println("Odměnu jsi už získal.");
                                canWork = false;
                            }

                            if (canWork) {
                                if (world.getNPC(keyNPC).getRequiredItemId() != null) {
                                    //pozadovani item
                                    if (!player.hasItem(world.getNPC(keyNPC).getRequiredItemId())) {
                                        System.out.print("Nemůžeš získat můj item. Nemáš v inventáři na výměnu: ");
                                        System.out.printf("item id: %-15s item name: %s%n",
                                                world.getItem(world.getNPC(keyNPC).getRequiredItemId()),
                                                world.getItem(world.getNPC(keyNPC).getRequiredItemId()).getName());
                                    } else {
                                        //pridani odmeny
                                        String rewardId = world.getNPC(keyNPC).getItems().keySet().iterator().next();
                                        player.removeItem(world.getNPC(keyNPC).getRequiredItemId());
                                        player.addItem(rewardId);
                                        world.getNPC(keyNPC).getItems().remove(rewardId);
                                        System.out.println("Odměna byla přidána do tvého batohu.");
                                    }
                                } else {
                                    //quest
                                    boolean working = true;
                                    //quest menu
                                    while (working) {
                                        String questId = world.getNPC(keyNPC).getQuests().keySet().iterator().next();
                                        System.out.println("Úkol: " + world.getQuest(questId).getQuestText());
                                        System.out.println("---------------------------------------------------");
                                        System.out.println("1. Odpovědět");
                                        System.out.println("2. Exit");
                                        System.out.println();
                                        System.out.print("číslo možnosti>");

                                        String input = sc.nextLine().toLowerCase();

                                        switch (input) {
                                            //odpoved na quest, pridani itemu
                                            case "1":
                                                System.out.println();
                                                System.out.print("odpověď>");
                                                String answer = sc.nextLine().toLowerCase();
                                                if (answer.equals(world.getQuest(questId).getAnswer())) {
                                                    System.out.println();
                                                    System.out.println("Správná odpověd!");
                                                    System.out.println("Odměna byla přidána do tvého batohu.");
                                                    String rewardId = world.getNPC(keyNPC).getItems().keySet().iterator().next();
                                                    player.addItem(rewardId);
                                                    world.getNPC(keyNPC).getItems().remove(rewardId);
                                                    working = false;
                                                } else {
                                                    System.out.println("Špatná odpověď. Zkus to znovu.");
                                                }
                                                break;
                                            case "2":
                                                //exit quest
                                                working = false;
                                                break;
                                            default:
                                                System.out.println("Neplatný vstup.");
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    //exit
                    case "3":
                        interaction = false;
                        System.out.println("----------------------------------------------------------------------------------------------------------------");
                        break;
                    default:
                        System.out.println("Neplatný vstup.");
                }
            }
        }
        return 0;
    }
}