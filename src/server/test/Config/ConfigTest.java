package Config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void loadConfiguration() {
        Config.loadConfiguration();
        assertNotNull(Config.dbConnection);
        assertNotNull(Config.port);
    }
}