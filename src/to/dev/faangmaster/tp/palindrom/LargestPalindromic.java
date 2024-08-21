package to.dev.faangmaster.tp.palindrom;

public class LargestPalindromic {
    public static void main(String[] args) {
        System.out.println(LargestPalindromic.largestPalindromic("444947137"));
    }

    /**
     * largestPalindromic
     *
     * @param num num
     * @return largestPalindromic
     * @link <a href="https://dev.to/faangmaster/zadacha-s-sobiesiedovaniia-v-microsoft-samoie-bolshoie-palindromnoie-chislo-1jhf">Задача с собеседования в Microsoft: Самое большое палиндромное число</a>
     */
    private static String largestPalindromic(String num) {
        int[] frequencies = new int[10];
        //Вычисляем таблицу частотности
        for (char c : num.toCharArray()) {
            frequencies[c - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        int center = -1;
        for (int i = 9; i >= 0; i--) {
            //Составляем палиндром из парных цифр, начиная с самых больших
            for (int j = 0; j < frequencies[i] / 2; j++) {
                // Нельзя добавлять нули в начало нашего палиндрома.
                // В середину числа вставлять нули можно.
                if (!sb.isEmpty() || i != 0) {
                    sb.append(i);
                }
            }
            //первая, самая большая цифра,
            //которая встречается нечетное число раз - искомый центр
            if (center == -1 && frequencies[i] % 2 == 1) {
                center = i;
            }
        }
        if (center == -1 && sb.isEmpty()) {
            return "0";
        }
        int i = sb.length() - 1;
        if (center != -1) {
            sb.append(center);
        }
        //Симметрично достраиваем хвост
        for (; i >= 0; i--) {
            sb.append(sb.charAt(i));
        }
        return sb.toString();
    }

}