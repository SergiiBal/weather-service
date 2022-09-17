import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {
    @Test
    void stringHelloName() {
        var stringObject = new HelloWorld();
        assertEquals("Hello Ostap", stringObject.printName("Ostap"));
    }

    @Test
    void stringHelloName2() {
        var stringObject = new HelloWorld();
        assertEquals("Hello Sergii", stringObject.printName("Sergii"));
    }


}