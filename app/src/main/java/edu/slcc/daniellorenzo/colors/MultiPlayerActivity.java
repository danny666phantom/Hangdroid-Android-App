package edu.slcc.daniellorenzo.colors;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MultiPlayerActivity extends Activity {


    EditText editText; // Declare class attribute so I can add a listender.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        // Connect to XML.
        editText = (EditText) findViewById(R.id.editTextWord);

        // Addlistener.
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Log.d("MYLOG", "afterTextChanged " + s);

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                Log.d("MYLOG", "beforeTextChanged" + " Start: " + start + " Count: " + count + " After: " + after);

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.d("MYLOG", "onTextChange" + " Start: " + start + " Before: " + before + " Count " + count);

            }
        });

    }

    public void playMultiPlayerGame(View view) {

        // Create new editText object from XML.
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        // Get word and cast word to a String.
        String wordToGuess = editText.getText().toString();
        // Debug.
        Log.d("MYLOG", "Multi-Player Word: " + wordToGuess);
        // Create intent.
        Intent intent = new Intent(this, GameMultiActivity.class);
        // Send word with intent.
        intent.putExtra("GUESS_WORD", wordToGuess);
        // Start activity.
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

} // End of class.
