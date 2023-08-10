package IntList;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrimesTest {
    @Test
    public void primesTest(){
        IntList lst=IntList.of(3,4,5,6,7,8);
        boolean changed=IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 4 -> 25 -> 6 -> 49 -> 8",lst.toString());
        assertTrue(changed);
    }
}
