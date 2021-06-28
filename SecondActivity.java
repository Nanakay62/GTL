package com.example.new_new_new;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecondActivity extends Activity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    DBHelper dbHelper;
    public static EditText etName, etEmail, etNationality, etAge;
    Button btnAdd;
    MainActivity main = new MainActivity();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNationality = (EditText) findViewById(R.id.etNationality);
        etAge = (EditText) findViewById(R.id.etAge);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.btnAdd) {
            String email = etEmail.getText().toString();
            CreateBase();
           SendEmail sendEmail = new SendEmail();

            sendEmail.execute();


//            try {
//                Mail();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }


            Toast toast = Toast.makeText(getApplicationContext(),
                    "Successfully Submitted!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            //LinearLayout toastContainer = (LinearLayout) toast.getView();
            // Устанавливаем прозрачность у контейнера
            // toastContainer.setBackgroundColor(Color.TRANSPARENT);
            toast.show();


            //navigateUpTo(new Intent(getBaseContext(),ThirdActivity.class));


//            Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
//            if (cursor.moveToFirst()){
//                int idIndex  = cursor.getColumnIndex(DBHelper.KEY_ID);
//                int nameIndex  = cursor.getColumnIndex(DBHelper.KEY_NAME);
//                int MailIndex  = cursor.getColumnIndex(DBHelper.KEY_MAIL);
//                int NationalityIndex  = cursor.getColumnIndex(DBHelper.KEY_NATIONALITY);
//                int AgeIndex = cursor.getColumnIndex(DBHelper.KEY_AGE);
//
//                if (name == DBHelper.KEY_NAME && email == DBHelper.KEY_MAIL){
//                    Log.d("mLog","ID = " + cursor.getInt(idIndex) + " Name " + cursor.getString(nameIndex));
//                }
//
//            }

            //String message =  "Id \" + GetId + \"\\nName : \" + GetName + \"\\nE-mail: \" + GetEmail + \"\\nNationality: \" + GetNationality + \"\\nAge: \" + GetAge + \"\\nResult: \" + GetResults;";
            Intent intent2 = new Intent(this, MainActivity.class);
            //intent2.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent2);


        }

    }


    public void Message(Context context) {


        Toast toast = Toast.makeText(getApplicationContext(), "over", Toast.LENGTH_LONG);
        toast.show();
    }
//    public void showToast(View view) {
//        Toast toast = Toast.makeText(getApplicationContext(),
//                "Чеширский кот", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        LinearLayout toastContainer = (LinearLayout) toast.getView();
//        // Устанавливаем прозрачность у контейнера
//        toastContainer.setBackgroundColor(Color.TRANSPARENT);
//        toast.show();
//    }


    public void CreateBase() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String nationality = etNationality.getText().toString();
        String age = etAge.getText().toString();
        String results = "in process";
        String appointment = "";
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME, name);
        contentValues.put(DBHelper.KEY_MAIL, email);
        contentValues.put(DBHelper.KEY_NATIONALITY, nationality);
        contentValues.put(DBHelper.KEY_AGE, age);
        contentValues.put(DBHelper.KEY_RESULTS, results);
        contentValues.put(DBHelper.KEY_APPOINTMENT, appointment);

        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);


    }

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("email.properties");
        properties.load(inputStream);
        return properties.getProperty(key);

    }

    public static void Mail() throws IOException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //FileInputStream in = new FileInputStream("email.properties");
       // properties.load(in);
        String email = etEmail.getText().toString();
        javax.mail.Session mailSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nastya.shvydko86@gmail.com", "Nn31122009");
                    }
                });

       // javax.mail.Session mailSession = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("nastya.shvydko86@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("m.a.34@yandex.ru"));
        message.setSubject("hello");
        message.setText("hello from lab");

        Transport tr = mailSession.getTransport();
        tr.connect("nastya.shvydko86@gmail.com", "Nn31122009");
        Transport.send(message, message.getAllRecipients());
        tr.close();

//    public void Mail(){
//
//
//    }
    }
}





