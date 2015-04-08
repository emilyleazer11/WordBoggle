package edu.augustana.csc490.wordboggle;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by emilyleazer11 on 3/31/2015.
 */
public class GameFragment extends Fragment{

    //string for error messages
    public static final String TAG = "WordBoggle Activity";
    //passed in when creating intent
    public static final String RESULT_EXTRA = "RESULT";

    private List<String> wordList;
    private List<String> wordListScrambled;
    private List<String> wordListScrambledWithCharScrambled;
    private int questionNumber = 0;
    private EditText answerSpace;

    //configures GameFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        Intent starterIntent = this.getActivity().getIntent();
        String category = starterIntent.getStringExtra(SettingsActivity.CATEGORY_EXTRA);

        Log.w(TAG, "Got category = " + category);
        //sets array list to be the proper category
        if (category.equals("Animals")) {
            String[] items = getResources().getStringArray(R.array.animal_list);
            wordList = new ArrayList<String>(Arrays.asList(items));
        } else {
            String[] items = getResources().getStringArray(R.array.food_list);
            wordList = new ArrayList<String>(Arrays.asList(items));
        }
        //shuffles the order of the word list
        Collections.shuffle(wordList);

        for (String word : wordList) {
            Log.w(TAG, "word: " + word);
            Log.w(TAG, "scrambled: " + StringUtilities.wordScramble(word));

        }
        //creates two word list arrays to keep track of the right answer
        wordListScrambled = new ArrayList<String>();
        wordListScrambled = wordList;
        wordListScrambledWithCharScrambled = new ArrayList<String>();
        //adds the scrambled word to the array list wordListScrambledWithCarScrambled
        for(int i=0; i<wordListScrambled.size(); i++){
            String word = wordListScrambled.get(i);
            String scrambledWord = StringUtilities.wordScramble(word);
            wordListScrambledWithCharScrambled.add(scrambledWord);
        }

        //displays the scambled word to the user
        TextView textViewScrambled = (TextView) view.findViewById(R.id.textViewScrambledWord);
        textViewScrambled.setText(wordListScrambledWithCharScrambled.get(questionNumber));

        //accesses the Submit button
        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(clickListener);

        return view;
    }

    //onClickListener for the Submit button
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkAnswer();
        }
    };

    //when the Submit button is clicked, this method is called to check if the answer is correct
    public void checkAnswer(){
        Intent intent = new Intent(this.getActivity(),EndGameActivity.class);
        answerSpace = (EditText) this.getView().findViewById(R.id.answerEditText);
        String answerSpaceString = answerSpace.getText().toString();
        //EditText answerFromUser = (EditText) this.getView().findViewById(R.id.answerEditText);
        //String answerFromUserString = answerFromUser.toString();

        Log.w(TAG, "Got answerFromUser = " + answerSpaceString);
        boolean answerCorrect;
        if(answerSpaceString.equals(wordListScrambled.get(questionNumber))){
            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
            answerCorrect = true;
        } else {
            Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
            answerCorrect = false;
        }
        //moves the arrayList forward to access the next word in the array
        questionNumber++;
        answerSpace = (EditText) this.getView().findViewById(R.id.answerEditText);
        //until user answers five correctly, game continues
        if(answerCorrect){
            if (questionNumber < 5){
                TextView textViewScrambled = (TextView) this.getView().findViewById(R.id.textViewScrambledWord);
                textViewScrambled.setText(wordListScrambledWithCharScrambled.get(questionNumber));
                answerSpace.setText("");
            } else {
                intent.putExtra(RESULT_EXTRA, "Winner");
                startActivity(intent);
            }

        } else {
            intent.putExtra(RESULT_EXTRA, "Loser");
            startActivity(intent);
        }



    }
} // end class WordBoggle

