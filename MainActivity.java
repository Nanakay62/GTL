package com.example.new_new_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE";
    public static  String EXTRA_MESSAGE2 = "";
    public static final String EXTRA_MESSAGE3 = "";
    EditText EnterName, EnterID;
    Button btnSingUp, btnSingIn;
    DBHelper dbHelper;
    //DBAssetsHelper dbAssetsHelper;
   // private SQLiteOpenHelper openHelper;
    //SQLiteDatabase db;

    public static String GetName, GetId, GetEmail, GetNationality, GetAge, GetResults;
    // private static final String DB_NAME = "contacts.db";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EnterName = (EditText) findViewById(R.id.EnterName);
        EnterID = (EditText) findViewById(R.id.EnterID);
        btnSingUp = findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(this);
        btnSingIn = findViewById(R.id.btnSingIn);
        btnSingIn.setOnClickListener(this);


        dbHelper = new DBHelper(this);
        //openHelper = new DBAssetsHelper(this);

    }


    @Override
    public void onClick(View v) {

        String name = EnterName.getText().toString();
        String id_int = EnterID.getText().toString();
        // int id = Integer.parseInt(id_int);
        if (v.getId() == R.id.btnSingUp) {
            Intent intent1 = new Intent(this, SecondActivity.class);


            startActivity(intent1);
        } else if (v.getId() == R.id.btnSingIn) {


            // File dbFile = getDatabasePath(DB_NAME);
            // SQLiteDatabase db =  SQLiteDatabase.openDatabase(, null, SQLiteDatabase.OPEN_READONLY);
            // copyDatabase(dbFile)
            SQLiteDatabase db = dbHelper.getReadableDatabase();

           // db = openHelper.getReadableDatabase();
            // Log.d("mLog", db);


            // Cursor cursor = db.rawQuery("select Results from contacts where _id= '"+id_int+"'", new String[]{});
            Cursor cursor = db.query(DBHelper.TABLE_CONTACTS, null, null, null, null,null,null);
// Cursor cursor = db.query(,null,null,null,null,null,null,null,null,null);
            //Cursor cursor = db.query(DBAssetsHelper.name,)
            //if (cursor != null && cursor.getCount() < 0) {

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    int nationalityIndex = cursor.getColumnIndex(DBHelper.KEY_NATIONALITY);
                    int ageIndex = cursor.getColumnIndex(DBHelper.KEY_AGE);
                    int resultIndex = cursor.getColumnIndex(DBHelper.KEY_RESULTS);
                    int appointmentIndex = cursor.getColumnIndex(DBHelper.KEY_APPOINTMENT);


                    do { Log.d("mLog ","ursor.getString(nameIndex)");
                        //cursor.getString(1);
                        if (cursor.getString(nameIndex).equals(name) && (cursor.getString(idIndex).equals(id_int))) {

                            GetId = cursor.getString(idIndex);
                            GetName = cursor.getString(nameIndex);
                            GetEmail = cursor.getString(emailIndex);
                            GetNationality = cursor.getString(nationalityIndex);
                            GetAge = cursor.getString(ageIndex);
                            GetResults = cursor.getString(resultIndex);

                            String message = "Id " + GetId + "\nName : " + GetName + "\nE-mail: " + GetEmail + "\nNationality: " + GetNationality + "\nAge: " + GetAge + "\nResult: " + GetResults;
                            //db.close();
                            Intent intent = new Intent(this, ThirdActivity.class);
                            intent.putExtra(EXTRA_MESSAGE1, message);
                            //Intent intent2 = new Intent(this, FourthActivity.class);
                           // Intent intent3 = new Intent(this, FourthActivity.class);
                            //EXTRA_MESSAGE2 = name;
                            intent.putExtra("name", name);
                            intent.putExtra("id", id_int);
                           // intent.putExtra(EXTRA_MESSAGE1,)
                            startActivity(intent);


                            Log.d("mLog ", name + id_int)
                            ;
                        }
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                 cursor.close();
            }
        }
    }
