package deprecated;

import deprecated.CoinChangeProblemDeprecated;
import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeProblemDeprecatedTest extends ProblemBase {

    private CoinChangeProblemDeprecated instance = new CoinChangeProblemDeprecated();

    @Test
    void testNumberOfWaysOfpayment() {
        int n0 = 0;int want0 = 1;
        int n = 3;int want = 2;
        int n1 = 4;int want1 = 3;
        int n2 = 5;int want2 = 5;

        assertAll("normal",
                ()->assertEquals(instance.numberOfWaysOfpayment(n0),want0),
                ()->assertEquals(instance.numberOfWaysOfpayment(n),want),
                ()->assertEquals(instance.numberOfWaysOfpayment(n1),want1),
                ()->assertEquals(instance.numberOfWaysOfpayment(n2),want2)
        );
    }

    @Test
    void testNumberOfWaysOfpaymentWithDenominations() {
        int n0 = 5; int want = 5; int [] denominations = {1,3,5,10};

        assertAll("normal",
                ()->assertEquals(instance.numberOfWaysOfPaymentWithDenominations(n0,denominations),want)
        );
    }

    @Test
    void TestNumberOfWaysOfPaymentWithDenominationsWithNumberOfCoins() {
        int n = 0; int[] denoms = {1,2,3,5}; int numOfCoins = 0; int want = 1;
        int n1 = 7; int[] denoms1 = {1,2,3,5}; int numOfCoins1 = 3; int want1 = 9;

        assertAll("regular",
                ()->assertEquals(instance.numberOfWaysOfPaymentWithDenominationsWithNumberOfCoins(n,denoms,numOfCoins),want),
                ()->assertEquals(instance.numberOfWaysOfPaymentWithDenominationsWithNumberOfCoins(n1,denoms1,numOfCoins1),want1)
        );

    }

    @Test
    void TestNumberOfWaysOfPaymentWithDenominationsUsingEvenNumberOfCoins() {
        int n = 4; int[] denoms = {1,3,5,10};  int want = 3;
        int n1 = 6; int[] denoms1 = {1,3,5,10};  int want1 = 8;

        assertAll("regular",
                ()->assertEquals(instance.numberOfWaysOfPaymentWithDenominationsUsingEvenNumberOfCoins(n,denoms),want),
                ()->assertEquals(instance.numberOfWaysOfPaymentWithDenominationsUsingEvenNumberOfCoins(n1,denoms1),want1)
        );
    }
}