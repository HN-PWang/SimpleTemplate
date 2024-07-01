package com.example.simpletemplate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import com.example.basemodel.base.BaseModelActivity;

public class SelectPhotoActivity extends BaseModelActivity {

    private ActivityResultLauncher<String> requestPermissionLauncher;

    private ActivityResultLauncher<Intent> selectImageLauncher;

    private ImageView ivImage;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_select_photo;
    }

    @Override
    public void initView() {
        ivImage = findViewById(R.id.iv_image);
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission()
                , new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            //允许权限
                            toSelectImage();
                        } else {
                            //不允许

                        }
                    }
                });
        selectImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    ivImage.setImageURI(result.getData().getData());
                }
            }
        });
    }

    @Override
    public void initLogic() {
        if (ContextCompat.checkSelfPermission(this
                , android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // 权限已被授予
            toSelectImage();
        } else {
            // 请求权限
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void toSelectImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(galleryIntent);
    }
}