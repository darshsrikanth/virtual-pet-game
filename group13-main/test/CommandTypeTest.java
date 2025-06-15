package test;

import classes.CommandType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

    private CommandType commandType;

    @BeforeEach
    void setUp() {
        // create a new command type object before each test
        commandType = new CommandType();
    }

    @AfterEach
    void tearDown() {
        // clean up the object after each test
        commandType = null;
    }

    @Test
    void getFEED() {
        // checking if feed returns 0
        assertEquals(0, commandType.getFEED(), "FEED should return 0");
    }

    @Test
    void getGIFT() {
        // checking if gift returns 5
        assertEquals(5, commandType.getGIFT(), "GIFT should return 5");
    }

    @Test
    void getBED() {
        // checking if bed returns 1
        assertEquals(1, commandType.getBED(), "BED should return 1");
    }

    @Test
    void getVET() {
        // checking if vet returns 2
        assertEquals(2, commandType.getVET(), "VET should return 2");
    }

    @Test
    void getPLAY() {
        // checking if play returns 3
        assertEquals(3, commandType.getPLAY(), "PLAY should return 3");
    }

    @Test
    void getEXERCISE() {
        // checking if exercise returns 4
        assertEquals(4, commandType.getEXERCISE(), "EXERCISE should return 4");
    }
}
