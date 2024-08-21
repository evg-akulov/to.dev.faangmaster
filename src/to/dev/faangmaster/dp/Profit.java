package to.dev.faangmaster.dp;

public class Profit {

    /**
     * maxProfit
     *
     * @param prices prices
     * @return max profit
     * @link <a href="https://dev.to/faangmaster/prostaia-zadacha-na-dinamichieskoie-proghrammirovaniie-luchshieie-vriemia-dlia-pokupki-i-prodazhi-aktsii-59gd">Простая задача на динамическое программирование: Лучшее время для покупки и продажи акции</a>
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = Integer.MIN_VALUE;
        int minPriceSoFar = Integer.MAX_VALUE;
        for (int price : prices) {
            minPriceSoFar = Math.min(minPriceSoFar, price);
            int currentProfit = price - minPriceSoFar;
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(Profit.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
