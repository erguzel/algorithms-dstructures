package com.egzel.problem;

import java.math.BigInteger;
import java.util.stream.IntStream;

import com.egzel.lib.model.ProblemBase;

/**552. Student Attendance Record II
 *
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *
 *     'A': Absent.
 *     'L': Late.
 *     'P': Present.
 *
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 *     The student was absent ('A') for strictly fewer than 2 days total.
 *     The student was never late ('L') for 3 or more consecutive days.
 *
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 */
public class StudentAttendanceRecordAwardProblem extends ProblemBase {

    public long getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(int numberofattendances){
        // 1. Define objective function
        //  f(i) = number of different award wining combination number for i number of attendance records
        // 2. Define base cases
        // f(0) = 1; f(1) = 3; f(2)=8, f(3)= 16  which staisfied the succes conditions
        // 3. Define recurrence relation
        // number of different ways of f(n) = f(n-1) + f(n-2)+f(n-3);
        // 4. Identify recurrence order
        //  bottom up
        // 5. Identify result location -> dp[numberofattendances]
        if(numberofattendances==0)return 1;
        if(numberofattendances==1)return 3;
        if(numberofattendances==2)return 8;
        long[] dp = new long[numberofattendances+1];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;

        // base cases

        IntStream.rangeClosed(4,numberofattendances).forEach(x->{
            dp[x] = 2*dp[x-1]+dp[x-4];
        });

        return dp[numberofattendances];
    }
}
