package com.example.practical7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FetchDetails extends AppCompatActivity {
    TextView tvDetails;
    String filename="prac7file3";
    EditText editText;
    String ID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_details);
        getSupportActionBar().hide();
        tvDetails=findViewById(R.id.tvDetails);
        editText=findViewById(R.id.fetchID);
    }

    public void fetchData(View view) {
        ID=editText.getText().toString();
        try{
            tvDetails.setTextColor(getResources().getColor(R.color.teal_700));
            FileInputStream fileInputStream=openFileInput(filename);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String msg;
            StringBuilder stringBuilder=new StringBuilder();
            while((msg=bufferedReader.readLine())!=null){
                stringBuilder.append(msg+"\n \n");
            }
            String detail=stringBuilder.toString();

            int indexStart=detail.indexOf("UniqueID = "+'\''+ID+'\''+",");
            int indexEnd=detail.indexOf("*****",indexStart);
            tvDetails.setText(detail.substring(indexStart,indexEnd));

//            tvDetails.setText(detail.substring(indexStart,indexStart+100));

//            if(msg.equals("UniqueID = "+'\''+ID+'\''+",")){
//                while(!msg.equals("*****")){
//                    stringBuilder.append(msg+"\n \n");
//                }
//            }

            //            tvDetails.setText(stringBuilder.toString());
        }catch (Exception e){
            tvDetails.setText("INVALID ID\nTRY AGAIN!!!");
            tvDetails.setTextColor(getResources().getColor(R.color.tomato));
            Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
        }
    }
}