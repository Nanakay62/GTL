package com.example.new_new_new;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FourthActivity extends Activity implements View.OnClickListener {
    Button btnten;
    DBHelper dbHelper;
    EditText EnterName, EnterID;
    String _name;
   // MainActivity main = new MainActivity();

   // Intent intent1 = getIntent();

   // String id = intent1.getStringExtra("id");


    //String name = _EnterName.getText().toString();
    //String id_int = main.EnterID.getText().toString();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        btnten = findViewById(R.id.btnten);
        btnten.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        _name = name;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnten) {
           Log.d("mLog ", _name);
            InsertToBase();
            btnten.setClickable(false);
            btnten.setBackgroundColor(Color.parseColor("#C3BFBF"));
        }
    }

    public void InsertToBase() {
        //EnterName = (EditText) findViewById(R.id.EnterName);
       // EnterID = (EditText) findViewById(R.id.EnterID);
       // String name = main.EnterName.getText().toString();
       // String id_int = main.EnterID.getText().toString();
        String time = "10:00";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("mLog ", "hello");

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int emailIndex = cursor.getColumnIndex(DBAssetsHelper.KEY_MAIL);
            int nationalityIndex = cursor.getColumnIndex(DBAssetsHelper.KEY_NATIONALITY);
            int ageIndex = cursor.getColumnIndex(DBAssetsHelper.KEY_AGE);
            int resultIndex = cursor.getColumnIndex(DBAssetsHelper.KEY_RESULTS);
            int appointmentIndex = cursor.getColumnIndex(DBHelper.KEY_APPOINTMENT);

//&& (cursor.getString(idIndex).equals(id))
            do {
                Log.d("mLog ", "HELLO ");
                //cursor.getString(1);
                if (cursor.getString(nameIndex).equals(_name) ) {

//                    GetId = cursor.getString(idIndex);
//                    GetName = cursor.getString(nameIndex);
//                    GetEmail = cursor.getString(emailIndex);
//                    GetNationality = cursor.getString(nationalityIndex);
//                    GetAge = cursor.getString(ageIndex);
//                    GetResults = cursor.getString(resultIndex);

                   // cursor.setExtras(appointmentIndex);
                   ContentValues contentValues = new ContentValues();
                    contentValues.put("Appointment", time);
                    database.update(DBHelper.TABLE_CONTACTS, contentValues, "Name = ?" , new String[]{_name});

                }
            }while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");
        cursor.close();
    }

}
