package com.example.codek;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView enableedititing, clearall, paste, save, share;
    ImageView copyt;
    SwitchMaterial switchMaterial;
    FloatingActionButton floatingActionButton;
    EditText openeditro;
    HorizontalScrollView horizontalScrollView;
    RelativeLayout relativeLayout, relat2;
    TextView t1, t2, t3, t4, t5;
    ImageView captitlize;
    ClipboardManager myClipboard;
    boolean open = false;
    boolean capitial = false;
    boolean checked = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_branch);
        t1 = findViewById(R.id.t1);
        openeditro = findViewById(R.id.openeditor);
        relat2 = findViewById(R.id.relared);
        relativeLayout = findViewById(R.id.mainr);
        clearall = findViewById(R.id.clearall);
        horizontalScrollView=findViewById(R.id.hs);
        paste = findViewById(R.id.pasty);
        share = findViewById(R.id.sharess);
        switchMaterial = findViewById(R.id.switch1);
        save = findViewById(R.id.save);
        enableedititing = findViewById(R.id.enableediting);
        copyt = findViewById(R.id.copty);
        floatingActionButton = findViewById(R.id.runcode);
        captitlize = findViewById(R.id.captilize);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked = true;
                    enabledarkmode();
                } else {
                    checked = false;
                    disablemode();
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("plain/text", openeditro.getText().toString());
                myClipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Code Copied", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this, WebViewActivity.class);
                it.putExtra("name", "onlingedgb");
                startActivity(it);
            }
        });
        captitlize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!capitial) {
                    capitial = true;
                    openeditro.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    openeditro.setHeight(500);
                    captitlize.setImageResource(R.drawable.activel);
                } else {
                    openeditro.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    capitial = false;
                    captitlize.setImageResource(R.drawable.lettera);
                }
            }
        });
        openeditro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // maketoast(MainActivity.this,s.toString());

                if (s.length() >= 1) {
                    switchMaterial.setVisibility(View.GONE);
                    clearall.setVisibility(View.VISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    captitlize.setVisibility(View.VISIBLE);
                } else {
                    clearall.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.INVISIBLE);
                    captitlize.setVisibility(View.INVISIBLE);
                    switchMaterial.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("\n\tWant To Clear All Codes ?");
                builder.setTitle("\nCodeX-Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sk=openeditro.getText().toString();
                        openeditro.setText("");
                        Snackbar snackbar = Snackbar.make(relativeLayout, "Code Deleted", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                openeditro.setText(sk);
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
        });

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void disablemode() {
        relat2.setBackgroundColor(getColor(R.color.white));
        enableedititing.setImageResource(R.drawable.pen);
        copyt.setImageResource(R.drawable.copy);
        clearall.setImageResource(R.drawable.delete);
        paste.setImageResource(R.drawable.paste);
        share.setImageResource(R.drawable.share);
        save.setImageResource(R.drawable.save);
        openeditro.setTextColor(getResources().getColor(R.color.black));
        t1.setTextColor(getResources().getColor(R.color.black));
        t2.setTextColor(getResources().getColor(R.color.black));
        t3.setTextColor(getResources().getColor(R.color.black));
        t4.setTextColor(getResources().getColor(R.color.black));
        t5.setTextColor(getResources().getColor(R.color.black));
    }

    public void enabletxt(View view) {
        if (!open) {
            t1.setText("Close Editor");
            horizontalScrollView.setVisibility(View.VISIBLE);
            if (!checked)
                enableedititing.setImageResource(R.drawable.close);
            else
                enableedititing.setImageResource(R.drawable.closedark);
            openeditro.setVisibility(View.VISIBLE);
            open = true;
            snackbarr(relativeLayout, "Editor opened");
            //Toast.makeText(MainActivity.this,"Editor opened",Toast.LENGTH_SHORT).show();
        } else {
            t1.setText("Open Editor");
            horizontalScrollView.setVisibility(View.INVISIBLE);
            enableedititing.setImageResource(R.drawable.pen);
            openeditro.setVisibility(View.GONE);
            openeditro.setText("");
            open = false;
            snackbarr(relativeLayout, "Editor closed");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sharecode(View view) {

        String x = openeditro.getText().toString();
        if (x.isEmpty()) {
            snackbarr(relativeLayout, "no code to share");
            return;
        }
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "CODE_X:CODE-EDITOR");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, x);
        startActivity(Intent.createChooser(intent, "share with"));
    }
    public void copytext(View view) {
        if (openeditro.getText().toString().isEmpty()) {
            snackbarr(relativeLayout, "No code to copy");
            return;
        }
        myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("plain/text", openeditro.getText().toString());
        myClipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Code Copied", Toast.LENGTH_SHORT).show();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void enabledarkmode() {
        relat2.setBackgroundColor(getColor(R.color.darkmode));
        enableedititing.setImageResource(R.drawable.pend);
        copyt.setImageResource(R.drawable.copydark);
        clearall.setImageResource(R.drawable.deletedark);
        paste.setImageResource(R.drawable.pastedark);
        share.setImageResource(R.drawable.sharedark);
        save.setImageResource(R.drawable.savedark);
        openeditro.setTextColor(getResources().getColor(R.color.white));
        t1.setTextColor(getResources().getColor(R.color.white));
        t2.setTextColor(getResources().getColor(R.color.white));
        t3.setTextColor(getResources().getColor(R.color.white));
        t4.setTextColor(getResources().getColor(R.color.white));
        t5.setTextColor(getResources().getColor(R.color.white));
    }
    public void pastext(View view) {
        ClipData abc = myClipboard.getPrimaryClip();
        ClipData.Item item = abc.getItemAt(0);
        String text = item.getText().toString();
        if (text.isEmpty()) {
            snackbarr(relativeLayout, "No code to paste");
            return;
        }
        openeditro.setVisibility(View.VISIBLE);
        openeditro.setText(text);
        t1.setText("Close Editor");
        enableedititing.setImageResource(R.drawable.close);
        openeditro.setVisibility(View.VISIBLE);
        open = true;
    }
    public void savetext(View view) {
        if (openeditro.getText().toString().isEmpty()) {
            snackbarr(relativeLayout, "No Code to Save!");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("edttext", openeditro.getText().toString());
        SaverFragment saverFragment = new SaverFragment();
        saverFragment.setArguments(bundle);
        saverFragment.show(getSupportFragmentManager(), saverFragment.getTag());
    }
    private void savefile(String filename, String content) {
        filename = filename + ".txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "file saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "file not Found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error Saving", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else
                    maketoast(this, "Permission Denied");


        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void maketoast(Context context, String mess) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
    private void snackbarr(RelativeLayout layout, String mess) {
        Snackbar snackbar = Snackbar.make(layout, mess, Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        snackbar.show();
    }
    public void openfiles(View view) {
        startActivity(new Intent(MainActivity.this, SavedFileActivity.class));
    }

    public void A(View view) {
        openeditro.setText(openeditro.getText().toString()+'A');
    } public void B(View view) {
        openeditro.setText(openeditro.getText().toString()+'B');
    } public void C(View view) {
        openeditro.setText(openeditro.getText().toString()+'C');
    } public void D(View view) {
        openeditro.setText(openeditro.getText().toString()+'D');
    } public void E(View view) {
        openeditro.setText(openeditro.getText().toString()+'E');
    } public void F(View view) {
        openeditro.setText(openeditro.getText().toString()+'F');
    } public void G(View view) {
        openeditro.setText(openeditro.getText().toString()+'G');
    } public void H(View view) {
        openeditro.setText(openeditro.getText().toString()+'H');
    } public void I(View view) {
        openeditro.setText(openeditro.getText().toString()+'I');
    } public void J(View view) {
        openeditro.setText(openeditro.getText().toString()+'J');
    } public void K(View view) {
        openeditro.setText(openeditro.getText().toString()+'K');
    } public void L(View view) {
        openeditro.setText(openeditro.getText().toString()+'L');
    } public void M(View view) {
        openeditro.setText(openeditro.getText().toString()+'M');
    } public void N(View view) {
        openeditro.setText(openeditro.getText().toString()+'N');
    } public void O(View view) {
        openeditro.setText(openeditro.getText().toString()+'O');
    } public void P(View view) {
        openeditro.setText(openeditro.getText().toString()+'P');
    } public void Q(View view) {
        openeditro.setText(openeditro.getText().toString()+'Q');
    } public void R(View view) {
        openeditro.setText(openeditro.getText().toString()+'R');
    } public void S(View view) {
        openeditro.setText(openeditro.getText().toString()+'S');
    } public void T(View view) {
        openeditro.setText(openeditro.getText().toString()+'T');
    } public void U(View view) {
        openeditro.setText(openeditro.getText().toString()+'U');
    } public void V(View view) {
        openeditro.setText(openeditro.getText().toString()+'V');
    } public void W(View view) {
        openeditro.setText(openeditro.getText().toString()+'W');
    }  public void X(View view) {
        openeditro.setText(openeditro.getText().toString()+'X');
    } public void Y(View view) {
        openeditro.setText(openeditro.getText().toString()+'Y');
    } public void Z(View view) {
        openeditro.setText(openeditro.getText().toString()+'Z');
    }

}


