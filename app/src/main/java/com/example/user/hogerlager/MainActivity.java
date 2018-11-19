package com.example.user.hogerlager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private Button mLowerButton;
    private Button mHigherButton;
    private ImageView mImageView;
    private List<String> mItems;
    private TextView mScore;
    private TextView mHighScore;
    private int currentImageIndex = 0;
    private int score = 0;
    private int highScore = 0;
    private int currentDice = 0;
    private int[] mImageNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLowerButton = findViewById(R.id.lowerButton);
        mHigherButton = findViewById(R.id.higherButton);
        mImageView = findViewById(R.id.imageView);
        mListView = findViewById(R.id.list_view);
        mScore= findViewById(R.id.textViewScore);
        mHighScore = findViewById(R.id.textViewHighScore);
        mItems = new ArrayList<>();



        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
        randomDice();
        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                currentDice = currentImageIndex;
                randomDice();
                if (currentImageIndex >= currentDice) {
                    score++;
                    message =  "Correct";
                }
                else {
                    score = 0;
                    message =  "Fout";
                }
                checkScore();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                currentDice = currentImageIndex;
                randomDice();
                if (currentImageIndex <= currentDice) {
                    score++;
                    message =  "Correct";
                }
                else {
                    score = 0;
                    message =  "Fout";
                }
                checkScore();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateUI() {
        // If the list adapter is null, a new one will be instantiated and set on our list view.
        if (mAdapter == null) {
            // We can use ‘this’ for the context argument because an Activity is a subclass of the Context class.
            // The ‘android.R.layout.simple_list_item_1’ argument refers to the simple_list_item_1 layout of the Android layout directory.
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
            mListView.setAdapter(mAdapter);
        } else {
            // When the adapter is not null, it has to know what to do when our dataset changes, when a change happens we need to call this method on the adapter so that it will update internally.
            mAdapter.notifyDataSetChanged();
        }
    }

    private void randomDice() {
        Random rand = new Random();
        int dice = rand.nextInt(6);
        mImageView.setImageResource(mImageNames[dice]);
        currentImageIndex = dice;
        mItems.add("De worp is " + (dice+1));
        updateUI();
    }

    private void checkScore() {
        mScore.setText("Score: "+score);
        if(score >= highScore){
            highScore = score;
            mHighScore.setText("HighScore: "+highScore);
        }
    }
}
