package edu.slcc.daniellorenzo.colors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.os.Handler;
import android.view.MenuItem;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Expand the splash xml to a view.
        setContentView(R.layout.activity_splash);

        // New thread. It will also run the method called nextActivity()
        Runnable wait3seconds = new Runnable() {
            public void run() {
                nextActivity();
            }
        };

        // Runs a delay for my splash to display.
        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);
    }

    public void nextActivity() {

        // Start next activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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


        return super.onOptionsItemSelected(item);
    }

}
