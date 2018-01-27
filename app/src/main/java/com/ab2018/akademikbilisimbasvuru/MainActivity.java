package com.ab2018.akademikbilisimbasvuru;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText bday,name,mail;
    RadioButton k,e;
    CheckBox chk;
    Spinner spn;
    String sehir="Ankara";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCalendar = Calendar.getInstance();

        bday= findViewById(R.id.birthday);
        name= findViewById(R.id.isimEt);
        mail= findViewById(R.id.emailEt);
        chk=  findViewById(R.id.chkBx);
        e = findViewById(R.id.erkek);
        k = findViewById(R.id.kadin);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        bday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spn = (Spinner) findViewById(R.id.sehirSpn);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int index, long l) {
                if (index==0)
                    sehir="Ankara";
                else if (index==1)
                    sehir="İzmir";
                else
                    sehir="Karabük";
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        bday.setText(sdf.format(myCalendar.getTime()));
    }


    public void onClickBtn(View v)
    {
        if(mail.getText().toString().isEmpty() || name.getText().toString().isEmpty()  || bday.getText().toString().isEmpty() )
        {
            Toast.makeText(this, "Lütfen bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
        }
        else{
            String msg="";
            msg+="İsim: "+name.getText();
            msg+="\nMail: "+mail.getText();
            msg+="\nDoğum Tarihi: "+bday.getText();
            msg+="\nŞehir: "+sehir;

            if(k.isChecked())
                msg+="\nCinsiyet: Kadın";
            else
                msg+="\nCinsiyet: Erkek";

            if (chk.isChecked())
                msg+="\nAkademik Bilişim'e önceden katıldı mı: Evet";
            else
                msg+="\nAkademik Bilişim'e önceden katıldı mı: Hayır";

            makeAndShowDialogBox(msg);

        }


    }

    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("Başvuru tamamlandı");
        myDialogBox.setMessage(message);
        myDialogBox.setIcon(R.drawable.ablogo);

        // Set three option buttons
        myDialogBox.setPositiveButton("Tamam",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // whatever should be done when answering "YES" goes
                        // here

                    }
                });

        // myDialogBox.setNegativeButton("NO",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        // myDialogBox.setNeutralButton("Cancel",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        myDialogBox.create();
        myDialogBox.show();
    }
}
