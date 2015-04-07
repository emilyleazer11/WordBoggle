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


    //the number of words in the game
    private static final int WORDS_IN_GAME = 5;

    private List<String> wordList;
    private List<String> wordListScrambled;
    private List<String> wordListScrambledWithCharScrambled;
    private int questionNumber = 0;
    private EditText answerSpace;
    private String correctAnswer; //correct word spelled right
    private String categoryWanted;
    private int totalAnswered; //amount of questions answered
    private int correctAnswers; //number answered correctly
    private int timePerGuess; //10, 20, or 30 seconds
    private Random random; //to keep quiz randomized

    //private TextView numberOfQuestionTextView; //shows which question number currently on
    //private TextView answerTextView; //displays Correct! of Incorrect!

    //configures GameFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        Intent starterIntent = this.getActivity().getIntent();
        String category = starterIntent.getStringExtra(SettingsActivity.CATEGORY_EXTRA);

        Log.w(TAG, "Got category = " + category);
        if (category.equals("Animals")) {
            String[] items = getResources().getStringArray(R.array.animal_list);
            wordList = new ArrayList<String>(Arrays.asList(items));
        } else {
            String[] items = getResources().getStringArray(R.array.food_list);
            wordList = new ArrayList<String>(Arrays.asList(items));
        }
        Collections.shuffle(wordList);

        for (String word : wordList) {
            Log.w(TAG, "word: " + word);
            Log.w(TAG, "scrambled: " + StringUtilities.wordScramble(word));

        }
        wordListScrambled = new ArrayList<String>();
        wordListScrambled = wordList;
        wordListScrambledWithCharScrambled = new ArrayList<String>();
        for(int i=0; i<wordListScrambled.size(); i++){
            String word = wordListScrambled.get(i);
            String scrambledWord = StringUtilities.wordScramble(word);
            wordListScrambledWithCharScrambled.add(scrambledWord);
        }

        TextView textViewScrambled = (TextView) view.findViewById(R.id.textViewScrambledWord);
        textViewScrambled.setText(wordListScrambledWithCharScrambled.get(questionNumber));

        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(clickListener);

        //random = new Random();
        //timePerGuess = new int();
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkAnswer();
        }
    };

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
        questionNumber++;
        answerSpace = (EditText) this.getView().findViewById(R.id.answerEditText);
        if(answerCorrect){
            if (questionNumber < 5){
                TextView textViewScrambled = (TextView) this.getView().findViewById(R.id.textViewScrambledWord);
                textViewScrambled.setText(wordListScrambledWithCharScrambled.get(questionNumber));
                answerSpace.setText("");
            } else {

            }

        } else {




        }
        //startActivity(intent);


    }


    public void resetGame(){

        //AssetManager assets = getActivity().getAssets();
        wordList.clear();
    }







} // end class WordBoggle

