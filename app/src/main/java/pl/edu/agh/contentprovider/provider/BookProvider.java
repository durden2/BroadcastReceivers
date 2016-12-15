package pl.edu.agh.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by pawel on 14.12.2016.
 */

public class BookProvider extends ContentProvider {
    static final String PROVIDER_NAME = "pl.edu.agh.contentprovider.provider.BookProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/books";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";

    static final int BOOKS = 1;
    static final int BOOKS_ID = 2;

    private static HashMap<String, String> BOOKS_PROJECTION_MAP;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "students", BOOKS);
        uriMatcher.addURI(PROVIDER_NAME, "students/#", BOOKS_ID);
    }


    private SQLiteDatabase db;
    static final String DATABASE_NAME = "ContentProviderAppDb";
    static final String BOOKS_TABLE_NAME = "books";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + BOOKS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " author TEXT NOT NULL, " +
                    " title TEXT NOT NULL);";

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


    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return !(db == null);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BOOKS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case BOOKS:
                qb.setProjectionMap(BOOKS_PROJECTION_MAP);
                break;

            case BOOKS_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            sortOrder = AUTHOR;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(	BOOKS_TABLE_NAME, "", values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db.delete(BOOKS_TABLE_NAME, selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        db.update(BOOKS_TABLE_NAME,contentValues, selection, selectionArgs);
        return 0;
    }
}
