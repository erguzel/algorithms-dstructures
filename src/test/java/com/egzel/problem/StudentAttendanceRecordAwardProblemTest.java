package com.egzel.problem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.egzel.problem.StudentAttendanceRecordAwardProblem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentAttendanceRecordAwardProblemTest {

    private StudentAttendanceRecordAwardProblem studentAttendanceRecordAwardProblem = new StudentAttendanceRecordAwardProblem();
    @Test
    @Disabled("Not implemented yet")
    void testGetNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(){

        int inputbase0 = 0;//1
        int inputbase1 = 1;//3
        int inputbase2 = 2;//8
        int inputbase3 = 3;//16

        long resultinputbase0 = studentAttendanceRecordAwardProblem.getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(inputbase0);
        long resultinputbase1 = studentAttendanceRecordAwardProblem.getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(inputbase1);
        long resultinputbase2 = studentAttendanceRecordAwardProblem.getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(inputbase2);
        long resultinputbase3 = studentAttendanceRecordAwardProblem.getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(inputbase3);


        int bigcase = 10101;//183236316
        long bigcaseresult = studentAttendanceRecordAwardProblem.getNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber(bigcase);

        assertAll("testGetNumberOfAwardWiningCombinationsFromGivenNumberOfAttendanceNumber",
                ()->assertEquals(resultinputbase0,1),
                ()->assertEquals(resultinputbase1,2),
                ()->assertEquals(resultinputbase2,4),
                ()->assertEquals(resultinputbase3,7)
               // ()->assertEquals(bigcaseresult,183236316)
        );

    }
}