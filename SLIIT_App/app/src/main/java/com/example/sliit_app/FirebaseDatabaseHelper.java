package com.example.sliit_app;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabse;
    private DatabaseReference mReferenceDatabase;
    private List<ContactUsClass> contacts = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<ContactUsClass> contacts, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    //Method to get reference of the "Contact Us" Node from the database
    public FirebaseDatabaseHelper() {
        mDatabse = FirebaseDatabase.getInstance();
        mReferenceDatabase = mDatabse.getReference("Contact Us");
    }

    //Method to read records
    public void readDetails(final DataStatus dataStatus){
        mReferenceDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contacts.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    ContactUsClass c1 = keyNode.getValue(ContactUsClass.class);
                    contacts.add(c1);
                }

                dataStatus.DataIsLoaded(contacts,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //Method to update record
    public void updateContact(String key, ContactUsClass contactus1, final DataStatus dataStatus){
        mReferenceDatabase.child(key).setValue(contactus1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    //Method to delete record
    public void deleteContact(String key, final DataStatus dataStatus){
        mReferenceDatabase.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
