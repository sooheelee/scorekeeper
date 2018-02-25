package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * App keeps track of the total score in a hockey match.
 * For each team also calculates Corsi, Fenwick and 7-Factor metrics.
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes metrics for each team to 0 using teamMetrics.java class.
     */
    teamMetrics foxes = new teamMetrics();
    teamMetrics wolves = new teamMetrics();

    /**
     * Saves values as strings.
     */
    public static final String FOXES_SCORE = "SCORE_FOXES";
    public static final String WOLVES_SCORE = "SCORE_WOLVES";
    public static final String FOXES_CORSI = "CORSI_FOXES";
    public static final String WOLVES_CORSI = "CORSI_WOLVES";
    public static final String FOXES_FENWICK = "FENWICK_FOXES";
    public static final String WOLVES_FENWICK = "FENWICK_WOLVES";
    public static final String FOXES_SEVENFACTOR = "SEVENFACTOR_FOXES";
    public static final String WOLVES_SEVENFACTOR = "SEVENFACTOR_WOLVES";
    public static final String FOXES_METRICS = "METRICS_FOXES";
    public static final String WOLVES_METRICS = "METRICS_WOLVES";

    /**
     * Calculates Corsi score from metrics.
     * Corsi = (5:5 shots on target + missed shots + blocked shots against) -
     * (5:5 shots on target against + missed shots against + blocked shots for)
     * <pre>
     * Metric excludes shot attempts that are unblocked and that do not contact the goal.
     * See <https://captaincalculator.com/sports/hockey/corsi-calculator/>.
     * </pre>
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
     * Fenwick = (5:5 shots on target + missed shots) - (5:5 shots on target against +
     * missed shots against)
     * <pre>
     * Metric excludes shot attempts that are unblocked and that do not contact the goal.
     * See <https://captaincalculator.com/sports/hockey/fenwick-calculator/>.
     * </pre>
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
     * Calculates 7-Factor Analysis score from metrics.
     * 7-Factor Analysis = (shots on net + blocked shots + finished checks + takeaways + completed passes)
     * - (turnovers + missed/blocked shot attempts)
     * <pre>
     * Metrics include shot attempts that are unblocked and that do not contact the goal.
     * See <https://glassandout.com/2014/11/7-factor-analysis-simple-analytics-for-any-level-of-hockey/>.
     * </pre>
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

    /**
     * Displays saved values after orientation changes and other context changes.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            foxes.score = savedInstanceState.getInt(FOXES_SCORE);
            wolves.score = savedInstanceState.getInt(WOLVES_SCORE);
            foxes.corsi = savedInstanceState.getInt(FOXES_CORSI);
            wolves.corsi = savedInstanceState.getInt(WOLVES_CORSI);
            foxes.fenwick = savedInstanceState.getInt(FOXES_FENWICK);
            wolves.fenwick = savedInstanceState.getInt(WOLVES_FENWICK);
            foxes.sevenFactor = savedInstanceState.getInt(FOXES_SEVENFACTOR);
            wolves.sevenFactor = savedInstanceState.getInt(WOLVES_SEVENFACTOR);
            foxes.metrics = savedInstanceState.getIntArray(FOXES_METRICS);
            wolves.metrics = savedInstanceState.getIntArray(WOLVES_METRICS);
            displayScore();
            displayCorsi();
            displayFenwick();
            displaySevenFactor();
        }
    }

    /**
     * Saves values.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(FOXES_SCORE, foxes.score);
        savedInstanceState.putInt(WOLVES_SCORE, wolves.score);
        savedInstanceState.putInt(FOXES_CORSI, foxes.corsi);
        savedInstanceState.putInt(WOLVES_CORSI, wolves.corsi);
        savedInstanceState.putInt(FOXES_FENWICK, foxes.fenwick);
        savedInstanceState.putInt(WOLVES_FENWICK, wolves.fenwick);
        savedInstanceState.putInt(FOXES_SEVENFACTOR, foxes.sevenFactor);
        savedInstanceState.putInt(WOLVES_SEVENFACTOR, wolves.sevenFactor);
        savedInstanceState.putIntArray(FOXES_METRICS, foxes.metrics);
        savedInstanceState.putIntArray(WOLVES_METRICS, wolves.metrics);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Creates a new instance.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScore();
        displayCorsi();
        displayFenwick();
        displaySevenFactor();
    }

    /**
     * Creates menu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Acts on menu item selection.
     *
     * @param item
     * @return
     */
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

        //yet to be wired function
        if (id == R.id.save) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays the total goals for each team.
     */
    public void displayScore() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.scoreFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.scoreWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.score));
        scoreViewWolves.setText(String.valueOf(wolves.score));
    }

    /**
     * Displays Corsi metrics for each team.
     */
    public void displayCorsi() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.corsiFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.corsiWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.corsi));
        scoreViewWolves.setText(String.valueOf(wolves.corsi));
    }

    /**
     * Displays Fenwick metrics for each team.
     */
    public void displayFenwick() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.fenwickFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.fenwickWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.fenwick));
        scoreViewWolves.setText(String.valueOf(wolves.fenwick));
    }

    /**
     * Displays 7-Factor metrics for each team.
     */
    public void displaySevenFactor() {
        TextView scoreViewFoxes = (TextView) findViewById(R.id.sevenFactorFoxes);
        TextView scoreViewWolves = (TextView) findViewById(R.id.sevenFactorWolves);
        scoreViewFoxes.setText(String.valueOf(foxes.sevenFactor));
        scoreViewWolves.setText(String.valueOf(wolves.sevenFactor));
    }

    /**
     * Increase the score for a team by 1 point.
     * Track 5-on-5 play goals and update metrics accordingly.
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
     * Track power-play and short-handed goals and update metrics accordingly.
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

    /**
     * Track shot attempts that the opposing team blocks and update metrics accordingly.
     */
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

    /**
     * Track shot attempts that hit the goal post but do not score and update metrics accordingly.
     */
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

    /**
     * Track remaining shot attempts that are neither blocked nor come in contact with goal.
     * Update metrics accordingly.
     */
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

    /**
     * Track takeaways by team and turnovers for the opposing team.
     * Update metrics accordingly.
     */
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

    /**
     * Track completed passes and update metrics accordingly.
     */
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

    /**
     * Track finished checks and update metrics accordingly.
     */
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
