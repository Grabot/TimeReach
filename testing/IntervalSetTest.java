//import junit.framework.TestCase;
import com.pholser.junit.quickcheck.generator.InRange;
import tue.data.Interval;
import tue.data.IntervalSet;

import com.pholser.junit.quickcheck.ForAll;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assume.*;
import static org.junit.Assert.*;

import org.junit.contrib.theories.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class IntervalSetTest {
    @Theory public void concatenatedStringLength(@ForAll @InRange(min = "0") Integer start, @ForAll @InRange(min = "0") Integer end) {
        if(start > end) return;

        IntervalSet s1 = new IntervalSet();
        s1.addInterval(new Interval(start, end));

        for (Integer i = 0; i < start-1; i++) {
            assertFalse(s1.covers(i));
        }

        for (Integer i = start; i < end-1; i++) {
            assertTrue(s1.covers(i));
        }

        for (Integer i = end+1; i < end+100; i++) {
            assertFalse(s1.covers(i));
        }

//        assertEquals(s1.length() + s2.length(), (s1 + s2).length());

    }
//    IntervalSet set;
//
//    public void setUp() throws Exception {
//
//        set = new IntervalSet();
//        set.addInterval(new Interval(0,2));
//    }
//
//    public void testCross() throws Exception {
//
//    }
//
//    public void testPlus() throws Exception {
//
//    }
//
//    public void testCovers() throws Exception {
//        assertTrue(set.covers(0));
//        assertTrue(set.covers(1));
//        assertTrue(set.covers(2));
//        assertFalse(set.covers(3));
//    }
//
//    public void testCovers1() throws Exception {
//        IntervalSet set2 = new IntervalSet();
//        set2.addInterval(new Interval(0,2));
//        IntervalSet set3 = new IntervalSet();
//        set3.addInterval(new Interval(3,4));
//
//    }
//
//    public void testAddInterval() throws Exception {
//
//    }
}