package com.example.sliit_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {

    //Declaration of Widgets
    private Context mContext;
    private contactAdapter mContactAdapter;

    //Configuration method to set the current layout and context
    public void setConfig(RecyclerView recyclerView, Context context, List<ContactUsClass> contacts, List<String> keys ){
        mContext= context;

        mContactAdapter = new contactAdapter(contacts,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mContactAdapter);
    }

    //Inner class to get each item on button click from Recycler View
    class ContactItemView extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mPhone;
        private TextView mEmail;
        private TextView mSubject;
        private TextView mMessage;

        private String key;

        public ContactItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.contact_list_item,parent,false ));

            mName = (TextView) itemView.findViewById((R.id.txt_name));
            mPhone = (TextView) itemView.findViewById((R.id.txt_phone));
            mEmail = (TextView) itemView.findViewById((R.id.txt_email));
            mSubject = (TextView) itemView.findViewById((R.id.txt_subject));
            mMessage = (TextView) itemView.findViewById((R.id.txt_message));



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ContactUsEdit.class);
                    intent.putExtra("key",key);
                    intent.putExtra("name",mName.getText().toString());
                    intent.putExtra("phone",mPhone.getText().toString());
                    intent.putExtra("email",mEmail.getText().toString());
                    intent.putExtra("subject",mSubject.getText().toString());
                    intent.putExtra("message",mMessage.getText().toString());

                  mContext.startActivity(intent);
                }
            });
        }

        //Binding values to each field from class attributes
        public void bind(ContactUsClass c1 , String key){
            mName.setText(c1.getName());
            mPhone.setText(c1.getPhone());
            mEmail.setText(c1.getEmail());
            mSubject.setText(c1.getSubject());
            mMessage.setText(c1.getMessage());
            this.key = key;
        }

    }

    //Adapter class
    class contactAdapter extends RecyclerView.Adapter<ContactItemView>{

        private List<ContactUsClass> mContactList;
        private List<String> mKeys;

        //constructor
        public contactAdapter(List<ContactUsClass> mContactList, List<String> mKeys) {
            this.mContactList = mContactList;
            this.mKeys = mKeys;
        }

        //Create and return a view to list items
        @Override
        public ContactItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContactItemView(parent);
        }

        //Binding the list items to view
        @Override
        public void onBindViewHolder(ContactItemView holder, int position) {
            holder.bind(mContactList.get(position),mKeys.get(position));
        }

        //Get count of items in list
        @Override
        public int getItemCount() {
            return mContactList.size();
        }


    }
}
