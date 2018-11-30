//import Java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import org.junit.Test;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class Testing {
    @Test
    public void testCases() throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date dt = new Date();

        //df.format(dt);

        SubmissionHistory as = new Assignment();

        as.add("abc9899", new Date(1000), 89);
        as.add("abc9899", new Date(2000), 56);

        as.add("def9899", new Date(3000), 99);
        as.add("def9899", new Date(4000), 65);
        as.add("def9899", new Date(5000), 34);

        //as.add("abc9899", dt, 98);

        //System.out.println(dt);

        Submission sb1 = as.getSubmissionFinal("abc9899");
        assertEquals(new Integer(56), sb1.getGrade());

        sb1 = as.getSubmissionFinal("def9899");
        assertEquals(new Integer(34), sb1.getGrade());

        System.out.println(sb1.getTime());

    }
}