# Únik z Ječné

**"Únik z Ječné"** je textová hra napsaná v programovacím jazyce Java.  
Hrajete jako student, který zůstal déle ve škole, aby dokončil projekt.  
Škola se ale už uzamkla, některé místnosti jsou uzamčeny a většina lidí už odešla.  
Vaším cílem je uniknout ze školy hlavním vchodem.

## Herní mechaniky
- Prozkoumávání různých místností  
- Interakce s NPC  
- Plnění úkolů  
- Sbírání a odkládání předmětů  
- Odemknutí hlavního vchodu pomocí karty a hesla

## Ovládání hry (Commandy)
Hru ovládáte zadáváním **command ID** do konzole:

| Command ID       | Popis                                               |
|-----------------|----------------------------------------------------|
| `go <směr>`      | Přesun mezi místnostmi                             |
| `map`            | Vykreslení mapy                                     |
| `help`           | Výpis nápovědy                                      |
| `exit`           | Ukončení hry                                        |
| `explore`        | Prozkoumání aktuální místnosti                     |
| `take <item ID>` | Sebrání předmětu z místnosti                        |
| `put <item ID>`  | Odložení předmětu v místnosti                       |
| `talk`           | Interakce s NPC (dále se ovládá pomocí čísel)     |
| `backpack`       | Výpis obsahu batohu                                 |

## Předměty ve hře
Každý předmět má své **ID** a název:

| Item ID    | Název předmětu                     |
|------------|----------------------------------|
| `heslo`    | Heslo hlavních dveří              |
| `karta`    | Karta hlavních dveří              |
| `pacidlo`  | Páčidlo                           |
| `kod`      | 3-číselný kód                     |
| `klic`     | Klíč od kabinetu                  |
| `kyselina` | Kyselina sírová                   |

## Jak spustit JAR soubor hry
Ke spuštění hry potřebujete mít nainstalovanou Java verzi 25.0.2.
1. Stáhněte si JAR soubor hry
2. Otevřete příkazový řádek
3. Hru spusťte pomocí příkazu:

```bash
java -jar Kesl-JecnaGame.jar
```

## Načítání herních dat z vlastního JSON souboru skrze InteliJ Idea
- Hru lze načítat z vlastního JSON souboru.
- Je poté nutné hru znovu zkompilovat do JAR souboru pro spuštění.
- JSON musí dodržet **formát původního souboru** (např. správný datový typ: String, Array, Object).  
- Umístěte vlastní JSON do složky `resources` a ve třídě `Game` upravte vstupní parametr metody:
  ```java
  loadWorld("/nazevvlastniho.json");
  ```
![alt text](https://github.com/mk676941/Kesl---Game/blob/master/resourcepath.png)
