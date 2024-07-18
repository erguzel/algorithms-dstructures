package com.egzel.problem;

import org.junit.jupiter.api.Test;

import com.egzel.problem.lcode.LongestCommonSubsequence;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSubsequenceTest {

    private LongestCommonSubsequence instance = new LongestCommonSubsequence();
    @Test
    void testLengthOfCommonSubsequence() {

        String inputa1 = "olgun";
        String inputa2="erguzel";
        int wanta = 2;

        String inputb1 = "stone";
        String inputb2="longest";
        int wantb = 3;

        String inputc1 = "abcdgh";
        String inputc2="aedfhr";
        int wantc = 3;

        String inputd1 = "aggtab";
        String inputd2="gxtxayb";
        int wantd = 4;

        String inpute1 = "xyz";
        String inpute2="abc";
        int wante= 0;

        assertAll("base",
                ()->assertEquals(instance.lengthOfCommonSubsequence(inputa1,inputa2),wanta),
                ()->assertEquals(instance.lengthOfCommonSubsequence(inputb1,inputb2),wantb),
                ()->assertEquals(instance.lengthOfCommonSubsequence(inputc1,inputc2),wantc),
                ()->assertEquals(instance.lengthOfCommonSubsequence(inputd1,inputd2),wantd),
                ()->assertEquals(instance.lengthOfCommonSubsequence(inpute1,inpute2),wante)
        );
    }
}