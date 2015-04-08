package edu.augustana.csc490.wordboggle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends Activity
{
    public static final String CATEGORY_EXTRA = "CATEGORY";
    // use FragmentManager to display SettingsFragment
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //accesses the Play Game button so when clicked it moved to the
        //next activity to play the game
        Button playGame = (Button) findViewById(R.id.buttonPlayGame);
        playGame.setOnClickListener(clickListener);
    }

    //creating onClickListener for the playGame button and calls the
    //start Game method
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startGame();
        }
    };

    //starts the next activity when the Play Game button is clicked
    public void startGame() {
        Intent intent = new Intent(this, MainActivity.class);
        Spinner spinnerCategoryText = (Spinner) findViewById(R.id.spinnerCategory);
        String categoryChosen = spinnerCategoryText.getSelectedItem().toString();
        Log.w(GameFragment.TAG, "category =" + categoryChosen);
        intent.putExtra(CATEGORY_EXTRA, categoryChosen);
        startActivity(intent);

        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);


    }
} // end class SettingsActivity
