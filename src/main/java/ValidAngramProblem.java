import java.util.*;

/**
 * 2 strings are called angram if they have the same letters with the same frequency int different order
 * Find if given 2 strings are angrams
 */
import java.util.*;

public class ValidAngramProblem extends ProblemBase{

    public static void main(String[] args) {

        String testString = "edip";
        String testString1 = "pide";

        ValidAngramProblem validAngramProblem = new ValidAngramProblem();
        String word1 = "namaless";
        String word12 = "salesman";
        boolean res = validAngramProblem.isValidAngramSorting(word1,word12);
        validAngramProblem.LOGGER.info(res);
    }

    /**
     * O(n) - O(n) time space
     * Store each frequencies and compare
     * @param word1
     * @param word2
     * @return
     */
    public boolean isValidAngram(String word1, String word2) {
        String validationState = word1.length() != word2.length() ? "lengthmismatch" :
                "none";
        if (validationState != "none") return false;

        Map<Character, Integer> wordfreq1 = new HashMap<>();
        Map<Character, Integer> wordfreq2 = new HashMap<>();

        for (Character c : word1.toCharArray()) {
            if (wordfreq1.containsKey(c)) {
                wordfreq1.compute(c, (key, val) -> val++);
            } else {
                wordfreq1.put(c, 1);
            }//w1 contains c
        }// for word1 characters
        for (Character c : word2.toCharArray()) {
            if (wordfreq2.containsKey(c)) {
                wordfreq2.compute(c, (key, val) -> val++);
            } else {
                wordfreq2.put(c, 1);
            }//w2  contains c
        }// for word2 characters

// check condition
        for (Character c : wordfreq1.keySet()) {
            boolean keyMismatch = !wordfreq2.containsKey(c);
            boolean freqMismatch = wordfreq1.get(c) != wordfreq1.get(c);

            if (keyMismatch || freqMismatch)
                return false;
        }//for keysets check

        return true;
    }// isValidAngram methid


    /**
     * O(nlogn) //sort 2 strings and compare if they are the same
     * @param word1
     * @param word2
     * @return
     */
    public boolean isValidAngramSorting(String word1, String word2){

        char[] temp1 = word1.toCharArray();
        Arrays.sort(temp1);
        String sortedw1 = new String(temp1);

        char[] temp2 = word2.toCharArray();
        Arrays.sort(temp2);
        String sortedw2 = new String(temp2);


        return sortedw1.equals(sortedw2);

    }



}//class


