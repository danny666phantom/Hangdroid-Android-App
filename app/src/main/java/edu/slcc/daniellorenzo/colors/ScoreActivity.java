package edu.slcc.daniellorenzo.colors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Find preferences
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);
        // Read preferences
        String scores = preferences.getString("SCORES", "NO SCORES"); // NO SCORES if preferences not found.
        // Get the textveiw for scores.
        TextView textView = (TextView) findViewById(R.id.textViewScores);
        // Put scores in textview.
        textView.setText(scores);

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

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_good_png) {
            Intent intent = new Intent(this, MultiPlayerActivity.class);
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
