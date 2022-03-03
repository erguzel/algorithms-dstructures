import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a function that accepts a target string and an array of strings
 * Function returns true if given target string can be constructed by given string array
 */
public class CanStringConstructProblem extends ProblemBase {


    public static void main(String[] args) {



        String target = "abcdef";String[] targs = {"ab", "abc", "cd", "def", "abcd"};
        String target1 = "enterapotentpot";String[] targs1 = {"a", "p", "ent", "enter", "ot","o","t"};
        String target2 = "eeeeeeeeeeeeeeeeeeeeeeeeef";String[] targs2 = {"e", "ee", "eee", "eeee", "eeeee","eeeeee"};
        String target3 = "skateboard";String[] targs3 = {"bo", "rd", "ate", "t", "ska","sk","boar"};
        String target4 = "purple";String[] targs4 = {"purp", "p", "ur", "le", "purpl"};
        String target5 = "olgun";String[] targs5 = {"gu", "un", "g", "ol", "olgu","n"};
        String[] target6 ={"one","two","three"};


        CanStringConstructProblem grr = new CanStringConstructProblem();
        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        Set<Set> num = grr.allSubSets(target6);
        grr.LOGGER.info("canConstructCountResult:" + (num));
        grr.LOGGER.info("canConstructCountCalls:" + grr.counter);
        timer.getBenchmark(timer, "canConstructCount");


//        new Thread(() -> {
//            CanStringConstructProblem grr = new CanStringConstructProblem();
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            boolean num = grr.canConstruct(target2, targs2);
//            grr.LOGGER.info("canConstruct:" + (num));
//            grr.LOGGER.info("canConstruct:" + grr.counter);
//            timer.getBenchmark(timer, "canConstruct");
//        }).start();
//
//        new Thread(() -> {
//            CanStringConstructProblem grr = new CanStringConstructProblem();
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            boolean num = grr.canConstruct(target2, targs2, new HashMap<>());
//            grr.LOGGER.info("canConstructMem:" + (num));
//            grr.LOGGER.info("canConstructMem:" + grr.counter1);
//            timer.getBenchmark(timer, "canConstructMem");
//        }).start();


    }

    public boolean canConstruct(String target, String[] strings) {
        return canConstructHelper(target, strings);
    }
    public boolean canConstruct(String target, String[] strings, Map<String,Boolean> mem) {
        return canConstructHelper(target, strings,mem);
    }
    public boolean canConstructHelper(String target, String[] strings, Map<String,Boolean> mem) {
        counter1++;
        if(mem.containsKey(target))return mem.get(target);
        if (target == "") return true;
        if (target == null) return false;
        for (int i = 0; i < strings.length; i++) {
            String remnant = target.startsWith(strings[i]) ? target.substring(strings[i].length()) : null;
            if (canConstructHelper(remnant, strings,mem)) {
                mem.put(target,true);
                return true;
            }
        }

        mem.put(target,false);
        return false;
    }
    public boolean canConstructHelper(String target, String[] strings) {
        counter++;
        if (target == "") return true;
        if (target == null) return false;

        for (int i = 0; i < strings.length; i++) {
            String remnant = target.startsWith(strings[i]) ? target.substring(strings[i].length()) : null;

            if (canConstructHelper(remnant, strings)) {
                return true;
            }

        }

        return false;
    }
    //////////////////////////////////////

//String target5 = "olgun";String[] targs5 = {"gu", "un", "g", "ol", "olgu","n"};
    public long canConstructCountHelper(String target, String[] strings) {
        counter++;
        if (target == "") return 1L;

        long totalCount = 0;

        for (int i = 0; i < strings.length; i++) {
            String removal = strings[i];
            String remnant = target.startsWith(removal) ? target.substring(removal.length()) : null;
            if(remnant  != null){
                long count = canConstructCountHelper(remnant,strings);
                totalCount = totalCount+count;
            }

        }

        return totalCount;
    }

    public Set<Set> allSubSets(String[] stringSet){

        Object[] sets = new Object[stringSet.length];
        for (int i =0; i < stringSet.length; i++){
            sets[i] = stringSet[i];
        }

        return allSubSetsHelper(sets);

    }

    public Set<Set> allSubSetsHelper(Object[] stringSet){
        counter++;
        if(stringSet.length == 0) return new HashSet<Set>();


        Set<Set> globalSet = new HashSet<Set>();

        for (int i =0; i < stringSet.length; i++){

            LinkedList splitted = new LinkedList(Arrays.stream(stringSet).toList());
            Object single = splitted.remove(i);

            if(!single.equals("")){
                globalSet.add(new HashSet(Arrays.asList(single)));
            }
            if(splitted.size() !=0){
                globalSet.add((Set) splitted.stream().collect(Collectors.toSet()));
            }

            Object[] nexts = splitted.toArray();

            allSubSetsHelper(nexts);

        }

        return globalSet;
    }



}
