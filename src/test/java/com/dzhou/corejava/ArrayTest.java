package com.dzhou.corejava;

/**
 * Created by huizhou on 6/28/14.
 */
import com.dzhou.corejava.dsaj.ch2.*;

import org.mockito.Mockito;
import org.testng.annotations.*;
import org.testng.Assert;

public class ArrayTest {

    @Test
    public void testSortedArray() {
        SortedArray <PhoneListing> NYC = new SortedArray<PhoneListing>(10);

        PhoneListing bob = new PhoneListing("Bob", "23 1st Avenue",
                "133-4573");
        PhoneListing roy = new PhoneListing("Roy", "421 east 24th Street",
                "897-2232");

        PhoneListing bob2 = new PhoneListing("Bob", "24 1st Avenue",
                "133-4573");

        PhoneListing roy2 = new PhoneListing("Roy", "422 east 24th Street",
                "897-2232");

        PhoneListing dave = new PhoneListing("David", "24 1st Avenue",
                "133-4573");

        PhoneListing dave1 = new PhoneListing("David", "25 1st Avenue",
                "133-4573");

        NYC.insert(bob);
        NYC.insert(roy);
        NYC.insert(bob2);
        NYC.insert(roy2);
        NYC.insert(dave);
        NYC.insert(dave1);

        NYC.showAll();
    }
}
