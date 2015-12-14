package edu.slcc.daniellorenzo.colors;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.transition.TransitionManager;
import android.widget.Toast;


public class MainActivity extends Activity {

    // Adds sound.
    MediaPlayer mySound;

    // To add Button that Transitions.
    ViewGroup dannysLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this, R.raw.sound);


        dannysLayout = (ViewGroup) findViewById(R.id.dannysLayout);


        dannysLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event){
                        moveButton();
                        return true;
                }
             }
        );

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "parleyregular.ttf");
        TextView myTextview = (TextView)findViewById(R.id.dannyDroidTextView);
        myTextview.setTypeface(myTypeface);
    }


    public void moveButton(){
        View dannysButton = findViewById(R.id.dannysbutton);

        TransitionManager.beginDelayedTransition(dannysLayout);

        // Change the position of the button.
        RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        dannysButton.setLayoutParams(positionRules);

        // Change the size of the button.
        ViewGroup.LayoutParams sizeRules = dannysButton.getLayoutParams();
        sizeRules.width = 400;
        sizeRules.height = 300;
        dannysButton.setLayoutParams(sizeRules);

    }

    public void startSinglePlayerGame(View view){

        // Explicit intent sends a message to start an activity.
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
    public void startMultiPlayerGame(View view){

        // Explicit intent sends a message to start an activity.
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);

    }

    public void openScores(View view){
        // Update scores xml.
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public void startTextPlayerGame(View view){
        // Update scores xml.
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    public void testing(View view){
        // Update scores xml.
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "You can't go back. ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Method that plays music sound.
    public void playMusic(View view){
        mySound.start();
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
