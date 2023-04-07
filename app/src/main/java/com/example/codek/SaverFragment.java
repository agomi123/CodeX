package com.example.codek;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

public class SaverFragment extends BottomSheetDialogFragment {

   Button savebtn;
   EditText editText;
   View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_saver, container, false);
        savebtn=view.findViewById(R.id.savebtn);
        editText=view.findViewById(R.id.filename);
        String strtext = getArguments().getString("edttext");
        savebtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().isEmpty())
                savefile(editText.getText().toString(),strtext);
            }
        });
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    private void savefile(String filename, String content){
        try {
            FileOutputStream fos = requireContext().openFileOutput(filename+".txt",Context.MODE_PRIVATE);
            Writer out = new OutputStreamWriter(fos);
            out.write(content);
            out.close();
            Toast.makeText(getContext(),"Code Saved!",Toast.LENGTH_SHORT).show();
            //arrayList.add(filename+".txt");
            saveinshare(filename);
            Intent it =new Intent(getContext(),MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(it);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveinshare(String filename){
        String currdate=String.valueOf(java.time.LocalDate.now());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String  password = sharedPreferences.getString("password", "");
        String  dating = sharedPreferences.getString("dating", "");
        editor.putString("password",password+","+filename);
        editor.putString("dating",dating+","+currdate);
        editor.apply();
    }
}