package problem.lcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {

    public UniquePaths instance = new UniquePaths();
    @Test
    void testNumberOfUniquePaths() {
        int m1= 3; int n1 = 7; int want1 = 28;
        int m2= 3; int n2 = 2; int want2 = 3;

        assertAll("base",
                ()->assertEquals(instance.numberOfUniquePaths(m1,n1),want1));
    }
}