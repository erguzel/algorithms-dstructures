package problem;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import problem.CoinChangeProblem;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeProblemTest extends ProblemBase {
    private CoinChangeProblem instance= new CoinChangeProblem();
    @Test
    void numberOfWaysToPrepareChange() {
        int n0 = 0;int want0 = 1;
        int n = 3;int want = 2;
        int n1 = 4;int want1 = 3;
        int n2 = 5;int want2 = 5;

        assertAll("normal",
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n0),want0),
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n),want),
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n1),want1),
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n2),want2)
        );
    }

    @Test
    void testNumberOfWaysToPrepareChange() {
        int n0 = 5; int want = 5; int [] denominations = {1,3,5,10};

        assertAll("normal",
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n0,denominations),want)
        );
    }

    @Test
    void testNumberOfWaysToPrepareChange1() {
        int n = 0; int[] denoms = {1,2,3,5}; int numOfCoins = 0; int want = 1;
        int n1 = 7; int[] denoms1 = {1,2,3,5}; int numOfCoins1 = 3; int want1 = 9;

        assertAll("regular",
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n,denoms,numOfCoins),want),
                ()->assertEquals(instance.numberOfWaysToPrepareChange(n1,denoms1,numOfCoins1),want1)
        );
    }

    @Disabled
    @Test
    void numberOfWaysToPrepareChangeWithEvenNumberOfCoins() {
        int n = 4; int[] denoms = {1,3,5,10};  int want = 3;
      //  int n1 = 6; int[] denoms1 = {1,3,5,10};  int want1 = 8;

        assertAll("regular",
                ()->assertEquals(instance.numberOfWaysToPrepareChangeWithEvenNumberOfCoins(n,denoms),want)
           //     ()->assertEquals(instance.numberOfWaysToPrepareChangeWithEvenNumberOfCoins(n1,denoms1),want1)
        );
    }

    @Test
    void numberOfWaysToPrepareChangeWithMinimumNumberOfCoins() {

        int n = 29; int[] denoms = {1,3,5};  int want = 7;
        int n1 = 1; int[] denoms1 = {2,3,5};  int want1 = -1;
        int n2 = 56; int[] denoms2 = {15,4,3};  int want2 = 6;

        assertAll("regular",
                ()->assertEquals(instance.numberOfWaysToPrepareChangeWithMinimumNumberOfCoins(n,denoms),want),
               ()->assertEquals(instance.numberOfWaysToPrepareChangeWithMinimumNumberOfCoins(n1,denoms1),want1),
                ()->assertEquals(instance.numberOfWaysToPrepareChangeWithMinimumNumberOfCoins(n2,denoms2),want2)
        );
    }

}