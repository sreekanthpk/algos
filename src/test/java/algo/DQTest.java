package algo;

import com.practice.algo.queue.DQ;
import com.practice.algo.queue.DQI;
import org.junit.Assert;
import org.junit.Test;


public class DQTest {


    @Test
    public void testInsertRear() {

    }

    @Test
    public void testInsertFront() {
        DQI<Integer> dq = new DQ<>(Integer.class, 100);
        dq.insertFront(10);
        Assert.assertEquals(new Integer(10), dq.peekFront());
    }

    @Test
    public void testRemoveRear() {

    }

    @Test
    public void testRemoveFront() {
    }

    @Test
    public void testSize() {

    }

    @Test
    public void testIsFull(){
    }

    @Test
    public void testIsEmpty() {

    }

    @Test
    public void testPeerRear() {

    }

    @Test
    public void testPeekFront() {

    }
}
