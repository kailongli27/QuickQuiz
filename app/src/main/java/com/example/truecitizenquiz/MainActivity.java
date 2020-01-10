package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button truebutton;
    private Button falsebutton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton previousButton;

    private int QuestionIndex = 0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_dogs, true),
            new Question(R.string.question_oceans, false),
            new Question(R.string.question_million, false),
            new Question(R.string.question_squareroot, false),
            new Question(R.string.question_water, true),
            new Question(R.string.question_fish, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falsebutton = findViewById(R.id.false_button);
        truebutton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);

        falsebutton.setOnClickListener(this);
        truebutton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                QuestionIndex = (QuestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;

            case R.id.previous_button:
                if (QuestionIndex == 0){
                    QuestionIndex = 5;
                } else {
                    QuestionIndex = (QuestionIndex - 1) % questionBank.length;
                }
                updateQuestion();
                break;
        }
    }

    private void updateQuestion(){
        questionTextView.setText(questionBank[QuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userAnswerCorrect){
        boolean answerIsTrue = questionBank[QuestionIndex].isAnswerTrue();
        int toastMessageId = 0;

        if (userAnswerCorrect == answerIsTrue){
            toastMessageId = R.string.correct;
        } else {
            toastMessageId = R.string.incorrect;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
