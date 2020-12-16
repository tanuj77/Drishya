package org.vsr.drishya;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ImageCaptureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagecapture);

    }

    public void addToFav(String dirName, Bitmap bitmap) {

        String resultPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) +
                dirName + System.currentTimeMillis() + ".jpg";
        Log.e("resultpath", resultPath);
        new File(resultPath).getParentFile().mkdir();


        if (Build.VERSION.SDK_INT < 29) {

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "Photo");
            values.put(MediaStore.Images.Media.DESCRIPTION, "Edited");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
            values.put("_data", resultPath);

            ContentResolver cr = getContentResolver();
            cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try {
                OutputStream fileOutputStream = new FileOutputStream(resultPath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                if (fileOutputStream != null) {
                    Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        } else {

            OutputStream fos = null;
            File file = new File(resultPath);

            final String relativeLocation = Environment.DIRECTORY_PICTURES;
            final ContentValues contentValues = new ContentValues();

            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation + "/" + dirName);
            contentValues.put(MediaStore.MediaColumns.TITLE, "Photo");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.BUCKET_ID, file.toString().toLowerCase(Locale.US).hashCode());
            contentValues.put(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME, file.getName().toLowerCase(Locale.US));

            final ContentResolver resolver = getContentResolver();
            final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Uri uri = resolver.insert(contentUri, contentValues);

            try {
                fos = resolver.openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fos != null) {
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
            }

        }


    }
}
