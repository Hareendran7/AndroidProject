package com.example.sliit_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends Fragment {

    //Declaration of Widgets
    private Button submit;
    private DatabaseReference rootRef;
    private EditText name,email,phone,subject,message;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Get reference of root node from Firebase
        rootRef =  FirebaseDatabase.getInstance().getReference();
        return inflater.inflate(R.layout.fragment_contact_us,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Initalization and references of widgets
        name = (EditText)view.findViewById(R.id.txt_name);
        email =(EditText)view.findViewById(R.id.txt_email);
        phone =(EditText)view.findViewById(R.id.txt_phone);
        subject = (EditText)view.findViewById(R.id.txt_subject);
        message = (EditText)view.findViewById(R.id.txt_message);

        submit = (Button)view.findViewById(R.id.btn_submit);

            submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNewContactRequest();
           }
        });
    }


    //Method to store new contactUs request details to database
    private void appendNewContactRequest()
    {
        DatabaseReference contactRef;
        HashMap<String, String> contactUsData = new HashMap<String, String>();

        contactUsData.put("Name", name.getText().toString());
        contactUsData.put("email",email.getText().toString());
        contactUsData.put("Phone", phone.getText().toString());
        contactUsData.put("Subject",subject.getText().toString());
        contactUsData.put("Message", message.getText().toString());

        contactRef = rootRef.child("Contact Us").child(name.getText().toString());
        contactRef.setValue(contactUsData);

    }
}

