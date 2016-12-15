package pl.edu.agh.contentprovider;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import pl.edu.agh.contentprovider.provider.BookProvider;

public class BookListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        String[] projection = { BookProvider.AUTHOR, BookProvider.TITLE };
        String[] uiBindFrom = { BookProvider._ID, BookProvider.AUTHOR, BookProvider.TITLE };
        int[] uiBindTo = { R.id.row_id, R.id.row_author , R.id.row_title};

        Cursor tutorials = getContentResolver().query(
                BookProvider.CONTENT_URI, null, null, null, "_id");

        CursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.book_row, tutorials,
                uiBindFrom, uiBindTo, 0);


        setListAdapter(adapter);

    }
}
