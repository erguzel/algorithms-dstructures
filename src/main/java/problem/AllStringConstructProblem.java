package problem;

import lib.model.ProblemBase;
import lib.util.ALogger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Write a function that accepts a target string and array of strings
 * return a 2d array containing all different combination of strings whihch can create the target string
 */

public class AllStringConstructProblem extends ProblemBase {


    private List<String> added = new ArrayList<>();

    public boolean allStringConstruct(String targetString, String[] strings,Set<String> result) {
        if(targetString=="")return true;
        counter++;
        String[] res = null;
        String cand = null;
        for(int i = 0; i < strings.length;i++){
            if(targetString.startsWith(strings[i])){
                int idx = i;
                cand = strings[idx];
                String remnant = targetString.substring(cand.length());
                String[] subset = Arrays.stream(strings).filter(x->!x.equals(strings[idx])).toArray(String[]::new);
                if(allStringConstruct(remnant,subset,result)){
                    result.add(cand);
                    return true;
                }
            }
        }

        return false;

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
