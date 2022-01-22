package com.sunag.medicinetime.medicine;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunag.medicinetime.R;
import com.sunag.medicinetime.views.Question;

public class quiz extends AppCompatActivity implements View.OnClickListener{

    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private ImageView Image;
    private TextView questionTextView;
    private int correct = 0;
    // to keep current question track
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            // array of objects of class Question
            // providing questions from string
            // resource and the correct ans
            new Question(R.string.z, false),
            new Question(R.string.z, true),
            new Question(R.string.z, false),
            new Question(R.string.z, true),
            new Question(R.string.z, true),
            new Question(R.string.z, true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // setting up the buttons
        // associated with id
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        // register our buttons to listen to
        // click events
        questionTextView
                = findViewById(R.id.answer_text_view);
        Image = findViewById(R.id.myimage);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(quiz.this);
        nextButton.setOnClickListener(quiz.this);
        prevButton.setOnClickListener(quiz.this);
        Image.setImageResource(R.drawable.g7);
        trueButton.setText("Kapil Dev");
        falseButton.setText("Sachin Tendulkar");
//        falseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(trial.this, "hi", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void onClick(View v)
    {
        // checking which button is
        // clicked by user
        // in this case user choose false
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                // go to next question
                // limiting question bank range
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;
                    // we are safe now!
                    // last question reached
                    // making buttons
                    // invisible
                    if (currentQuestionIndex == 6) {
                        Image.setImageResource(R.drawable.g6);
                        questionTextView.setText(getString(
                                R.string.correct, correct));
                        nextButton.setVisibility(
                                View.INVISIBLE);
                        prevButton.setVisibility(
                                View.INVISIBLE);
                        trueButton.setVisibility(
                                View.INVISIBLE);
                        falseButton.setVisibility(
                                View.INVISIBLE);
                        if (correct > 3)

                            questionTextView.setText(
                                    "CORRECTNESS IS " + correct
                                            + " "
                                            + "OUT OF 6");
                        // showing correctness
//                        else
//                            Image.setImageResource(
//                                    R.drawable.resu);
                        // if correctness<3 showing sad emoji
                    }
                    else {
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());
        // setting the textview with new question
        switch (currentQuestionIndex) {
            case 1:
                // setting up image for each
                // question
                Image.setImageResource(R.drawable.g1);
                trueButton.setText("2");
                falseButton.setText("3");
                break;
            case 2:
                Image.setImageResource(R.drawable.g2);
                trueButton.setText("false");
                falseButton.setText("true");
                break;
            case 3:
                Image.setImageResource(R.drawable.g3);
                trueButton.setText("Einstein");
                falseButton.setText("Tesla");
                break;
            case 4:
                Image.setImageResource(R.drawable.g4);
                trueButton.setText("Amitabh Bachchan");
                falseButton.setText("Amir Khan");
                break;
            case 5:
                Image.setImageResource(R.drawable.g5);
                trueButton.setText("Red Fort");
                falseButton.setText("Taj Mahal");
                break;
            case 6:
                Image.setImageResource(R.drawable.g6);
                trueButton.setText("2");
                falseButton.setText("3");
                break;
            case 7:
                Image.setImageResource(R.drawable.g7);
                trueButton.setText("Kapil Dev");
                falseButton.setText("Sachin Tendulkar");
                break;
        }
    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();
        // getting correct ans of current question
        int toastMessageId;
        // if ans matches with the
        // button clicked

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(quiz.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}