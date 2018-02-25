package com.example.android.scorekeeper;

/**
 * Initializes metrics for each team to zero.
 * Score, Corsi, Fenwick and SevenFactor are calculated from the metrics array.
 * The metrics array contains, in order:
 * <ul>
 * <li>Index 0 refers to 5:5 goals.</li>
 * <li>Index 1 refers to other types of goals.</li>
 * <li>Index 2 refers to attempted shots blocked by the defensive team.</li>
 * <li>Index 3 refers to attempted shots that come in contact with goal post but that do not score.</li>
 * <li>Index 4 refers to other shot attempts that are neither blocked nor come in contact with goal.</li>
 * <li>Index 5 refers to takeaways by team and implies a turnover for the opposing team.</li>
 * <li>Index 6 refers to completed passes.</li>
 * <li>Index 7 refers to finished checks.</li>
 * </ul>
 * Created by SHAIR on 2/24/18.
 */

public class teamMetrics {
    public int score = 0;
    public int corsi = 0;
    public int fenwick = 0;
    public int sevenFactor = 0;
    public int[] metrics = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    public teamMetrics() {
    }
}
