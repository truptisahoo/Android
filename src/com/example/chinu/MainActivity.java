package com.example.chinu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	LinearLayout parentLayout;
	LinearLayout[] dynamicLayout;
	TextView[] mTextview;
	CheckBox[] mCheckbox;
	Button mButton;
	int i = 0;
	List<String> list = new ArrayList<String>();

	String[] answers;
	String[] QuestionList = new String[] { "q1", "q2", "q3", "q4" };
	String[] Question = new String[] { "what is capital of india",
			"who is the father of computer", "Who invented Telephone",
			"Java is a ", "1+2=?" };
	String[] option = new String[] { "Delhi", "Patna", "BBSR", "Mumbai",
			"option5", "option6", "option7", "option8", "option9", "option10",
			"option11", "option12", "option13", "option14", "option15",
			"option16", "option13", "option14", "option15", "option16" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parentLayout = (LinearLayout) findViewById(R.id.llayout);
		mButton = (Button) findViewById(R.id.save);
		// Checkbox's size depends upon the database for instance, i have taken
		// hardcoded value.
		mCheckbox = new CheckBox[option.length];
		// similarly textviews size depends upon the no of question in the
		// database
		mTextview = new TextView[Question.length];
		dynamicLayout = new LinearLayout[Question.length];
		for (int i = 0; i < option.length; i++) {
			mCheckbox[i] = new CheckBox(this);
			mCheckbox[i].setText(option[i]);
			mCheckbox[i].setId(i);

		}

		for (int i = 0; i < Question.length; i++) {
			mTextview[i] = new TextView(this);
			mTextview[i].setText(Question[i]);
			mTextview[i].setId(i);
		}

		for (i = 0; i < Question.length; i++) {
			dynamicLayout[i] = new LinearLayout(this);
			dynamicLayout[i].setOrientation(LinearLayout.VERTICAL);
			dynamicLayout[i].addView(mTextview[i]);
			dynamicLayout[i].addView(mCheckbox[i * 4 + 0]);
			dynamicLayout[i].addView(mCheckbox[i * 4 + 1]);
			dynamicLayout[i].addView(mCheckbox[i * 4 + 2]);
			dynamicLayout[i].addView(mCheckbox[i * 4 + 3]);
			parentLayout.addView(dynamicLayout[i]);

		}

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				loopQuestions(parentLayout);
			}
		});
	}

	private void loopQuestions(ViewGroup parent) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			View child = parent.getChildAt(i);
			/*
			 * if(child instanceof RadioGroup ) { //Support for RadioGroups
			 * RadioGroup radio = (RadioGroup)child; storeAnswer(radio.getId(),
			 * radio.getCheckedRadioButtonId()); }
			 */
			if (child instanceof CheckBox) {
				// Support for Checkboxes
				CheckBox cb = (CheckBox) child;
				int answer = cb.isChecked() ? 1 : 0;
				if (cb.isChecked()) {
					Toast.makeText(getApplicationContext(), cb.getText(),
							Toast.LENGTH_LONG).show();
					list.add(cb.getText().toString()); // add the answers to the
														// list
					System.out.println(list); // prints the answers in the
												// console

				}

				// System.out.println("amswer is------->" + answer);
				storeAnswer(cb.getId(), answer);// not completed yet i have
												// created it to store the
												// question and answers as pair.
			}

			if (child instanceof ViewGroup) {
				// Nested Q&A
				ViewGroup group = (ViewGroup) child;
				loopQuestions(group);
			}
		}
	}

	private void storeAnswer(int question, int answer) {
		// Log.w("ANDROID DYNAMIC VIEWS:", "Question: " +
		// String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer)
		// );

		// Toast toast = Toast.makeText(this, String.valueOf(question) + " * "+
		// "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
		// toast.setGravity(Gravity.TOP, 25, 400);
		// toast.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
