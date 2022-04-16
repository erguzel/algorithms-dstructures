package problem;

import lib.model.ProblemBase;

import java.util.*;
import java.util.stream.Collectors;

public class AllSubArraysProblem extends ProblemBase {

    public Set<Set<Object>> allSubArrays(List target) {
        if (target.size() == 0) return new HashSet<Set<Object>>();

        Set<Set<Object>> result = new HashSet<Set<Object>>();

        for (int i = 0; i < target.size(); i++) {
            // remove index
            List reduced = new ArrayList(target);
            Object removed = reduced.remove(i);
            Set reducedSet = (Set) reduced.stream().collect(Collectors.toSet());
            Set<Set<Object>> returned = allSubArrays(reduced.stream().toList());
            if (!returned.contains(reduced)) {
                returned.add(reducedSet);
            }
            Set aan = new HashSet(Arrays.asList(removed));
            if (!returned.contains(aan)) {
                returned.add(aan);
            }
            result.addAll(returned);
        }

        return result;
    }

    public Set<Set<Object>> allSubArrays1(List target, Set<Set<Object>> mem) {
        counter++;
        if (target.size() == 0) return new HashSet<Set<Object>>();

        Set<Set<Object>> result = new HashSet<Set<Object>>();

        for (int i = 0; i < target.size(); i++) {
            // remove index
            List reduced = new ArrayList(target);
            Object removed = reduced.remove(i);
            Set reducedSet = (Set) reduced.parallelStream().collect(Collectors.toSet());
            if (!mem.contains(reducedSet)) {
                Set<Set<Object>> returned = allSubArrays1(reduced.parallelStream().toList(), mem);
                mem.add((Set) reduced.stream().collect(Collectors.toSet()));
                if (!returned.contains(reduced)) {
                    returned.add(reducedSet);
                }
                Set aan = new HashSet(Arrays.asList(removed));
                if (!returned.contains(aan)) {
                    returned.add(aan);
                }
                result.addAll(returned);
            }
        }

        return result;
    }



    public Set AllSubArraysWitSum(int targetNo, int[] numbers, Set results) {
        counter++;
        if (Arrays.stream(numbers).sum() == targetNo) {
            if (!results.contains(numbers)) {
                Set<Object> numsToAdd = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
                results.add(numsToAdd);

            }
            return results;
        }
        for (int number : numbers) {
            LinkedList<Integer> reduced = new LinkedList<>();
            reduced.addAll(Arrays.stream(numbers).boxed().collect(Collectors.toList()));
            reduced.remove((Integer) number);
            int[] news = reduced.stream().mapToInt(a -> a).toArray();
            Set<Object> key = Arrays.stream(news).boxed().collect(Collectors.toSet());
            results = AllSubArraysWitSum(targetNo, news, results);
        }

        return results;
    }

    //    public Set<Set> howSumDfs(int targetSum, int[] numbers) {
//        // 1 element sub sets
//
//        Set<Set> assets = new HashSet<>();
//        int sum = IntStream.of(numbers).sum();
//        if(sum == targetSum)assets.add(Stream.of(sum).collect(Collectors.toSet()));
//        //1 element subsets
//        for(int i = 0; i < numbers.length; i++){
//             boolean isTarget = numbers[i]==targetSum;
//             if(isTarget)assets.add(Stream.of(numbers[i]).collect(Collectors.toSet()));
//        }
//        return assets;
//    }
}
