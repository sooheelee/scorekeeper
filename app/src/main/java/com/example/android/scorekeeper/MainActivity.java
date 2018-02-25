package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes scores and metrics for each team to 0.
     */
    teamMetrics foxes = new teamMetrics();
    teamMetrics wolves = new teamMetrics();

    /**
     * Calculates Corsi score from metrics.
     *
     * @param teamA
     * @param teamB
     */
    public void setCorsi(teamMetrics teamA, teamMetrics teamB) {
        teamA.corsi = (teamA.metrics[0] + teamA.metrics[2] + teamA.metrics[3])
                - (teamB.metrics[0] + teamB.metrics[2] + teamB.metrics[3]);
        teamB.corsi = (teamB.metrics[0] + teamB.metrics[2] + teamB.metrics[3])
                - (teamA.metrics[0] + teamA.metrics[2] + teamA.metrics[3]);
    }

    /**
     * Calculates Fenwick score from metrics.
     *
     * @param teamA
     * @param teamB
     */
    public void setFenwick(teamMetrics teamA, teamMetrics teamB) {
        teamA.fenwick = (teamA.metrics[0] + teamA.metrics[3])
                - (teamB.metrics[0] + teamB.metrics[3]);
        teamB.fenwick = (teamB.metrics[0] + teamB.metrics[3])
                - (teamA.metrics[0] + teamA.metrics[3]);
    }

    /**
     * Calculates 7-Factor score from metrics.
     *
     * @param teamA
     * @param teamB
     */
    public void setSevenFactor(teamMetrics teamA, teamMetrics teamB) {
        teamA.sevenFactor = (teamA.metrics[0] + teamA.metrics[1] + teamA.metrics[2] + teamA.metrics[3]
                + teamA.metrics[4] + teamA.metrics[5] + teamA.metrics[6] + teamA.metrics[7])
                - (teamB.metrics[2] + teamB.metrics[5]);
        teamB.sevenFactor = (teamB.metrics[0] + teamB.metrics[1] + teamB.metrics[2] + teamB.metrics[3]
                + teamB.metrics[4] + teamB.metrics[5] + teamB.metrics[6] + teamB.metrics[7])
                - (teamA.metrics[2] + teamA.metrics[5]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
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
            foxes.score = 0;
            wolves.score = 0;
            foxes.corsi = 0;
            wolves.corsi = 0;
            foxes.fenwick = 0;
            wolves.fenwick = 0;
            foxes.sevenFactor = 0;
            wolves.sevenFactor = 0;
            foxes.metrics = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            wolves.metrics = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
            displayScore();
            displayCorsi();
            displayFenwick();
            displaySevenFactor();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays the total goals for Teams.
     */
    public void displayScore() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.scoreFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.scoreWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.score));
        scoreViewWolves.setText(String.valueOf(wolves.score));
    }

    /**
     * Displays Corsi metrics.
     */
    public void displayCorsi() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.corsiFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.corsiWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.corsi));
        scoreViewWolves.setText(String.valueOf(wolves.corsi));
    }

    /**
     * Displays Fenwick metrics.
     */
    public void displayFenwick() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.fenwickFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.fenwickWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.fenwick));
        scoreViewWolves.setText(String.valueOf(wolves.fenwick));
    }

    /**
     * Displays 7-Factor metrics.
     */
    public void displaySevenFactor() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.sevenFactorFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.sevenFactorWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.sevenFactor));
        scoreViewWolves.setText(String.valueOf(wolves.sevenFactor));
    }

    /**
     * Increase the score for a team by 1 point.
     * Track 5-on-5 play goals.
     */
    public void fiveOnFiveGoal(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.score++;
        //foxes.score = scoreFoxes + 1;
        offensiveTeam.metrics[0]++;
        //metricsFoxes[0] = metricsFoxes[0] + 1;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    /**
     * Increase the score for a team by 1 point.
     * Track power-play and short-handed goals.
     */
    public void otherGoal(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.score++;
        offensiveTeam.metrics[1]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void shotBlocked(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[2]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void shotOnPost(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[3]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void otherShotAttempt(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[4]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void takeaway(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[5]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void completedPass(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[6]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    public void finishedCheck(View v) {
        String team = v.getTag().toString();
        teamMetrics offensiveTeam;
        teamMetrics defensiveTeam;

        if (team.equals("foxes")) {
            offensiveTeam = foxes;
            defensiveTeam = wolves;
        } else {
            offensiveTeam = wolves;
            defensiveTeam = foxes;
        }

        offensiveTeam.metrics[7]++;
        setCorsi(offensiveTeam, defensiveTeam);
        setFenwick(offensiveTeam, defensiveTeam);
        setSevenFactor(offensiveTeam, defensiveTeam);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }
}
