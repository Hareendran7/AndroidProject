package com.example.sliit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends Fragment {

    //Declaration of Widgets
    private Button submit,view1;
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
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        //Initalization and references of widgets
        name = (EditText)view.findViewById(R.id.txt_name);
        email =(EditText)view.findViewById(R.id.txt_email);
        phone =(EditText)view.findViewById(R.id.txt_phone);
        subject = (EditText)view.findViewById(R.id.txt_subject);
        message = (EditText)view.findViewById(R.id.txt_message);

        submit = (Button)view.findViewById(R.id.btn_submit);
        view1 = (Button)view.findViewById(R.id.btn_view);

        //Button click to view all the contactUs details in the database
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(view.getContext(),ContactUsView.class);
                view.getContext().startActivity(myIntent);
            }
        });

        //Button click to check for empty fields and  insert record to database
            submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getActivity(),"Please enter a name",Toast.LENGTH_LONG).show();
                }


                else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getActivity(),"Please enter a email",Toast.LENGTH_LONG).show();
                }


                else if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(getActivity(),"Please enter a phone",Toast.LENGTH_LONG).show();
                }


                else if(TextUtils.isEmpty(subject.getText().toString())){
                    Toast.makeText(getActivity(),"Please enter a subject",Toast.LENGTH_LONG).show();
                }


                else if(TextUtils.isEmpty(message.getText().toString())){
                    Toast.makeText(getActivity(),"Please enter a message",Toast.LENGTH_LONG).show();
                }

                else {
                    appendNewContactRequest();
                    Toast.makeText(getActivity(), "Record Successfully Inserted", Toast.LENGTH_LONG).show();
                }
           }
        });
    }

    //Method to store new contactUs request details to database
    private void appendNewContactRequest()
    {
        DatabaseReference contactRef;
        HashMap<String, String> contactUsData = new HashMap<String, String>();

        contactUsData.put("name", name.getText().toString());
        contactUsData.put("email",email.getText().toString());
        contactUsData.put("phone", phone.getText().toString());
        contactUsData.put("subject",subject.getText().toString());
        contactUsData.put("message", message.getText().toString());

        contactRef = rootRef.child("Contact Us").child(name.getText().toString());
        contactRef.setValue(contactUsData);

    }
}

