package problem.lcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Given 2 strings, find the length of longest common subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 *     For example, "ace" is a subsequence of "abcde".
 *
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *

 Example 1:

 Input: text1 = "abcde", text2 = "ace"
 Output: 3
 Explanation: The longest common subsequence is "ace" and its length is 3.

 Example 2:

 Input: text1 = "abc", text2 = "abc"
 Output: 3
 Explanation: The longest common subsequence is "abc" and its length is 3.

 Example 3:

 Input: text1 = "abc", text2 = "def"
 Output: 0
 Explanation: There is no such common subsequence, so the result is 0.

 */
public class LongestCommonSubsequence {

    public int lengthOfCommonSubsequence(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();

        List<Character> path = new ArrayList<>();
        IntStream.range(1,dp.length).forEach(row->{
            IntStream.range(1,dp[0].length).forEach(col->{
                if(s1chars[row-1]==s2chars[col-1]){
                    dp[row][col] = 1+ dp[row-1][col-1];

                }else {
                    dp[row][col] = Math.max(dp[row-1][col],dp[row][col-1]);
                }
            });
        });

        return dp[s1.length()][s2.length()];
    }
}
