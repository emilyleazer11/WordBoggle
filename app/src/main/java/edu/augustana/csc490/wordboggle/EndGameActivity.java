package edu.augustana.csc490.wordboggle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EndGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent starterIntent = this.getIntent();
        String result = starterIntent.getStringExtra(GameFragment.RESULT_EXTRA);
        TextView resultText = (TextView) findViewById(R.id.resultTextView);
        Log.w(GameFragment.TAG, "Got result:" + result);
        if (result.equals("Winner")){
            //set large text to YOU WIN!
            resultText.setText("YOU WIN!");


        } else {
            //set large text to YOU LOSE!
            resultText.setText("YOU LOSE!");

        }

        //accesses Play Again button
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        playAgain.setOnClickListener(clickListener);

    }

    //onClickListener for the Play Again Button
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            beginGameAgain();
        }
    };

    //starts the SettingsActivity over in order to play game again
    public void beginGameAgain(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }


}
