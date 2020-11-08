package dev.muathamer.assignment2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("SetTextI18n")
public class TimerActivity extends AppCompatActivity {


    private ExtendedFloatingActionButton playButton;
    private ExtendedFloatingActionButton pauseButton;
    private TextInputEditText hoursEditText;
    private TextInputEditText minutesEditText;
    private TextInputEditText secondsEditText;

    private boolean isRunning = false;
    private int hours = 0, minutes = 0, seconds = 0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setStatusBarColor(R.color.indigo_700);
        initView();

        forceEditTextToHaveNumericValue(hoursEditText);
        forceEditTextToHaveNumericValue(minutesEditText);
        forceEditTextToHaveNumericValue(secondsEditText);

        turnTimerOff(null);
        continueFromSavedState(savedInstanceState);
        startTimerThread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("hours", hours);
        outState.putInt("minutes", minutes);
        outState.putInt("seconds", seconds);
        super.onSaveInstanceState(outState);
    }

    private void continueFromSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("isRunning")) {
            isRunning = savedInstanceState.getBoolean("isRunning");

            if (isRunning) turnTimerOn(null);
            else turnTimerOff(null);

            hours = savedInstanceState.getInt("hours");
            minutes = savedInstanceState.getInt("minutes");
            seconds = savedInstanceState.getInt("seconds");
        }
    }

    private void setStatusBarColor(int colorResource) {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, colorResource));
    }

    public void goToBmiActivity(View view) {
        Intent intent = new Intent(this, BmiActivity.class);
        startActivity(intent);
        finish();
    }

    private void startTimerThread() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isRunning)
                    countdownOneSecond();
            }
        }, 0, 1000);
    }

    private void readTimerInputViews() {
        hours = Integer.parseInt(hoursEditText.getText().toString());
        minutes = Integer.parseInt(minutesEditText.getText().toString());
        seconds = Integer.parseInt(secondsEditText.getText().toString());
    }

    private void updateTimerViews() {
        hoursEditText.setText(hours + "");
        minutesEditText.setText(minutes + "");
        secondsEditText.setText(seconds + "");
    }

    private void countdownOneSecond() {
        if (seconds > 0) {
            seconds--;
        } else if (countdownOneMinute()) {
            seconds = 59;
        } else {
            runOnUiThread(() -> {
                turnTimerOff(null);
                showFinishedDialog();
            });
        }

        updateTimerViews();
    }


    private boolean countdownOneMinute() {
        if (minutes > 0) {
            minutes--;
        } else if (countdownOneHour()) {
            minutes = 59;
        } else {
            return false;
        }

        return true;
    }

    private boolean countdownOneHour() {
        if (hours > 0) {
            hours--;
            return true;
        } else
            return false;
    }

    public void turnTimerOn(View view) {
        readTimerInputViews();
        hoursEditText.setFocusable(false);
        minutesEditText.setFocusable(false);
        secondsEditText.setFocusable(false);

        isRunning = true;
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
    }

    public void turnTimerOff(View view) {
        hoursEditText.setFocusable(true);
        hoursEditText.setFocusableInTouchMode(true);
        minutesEditText.setFocusable(true);
        minutesEditText.setFocusableInTouchMode(true);
        secondsEditText.setFocusable(true);
        secondsEditText.setFocusableInTouchMode(true);

        isRunning = false;
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
    }

    public void resetTimer(View view) {
        turnTimerOff(null);
        hours = minutes = seconds = 0;
        updateTimerViews();
    }

    private void showFinishedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Timer finished")
                .setMessage("Timer has finished counting down.")
                .setPositiveButton(android.R.string.yes, null)
                .setIcon(R.drawable.ic_round_hourglass_empty_24)
                .show();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void forceEditTextToHaveNumericValue(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this);
                if (s.toString().isEmpty())
                    editText.setText(R.string.clock_digit_zero);
                else {
                    int value = Integer.parseInt(editText.getText().toString());
                    editText.setText(value + "");
                }
                editText.setSelection(editText.getText().length());
                editText.addTextChangedListener(this);
            }
        });

        editText.setOnTouchListener((v, event) -> {
            System.out.println("touched");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> editText.setSelection(editText.getText().length()), 10);
            return v.performClick();
        });

    }

    private void initView() {
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        hoursEditText = findViewById(R.id.hoursEditText);
        minutesEditText = findViewById(R.id.minutesEditText);
        secondsEditText = findViewById(R.id.secondsEditText);
    }
}