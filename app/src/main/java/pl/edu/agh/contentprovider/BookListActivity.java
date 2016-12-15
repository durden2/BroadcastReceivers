package pl.edu.agh.contentprovider;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.edu.agh.contentprovider.provider.BookProvider;

public class BookListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        //TODO : opcjonalnie - utworzyc tablice stringow z projekcja (tablica powinna zawierac nazwy kolumn tabeli)
        //TODO: dodac nazwy kolumn id,autor,tytuł do tablicy  uiBindFrom
        String[] uiBindFrom = { };
        int[] uiBindTo = { R.id.row_id, R.id.row_author , R.id.row_title};

        //TODO: wywolac metode query z odpowiednimi argumentami, przypisac do obiektu
        Cursor tutorials;

        CursorAdapter adapter /*= new SimpleCursorAdapter(getApplicationContext(), R.layout.book_row, tutorials,
                uiBindFrom, uiBindTo, 0)*/;

        //TODO: Odkomentowac 
        //setListAdapter(adapter);
    }
}
