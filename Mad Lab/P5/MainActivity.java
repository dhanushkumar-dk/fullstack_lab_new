package com.example.labp5;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String DYNAMIC_MODULE_PACKAGE = "com.example.dynamicmodule";
    private static final String DYNAMIC_MODULE_ACTIVITY =
            "com.example.dynamicmodule.DynamicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDynamicModule(View view) {
        if (isDynamicModuleInstalled()) {
            Intent moduleIntent = new Intent();
            moduleIntent.setClassName(DYNAMIC_MODULE_PACKAGE, DYNAMIC_MODULE_ACTIVITY);
            startActivity(moduleIntent);
        } else {
            Toast.makeText(this, "Dynamic module is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDynamicModuleInstalled() {  // Fixed the method declaration
        PackageManager packageManager = getPackageManager();
        Intent moduleIntent = new Intent();
        moduleIntent.setClassName(DYNAMIC_MODULE_PACKAGE, DYNAMIC_MODULE_ACTIVITY);
        List<ResolveInfo> activities = packageManager.queryIntentActivities(moduleIntent, 0);
        return !activities.isEmpty();
    }
}
