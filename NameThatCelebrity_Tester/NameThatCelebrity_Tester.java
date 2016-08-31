public class NameThatCelebrity_Tester { 
    /*/
     * Created by Michael MacLeod
     * 8/29/2016, 4:33 PM
    /*/
    public static void main(String args[]) {
        nameThatCelebrity();
        arithmeticAssignment();
    }

    private static void nameThatCelebrity() {
        System.out.println("-Name That Celebrity-");
        String s1 = "Allan Alda", s2 = "John Wayne", s3 = "Gregory Peck";
        System.out.println(s1 + ">>>" + s1.substring(2, s1.length() - 3));
        System.out.println(s2 + ">>>" + s2.substring(2, s2.length() - 3));
        System.out.println(s3 + ">>>" + s3.substring(2, s3.length() - 3));
    }

    private static void arithmeticAssignment() {
        System.out.println("-Arithmetic Assignment-");
        int p1 = 79 + 3 * (4 + 82 - 68) - 7 + 19,
            p2 = (179 + 21 + 10) / 7 + 181,
            p3 = 10389 * 56 * 11 + 2246;
        System.out.println("79 + 3 * (4 + 82 - 68) - 7 + 19 = " + p1);
        System.out.println("(179 + 21 + 10) / 7 + 181 = " + p2);
        System.out.println("10389 * 56 * 11 + 2246 = " + p3);
    }
}