import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class AllStringConstructProblemTest {

    private AllStringConstructProblem instance = new AllStringConstructProblem();

    @Test
    void allStringConstruct() {
        String target = "cozul";
        String[] strings = {"zu", "aa","kk","oz","c", "k","um"};
        String[] want = {"a", "bc", "f", "de"};
        String target5 = "olgun";
        String[] targs5 = {"gu", "un", "g", "ol", "olgu", "n"};
        String target3 = "skateboard";
        String[] targs3 = {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        Set<String> rr = new HashSet<>();
        String[] res = rr.stream().toArray(String[]::new);
        instance.allStringConstruct(target5,targs5,rr);
        System.out.println(instance.counter);
        assertAll("simple",
                ()->assertArrayEquals(res,want));

    }
}