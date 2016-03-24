package com.example.tndeen.belian;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private CommentsDataSource datasource;

    Button btnAdd = null;
    Button btnCal = null;
    EditText etName = null;
    EditText etKg = null;
    EditText etMultiply= null;
    EditText etRm = null;
    EditText etDetail = null;
    EditText etDate = null;


    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datasource = new CommentsDataSource(this);
        datasource.open();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnCal.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etKg = (EditText) findViewById(R.id.etKG);
        etMultiply = (EditText) findViewById(R.id.etMultiply);
        etRm = (EditText) findViewById(R.id.etRM);
        etDetail = (EditText) findViewById(R.id.etDetail);
        etDate = (EditText) findViewById(R.id.etDate);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MainActivity.this, Belian.class);
                startActivity(k);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Comment comment = null;
        switch(v.getId()) {
            case R.id.btnAdd:

                if (etName.getText().length() == 0
                        || etKg.getText().length() == 0
                        || etMultiply.getText().length() == 0
                        || etDate.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please Complete Form!", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    comment = new Comment();
                    comment.setComment(etName.getText().toString());
                    comment.setKg(etKg.getText().toString());
                    comment.setMultiply(etMultiply.getText().toString());
                    float kg = Float.parseFloat(etKg.getText().toString());
                    float multiply = Float.parseFloat(etMultiply.getText().toString());
                    final float rm = (kg * multiply);
                    etRm.setText(String.valueOf(rm));
                    comment.setRm(etRm.getText().toString());
                    comment.setDetail(etDetail.getText() == null ? "" : etDetail.getText().toString());
                    comment.setDate(etDate.getText().toString());

                    final Comment saveComm = datasource.createComment(comment);

                    etName.setText("");
                    etKg.setText("");
                    etRm.setText("");
                    etDetail.setText("");
                    Toast.makeText(MainActivity.this, "Data Save!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCal:
                showDialog(999);
                Toast.makeText(getApplicationContext(), "Select Date!", Toast.LENGTH_SHORT)
                        .show();
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        etDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
