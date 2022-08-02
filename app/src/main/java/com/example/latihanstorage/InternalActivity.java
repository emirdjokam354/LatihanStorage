package com.example.latihanstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button buatFile, bacaFile, ubahFile, hapusFile;
    TextView hasilBaca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        buatFile = (Button) findViewById(R.id.createFile);
        bacaFile = (Button) findViewById(R.id.readFile);
        ubahFile = (Button) findViewById(R.id.editFile);
        hapusFile = (Button) findViewById(R.id.deleteFile);

        hasilBaca = (TextView) findViewById(R.id.txtFile);

        buatFile.setOnClickListener((View.OnClickListener) this);
        bacaFile.setOnClickListener((View.OnClickListener) this);
        ubahFile.setOnClickListener((View.OnClickListener) this);
        hapusFile.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        jalankanFIle(view.getId());
    }


    public void jalankanFIle(int id) {
        switch (id) {
            case R.id.createFile:
                buatFile();
                break;
            case R.id.readFile:
                bacaFile();
                break;
            case R.id.editFile:
                ubahFile();
                break;
            case R.id.deleteFile:
                hapusFile();
                break;
        }
    }

    void buatFile() {
        String isiFile = "Coba isi data file text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (FileNotFoundException e)   {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaFile() {
        File fileInternal = getFilesDir();
        File file = new File(fileInternal, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while(line != null) {
                    text.append(line);
                    line = br.readLine();
                }

                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            hasilBaca.setText(text.toString());
        }
    }


    void ubahFile() {
        String ubah = "Ubah file ini...";

        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream =  new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader baris = new BufferedReader(new FileReader(file));
                String line = baris.readLine();

                while (line != null) {
                    text.append(line);
                    line = baris.readLine();
                }

                baris.close();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
            hasilBaca.setText(text.toString());
        }
    }


    
}
