package com.example.betterworld.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityCharityFormBinding;

import com.example.betterworld.utils.Actions;

import com.example.betterworld.viewmodels.CharityViewModel;

import com.google.android.material.snackbar.Snackbar;

import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


import static com.example.betterworld.utils.Actions.gotoMainActivity;

import static com.example.betterworld.utils.HelperClass.getDateDiff;
import static com.example.betterworld.utils.HelperClass.logErrorMessage;

@AndroidEntryPoint
public class CharityFormActivity extends AppCompatActivity {


    static final String TAG = "TAG CharityFormActivity";
    private static final int GALLERY_REQUEST_CODE = 0x001;

    private ActivityCharityFormBinding activityCharityFormBinding;
    private DatePickerDialog.OnDateSetListener onDateStartedSetListener;
    private DatePickerDialog.OnDateSetListener onDateEndedSetListener;

    int step;
    Date dueDate, startDate;
    String startedDate, endedDate, categoryName, categoryId;
    Uri imageUri;


    @Inject
    CharityViewModel charityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCharityFormBinding = DataBindingUtil.setContentView(this, R.layout.activity_charity_form);
        activityCharityFormBinding.setCharityModel(charityViewModel);
        _initComponents();

        step = -1;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    Actions.launchImageCrop(this, imageUri, 1920, 1080);
                }
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK && data != null) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri uri = result.getUri();
                if (uri != null) {
                    _setImage(uri);
                    this.imageUri = uri;
                }
            }
        }
    }

    private void _initComponents() {
        categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        categoryId = "#" + categoryName.toLowerCase();
        activityCharityFormBinding.btnNext.setOnClickListener(view -> _nextStep(1));
        activityCharityFormBinding.btnPrevious.setOnClickListener(view -> _previousStep());
        activityCharityFormBinding.ibThumbnail.setOnClickListener(view -> Actions.startImagePickingActivity(this, GALLERY_REQUEST_CODE));
        activityCharityFormBinding.etDate.setOnClickListener(view -> _dateTimeFrameLayout());
        activityCharityFormBinding.etDateStarted.setOnClickListener(view -> _dateTimeStartedPopup());
        activityCharityFormBinding.etDateEnded.setOnClickListener(view -> _dateTimeEndedPopup());
        activityCharityFormBinding.btnDone.setOnClickListener(view -> _dateTimeDone());
        activityCharityFormBinding.btnHome.setOnClickListener(view -> {
            gotoMainActivity(this);
        });
        activityCharityFormBinding.frameLayoutTransparent.setOnClickListener(view -> {
            activityCharityFormBinding.frameLayoutSuccess.setVisibility(View.GONE);
            activityCharityFormBinding.frameLayoutTransparent.setVisibility(View.GONE);
            activityCharityFormBinding.frameLayoutDatePopup.setVisibility(View.GONE);

        });

        onDateStartedSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                startedDate =  (month + 1) + "/" + day + "/" + year;
                activityCharityFormBinding.etDateStarted.setText(startedDate);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                startDate = new Date(calendar.getTimeInMillis());
            }
        };


        onDateEndedSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                endedDate = (month + 1) + "/" + day + "/" + year;
                activityCharityFormBinding.etDateEnded.setText(endedDate);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                dueDate = new Date(calendar.getTimeInMillis());
            }
        };

        activityCharityFormBinding.etPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void _createCharity() {
//       String profession =  activityCharityFormBinding.etProfession.getText().toString();
        String nameOfInstitution = activityCharityFormBinding.etNameOfInstitution.getText().toString();
        String socialMediaAccount = activityCharityFormBinding.etSocialMediaAccount.getText().toString();
        String address = activityCharityFormBinding.etAddress.getText().toString();
        String phoneNumber = activityCharityFormBinding.etPhoneNumber.getText().toString();

        charityViewModel.uploadImage(imageUri).observe(this, fileNameOrExp -> {
            if (fileNameOrExp.data != null) {
                final String fileName = fileNameOrExp.data;

                String title = activityCharityFormBinding.etTitle.getText().toString();
                String whoBenefits = activityCharityFormBinding.etWhoBenefits.getText().toString();
                float target = Float.parseFloat("0" + activityCharityFormBinding.etTarget.getText().toString());
                String description = activityCharityFormBinding.etDescription.getText().toString();

                charityViewModel.createCharity(title, categoryId, categoryName, whoBenefits, description, target, startDate, dueDate, fileName).observe(
                        this, charityOrExp -> {
                            if (charityOrExp.data != null) {
                                charityViewModel.editUserInFireStore(
                                        new HashMap<String, Object>() {{
                                            put("address", address);
                                            put("nameOfInstitution", nameOfInstitution);
                                            put("socialMediaAccount", socialMediaAccount);
                                            put("phoneNumber", phoneNumber);
                                            put("address", address);
                                        }}

                                ).observe(
                                        this, userOrExp -> {
                                            if (userOrExp.data != null) {
                                                _charityFormPageSuccess();
                                            } else {
                                                Snackbar.make(activityCharityFormBinding.clCharityForm, "Error while updating user data. Please try again later.", Snackbar.LENGTH_SHORT).show();
                                            }
                                        }
                                );
                            } else {
                                Snackbar.make(activityCharityFormBinding.clCharityForm, "Error while creating charity. Please try again later.", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                );
            } else {
                Snackbar.make(activityCharityFormBinding.clCharityForm, "Error while uploading image. Please try again later", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    private void _nextStep(int i) {
        _controlPageStep(i);
        logErrorMessage("Step :" + step);
        switch (step) {
            case 0:
                if (activityCharityFormBinding.etProfession.getText().toString().isEmpty()
                        || activityCharityFormBinding.etNameOfInstitution.getText().toString().isEmpty()
                        || activityCharityFormBinding.etSocialMediaAccount.getText().toString().isEmpty()
                        || activityCharityFormBinding.etAddress.getText().toString().isEmpty()
                ) {
                    if (activityCharityFormBinding.etProfession.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etProfession.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etNameOfInstitution.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etNameOfInstitution.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etSocialMediaAccount.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etSocialMediaAccount.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etAddress.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etAddress.setError("Fill this filed");
                    }
                    _controlPageStep(-1);

                } else {
                    _charityFormPage2();
                }
                break;
            case 1:
                logErrorMessage("Step : yoyoyoyo : " + step);

                if (activityCharityFormBinding.etTitle.getText().toString().isEmpty()
                        || activityCharityFormBinding.etWhoBenefits.getText().toString().isEmpty()
                        || activityCharityFormBinding.etSocialMediaAccount.getText().toString().isEmpty()
                        || activityCharityFormBinding.etDate.getText().toString().isEmpty()
                ) {
                    if (activityCharityFormBinding.etTitle.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etTitle.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etWhoBenefits.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etWhoBenefits.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etTarget.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etTarget.setError("Fill this filed");
                    }
                    if (activityCharityFormBinding.etDate.getText().toString().isEmpty()) {
                        activityCharityFormBinding.etDate.setError("Fill this filed");
                    }

                    _controlPageStep(-1);
                } else if (getDateDiff(startDate, dueDate) <= 0) {
                    activityCharityFormBinding.etDate.setError("At least 1 day");
                    activityCharityFormBinding.etDateEnded.setError("At least 1 day later");
                    _controlPageStep(-1);

                } else {
                    _charityFormPage3();
                }
                break;
            case 2:
                if (activityCharityFormBinding.etDescription.getText().toString().isEmpty()) {
                    activityCharityFormBinding.etDescription.setError("Fill this filed");
                    _controlPageStep(-1);
                } else {
                    _charityFormPage4();
                }
                break;
            case 3:
                if (activityCharityFormBinding.etPhoneNumber.getText().toString().isEmpty()) {
                    activityCharityFormBinding.etPhoneNumber.setError("Fill this filed");
                    _controlPageStep(-1);
                } else if (activityCharityFormBinding.etPhoneNumber.getText().toString().length() != 14
                        || !activityCharityFormBinding.etPhoneNumber.getText().toString().contains("(")
                        || !activityCharityFormBinding.etPhoneNumber.getText().toString().contains(")")
                        || !activityCharityFormBinding.etPhoneNumber.getText().toString().contains("-")) {
                    activityCharityFormBinding.etPhoneNumber.setError("Invalid phone number");
                    _controlPageStep(-1);
                } else {
                    _createCharity();
                }
                break;
            case -1:
                _charityFormPage1();
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
        activityCharityFormBinding.frameLayoutPage1.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage3.setVisibility(View.GONE);
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
        activityCharityFormBinding.frameLayoutPage1.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage3.setVisibility(View.VISIBLE);
        activityCharityFormBinding.frameLayoutPage4.setVisibility(View.GONE);
        activityCharityFormBinding.lineStepper2.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_blue));
        activityCharityFormBinding.lineStepper3.setBackgroundColor(ContextCompat.getColor(this, R.color.bw_light_grey));

        hideKeyboardFrom(getApplicationContext(), activityCharityFormBinding.frameLayoutPage3);

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

        activityCharityFormBinding.frameLayoutPage1.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.VISIBLE);
        activityCharityFormBinding.frameLayoutPage3.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage4.setVisibility(View.GONE);

        activityCharityFormBinding.btnPrevious.setVisibility(View.VISIBLE);

        hideKeyboardFrom(getApplicationContext(), activityCharityFormBinding.frameLayoutPage2);

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
        activityCharityFormBinding.frameLayoutPage2.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage3.setVisibility(View.GONE);
        activityCharityFormBinding.frameLayoutPage4.setVisibility(View.GONE);
        activityCharityFormBinding.btnPrevious.setVisibility(View.GONE);

        hideKeyboardFrom(getApplicationContext(), activityCharityFormBinding.frameLayoutPage1);

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

    private void _dateTimeDone() {
        activityCharityFormBinding.etDate.setText(startedDate + " - " + endedDate);
        activityCharityFormBinding.frameLayoutTransparent.setVisibility(View.INVISIBLE);
        activityCharityFormBinding.frameLayoutDatePopup.setVisibility(View.INVISIBLE);
    }

    private void _setImage(Uri uri) {
        Glide.with(this).load(uri).into(activityCharityFormBinding.ibThumbnail);
    }

}