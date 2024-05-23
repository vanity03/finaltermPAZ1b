package FinaltermP1_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CestyIITriedy {

    private static final List<String> validNumbers = Arrays.asList("1", "2", "8", "22", "23", "111", "222", "223"); // List of valid road numbers
    private List<String> results;
    private String input;

    public CestyIITriedy(String input) {
        this.input = input;
        this.results = new ArrayList<>();
        generuj(0, new ArrayList<>());
    }

    private void generuj(int startIndex, List<String> currentCombination) {
        if (startIndex == input.length()) {
            results.add(String.join("-", currentCombination));
            return;
        }

        boolean progressMade = false;
        for (String number : validNumbers) {
            if (canStartWith(input, startIndex, number)) {
                currentCombination.add(number);
                generuj(startIndex + number.length(), currentCombination);
                currentCombination.remove(currentCombination.size() - 1); // backtrack
                progressMade = true;
            }
        }

        // If no valid road number could be matched starting from this index, terminate early
        if (!progressMade && !currentCombination.isEmpty()) {
            return;
        }
    }

    // Helper method to check if the sequence can start with the given number at the specified index
    private boolean canStartWith(String sequence, int start, String number) {
        if (start + number.length() > sequence.length()) return false;
        return sequence.substring(start, start + number.length()).equals(number);
    }

    public void printResults() {
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        String input = "82223"; // Example input
        CestyIITriedy cesty = new CestyIITriedy(input);
        cesty.printResults(); // Display all possible combinations
    }
}