package to.dev.faangmaster.tp.palindrom;

public class Solution {

    /**
     * isPalindrome
     *
     * @param str string
     * @return result
     * @link <a href="https://dev.to/faangmaster/provierka-na-palindrom-3e7p">Проверка на палиндром</a>
     */
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Solution.isPalindrome("abcba"));
    }

}
