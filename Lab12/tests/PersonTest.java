import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    static PersonDirectory directory;

    @Test
    @DisplayName("Test caulculateAge")
    void calculateAgeTest() {
        Person person = new Person("Curly", new Date(2004, 1, 1));
        assertEquals(20, person.calculateAge());
    }

    @BeforeAll
    @DisplayName("Set up directory")
    static void setUp() {
        directory = new PersonDirectory(new ArrayList<>());
        directory.getPeople().add(new Person("Moe", new Date(2000, 1, 1)));
        directory.getPeople().add(new Person("Larry", new Date(2001, 1, 1)));
        directory.getPeople().add(new Person("Shemp", new Date(2002, 1, 1)));
    }

    @Test
    @DisplayName("Test calculateAverageAge")
    void calculateAverageAgeTest() {
        assertEquals(23, directory.calculateAverageAge());
    }

    @AfterAll
    @DisplayName("Tear down directory")
    static void tearDown() {
        directory = null;
    }
}