package com.example.betterworld.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Toast;

import static com.example.betterworld.utils.Actions.goToCharityStartActivity;
import static com.example.betterworld.utils.Actions.popOut;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityProfileEditBinding;
import com.example.betterworld.utils.Actions;
import com.example.betterworld.viewmodels.ProfileViewModel;
import com.google.firebase.auth.FirebaseUser;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Actions.gotoProfileActivity;

@AndroidEntryPoint
public class ProfileEdit extends AppCompatActivity {

    ActivityProfileEditBinding activityProfileEditBinding;
    private static final int GALLERY_REQUEST_CODE = 0x001;
    Uri imageUri;
    FirebaseUser fbUser;
    @Inject
    ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit);
        fbUser = profileViewModel.checkIfUserIsAuthenticated();
        _initComponents();
    }

    private void _initComponents() {
        checkAuthAndPopulateFields();
        activityProfileEditBinding.uploadAttribute.setOnClickListener(view -> Actions.startImagePickingActivity(this, GALLERY_REQUEST_CODE));
        activityProfileEditBinding.etUserPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        activityProfileEditBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileEdit.this, "Editing useer", Toast.LENGTH_LONG).show();
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("firstName", activityProfileEditBinding.etUserFirstName.getText().toString() + "");
                userMap.put("lastName", activityProfileEditBinding.etUserLastName.getText().toString() + "");
                userMap.put("phoneNumber", activityProfileEditBinding.etUserPhone.getText().toString() + "");
                userMap.put("email", activityProfileEditBinding.etUserEmail.getText().toString() + "");

                profileViewModel.editUserInFireStore(fbUser.getUid(), userMap)
                        .observe(ProfileEdit.this, dataOrExc -> {
                            if (dataOrExc.data != null) {
                                Toast.makeText(ProfileEdit.this, "User updated successful", Toast.LENGTH_LONG).show();
                                if (imageUri != null) {
                                    createUserProfilePic();
                                } else {
                                    popOut(ProfileEdit.this);
                                }
                            } else {
                                Toast.makeText(ProfileEdit.this, "User Update Not successful try Again", Toast.LENGTH_LONG).show();
                            }
                        });


            }
        });


        activityProfileEditBinding.btnBlur.setOnClickListener(view -> {
            popOut(this);
            goToCharityStartActivity(this);
        });
        activityProfileEditBinding.btnProfile.setOnClickListener(view -> {
            popOut(this);
        });
        activityProfileEditBinding.btnReturnPage.setOnClickListener(view -> {
            popOut(this);
        });

    }


    private void checkAuthAndPopulateFields() {
        if (fbUser != null) {
            activityProfileEditBinding.etUserEmail.setText(fbUser.getEmail());
            profileViewModel.getUserFromFirestore(fbUser.getUid(), fbUser.getEmail()).observe(this, dataOrException -> {
                if (dataOrException.data != null) {
                    activityProfileEditBinding.etUserFirstName.setText(dataOrException.data.getFirstName());
                    activityProfileEditBinding.etUserLastName.setText(dataOrException.data.getLastName());
                    activityProfileEditBinding.etUserPhone.setText(dataOrException.data.getPhoneNumber());
                    Glide.with(this).load(dataOrException.data.getImageUrl()).into(activityProfileEditBinding.userThumbnail);
                } else {
                    goToLoginActivity(this);
                }
            });

        } else {
            Toast.makeText(this, "\"Not Authenticated: Going to login Page\"", Toast.LENGTH_SHORT).show();
            goToLoginActivity(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                Actions.launchImageCrop(this, imageUri, 1000, 1000);
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

    private void _setImage(Uri uri) {
        Glide.with(this).load(uri).into(activityProfileEditBinding.userThumbnail);
    }

    private void createUserProfilePic() {
        profileViewModel.uploadImage(imageUri).observe(this, fileNameOrExp -> {
            if (fileNameOrExp.data != null) {
                final String fileName = fileNameOrExp.data;
                profileViewModel.editUserInFireStore(fbUser.getUid(), new HashMap<String, Object>() {{
                    put("imageUrl", fileName);
                }}).observe(ProfileEdit.this, datOrEx -> {
                    if (datOrEx != null) {
                        Toast.makeText(this, "Image upload successfull!", Toast.LENGTH_SHORT).show();
                        popOut(ProfileEdit.this);
                    }
                });

            } else {
                Toast.makeText(this, "Error while uploading image. Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}