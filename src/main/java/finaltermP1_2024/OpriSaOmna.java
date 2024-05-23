package finaltermP1_2024;

public class OpriSaOmna {

    /**
     * Computes the maximum score a person can achieve based on the provided scores.
     *
     * @param scores An array of integers representing the scores of the people.
     * @return The maximum achievable score.
     */
    public static int maxScore(int[] scores) {
        int n = scores.length;
        if (n == 0) return 0;

        // DP array to store the maximum score up to each person
        int[] dp = new int[n];

        // Initialize
        dp[0] = scores[0];
        if (n > 1) {
            dp[1] = scores[1];
        }
        if (n > 2) {
            dp[2] = scores[2] + scores[0];
        }

        // Fill DP array considering each person can lean left or right
        for (int i = 3; i < n; i++) {
            dp[i] = scores[i] + Math.max(dp[i - 2], dp[i - 3]);
        }

        // Calculate max score
        int maxScore = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i] > maxScore) {
                maxScore = dp[i];
            }
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] scores = {1, 3, 5, 2, 8, 7, 4, 5};
        System.out.println("Maximum score: " + maxScore(scores));
    }
}
