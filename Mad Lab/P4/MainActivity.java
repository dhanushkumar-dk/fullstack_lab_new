package com.example.labp4;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {
    TextView txt;
    TextView ram;
    Button btn;
    long bytesAvailable,megAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        StatFs statFs = new StatFs(Environment.getDownloadCacheDirectory().getPath());
        bytesAvailable =statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        megAvailable = bytesAvailable/ (1024*1024);
    }
    private void initView() {
        txt= findViewById(R.id.setInformation);
        btn= findViewById(R.id.button);
        ram=findViewById(R.id.ram);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information= getHardwareAndSoftwareInfo();
                txt.setText(information);
                ram.setText(getMemoryinfo());
            }
        });
    }
    private String getMemoryinfo() {
        Context context = getApplicationContext();
        ActivityManager activityManager = (ActivityManager)
        context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        DecimalFormat twoDecimalFormate = new DecimalFormat("#.##");
        String finalvalue = "";
        long totalMemory = memoryInfo.totalMem;
        double kb = totalMemory / 1024.0;
        double mb = totalMemory / 1048576.0;
        double gb = totalMemory / 10737541824.0;
        double tb = totalMemory / 1099511627776.0;
        if (tb > 1) {
            finalvalue = twoDecimalFormate.format(tb).concat("TB");
        } else if (gb> 1) {

            finalvalue = twoDecimalFormate.format(gb).concat("GB");
        } else if (mb > 1) {
            finalvalue = twoDecimalFormate.format(mb).concat("MB");
        } else if (kb > 1) {
            finalvalue = twoDecimalFormate.format(kb).concat("KB");
        }
        else
        {
            finalvalue = twoDecimalFormate.format(totalMemory).concat("Bytes");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ram: ").append(finalvalue).append("\n").
                append("Available internal/external free space:" + megAvailable + "MB");
        return stringBuilder.toString();
    }
    private String getHardwareAndSoftwareInfo() {
        return "Model"+" "+ Build.SERIAL+ "\n"+
                "id" + " "+ Build.ID+"\n"+
                "manufacture" + " "+ Build.MANUFACTURER+"\n"+
                "Brand" + " "+ Build.BRAND+"\n"+
                "id" + " "+ Build.ID+"\n" ;
    } }