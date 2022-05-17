package Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void loadConfiguration() {
        Config.loadConfiguration();

        Config.getPort();

        assertNotNull( Config.getIp());
        assertNotNull( Config.getPort());
    }
}