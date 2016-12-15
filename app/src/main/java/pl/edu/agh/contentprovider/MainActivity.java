package pl.edu.agh.contentprovider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.contentprovider.provider.BookProvider;

public class MainActivity extends AppCompatActivity {

    public static final String SUCCESS_MSG = "Successfully added book!";
    public static final String BOTH_FIELDS_EMPTY_MSG = "Gimme some book info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertBook(View view) {

        String author = ((EditText)findViewById(R.id.authorText)).getText().toString();
        String title = ((EditText)findViewById(R.id.titleText)).getText().toString();

        ContentValues values = new ContentValues();
        values.put(BookProvider.AUTHOR, author);

        values.put(BookProvider.TITLE, title);

        if(!(author.isEmpty() && title.isEmpty())) {
            getContentResolver().insert(
                    BookProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(),
                    SUCCESS_MSG, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(),
                    BOTH_FIELDS_EMPTY_MSG, Toast.LENGTH_LONG).show();
        }
    }

    public void redirectToBookList(View view) {
        Intent list = new Intent(this, BookListActivity.class);
        startActivity(list);
    }

    public void updateBook(View view) {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Updating book");

        Button updateButton = (Button) dialog.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(BookProvider.AUTHOR, ((EditText) dialog.findViewById(R.id.updateAuthor)).getText().toString());
                values.put(BookProvider.TITLE,((EditText) dialog.findViewById(R.id.updateTitle)).getText().toString());
                getContentResolver().update(BookProvider.CONTENT_URI, values, "_id = ?", new String[]{((EditText) dialog.findViewById(R.id.updateId)).getText().toString()});

                dialog.dismiss();
            }
        });

        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    public void deleteBook(View view) {
        final EditText deletedBookId = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Delete book")
                .setMessage("Enter ID of book that you want to be removed")
                .setView(deletedBookId)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        getContentResolver().delete(BookProvider.CONTENT_URI, "_id = ?", new String[]{deletedBookId.getText().toString()});
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

}
