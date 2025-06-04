package models;

import java.time.Duration;
import java.time.Instant;

/**
 * lihtne mänguaja mõõtja, mis võimaldab
 * aja käivitamist
 * peatamist
 * nullist uuesti alustamist
 * Aega mõõdetakse süsteemi kellaaja alusel
 */
public class GameTimer {
    private Instant startTime;
    private boolean running;
    private Duration duration = Duration.ZERO;

    public void start() {
        startTime = Instant.now(); // Alustame mänguaja mõõtmist praegusest hetkest
        running = true;
        duration = Duration.ZERO;
    }
    public void stop() {
        if(running && startTime != null) {
            duration = Duration.between(startTime, Instant.now());
        }
        running = false;

    }
    public boolean isRunning() {
        return running;
    }

    /**
     * tagastab kui palju aega on möödunud sekundites
     * @return aegf sekundites või 0
     */
    public int getElapsedSeconds() {
        if(startTime == null) {
            return 0;
        }
        if(running) {
            return (int) Duration.between(startTime, Instant.now()).getSeconds();
        }
        else {
            return (int) duration.getSeconds();
        }
    }

    /**
     * vormindab sekundid  kujule MM:SS ehk 25 sek = 00:25
     * @return vormindatud aeg
     */
    public String formatGameTime() {
        int totalSeconds = getElapsedSeconds();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
