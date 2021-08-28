package ru.bilchuk.dictionary.data.datastores.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.bilchuk.dictionary.data.datastores.db.DBMetaData;
import ru.bilchuk.dictionary.data.datastores.db.DBOpenHelper;

public class DictionaryContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcer;

    private static final int PATH_ROOT = 0;
    private static final int PATH_TRANSLATIONS = 1;

    static {
        uriMatcer = new UriMatcher(PATH_ROOT);
        uriMatcer.addURI(DictionaryProviderMetaData.AUTHORITY, DictionaryProviderMetaData.TRANSLATES_CONTENT_PATH,
                PATH_TRANSLATIONS);
    }

    private DBOpenHelper dbOpenHelper;

    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String orderBy) {
        switch (uriMatcer.match(uri)) {
            case PATH_TRANSLATIONS:
                return dbOpenHelper.getReadableDatabase().query(DBMetaData.TranslationTableMetaData.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, orderBy);
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcer.match(uri)) {
            case PATH_TRANSLATIONS:
                return DictionaryProviderMetaData.TRANSLATION_CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcer.match(uri)) {
            case PATH_TRANSLATIONS:
                dbOpenHelper.getWritableDatabase().insert(DBMetaData.TranslationTableMetaData.TABLE_NAME,
                        null, contentValues);
                notifyTranslationsObservers();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private void notifyTranslationsObservers() {
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(DictionaryProviderMetaData.TRANSLATES_CONTENT_URI, null);
        }
    }
}

