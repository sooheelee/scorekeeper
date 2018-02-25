package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes score and metrics for each team to 0.
     */
    int scoreFoxes = 0;
    int scoreWolves = 0;
    int corsiFoxes = 0;
    int corsiWolves = 0;
    int fenwickFoxes = 0;
    int fenwickWolves = 0;
    int sevenFactorFoxes = 0;
    int sevenFactorWolves = 0;
    int[] metricsFoxes = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    int[] metricsWolves = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * Calculates Corsi score from metrics for Foxes
     *
     * @param corsiFoxes
     */
    public void setCorsiFoxes(int corsiFoxes) {
        this.corsiFoxes = (metricsFoxes[0] + metricsFoxes[2] + metricsFoxes[3] + metricsFoxes[4])
                - (metricsWolves[0] + metricsWolves[2] + metricsWolves[3] + metricsWolves[4]);
    }

    /**
     * Calculates Fenwick score from metrics for Foxes
     *
     * @param fenwickFoxes
     */
    public void setFenwickFoxes(int fenwickFoxes) {
        this.fenwickFoxes = (metricsFoxes[0] + metricsFoxes[3] + metricsFoxes[4])
                - (metricsWolves[0] + metricsWolves[3] + metricsWolves[4]);
    }

    /**
     * Calculates 7-Factor score from metrics for Foxes
     *
     * @param sevenFactorFoxes
     */
    public void setSevenFactorFoxes(int sevenFactorFoxes) {
        this.sevenFactorFoxes = (metricsFoxes[0] + metricsFoxes[1] + metricsFoxes[2] + metricsFoxes[3]
                + metricsFoxes[4] + metricsFoxes[5] + metricsFoxes[6] + metricsFoxes[7])
                - (metricsWolves[2] + metricsWolves[5]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScoreFoxes(scoreFoxes);
        displayScoreWolves(scoreWolves);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            scoreFoxes = 0;
            scoreWolves = 0;
            corsiFoxes = 0;
            corsiWolves = 0;
            fenwickFoxes = 0;
            fenwickWolves = 0;
            sevenFactorFoxes = 0;
            sevenFactorWolves = 0;
            metricsFoxes = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            metricsWolves = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            displayScoreFoxes(scoreFoxes);
            displayScoreWolves(scoreWolves);
            displayCorsiFoxes(corsiFoxes);
            displayCorsiWolves(corsiWolves);
            displayFenwickFoxes(fenwickFoxes);
            displayFenwickWolves(fenwickWolves);
            displaySevenFactorFoxes(sevenFactorFoxes);
            displaySevenFactorWolves(sevenFactorWolves);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays the total score for Team Foxes.
     */
    public void displayScoreFoxes(int scoreFoxes) {
        TextView scoreView = (TextView) findViewById(R.id.scoreFoxes);
        scoreView.setText(String.valueOf(scoreFoxes));
    }

    /**
     * Displays the total score for Team Wolves.
     */
    public void displayScoreWolves(int scoreWolves) {
        TextView scoreView = (TextView) findViewById(R.id.scoreWolves);
        scoreView.setText(String.valueOf(scoreWolves));
    }

    /**
     * Displays Corsi metric for Foxes.
     */
    public void displayCorsiFoxes(int corsiFoxes) {
        TextView scoreView = (TextView) findViewById(R.id.corsiFoxes);
        setCorsiFoxes(corsiFoxes);
        scoreView.setText(String.valueOf(corsiFoxes));
    }

    /**
     * Displays Corsi metric for Wolves.
     */
    public void displayCorsiWolves(int corsiWolves) {
        TextView scoreView = (TextView) findViewById(R.id.corsiWolves);
        scoreView.setText(String.valueOf(corsiWolves));
    }

    /**
     * Displays Fenwick metric for Foxes.
     */
    public void displayFenwickFoxes(int fenwickFoxes) {
        TextView scoreView = (TextView) findViewById(R.id.fenwickFoxes);
        setFenwickFoxes(fenwickFoxes);
        scoreView.setText(String.valueOf(fenwickFoxes));
    }

    /**
     * Displays Fenwick metric for Wolves.
     */
    public void displayFenwickWolves(int fenwickWolves) {
        TextView scoreView = (TextView) findViewById(R.id.fenwickWolves);
        scoreView.setText(String.valueOf(fenwickWolves));
    }

    /**
     * Displays 7-Factor metric for Foxes.
     */
    public void displaySevenFactorFoxes(int sevenFactorFoxes) {
        TextView scoreView = (TextView) findViewById(R.id.sevenFactorFoxes);
        setSevenFactorFoxes(sevenFactorFoxes);
        scoreView.setText(String.valueOf(sevenFactorFoxes));
    }

    /**
     * Displays 7-Factor metric for Wolves.
     */
    public void displaySevenFactorWolves(int sevenFactorWolves) {
        TextView scoreView = (TextView) findViewById(R.id.sevenFactorWolves);
        scoreView.setText(String.valueOf(sevenFactorWolves));
    }

    /**
     * Increase the score for Team Foxes by 1 point.
     * Track 5-on-5 play goals.
     */
    public void fiveOnFiveGoalFoxes(View v) {
        scoreFoxes = scoreFoxes + 1;
        metricsFoxes[0] = metricsFoxes[0] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayScoreFoxes(scoreFoxes);
        displayScoreWolves(scoreWolves);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    /**
     * Increase the score for Team Foxes by 1 point.
     * Track power-play and short-handed goals.
     */
    public void otherGoalFoxes(View v) {
        scoreFoxes = scoreFoxes + 1;
        metricsFoxes[1] = metricsFoxes[1] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayScoreFoxes(scoreFoxes);
        displayScoreWolves(scoreWolves);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void shotBlockedFoxes(View v) {
        metricsFoxes[2] = metricsFoxes[2] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void shotOnPostFoxes(View v) {
        metricsFoxes[3] = metricsFoxes[3] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void otherShotAttemptFoxes(View v) {
        metricsFoxes[4] = metricsFoxes[4] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void takeawayFoxes(View v) {
        metricsFoxes[5] = metricsFoxes[5] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void completedPassFoxes(View v) {
        metricsFoxes[6] = metricsFoxes[6] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }

    public void finishedCheckFoxes(View v) {
        metricsFoxes[7] = metricsFoxes[7] + 1;
        setCorsiFoxes(corsiFoxes);
        setFenwickFoxes(fenwickWolves);
        setSevenFactorFoxes(sevenFactorFoxes);
        displayCorsiFoxes(corsiFoxes);
        displayCorsiWolves(corsiWolves);
        displayFenwickFoxes(fenwickFoxes);
        displayFenwickWolves(fenwickWolves);
        displaySevenFactorFoxes(sevenFactorFoxes);
        displaySevenFactorWolves(sevenFactorWolves);
    }
}
