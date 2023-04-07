package com.example.codek;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<FileName> arrayList;

    public FileAdapter(Context context, ArrayList<FileName> arrayList) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater;
        layoutInflater = LayoutInflater.from(context).inflate(R.layout.activity_listview, parent, false);
        return new ViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ViewHolder holder, int position) {
        FileName headline = arrayList.get(position);
       holder.name.setText(headline.getFilename());
       holder.dater.setText(headline.getFiledate());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   String filecontent=readFromFile(headline.getFilename());
//                   assert filecontent != null;
                   if(!filecontent.isEmpty()){
                      ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                       ClipData clip = ClipData.newPlainText("plain/text", filecontent);
                       myClipboard.setPrimaryClip(clip);
                       Toast.makeText(context,"Code Copied",Toast.LENGTH_SHORT).show();
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          private TextView name,dater;
          private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardf);
            name=itemView.findViewById(R.id.label);
            dater=itemView.findViewById(R.id.dater);
        }
    }
    private String readFromFile(String filname) throws IOException {
        try {
            FileInputStream fis = context.openFileInput(filname+".txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(fis));
            StringBuilder line= new StringBuilder(r.readLine());
            String st;
            // Condition holds true till
            // there is character in a string
            while ((st = r.readLine()) != null)
                line.append(st);
            r.close();
            return line.toString();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.i("TESTE", "FILE - false");
            return null;
        }
    }
}
