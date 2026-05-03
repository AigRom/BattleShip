# README.md

# Ülesanded
1. Edetabelit ei saa vaadata mängu ajal.
2. Mängulaua suurust ei saa muuta mängu ajal.
3. Mängu lõppedes ei kao lõppseis ekraanilt.
4. Kui muudetakse mängulaua suurust, siis eelmise mängu seis muutub.(hetkel jookseb kokku)
5. Kogu mänguaknal võiks olla miinimumsuurus millest väiksemaks ei saa
(arvesta mängulaua suurusega, kui ei saa siis proovi lihtsamat).
6. Kui võtta ära linnuke "Eraldi aknas", siis peab edetabel tekkima mängu peale(eraldi paneeli - JFrame peal eraldi JPanel(vihje - flow layoutmanager) loomine koos sulgemisega). 
7. DB Käivitamine IntelliJ-s
    
    Projekt kasutab SQLite andmebaasi tulemuste salvestamiseks.
    
    Kui andmebaasi salvestamine ei tööta ja tekib viga:
    "No suitable driver found for jdbc:sqlite:scores.db",
    siis kontrolli, et SQLite JDBC driver oleks lisatud:
    
    File → Project Structure → Modules → Dependencies → + → JARs or Directories → lib/sqlite-jdbc-3.53.0.0.jar
    
    Pärast seda käivita App.java uuesti.