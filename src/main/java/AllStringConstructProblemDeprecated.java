import lib.model.ProblemBase;
import lib.util.ALogger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a function that accepts a target string and array of strings
 * return a 2d array containing all different combination of strings whihch can create the target string
 */

public class AllStringConstructProblemDeprecated extends ProblemBase {

    public static void main(String[] args) {
        String target = "abcdef";
        String[] targs = {"ab", "abc", "cd", "def", "abcd"};
        String target1 = "enterapotentpot";
        String[] targs1 = {"a", "p", "ent", "enter", "ot", "o", "t"};
        String target2 = "eeeeeeeeeeeeeeeeeeeeeeeeef";
        String[] targs2 = {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        String target3 = "skateboard";
        String[] targs3 = {"bo", "rd", "ate", "t", "ska", "sk", "boar"};
        String target4 = "purple";
        String[] targs4 = {"purp", "p", "ur", "le", "purpl"};
        String target5 = "olgun";
        String[] targs5 = {"gu", "un", "g", "ol", "olgu", "n"};
        String[] target6 = {"1", "2", "3", "4"};


        new Thread(() -> {
            AllStringConstructProblemDeprecated grr = new AllStringConstructProblemDeprecated();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            Set<Set<Object>> num = grr.allStringConstructX(target4, Arrays.stream(targs4).collect(Collectors.toList()));
            grr.LOGGER.info("AllStringConstructProblemRes:" + num);
            grr.LOGGER.info("AllStringConstructProblemCalls:" + grr.counter);
            timer.getBenchmark(timer, "AllStringConstructProblem");
        }).start();
    }





    public List allStringConstruct(String targetString, List<String> strings) {
        if (targetString == "") return new ArrayList();
        if (targetString == null) return null;

        List result = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {

            final String finalVal = (String) strings.get(i);
            if (targetString.indexOf(finalVal) == 0) {
                String remnant = targetString.substring(finalVal.toString().length());
                List suffixways = allStringConstruct(remnant, strings);
                suffixways.add(List.of(finalVal));
                result.add(suffixways);
                //   List<List<String>> targetWays = suffixways.stream().map(a-> Stream.concat(a.stream(),Stream.of(finalVal)).collect(Collectors.toList())).collect(Collectors.toList());
                //  targetWays.forEach(a->result.add(a));

            }

        }

        return result;
    }

    /**
     * TODO:INCOMPLETE
     *
     * @param target
     * @param words
     * @return
     */
    public Set<Set<Object>> allStringConstructX(String target, List words) {
        if (target == "") return new HashSet<Set<Object>>();

        Set<Set<Object>> result = new HashSet<Set<Object>>();

        for (Object word : words) {
            // remove index
            if (target.startsWith(word.toString())) {
                String sliced = target.substring(word.toString().length());
                Set<Set<Object>> returned = allStringConstructX(sliced, words);
                if (returned.size() == 0) {
                    Set<Object> kss = new HashSet<>(Arrays.asList(word));
                    returned.add(kss);
                } else {
                    for (Set<Object> dsd : returned) {
                        dsd.add(word);
                    }
                }
                //Set<Object> kss = new HashSet<>(Arrays.asList(sliced));
//                returned.add(kss);
                result.addAll(returned);
            }
        }

        return result;
    }

}
