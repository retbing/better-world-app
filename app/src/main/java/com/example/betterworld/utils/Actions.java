package com.example.betterworld.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.betterworld.activities.CharityDetailsActivity;
import com.example.betterworld.activities.CharityFormActivity;
import com.example.betterworld.activities.ChooseCategoryActivity;
import com.example.betterworld.activities.DonationDetailActivity;
import com.example.betterworld.activities.ForgotPasswordActivity;
import com.example.betterworld.activities.HomeActivity;
import com.example.betterworld.activities.LoginActivity;
import com.example.betterworld.activities.NotificationActivity;
import com.example.betterworld.activities.PaymentActivity;
import com.example.betterworld.activities.ProfileActivity;
import com.example.betterworld.activities.ProfileEdit;
import com.example.betterworld.activities.RegisterActivity;
import com.example.betterworld.models.Charity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

public class Actions {
    private static final String TAG = "Actions";


    public static void gotoMainActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
    public  static  void  gotoProfileActivity(Activity activity){
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }
    public  static  void  goToEditProfileActivity(Activity activity){
        Intent intent = new Intent(activity, ProfileEdit.class);
        activity.startActivity(intent);
    }


    public static void goToPaymentActivity(Activity activity) {
        Intent intent = new Intent(activity, PaymentActivity.class);
        activity.startActivity(intent);
    }

    public static void goToCharityDetailsActivity(Activity activity, String CHARITY_ID) {
        Intent intent = new Intent(activity, CharityDetailsActivity.class);
        intent.putExtra("CHARITY_ID",CHARITY_ID);
        activity.startActivity(intent);
    }


    public static void goToDonationDetailActivity(Activity activity,float amount,String charityId) {
        Intent intent = new Intent(activity, DonationDetailActivity.class);
        intent.putExtra("DONATION_AMOUNT", amount);
        intent.putExtra("CHARITY_ID", charityId);
        activity.startActivity(intent);
    }

    public static void gotoNotificationActivity(Activity activity) {
        Intent intent = new Intent(activity, NotificationActivity.class);
        activity.startActivity(intent);
    }

    public static void goToLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }


    public static void goToRegisterActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    public  static void goToCharityStartActivity(Activity activity) {
        Intent intent = new Intent(activity, ChooseCategoryActivity.class);
        activity.startActivity(intent);
    }

    public static void goToCharityFormActivity(Activity activity, String categoryName) {
        Intent intent = new Intent(activity, CharityFormActivity.class);
        intent.putExtra("CATEGORY_NAME", categoryName);
        activity.startActivity(intent);
    }

    public static void goToForgotPasswordActivity(Activity activity) {
        Intent intent = new Intent(activity, ForgotPasswordActivity.class);
        activity.startActivity(intent);
    }

    public static void startImagePickingActivity(@NotNull Activity activity, int GALLERY_REQUEST_CODE) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png", "image/jpg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.setAction(Intent.ACTION_GET_CONTENT);

        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);

    }

    public static void launchImageCrop(Activity activity, Uri uri) {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1920, 1080)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .start(activity);
    }
}
