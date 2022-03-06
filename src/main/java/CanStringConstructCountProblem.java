import java.util.HashMap;
import java.util.Map;
/**
 * Write a function that accepts a target string and an array of strings
 * Function returns number of different ways if given target string can be constructed by given string array
 */
public class CanStringConstructCountProblem extends ProblemBase{

    public static void main(String[] args) {

        String target = "abcdef";String[] targs = {"ab", "abc", "cd", "def", "abcd"};
        String target1 = "enterapotentpot";String[] targs1 = {"a", "p", "ent", "enter", "ot","o","t"};
        String target2 = "eeeeeeeeeeeeeeeeeeeeeeeeef";String[] targs2 = {"e", "ee", "eee", "eeee", "eeeee","eeeeee"};
        String target3 = "skateboard";String[] targs3 = {"bo", "rd", "ate", "t", "ska","sk","boar"};
        String target4 = "purple";String[] targs4 = {"purp", "p", "ur", "le", "purpl"};
        String target5 = "olgun";String[] targs5 = {"gu", "un", "g", "ol", "olgu","n"};
        String[] target6 ={"1","2","3","4"};


        new Thread(() -> {
            CanStringConstructCountProblem grr = new CanStringConstructCountProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            long num = grr.canConstructCount(target1,targs1);
            grr.LOGGER.info("canConstructCountResult:" + num);
            grr.LOGGER.info("canConstructCountCalls:" + grr.counter);
            timer.getBenchmark(timer, "canConstructCount");
        }).start();

        new Thread(()->{
            CanStringConstructCountProblem grr = new CanStringConstructCountProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            Long num = grr.canConstructCount(target1,targs1,new HashMap<String,Long>());
            grr.LOGGER.info("canConstructCountResultMem:" + num);
            grr.LOGGER.info("canConstructCountCallsMem:" + grr.counter1);
            timer.getBenchmark(timer, "canConstructCountMem");
        }).start();
    }

    public long canConstructCount(String target, String[] strings){

        return canConstructCountHelper(target,strings);
    }

    public long canConstructCount(String target, String[] strings, Map<String,Long> mem){

        return canConstructCountHelperMem(target,strings,mem);
    }

    private long canConstructCountHelper(String target, String[] strings) {
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

    private long canConstructCountHelperMem(String target, String[] strings,Map<String,Long> mem) {
        counter1++;
        if(mem.containsKey(target))return mem.get(target);
        if (target == "") return 1L;

        long totalCount = 0;

        for (int i = 0; i < strings.length; i++) {
            String removal = strings[i];
            String remnant = target.startsWith(removal) ? target.substring(removal.length()) : null;
            if(remnant  != null){
                long count = canConstructCountHelperMem(remnant,strings,mem);
                totalCount = totalCount+count;
                mem.put(remnant,totalCount);
            }

        }
        mem.put(target,totalCount);
        return totalCount;
    }
}
