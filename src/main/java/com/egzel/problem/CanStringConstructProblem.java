package com.egzel.problem;

import java.util.*;

import com.egzel.lib.model.ProblemBase;
import com.egzel.lib.util.ALogger;

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
        String[] target6 ={"1","2","3","4"};


        new Thread(() -> {
            CanStringConstructProblem grr = new CanStringConstructProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            boolean num = grr.canConstruct(target2,targs2);
            grr.LOGGER.info("canConstructCountResult:" + num);
            grr.LOGGER.info("canConstructCountCalls:" + grr.counter);
            timer.getBenchmark(timer, "canConstructCount");
        }).start();

        new Thread(()->{
            CanStringConstructProblem grr = new CanStringConstructProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            boolean num = grr.canConstruct(target2,targs2,new HashMap<String,Boolean>());
            grr.LOGGER.info("canConstructCountResultMem:" + num);
            grr.LOGGER.info("canConstructCountCallsMem:" + grr.counter1);
            timer.getBenchmark(timer, "canConstructCountMem");
        }).start();


    }

    public boolean canConstruct(String target, String[] strings) {
        return canConstructHelper(target, strings);
    }
    public boolean canConstruct(String target, String[] strings, Map<String,Boolean> mem) {
        return canConstructHelper(target, strings,mem);
    }
    private boolean canConstructHelper(String target, String[] strings, Map<String,Boolean> mem) {
        counter1++;
        if(mem.containsKey(target))return mem.get(target);
        if (target == "") return true;
        if (target == null) return false;
        for (int i = 0; i < strings.length; i++) {
            String remnant = target.startsWith(strings[i]) ? target.substring(strings[i].length()) : null;
            if (canConstructHelper(remnant, strings, mem)) {
                mem.put(target,true);
                return true;
            }
        }

        mem.put(target,false);
        return false;
    }
    private boolean canConstructHelper(String target, String[] strings) {
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



}
