package pl.edu.agh.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
/**
 * Created by pawel on 14.12.2016.
 */

public class BookProvider extends ContentProvider {

    //TODO: wprowadzic odpowiednie wartosci
    static final String PROVIDER_NAME = new String();
    static final String URL = new String();
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String AUTHOR = "author";
    static final String TITLE = "title";

    static final int BOOKS = 1;
    static final int BOOKS_ID = 2;

    private SQLiteDatabase db;

    static final String DATABASE_NAME = "ContentProviderAppDb";
    static final String BOOKS_TABLE_NAME = "books";
    static final int DATABASE_VERSION = 1;
    // TODO: Napisac SQLa tworzacego db
    static final String CREATE_DB_TABLE = new String();

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  BOOKS_TABLE_NAME);
            onCreate(db);
        }
    }



    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //TODO: utworzyc obiekt SQLiteQueryBuilder, ustawic w nim tabelę, wywolac metode query (wynik zwrocic do obiektu Cursor)
        // wywolac na cursorze metode setNotificationUri(getContext().getContentResolver(), uri), zwrocic cursor
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        // TODO: wywolac metode insert obiektu db, sprawdzic czy zwrocona wartosc (id) jest wieksza od zera, zwrocic obiekt uri (jak go utworzyc - branch final)
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        //TODO: wywolac metode delete obiektu db ;)
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        //TODO: wywolac metode update obiektu db ;)
        return 0;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

}
