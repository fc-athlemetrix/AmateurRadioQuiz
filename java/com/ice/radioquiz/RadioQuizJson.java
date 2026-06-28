package com.ice.radioquiz;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class RadioQuizJson extends Activity {

    TabHost tabHost;
    private ListView listView;
    int numOfRecords = 500;
    int index = 0;
    int correctAnswers;
    int totalAnswers;

    private List<Answer> answerList = new ArrayList<Answer>();
    String   jsonFile = "2020ExtraClass.json";               // put this value in setup files

    String    correcttext = "";
    Button    nextButton;
    Button    fastButton;
    Button    randomButton;
    Button    answerButton;
    TextView  question;
    TextView  answera;
    TextView  answerb;
    TextView  answerc;
    TextView  answerd;
    TextView  score;
    ImageView figure;
    TextView  figuretext;
    Answer  currentAnswer;
    Answer  correctAnswer;

    String    jsonResponse;


    int correct;
    int total;
    private GestureDetector gestureDetector;


    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainjson);
        Resources res = getResources(); // Resource object to get Drawables

        nextButton   = (Button) findViewById(R.id.nextButton);
        fastButton   = (Button) findViewById(R.id.fastButton);
        randomButton = (Button) findViewById(R.id.randomButton);

        question     = (TextView)findViewById(R.id.question);
        answera      = (TextView)findViewById(R.id.a);
        answerb = (TextView)findViewById(R.id.b);
        answerc = (TextView)findViewById(R.id.c);
        answerd = (TextView)findViewById(R.id.d);
        score = (TextView)findViewById(R.id.score);
        figure = (ImageView)findViewById(R.id.image1);
        figuretext = (TextView)findViewById(R.id.figuretext);
	
        Intent intent = getIntent();
       jsonFile = intent.getStringExtra("dataset");

     //   PreferenceScreen prefSet = getPreferenceScreen();
     //   mListPreference = (ListPreference) prefSet.findPreference(SERVER);
        nextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                index += 1;
                if (index >= answerList.size())
                    index = 0;
                currentAnswer = answerList.get(index);
                printQuestion(currentAnswer);

            }
        });

        randomButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int theSize = answerList.size();
                index = (int)Math.ceil(Math.random()*theSize);
                if (index >= theSize)
                    index = 0;
                currentAnswer = answerList.get(index);
                printQuestion(currentAnswer);

            }
        });


        answera.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct == 0) {
                    answera.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answera.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct == 1) {
                    answerb.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerb.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerc.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct == 2) {
                    answerc.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerc.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct == 3) {
                    answerd.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerd.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        // get json file from preferences
        // jsonFile =
        index = 0;
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        jsonResponse = loadJSONFromAsset(jsonFile);
        doLanguageJSONDecode(jsonResponse);

        currentAnswer = answerList.get(0);
        printQuestion(currentAnswer);
    }

    public void onClick(View v) {
        int item = v.getId();
        return;
    }

    public void onPause(Bundle savedInstanceState) {
    }

    public void onResume(Bundle savedInstanceState) {
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); inflater.inflate(R.menu.mainmenu, menu); return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.menu_prefs) {
// Launch to our preferences screen. Intent intent = new Intent().setClass(this, com.androidbook.preferences.sample.FlightPreferenceActivity.class);
            this.startActivityForResult(intent, 0); }
        return true;
    }
*/


    public void printQuestion(Answer a)
    {

        question.setText(a.question);
        answera.setText("A. " + a.a);
        answerb.setText("B. " + a.b);
        answerc.setText("C. " + a.c);
        answerd.setText("D. " + a.d);
        answera.setBackgroundColor(Color.BLACK);
        answerb.setBackgroundColor(Color.BLACK);
        answerc.setBackgroundColor(Color.BLACK);
        answerd.setBackgroundColor(Color.BLACK);
        /*
        if (a.figure != null) {
            Resources res = getResources();
            int resourceId = res.getIdentifier(a.figure, "drawable", getPackageName());
            figure.setImageResource(resourceId);
            figuretext.setText(a.figure);
        } else {
            figure.setImageResource(android.R.color.transparent);
            figuretext.setText("");
        }

         */
    }

    public void updateScore()
    {
        total++;
        score.setText(correct + "/" + total);
    }

    public void getCorrectAnswer(Answer a)
    {

        if (a.correct == 0) {
            answera.setBackgroundColor(Color.GREEN);
        }
        if (a.correct == 1) {
            answerb.setBackgroundColor(Color.GREEN);
        }
        if (a.correct == 2) {
            answerc.setBackgroundColor(Color.GREEN);
        }
        if (a.correct == 3) {
            answerd.setBackgroundColor(Color.GREEN);
        }
    }


    public String loadJSONFromAsset(String assetName) {
        String json = null;
        try {
            InputStream is = getAssets().open(assetName);

            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    protected void doLanguageJSONDecode(String jsonResponse) {

        String question ="", a ="", b ="", c ="", d="", number;
        int correct;

        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray word = jsonObject.getJSONArray("word");
            /*********** Process each JSON Node ************/

            for (int i = 0; i < word.length(); i++) {
                /****** Get Object for each JSON node.***********/
                JSONObject v = word.getJSONObject(i);

                /******* Fetch node values **********/
                number = v.optString("id");
                question = v.optString("question");
                JSONArray answerStrings = v.getJSONArray("answers");
                String strings[] = new String[answerStrings.length()];
                a = answerStrings.getString(0);
                b = answerStrings.getString(1);
                c = answerStrings.getString(2);
                d = answerStrings.getString(3);
                correct = v.optInt("correct");



                Log.e("id", number + "" + question+ "  " + a + "  " + b);

                answerList.add(new Answer(number, question, a, b, c, d, correct, ""));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}