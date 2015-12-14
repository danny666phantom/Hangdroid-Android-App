package edu.slcc.daniellorenzo.colors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends Activity {

    private int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("PointsID", 0); // Defaults if data does not come with intent

        TextView textView = (TextView) findViewById(R.id.textViewPoints);   // Specified from game over xml.
        textView.setText(String. valueOf(points));  // All text fields are Strings ... it will accept an int but with

        playerPoints = points;

    }

    // Called from button onclick in activities xml.
    public void saveScore(View view){ //Every time you implement an onclick it needs a view for a parameter.

        // SET up to score preferences.
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);
        // Get name from game over XML.
        EditText editText = (EditText) findViewById(R.id.editTextName);
        // set it to a string.
        String name = editText.getText().toString();

        // Start the preference editor.
        SharedPreferences.Editor editor = preferences.edit();
        // Get previous scores USING THE KEY!
        String previousScores = preferences.getString("SCORES", "");
        Log.d("MYLOG", "Previous Scores: " + previousScores);

        // Key = SCORES, VALUE = Concatinated String...
        editor.putString("SCORES", name + " " + playerPoints + "POINTS\n" + previousScores);
        // Saves buffer.
        editor.commit();

        // NAME X POINTS \n NAME2 Y POINTS.

        Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
        editText.setText("");
        // finish();


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


        if (id == R.id.action_favorite) {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
