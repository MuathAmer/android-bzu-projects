package dev.muathamer.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;

@SuppressLint("SetTextI18n")
public class BmiActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView toolbarTextView;
    private TextView titleTextView;
    private ImageView nameIconImageView;
    private TextInputLayout nameTextField;
    private TextInputLayout ageTextField;
    private TextInputLayout genderTextField;
    private AutoCompleteTextView genderDropdown;
    private TextView heightLabel;
    private Slider heightSlider;
    private TextView weightLabel;
    private Slider weightSlider;
    private TextView heightValueTextView;
    private TextView weightValueTextView;
    private Button saveButton;
    private Button calculateButton;

    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        initView();

        populateGenderSpinner();
        attacheTextViewToSlider(heightValueTextView, heightSlider, "cm");
        attacheTextViewToSlider(weightValueTextView, weightSlider, "kg");
        setupSharedPreferences();
        populateViewsFromSharedPrefs();
    }

    private void populateGenderSpinner() {
        String[] genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        genderDropdown.setAdapter(adapter);
    }

    private void attacheTextViewToSlider(TextView valueTextView, Slider slider, String postfix) {
        slider.addOnChangeListener((slider1, value, fromUser) -> {
            valueTextView.setText((int) value + postfix);
        });
    }

    public void calculateAndShowBmiResult(View view) {
        float weightValue = weightSlider.getValue();
        float heightValue = heightSlider.getValue();

        double bmiValue = 10000 * weightValue / (heightValue * heightValue);
        bmiValue = Math.round(bmiValue * 10) / 10.0;

        String bmiStatus;
        if (bmiValue < 18.5)
            bmiStatus = getString(R.string.underweight);
        else if (bmiValue < 25)
            bmiStatus = getString(R.string.normal);
        else if (bmiValue < 30)
            bmiStatus = getString(R.string.overweight);
        else if (bmiValue <= 40)
            bmiStatus = getString(R.string.obese);
        else
            bmiStatus = getString(R.string.extreme_obese);

        Bundle bundle = new Bundle();
        bundle.putDouble("bmiValue", bmiValue);
        bundle.putString("bmiStatus", bmiStatus);

        BmiResultBottomDialogFragment bmiResultBottomDialogFragment = BmiResultBottomDialogFragment.newInstance();
        bmiResultBottomDialogFragment.setArguments(bundle);

        bmiResultBottomDialogFragment.show(getSupportFragmentManager(), "show_bmi_result_dialog_fragment");
    }


    public void setupSharedPreferences() {
        sharedPrefs = getSharedPreferences(getString(R.string.bmi_preference_file_key), Context.MODE_PRIVATE);
    }

    public void savePersonData(View view) {
        String name = nameTextField.getEditText().getText().toString();
        String gender = genderDropdown.getText().toString();
        float height = heightSlider.getValue();
        float weight = weightSlider.getValue();
        String ageStr = ageTextField.getEditText().getText().toString();

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(getString(R.string.saved_person_name_key), name);
        editor.putString(getString(R.string.saved_person_gender_key), gender);
        editor.putFloat(getString(R.string.saved_person_height_key), height);
        editor.putFloat(getString(R.string.saved_person_weight_key), weight);
        if (!ageStr.isEmpty())
            editor.putInt(getString(R.string.saved_person_age_key), Integer.parseInt(ageStr));

        editor.apply();

        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
    }

    public void populateViewsFromSharedPrefs() {
        String name = sharedPrefs.getString(getString(R.string.saved_person_name_key), "");
        String gender = sharedPrefs.getString(getString(R.string.saved_person_gender_key), "");
        float height = sharedPrefs.getFloat(getString(R.string.saved_person_height_key), -1);
        float weight = sharedPrefs.getFloat(getString(R.string.saved_person_weight_key), -1);
        int age = sharedPrefs.getInt(getString(R.string.saved_person_age_key), -1);

        nameTextField.getEditText().setText(name);
        genderTextField.getEditText().setText(gender);
        if (height != -1) heightSlider.setValue(height);
        if (weight != -1) weightSlider.setValue(weight);
        if (age != -1) ageTextField.getEditText().setText(age + "");
    }

    public void goToTimerActivity(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTextView = findViewById(R.id.toolbarTextView);
        titleTextView = findViewById(R.id.titleTextView);
        nameIconImageView = findViewById(R.id.nameIconImageView);
        nameTextField = findViewById(R.id.nameTextField);
        ageTextField = findViewById(R.id.ageTextField);
        genderTextField = findViewById(R.id.genderTextField);
        genderDropdown = findViewById(R.id.genderDropdown);
        heightLabel = findViewById(R.id.heightLabel);
        heightSlider = findViewById(R.id.heightSlider);
        weightLabel = findViewById(R.id.weightLabel);
        weightSlider = findViewById(R.id.weightSlider);
        heightValueTextView = findViewById(R.id.heightValueTextView);
        weightValueTextView = findViewById(R.id.weightValueTextView);
        saveButton = findViewById(R.id.saveButton);
        calculateButton = findViewById(R.id.calculateButton);
    }
}