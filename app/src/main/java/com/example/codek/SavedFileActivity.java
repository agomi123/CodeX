package com.example.codek;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class SavedFileActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FileAdapter fileAdapter;
    RelativeLayout relativeLayout;
    ArrayList<FileName> arrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_file);
        recyclerView = findViewById(R.id.recycle2);
        relativeLayout=findViewById(R.id.rel123);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
        String password = sharedPreferences.getString("password", "");
        String password2 = sharedPreferences.getString("dating", "");
        String[] playlists = password.split(",");
        String[] dater = password2.split(",");
        int n = playlists.length;
        int n2 = dater.length;
        if (n > 1 && n2 > 1) {
            for (int i = 1; i < n; i++) {
                arrayList.add(new FileName(playlists[i], dater[i]));
            }
        }
        fileAdapter = new FileAdapter(getApplicationContext(), arrayList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(fileAdapter);

    }



    public void programiz(View view) {
        Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "programiz");
        startActivity(it);
    }

    public void codechef(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "codechef");  startActivity(it);
    }

    public void w3school(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "w3");  startActivity(it);
    }

    public void interviewbit(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "interview");  startActivity(it);
    }

    public void tutorialpoint(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "tutorial");  startActivity(it);
    }

    public void gfg(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "gfg");  startActivity(it);
    }
    public void onlingedgb(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "onlingedgb");  startActivity(it);
    }
    public void joodle(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "joodle");  startActivity(it);
    }
    public void onecompiler(View view) { Intent it = new Intent(this, WebViewActivity.class);
        it.putExtra("name", "onecompiler");  startActivity(it);
    }

    public void clearall(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SavedFileActivity.this);
        builder.setMessage("\n\tWant To Delete All Codes Files ?");
        builder.setTitle("\nCodeX-Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.clear();
                SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
                settings.edit().clear().apply();
                fileAdapter.notifyDataSetChanged();
                fileAdapter.notifyItemRemoved(0);
                Snackbar snackbar = Snackbar.make(relativeLayout, "Code Deleted", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                snackbar.show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}