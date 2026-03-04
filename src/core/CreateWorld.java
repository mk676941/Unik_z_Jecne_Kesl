package core;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Trida pro vytvareni sveta
 * Obsahuje metody pro praci s json souborem
 * Obsahuje metodu pro zakladni naplneni sveta
 */
public class CreateWorld {
    private World world;

    public CreateWorld(World world) {
        this.world = world;
    }

    /**
     *Vyplni zakladni svet
     */
    public void fillWorld () {
        //mistnosti
        world.addRoom(new Room("kmenovaucebna", "Kmenová učebna", "Výchozí místnost hráče"));
        world.addRoom(new Room("chodba", "Chodba", "Hlavní propojovací prostor"));
        world.addRoom(new Room("pocitacovaucebna", "Počítačová učebna", "beep-beep-beep"));
        world.addRoom(new Room("laborka", "Laborka", "Smradlavá místnost chemie"));
        world.addRoom(new Room("dilna", "Dílna", "Dílna plná harampádí"));
        world.addRoom(new Room("kabinet", "Kabinet", "Kabinet zlé třídní"));
        world.addRoom(new Room("serverovaucebna", "Serverová učebna", "Školní serverovna"));
        world.addRoom(new Room("vratnice", "Vrátnice", "Školníkuv bejvák"));
        world.addRoom(new Room("hlavnivchod", "Hlavní vchod", "Úniková cesta"));

        //itemy
        world.addItem(new Item("pacidlo", "Páčidlo"));
        world.addItem(new Item("kyselina", "Kyselina sírová"));
        world.addItem(new Item("klic", "Klíč od kabinetu"));
        world.addItem(new Item("karta", "Karta hlavních dveří"));
        world.addItem(new Item("kod", "3-číselný kód"));
        world.addItem(new Item("heslo", "Heslo hlavních dveří"));

        //npc
        world.addNPC(new NPC("studentnachodbe", "Student na chodbě", "Ahoj, jak ti mohu pomoci?"));
        world.addNPC(new NPC("studentvpocitacoveucebne", "Student v počítačové učebně", "Ahoj. Jestli chceš získat 3-číselný kód, budeš si ho muset zasloužit."));
        world.addNPC(new NPC("spravcedilny", "Správce dílny", "Konečně někdo odsunul tu skřín! Děkuji, chceš získat páčidlo?"));
        world.addNPC(new NPC("spravceserveroveucebny", "Správce serverové učebny", "Koukám že jsi zjistil kód. Zvládneš další úlohu pro získání hesla?"));
        world.addNPC(new NPC("skolnik","Školník", "Arghh... Jestli chceš klíč od kabinetu tak mi přines kyselinu z laborky"));

        //questy
        world.addQest(new Quest("pcstudent", "Na monitoru svítí nápis: Pro přístup zadej správné slovo.\n" +
                "Nápověda: Jsem začátek i konec abecedy. Bez mě bys nic nenapsal. Co jsem?\n" +
                "Zadej odpověď jedním slovem.", "pismeno"));
        world.addQest(new Quest("spravcedilny", "Správce dílny: Než ti něco dám, řekni mi jednu věc.\n" +
                "Jaký předmět si bereš na ruce, když pracuješ v dílně?\n" +
                "Napiš odpověď jedním slovem.", "rukavice"));
        world.addQest(new Quest("spravceserverovny", "Správce serverovny: Chci si být jistý, že chápeš, co je to heslo.\n" +
                "Otázka: Co je silnější: heslo 123456 nebo K0c0ur!2026?\n" +
                "Napiš pouze: A pro první, nebo B pro druhé.", "b"));

        //umisteni questu
        world.getNPC("studentvpocitacoveucebne").addQuest("pcstudent");
        world.getNPC("spravcedilny").addQuest("spravcedilny");
        world.getNPC("spravceserveroveucebny").addQuest("spravceserverovny");

        //umisteni npc
        world.getRoom("chodba").addNpc("studentnachodbe");
        world.getRoom("pocitacovaucebna").addNpc("studentvpocitacoveucebne");
        world.getRoom("dilna").addNpc("spravcedilny");
        world.getRoom("serverovaucebna").addNpc("spravceserveroveucebny");
        world.getRoom("vratnice").addNpc("skolnik");

        //umisteni itemu
        world.getNPC("studentvpocitacoveucebne").addItem("kod");
        world.getRoom("laborka").addItem("kyselina");
        world.getNPC("spravcedilny").addItem("pacidlo");
        world.getRoom("kabinet").addItem("karta");
        world.getNPC("spravceserveroveucebny").addItem("heslo");
        world.getNPC("skolnik").addItem("klic");

        //exity
        world.getRoom("kmenovaucebna").addExit("J", "chodba");
        world.getRoom("pocitacovaucebna").addExit("JZ","chodba");
        world.getRoom("laborka").addExit("Z", "chodba");
        world.getRoom("dilna").addExit("V", "chodba");
        world.getRoom("kabinet").addExit("JV", "chodba");
        world.getRoom("serverovaucebna").addExit("SZ", "chodba");
        world.getRoom("vratnice").addExit("SV", "chodba");
        world.getRoom("hlavnivchod").addExit("S","chodba");

        world.getRoom("chodba").addExit("S", "kmenovaucebna");
        world.getRoom("chodba").addExit("SV", "pocitacovaucebna");
        world.getRoom("chodba").addExit("V", "laborka");
        world.getRoom("chodba").addExit("Z", "dilna");
        world.getRoom("chodba").addExit("SZ", "kabinet");
        world.getRoom("chodba").addExit("JV", "serverovaucebna");
        world.getRoom("chodba").addExit("JZ", "vratnice");
        world.getRoom("chodba").addExit("J", "hlavnivchod");

        //pomoc
        world.getNPC("studentnachodbe").setCanHelp(true);
        world.getNPC("skolnik").setRequiredItemId("kyselina");

        //blokovani
        world.getRoom("laborka").setBlocked(true, "pacidlo", false);
        world.getRoom("dilna").setBlocked(true, null, true);
        world.getRoom("kabinet").setBlocked(true, "klic", false);
        world.getRoom("serverovaucebna").setBlocked(true, "kod", false);
    }

    /**
     * Ulozi json soubor ze vsech parametru
     * Pouze pro ulozeni zakladniho jsonu ve resources
     */
    public void saveWorld() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(world);
        try {
            Files.write(Paths.get("./resources/world.json"),
                    json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Nacte data sveta z json souboru
     * @param resourcePath - zadany nazev json souboru
     * @return vyplneny world
     */
    public World loadWorld(String resourcePath) {
        Gson gson = new Gson();
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " - Zkontrolujte, že soubor je v resources a před cestou je /.");
            }
            return world = gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), World.class);
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }
}