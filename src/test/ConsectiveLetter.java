package test;

public class ConsectiveLetter {
    public static void main(String[] args) {
        String[] s = {"s","h","a","k","e","w","e","i","g","h","t"};
        System.out.println(findConsecutiveMax(s));
    }

    public static String findConsecutiveMax(String[] c) {
        if (c.length == 0) {
            return "";
        }

        String highestChar = c[0];
        int highestCount = 1;
        int currentCount = 1;

        for (int i = 1; i < c.length; i++) {
            if (c[i] == c[i - 1]) {
                currentCount++;
            } else {
                if (currentCount > highestCount) {
                    highestCount = currentCount;
                    highestChar = c[i - 1];
                }
                currentCount = 1;
            }
        }

        return highestChar;
    }
}
