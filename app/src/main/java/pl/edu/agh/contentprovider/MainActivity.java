package pl.edu.agh.contentprovider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public static final String SUCCESS_MSG = "Successfully added book!";
    public static final String BOTH_FIELDS_EMPTY_MSG = "Gimme some book info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertBook(View view) {

        /*
           Wartości pól z głównego activity
         */
        String author = ((EditText)findViewById(R.id.authorText)).getText().toString();
        String title = ((EditText)findViewById(R.id.titleText)).getText().toString();

        // TODO: utworzyc obiekt z wartosciami przekazywanymi do metody insert, wypelnic go wartosciami z powyzszych pol

        if(!(author.isEmpty() && title.isEmpty())) {
            // TODO: wywołać metodę insert

            Toast.makeText(getBaseContext(),
                    SUCCESS_MSG, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(),
                    BOTH_FIELDS_EMPTY_MSG, Toast.LENGTH_LONG).show();
        }
    }

    public void updateBook(View view) {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Updating book");

        // Wartosci pol wpisane w oknie dialogowym
        final String updatedAuthor = ((EditText) dialog.findViewById(R.id.updateAuthor)).getText().toString();
        final String updatedTitle = ((EditText) dialog.findViewById(R.id.updateTitle)).getText().toString();
        final String updatedId = ((EditText) dialog.findViewById(R.id.updateId)).getText().toString();

        Button updateButton = (Button) dialog.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Utworzyc obiekt z wartosciami, ktore chcemy umiescic update, wywolac metodę update z odpowiednim selektorem
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
        // Wartosc ID wpisana w oknie dialogowym
        final String deletedBookIdString = deletedBookId.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Delete book")
                .setMessage("Enter ID of book that you want to be removed")
                .setView(deletedBookId)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //TODO: Wywolac metode delete z odpowiednim selektorem oraz id ksiazki (jako String[])
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    public void redirectToBookList(View view) {
        Intent list = new Intent(this, BookListActivity.class);
        startActivity(list);
    }

}
