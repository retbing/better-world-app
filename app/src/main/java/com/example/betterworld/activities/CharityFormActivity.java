package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityFormBinding;

import java.util.Calendar;

import static com.example.betterworld.utils.Actions.goToCharityFormActivity;

public class CharityFormActivity extends AppCompatActivity {
    private ActivityCharityFormBinding activityCharityFormBinding;
    private DatePickerDialog.OnDateSetListener onDateStartedSetListener;
    private DatePickerDialog.OnDateSetListener onDateEndedSetListener;

    private int step;
    String endedDate;
    String startedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCharityFormBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_form);
        _initComponents();
        step = 0;
    }


    private void _initComponents() {
        activityCharityFormBinding.btnNext.setOnClickListener(view -> _nextStep(1));
        activityCharityFormBinding.btnPrevious.setOnClickListener(view -> _previousStep());
        activityCharityFormBinding.et4Page2.setOnClickListener(view -> _dateTimeFrameLayout());
        activityCharityFormBinding.etDateStarted.setOnClickListener(view -> _dateTimeStartedPopup());
        activityCharityFormBinding.etDateEnded.setOnClickListener(view -> _dateTimeEndedPopup());
        activityCharityFormBinding.btnDone.setOnClickListener(view -> _dateTimeDone());

        onDateStartedSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                startedDate = month + "/" + day + "/" + year;
                activityCharityFormBinding.etDateStarted.setText(startedDate);
            }
        };

        onDateEndedSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                endedDate = month + "/" + day + "/" + year;
                activityCharityFormBinding.etDateEnded.setText(endedDate);
            }
        };

    }

    private void _nextStep(int i) {
        _controlPageStep(i);
        switch (step) {
            case 0:
                _charityFormPage1();
                break;
            case 1:
                _charityFormPage2();
                break;
            case 2:
                _charityFormPage3();
                break;
            case 3:
                _charityFormPage4();
                break;
            case 4:
                _charityFormPageSuccess();
                break;
        }
    }

    private void _controlPageStep(int i) {
        step += i;
        if (step > 4) {
            step -= 1;
        }
    }

    private void _charityFormPageSuccess() {
        activityCharityFormBinding.btnStepper1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper4.setTextColor(ContextCompat.getColor(this, R.color.white));
        activityCharityFormBinding.btnStepper4.setText("");
        activityCharityFormBinding.frameLayoutSuccess.setVisibility(View.VISIBLE);
        activityCharityFormBinding.frameLayoutTransparent.setVisibility(View.VISIBLE);
    }

    private void _charityFormPage4() {
        activityCharityFormBinding.btnStepper1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_current));
        activityCharityFormBinding.btnStepper3.setTextColor(ContextCompat.getColor(this, R.color.white));
        activityCharityFormBinding.btnStepper4.setTextColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.btnStepper3.setText("");
        activityCharityFormBinding.btnStepper4.setText("4");
        activityCharityFormBinding.frameLayoutPage4.setVisibility(View.VISIBLE);
        activityCharityFormBinding.lineStepper3.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_blue));
    }

    private void _charityFormPage3() {
        activityCharityFormBinding.btnStepper1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_current));
        activityCharityFormBinding.btnStepper4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper2.setTextColor(ContextCompat.getColor(this, R.color.white));
        activityCharityFormBinding.btnStepper3.setTextColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.btnStepper4.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.btnStepper2.setText("");
        activityCharityFormBinding.btnStepper3.setText("3");
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.frameLayoutPage4.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.lineStepper2.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.lineStepper3.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
    }

    private void _charityFormPage2() {
        activityCharityFormBinding.btnStepper1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_done));
        activityCharityFormBinding.btnStepper2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_current));
        activityCharityFormBinding.btnStepper3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper1.setTextColor(ContextCompat.getColor(this, R.color.white));
        activityCharityFormBinding.btnStepper2.setTextColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.btnStepper3.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.btnStepper4.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.btnStepper1.setText("");
        activityCharityFormBinding.btnStepper2.setText("2");
        activityCharityFormBinding.lineStepper1.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.lineStepper2.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
        activityCharityFormBinding.lineStepper3.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
        activityCharityFormBinding.frameLayoutPage1.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.VISIBLE);
        activityCharityFormBinding.btnPrevious.setVisibility(View.VISIBLE);
    }

    private void _charityFormPage1() {
        activityCharityFormBinding.btnStepper1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_current));
        activityCharityFormBinding.btnStepper2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_charity_form_unfocus));
        activityCharityFormBinding.btnStepper1.setTextColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.btnStepper2.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.btnStepper3.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.btnStepper4.setTextColor(ContextCompat.getColor(this, R.color.bw_light_grey_darker));
        activityCharityFormBinding.lineStepper1.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
        activityCharityFormBinding.lineStepper2.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
        activityCharityFormBinding.lineStepper3.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));
        activityCharityFormBinding.btnStepper1.setText("1");
        activityCharityFormBinding.frameLayoutPage1.setVisibility(View.VISIBLE);
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.btnPrevious.setVisibility(View.GONE);
    }

    private void _previousStep() {
        this._nextStep(-1);
    }

    private void _dateTimeFrameLayout() {
        activityCharityFormBinding.frameLayoutDatePopup.setVisibility(View.VISIBLE);
        activityCharityFormBinding.frameLayoutTransparent.setVisibility(View.VISIBLE);
    }

    private void _dateTimeStartedPopup() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateStartedSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    private void _dateTimeEndedPopup() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateEndedSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    private void _dateTimeDone(){
        String fullDateTime = startedDate + " - " + endedDate;
        activityCharityFormBinding.et4Page2.setText(fullDateTime);
        activityCharityFormBinding.frameLayoutTransparent.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.frameLayoutDatePopup.setVisibility(View.INVISIBLE);
    }

}