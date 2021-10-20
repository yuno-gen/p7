package com.example.practical7;

import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
        import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

import com.jakewharton.processphoenix.ProcessPhoenix;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    int flagName=0,flagEmail=0,flagDob=0,flagMob=0,flagAddress=0,flagCity=0,flagState=0,flagMName=0,flagPerc=0,flagCB=0;
    int flag=0;
    ImageButton bName,bMName,bEmail,bDob,bMob,bAddr,bCity,bState,bPerc;
    EditText etName,etEmail,etMob,etDob,etAddress,etCity,etState,etMName,etPerc;
    ProgressBar progressBar;
    CheckBox checkBox;
    TextView textView;
    Button button;
    int perc=0;
    String msg="";
    String filename="prac7file3";
//    ArrayList<String> detail=new ArrayList<>();
    Student detail=new Student();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etAddress=findViewById(R.id.etAddress);
        etCity=findViewById(R.id.etCity);
        etMob=findViewById(R.id.etMob);
        etDob=findViewById(R.id.etDob);
        etState=findViewById(R.id.etState);
        etMName=findViewById(R.id.etMName);
        etPerc=findViewById(R.id.etPerc);

        bName=findViewById(R.id.btname);
        bMName=findViewById(R.id.btmname);
        bDob=findViewById(R.id.btdob);
        bEmail=findViewById(R.id.btemail);
        bMob=findViewById(R.id.btmob);
        bAddr=findViewById(R.id.btaddr);
        bCity=findViewById(R.id.btcity);
        bState=findViewById(R.id.btstate);
        bPerc=findViewById(R.id.btperc);


        progressBar=findViewById(R.id.progressBar);
        checkBox=findViewById(R.id.checkBox);
        textView=findViewById(R.id.tvProgress);

        progressBar.setProgress(0);
        progressBar.setMax(10);
        new MyAsyncTask().execute(10);

        button=findViewById(R.id.button);

    }



    public void onSubmit(View view){

        if(textView.getText().toString().equals("100 %")){
            try{
                FileInputStream fileInputStream=openFileInput(filename);
                InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while ((temp=bufferedReader.readLine())!=null){
                    msg=msg+(temp+"\n");
                }

            }catch (Exception e){e.printStackTrace();}
            try {
                FileOutputStream fileOutputStream=openFileOutput(filename, Context.MODE_PRIVATE);
                fileOutputStream.write((msg+detail.toString()).getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Please enter data again", Toast.LENGTH_SHORT).show();
        }
    }

    public void onFetch(View view) {

        Intent in=new Intent(MainActivity.this,FetchDetails.class);
        startActivity(in);
        //overridePendingTransition(android.R.anim.bounce_interpolator, android.R.anim.accelerate_interpolator);
    }

    public void onReSubmit(View view) {
        ProcessPhoenix.triggerRebirth(getApplicationContext());
        overridePendingTransition(android.R.anim.bounce_interpolator, android.R.anim.accelerate_interpolator);
    }

    class MyAsyncTask extends AsyncTask<Integer ,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(Integer... integers) {
            etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isNameValid(etName.getText().toString())){
                            if(flagName==1){

                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setName(etName.getText().toString());
                                bName.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bName.setEnabled(false);
                                flagName = 1;
                            }
                        }else{
                            etName.setError("Enter this field correctly");
                            bName.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagName==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagName=0;
                            }
                        }
                    }
                }
            });

            etMName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isIDValid(etMName.getText().toString())){
                            if(flagMName==1){
                                
                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setUniqueID(etMName.getText().toString());
                                bMName.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bMName.setEnabled(false);
                                flagMName = 1;
                            }
                        }else{
                            etMName.setError("Enter this field correctly");
                            bMName.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagMName==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagMName=0;
                            }
                        }
                    }
                }
            });

            etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isEmailValid(etEmail.getText().toString())){
                            if(flagEmail==1){

                            }else{
                                perc+=1;
                                publishProgress(perc);
                                detail.setEmail(etEmail.getText().toString());
                                bEmail.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bEmail.setEnabled(false);
                                flagEmail=1;
                            }
                        }else{
                            etEmail.setError("Enter this field correctly");
                            bEmail.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagEmail==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagEmail=0;
                            }
                        }
                    }
                }
            });

            etDob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isDobValid(etDob.getText().toString())){
                            if(flagDob==1){

                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setDOB(etDob.getText().toString());
                                bDob.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bDob.setEnabled(false);
                                flagDob = 1;
                            }
                        }else{
                            etDob.setError("Enter this field correctly");
                            bDob.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagDob==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagDob=0;
                            }
                        }
                    }
                }
            });

            etMob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isMobValid(etMob.getText().toString())){
                            if(flagMob==1) {

                            }else{
                                perc += 1;
                                publishProgress(perc);
                                detail.setMob(etMob.getText().toString());
                                bMob.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bMob.setEnabled(false);
                                flagMob = 1;
                            }
                        }else{
                            etMob.setError("Enter this field correctly");
                            bMob.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagMob==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagMob=0;
                            }
                        }
                    }
                }
            });

            etAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isNameValid(etAddress.getText().toString())){
                            if(flagAddress==1){

                            }
                            else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setAddress(etAddress.getText().toString());
                                bAddr.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bAddr.setEnabled(false);
                                flagAddress = 1;
                            }
                        }else{
                            etAddress.setError("Enter this field correctly");
                            bAddr.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagAddress==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagAddress=0;
                            }
                        }
                    }
                }
            });

            etCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isNameValid(etCity.getText().toString())){
                            if(flagCity==1){

                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setCity(etCity.getText().toString());
                                bCity.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bCity.setEnabled(false);
                                flagCity = 1;
                            }
                        }else{
                            etCity.setError("Enter this field correctly");
                            bCity.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagCity==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagCity=0;
                            }
                        }
                    }
                }
            });

            etState.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isNameValid(etState.getText().toString())){
                            if(flagState==1){

                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setState(etState.getText().toString());
                                bState.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bState.setEnabled(false);
                                flagState = 1;
                            }
                        }else{
                            etState.setError("Enter this field correctly");
                            bState.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagState==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagState=0;
                            }
                        }
                    }
                }
            });

            etPerc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){

                    }else{
                        if(isPercValid(etPerc.getText().toString())){
                            if(flagPerc==1){

                            }else {
                                perc += 1;
                                publishProgress(perc);
                                detail.setPercentage(etPerc.getText().toString());
                                bPerc.setImageDrawable(getResources().getDrawable(R.drawable.img_tick));
                                bPerc.setEnabled(false);
                                flagPerc = 1;
                            }
                        }else{
                            etPerc.setError("Enter this field correctly");
                            bPerc.setImageDrawable(getResources().getDrawable(R.drawable.img_tickwr));
                            if(flagPerc==1){
                                perc=perc-1;
                                publishProgress(perc);
                                flagPerc=0;
                            }
                        }
                    }
                }
            });

           checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked){
                       perc+=1;
                       publishProgress(perc);
                   }else{
                       perc=perc-1;
                       publishProgress(perc);
                   }
               }
           });
            return "";
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText(String.valueOf(values[0] * 10)+" %");
            if (textView.getText().toString().equals("100 %")) {
                textView.setTextColor(getResources().getColor(R.color.teal_700));
                progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.teal_700)));
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isNameValid(String name){
        if(!name.equals("") && name.length()>1){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean isPercValid(String perc){
        try{
            float fperc=Float.parseFloat(perc);
            return fperc <= 100;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isEmailValid(String email){
        if(email.equals("")){
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public boolean isMobValid(String mob){
        if(mob.equals("")){
            return false;
        }
        return mob.length() == 10;
    }
    public boolean isDobValid(String dob){
        boolean fdate=false,fmonth=false,fyear=false;
        try {
            String[] sArr=dob.split("/");
            float date=Float.parseFloat(sArr[0]);
            float month=Float.parseFloat(sArr[1]);
            float year= Float.parseFloat(sArr[2]);

            if(date >0 && date<=31){
                fdate=true;
            }
            if(month>0 && month<=12){
                fmonth=true;
            }
            if(year>1900 && year<=2020){
                fyear=true;
            }
            if(fdate&&fmonth&&fyear){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public boolean isIDValid(String id){
        if(id.length()!=8){
            return false;
        }
        int temp=0;
        for(int i=0;i<8;i++){
            try{
                temp=Integer.parseInt(id.charAt(i)+"");
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

    public class Student{
        String Name,UniqueID,Email,DOB,Mob,Address,City,State,Percentage;

        public void setName(String name) {
            Name = name;
        }

        public void setUniqueID(String uniqueID) {
            UniqueID = uniqueID;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public void setMob(String mob) {
            Mob = mob;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public void setCity(String city) {
            City = city;
        }

        public void setState(String state) {
            State = state;
        }

        public void setPercentage(String percentage) {
            Percentage = percentage;
        }

        @Override
        public String toString() {
            return "UniqueID = '" + UniqueID + '\'' +
                    ",\nName = '" + Name + '\'' +
                    ",\nEmail = '" + Email + '\'' +
                    ",\nDOB = '" + DOB + '\'' +
                    ",\nMob No. = '" + Mob + '\'' +
                    ",\nAddress = '" + Address + '\'' +
                    ",\nCity = '" + City + '\'' +
                    ",\nState = '" + State + '\'' +
                    ",\nPercentage = '" + Percentage + '\''+
                    "\n*****";
        }
    }

}