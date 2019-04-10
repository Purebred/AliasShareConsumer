package red.hound.aliasshareconsumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AliasShareConsumer";

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                "red.hound.aliasshareprovider.READ_ALIAS")
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "red.hound.aliasshareprovider.READ_ALIAS")) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{"red.hound.aliasshareprovider.READ_ALIAS"},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    public void fetchAliases(View view)
    {
        TextView tv = findViewById(R.id.textview);
        fetchAliases(getApplicationContext(), tv);
    }
    public static void fetchAliases(Context context, TextView tv)
    {
        try {
            ContentResolver mContentResolver = context.getContentResolver();
            String cpUri = "content://red.hound.aliasshareprovider/alias";

            // get aliases and certs
            final String[] aliasesAndCertsAndType = {"alias", "certificate", "type"};
            final String[] aliasesAndCerts = {"alias", "certificate"};
            final String[] aliasesAndType = {"alias", "type"};
            final String[] certsAndType = {"certificate", "type"};
            final String[] aliasesOnly = {"alias"};
            final String[] certsOnly = {"certificate"};
            final String[] typeOnly = {"type"};

            //StringBuilder sb = new StringBuilder();
            List<String> sb = new ArrayList<>();

            sb.add("\nAliases only * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            Cursor data = mContentResolver.query(Uri.parse(cpUri), aliasesOnly, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String a = data.getString(0);
                sb.add(a + "\n");
            }

            sb.add("\nTypes only * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), typeOnly, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String t = data.getString(0);
                sb.add(t + "\n");
            }

            sb.add("\nAliases and types * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), aliasesAndType, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String a = data.getString(0);
                String t = data.getString(1);
                sb.add(a + ": " + t + "\n");
            }

            sb.add("\nAliases and certificates and types * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), aliasesAndCertsAndType, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String a = data.getString(0);
                String c = data.getString(1);
                String t = data.getString(2);
                sb.add(a + "("+ t + "): " + c + "\n");
            }

            sb.add("\nAliases and certificates * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), aliasesAndCerts, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String a = data.getString(0);
                String c = data.getString(1);
                sb.add(a + ": " + c + "\n");
            }

            sb.add("\nCertificates and types * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), certsAndType, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String c = data.getString(0);
                String t = data.getString(1);
                sb.add(t + ": " + c + "\n");
            }

            sb.add("\nCertificates only * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
            data = mContentResolver.query(Uri.parse(cpUri), certsOnly, null, null, null);
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                // do what you need with the cursor here
                String c = data.getString(0);
                sb.add(c + "\n");
            }

            for(String s : sb) {
                if(null != tv) {
                    tv.append(s);
                }
                else {
                    Log.d(TAG, s);
                }
            }
        }
        catch(Exception e) {
            Log.e(TAG, "Error: " + e.getMessage());
        }

    }
}
