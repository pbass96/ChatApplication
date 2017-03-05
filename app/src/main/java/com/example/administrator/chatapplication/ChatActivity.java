package com.example.administrator.chatapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity  {

    private EditText mInputMessage;
   // private Button mSendMessage;
    private LinearLayout mMessageLog;
   // private TextView mCpuMessage;

    private ScrollView mScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mInputMessage = (EditText) findViewById(R.id.input_message);
       // mSendMessage = (Button) findViewById(R.id.send_message);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
      //  mCpuMessage = (TextView) findViewById(R.id.cpu_message);

        mScroll = (ScrollView) findViewById(R.id.scroll);





    }

    public void sendMessage(View v) {
        String inputText = mInputMessage.getText().toString();
        String lowerInputText = inputText.toLowerCase();

        String answer="";

        final int marginSize = getResources().getDimensionPixelSize(R.dimen.message_margin);

        TextView userMessage = new TextView(this);
       //userMessage.setGravity(Gravity.END);
        LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        userMessageLayout.gravity = Gravity.END;
        userMessageLayout.setMargins(0, marginSize, 0, marginSize);

        int messageColor = getResources().getColor(R.color.message_color);
        userMessage.setTextColor(messageColor);
        //userMessage.setLayoutParams(userMessageLayout);

        userMessage.setText(inputText);
        userMessage.setBackgroundResource(R.drawable.user_message);

        mMessageLog.addView(userMessage, 0, userMessageLayout);


        if(lowerInputText.contains(getString(R.string.how_are_you))) {
            answer = getString(R.string.fine);
        }
        else if(lowerInputText.contains(getString(R.string.tire))) {
            answer = getString(R.string.bless_you);
        }
        else if(lowerInputText.contains(getString(R.string.fortune))) {
            double rand = Math.random() * 5d;

            if(rand < 1d) {
                answer = getString(R.string.worst_luck);
            }
            else if(rand < 2d) {
                answer = getString(R.string.good_luck);
            }
            else if(rand < 3d) {
                answer = getString(R.string.nice_luck);
            }
            else if(rand < 4d) {
                answer = getString(R.string.best_luck);
            }
            else {
                answer = getString(R.string.best_luck);
            }
        }
        else if(lowerInputText.contains(getString(R.string.time))) {
            //answer = "시간";
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);

            //answer = String.format("현재 시각은 %1$d시 %2$d분 %3$d초 입니다", hour,minute,second);
            answer = getString(R.string.time_format, hour, minute, second);

        }
        else {
            answer = getString(R.string.good);
        }

        //mCpuMessage.setText(answer);
        final TextView cpuMessage = new TextView(this);

        int cpuMessageColor = getResources().getColor(R.color.cpumessage_color);
        cpuMessage.setTextColor(cpuMessageColor);

        cpuMessage.setText(answer);
        cpuMessage.setGravity(Gravity.START);
        cpuMessage.setBackgroundResource(R.drawable.cpu_message);

        mScroll.scrollTo(0,0);

        mInputMessage.setText("");

        TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(), 0,0,0);
        userMessageAnimation.setDuration(1000);
        userMessage.startAnimation(userMessageAnimation);

        userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                cpuMessageLayout.gravity = Gravity.START;
                // 간격 설정
                cpuMessageLayout.setMargins(marginSize,marginSize,marginSize,marginSize);
                mMessageLog.addView(cpuMessage,0, cpuMessageLayout);

                TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                cpuAnimation.setDuration(1000);
                cpuMessage.setAnimation(cpuAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

}
