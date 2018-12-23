import junit.framework.TestCase;
import org.junit.Test;

public class FizzBuzzTest extends TestCase {
    Game fb ;
    protected void setUp(){
        fb = new Game();
    }
    @Test
    public void testOneCount(){
        assertEquals("1", fb.start(1));
    }
    @Test
    public void testTwoCount(){ assertEquals("1, 2", fb.start(2)); }
    @Test
    public void testFizz() {
        assertEquals("1, 2, Fizz", fb.start(3));
    }

    @Test
    public void testBuzz() {
        assertEquals("1, 2, Fizz, 4, Buzz", fb.start(5));
    }
    @Test
    public void testFizzBuzz() {
        assertEquals("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz", fb.start(15)); }
    @Test
    public void testZeroCount(){ assertEquals("wrong input", fb.start(0)); }
    @Test
    public void testNegative() {
        assertEquals("wrong input", fb.start(-1));
    }
    @Test
    public void testOver100() {
        assertEquals("wrong input", fb.start(101));
    }
}
