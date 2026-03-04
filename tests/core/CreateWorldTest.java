package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Trida pro testovani loadWorld metody ve tride CreateWorld
 * Testuje mozne vysledky nacitani dat z jsonu
 */
class CreateWorldTest {

    private World world;
    private CreateWorld createWorld;

    @BeforeEach
    /**
     * Vytvori instance trid World a CreateWorld pred kazdym testem
     */
    void setUp() {
        world = new World();
        createWorld = new CreateWorld(world);
    }

    @Test
    /**
     * Testuje zda se nacetla data z platneho jsonu
     */
    void loadValidWorld() {
        World loaded = createWorld.loadWorld("/world.json");
        assertNotNull(loaded, "World by neměl být null");
        assertNotNull(loaded.getRooms(), "Místnosti by neměly být null");
        assertFalse(loaded.getRooms().isEmpty(), "Místnosti by neměly být prázdné");
        assertNotNull(loaded.getItems(), "Itemy by neměly být null");
        assertNotNull(loaded.getNpcs(), "NPC by neměly být null");
        assertNotNull(loaded.getQuests(), "Questy by neměly být null");
    }

    @Test
    /**
     * Testuje zda metoda vyhodi vyjimku kdyz json neexistuje
     */
    void loadMissingWorld() {
        assertThrows(RuntimeException.class, () -> {
            createWorld.loadWorld("/nonexisting.json");
        });
    }

    @Test
    /**
     * Testuje zda metoda vyhodi vyjimku kdyz jsou data v jsonu chybna
     */
    void loadBrokenWorld() {
        assertThrows(RuntimeException.class, () -> {
            createWorld.loadWorld("/broken.json");
        });
    }
}