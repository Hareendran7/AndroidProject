package com.example.sliit_app;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ContactUsEdit extends AppCompatActivity {

    //declaration of widgets
    private EditText name1;
    private EditText phone1;
    private EditText email1;
    private EditText subject1;
    private EditText message1;
    private Button update;
    private Button delete;
    private Button back;

    //declaration of variables
    private String key;
    private String name;
    private String phone;
    private String email;
    private String subject;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorDarkBlue)));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_edit);


        //receiving data passed from RecyclerView item selection
        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        subject = getIntent().getStringExtra("subject");
        message = getIntent().getStringExtra("message");

        //Initalization and references of widgets
        name1 = (EditText)findViewById(R.id.txt_name1);
        phone1 = (EditText)findViewById(R.id.txt_phone1);
        email1 = (EditText)findViewById(R.id.txt_email1);
        subject1 = (EditText)findViewById(R.id.txt_subject1);
        message1 = (EditText)findViewById(R.id.txt_message1);
        update = (Button)findViewById(R.id.btn_update);
        delete = (Button)findViewById(R.id.btn_delete);
        back = (Button)findViewById(R.id.btn_back);

        //Setting the received data to fields
        name1.setText(name);
        phone1.setText(phone);
        email1.setText(email);
        subject1.setText(subject);
        message1.setText(message);



        //button click to update selected record
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsClass c1 = new ContactUsClass();
                c1.setName(name1.getText().toString());
                c1.setPhone(phone1.getText().toString());
                c1.setEmail(email1.getText().toString());
                c1.setSubject(subject1.getText().toString());
                c1.setMessage(message1.getText().toString());

                new FirebaseDatabaseHelper().updateContact(key, c1, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ContactUsClass> contactus, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ContactUsEdit.this,"Record Successfully Updated",Toast.LENGTH_LONG).show();
                            }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


        //button click to delete selected record
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteContact(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ContactUsClass> contactus, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(ContactUsEdit.this,"Record Successfully deleted",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });


        //button click to go back to view of all records
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }
}