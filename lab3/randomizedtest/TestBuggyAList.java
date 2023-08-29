package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> bug=new BuggyAList<>();
        AListNoResizing<Integer> al=new AListNoResizing<>();
        al.addLast(4);
        bug.addLast(4);
        al.addLast(5);
        bug.addLast(5);
        al.addLast(6);
        bug.addLast(6);
        assertEquals(bug.size(),al.size());
        assertEquals(bug.removeLast(),al.removeLast());
        assertEquals(bug.removeLast(),al.removeLast());
        assertEquals(bug.removeLast(),al.removeLast());
    }
    @Test
    public  void testing(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> b=new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                b.addLast(randVal);

            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = b.size();

                assertEquals(size,size1);
            }
            else if(operationNumber==2){
                if(L.size()==0) continue;
                int x=L.getLast();
                int y=b.getLast();

                assertEquals(x,y);
            }
            else if(operationNumber==3){
                if(L.size()==0)  continue;
                int x=L.removeLast();
                int y=b.removeLast();

                assertEquals(x,y);

            }
        }
    }
}
