package com.nuzharukiya.hzaun_app_base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.Gravity;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.nuzharukiya.hzaun_app_base.models.FileModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nuzha Rukiya on 18/09/11.
 */
public class BaseUtils {

    private Context context;

    private ProgressDialog progressDialog;

    public BaseUtils(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isOnline(Context _context) {
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Creates a short toast given a string resource
     *
     * @param message
     */
    public void makeToast(int message) {
        makeToast(context.getString(message));
    }

    /**
     * Creates a short toast given a string
     *
     * @param message
     */
    public void makeToast(final String message) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String capitalizeWords(String str) {

        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder b = new StringBuilder(str);
        int i = 0;
        do {
            if (b.length() > i + 1)
                b.replace(i, i + 1, b.substring(i, i + 1).toUpperCase());
            else {
                b.replace(i, i + 1, String.valueOf(Character.toUpperCase(b.charAt(i))));
            }
            i = b.indexOf(" ", i) + 1;
        } while (i > 0 && i < b.length());

        return b.toString();
    }

    private static String getMimeType(File file) {
        String type = null;
        final String url = file.toString();
        final String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        }
        if (type == null) {
            type = "image"; // fallback type. You might set it to */*
        }
        return type;
    }

    private static String getFileExtension(File file) {
        final String url = file.toString();
        final String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        return extension;
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        } else if (exifOrientation == ExifInterface.ORIENTATION_NORMAL) {
            return 90;
        }
        return 0;
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public FileModel fileModelFromPath(String filePath) {

        FileModel fileModel = new FileModel();
        File file = new File(filePath);

        try {
            fileModel.setFileContents(extractBytes(filePath));
            fileModel.setFileName(file.getName());
            fileModel.setFileExtension(getFileExtension(file));
            fileModel.setFileType(getMimeType(file));
            fileModel.setFileUrl(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileModel;
    }

    private byte[] extractBytes(String filePath) {

        Bitmap bm = BitmapFactory.decodeFile(filePath);

        if (bm != null) {
            try {
                FileOutputStream fOut = new FileOutputStream(filePath);
                bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);


                ExifInterface exif = new ExifInterface(filePath);
                int exifOrientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                int rotationInDegrees = exifToDegrees(exifOrientation);
                Matrix matrix = new Matrix();
                if (exifOrientation != 0f) {
                    matrix.preRotate(rotationInDegrees);
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);

                fOut.close();
                return stream.toByteArray();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            try {
                File file = new File(filePath);

                return getBytes(file);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    private byte[] getBytes(File file) {
        try {
            InputStream input = new FileInputStream(file);
            return getBytes(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getBytes(InputStream input) {
        byte[] data = new byte[1024];
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int read = 0;
            while ((read = input.read(data, 0, data.length)) != -1) {
                output.write(data, 0, read);
            }
            output.flush();
            data = output.toByteArray();
            output.close();
            input.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getLoader() {
        progressDialog = new ProgressDialog(context, R.style.AppDialogTheme);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.show();
    }

    public void dismissLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
