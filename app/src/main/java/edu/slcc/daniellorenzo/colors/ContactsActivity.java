package edu.slcc.daniellorenzo.colors;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

//-1- change extends to ListActivity.
public class ContactsActivity extends ListActivity {

    //-2-declare ListView and Cursor attributes.
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //-4- construct cursor...
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor); // Replaced by CursorLoader for async loading.

        final String[] Texter = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        // Item is the combined layouts of text1 and text2.
        int[] item = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, Texter, item, 0);

        setListAdapter(listadapter);

        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//        // Add listener to ListView and respond to click by calling the TextActivity and send it the selected phone number.
//       listView.setOnItemClickListener((parent, view, position, id) {
//            String selectPhone = ((TextView)(view.findViewById(android.R.id.text2))).getText().toString();
//
//            Log.d("MYLOG", "onClick: " + position + "/" + id + "/" + selectPhone);
//
//            Intent intent = new Intent(ContactsActivity.this, TextActivity.class);
//
//            intent.putExtra("Phone", selectPhone);
//
//            startActivity(intent);
//
//
//        });
    }

    @Override
    public int getSelectedItemPosition() {
        Log.d("MYLOG", "getSelectedItemPosition");
        return super.getSelectedItemPosition();
    }

    public ContactsActivity() { super(); }

    @Override
    public long getSelectedItemId(){
        Log.d("MYLOG", "getSelectedItemID");
        return super.getSelectedItemId();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        Log.d("MYLOG", "onListItemClick");
        super.onListItemClick(l,v,position,id);
    }

}
