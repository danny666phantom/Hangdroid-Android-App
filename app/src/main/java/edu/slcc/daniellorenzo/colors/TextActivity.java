package edu.slcc.daniellorenzo.colors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextActivity extends Activity {

    private EditText editText;
    private SharedPreferences preferences;
    private TextView textView;
    private String textWord;
    private String friendPhone;
    private String texterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        // Get text message from shared preferences.
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);
        // Read preferences to get friends phone if called from contacts activity.
        friendPhone = getIntent().getStringExtra("Phone"); // Defaults if data does not
        Log.d("MYLOG", "Friend: " + friendPhone);
        getTextFromPref();
        // get phone from intent if it was called from contacts.

    }
// Get word from pref when button pressed.

    public void setTextMessage(View view) {
        getTextFromPref();
    }

    public void getTextFromPref() {
        // Get text message from shared preferences.
        // Preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);
        // Read preferences.
        textWord = preferences.getString("TextedWord", "NO WORD"); // NO WORD if preferences not found.
        texterPhone = preferences.getString("TexterPhone", "NO PHONE"); // NO PHONE if preferences not found.
        textView = (TextView) findViewById(R.id.editTextWord);

        // Set up boolean values.
        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if (textWord == "NO WORD") word = false;
        if (texterPhone == "NO PHONE") phone = false;
        if (friendPhone == null) friend = false;

        // Word but no friend selected.
        if (!friend && word) {
            textView.setText(textWord);
            textWord = "";
            texterPhone = "";
            return;
        }

        if (word && phone) {
            if (friendPhone.equals(texterPhone)) {
                textView.setText(textWord);
                textWord = "";
                texterPhone = "";
            } else {
                Toast.makeText(this, "No Text from Selected Friend", Toast.LENGTH_LONG).show();
            }
            return;
        }
        if (!word) {
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
        }
    }

    // Play game when button pressed.
    public void playMultiPlayerGame(View view) {
        // Connect to XML.
        // Get word and cas word to a String.
        String textWord = textView.getText().toString();    // editText.getText().toString();
        if (textWord.length() > 0) {
            // Clear field for next word.
            textView.setText("");
            // Clear word from shared preferences.
            preferences.edit().remove("TextedWord").commit();
            Log.d("MYLOG", "Removed Texted Word: " + textView);
            // Create intent.
            Intent intent = new Intent(this, GameMultiActivity.class);
            // Send word with intent.
            intent.putExtra("GUESS_WORD", textWord);
            // intent.putExtra("GUESS_WORD", wordToGuess);
            // Start activity.
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Word Found - Try GET NEW TEXT", Toast.LENGTH_LONG).show();
        }
    }

}