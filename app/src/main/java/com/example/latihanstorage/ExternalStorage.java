package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExternalStorage extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME_EXTERNAL = "fileExternal.txt";
    public static final String FILENAME_PATH = "MyFileDir";
    Button readExternal, writeExternal, editExternal, deleteExternal;
    TextView readResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        readExternal = (Button) findViewById(R.id.readFileExternal);
        writeExternal = (Button) findViewById(R.id.createFileExternal);
        editExternal = (Button) findViewById(R.id.editFileExternal);
        deleteExternal = (Button) findViewById(R.id.deleteFileExternal);

        readResult = (TextView) findViewById(R.id.txtFileExternal);

        readExternal.setOnClickListener((View.OnClickListener) this);
        writeExternal.setOnClickListener((View.OnClickListener) this);
        editExternal.setOnClickListener((View.OnClickListener) this);
        deleteExternal.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        jalankanMethod(view.getId());
    }

    public void jalankanMethod(int id) {
        switch (id) {
            case R.id.createFileExternal:
                buatFile();
                break;
            case R.id.readFileExternal:
                bacaFile();
                break;
            case R.id.editFileExternal:
                editFile();
                break;
            case R.id.deleteFileExternal:
                hapusFile();
                break;
        }
    }

    void buatFile() {
        String isiFile = "Tulis File External Storage";
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)){
            Toast.makeText(this, "External SD Card not mounted", Toast.LENGTH_SHORT).show();
        }

//        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File file = new File(getExternalFilesDir(FILENAME_PATH), FILENAME_EXTERNAL);

            FileOutputStream outputStream = null;
            try {
//                file.createNewFile();
                outputStream = new FileOutputStream(file, true);
                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        Toast.makeText(this, "File created...", Toast.LENGTH_SHORT).show();

    }

    void editFile () {
        String isiFile = "Edit File External Storage";
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)){
            Toast.makeText(this, "External SD Card not mounted", Toast.LENGTH_SHORT).show();
        }

//        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        File file = new File(getExternalFilesDir(FILENAME_PATH), FILENAME_EXTERNAL);

        FileOutputStream outputStream = null;
        try {
//                file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "File updated...", Toast.LENGTH_SHORT).show();

    }
    void bacaFile() {
        File sdcard = getExternalFilesDir(FILENAME_PATH);
        File file = new File(sdcard, FILENAME_EXTERNAL);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line !=null) {
                    text.append(line);
                    line = br.readLine();
                }

                br.close();

            }catch (IOException e) {
                System.out.println("Ini Error " + e.getMessage());
            }

            readResult.setText(text.toString());
        }

    }

    void hapusFile() {
        File file = new File(getExternalFilesDir(FILENAME_PATH), FILENAME_EXTERNAL);

        if (file.exists()){
            file.delete();
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }

                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            readResult.setText(text.toString());

        }else {
            Toast.makeText(this, "File cannot create...", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}