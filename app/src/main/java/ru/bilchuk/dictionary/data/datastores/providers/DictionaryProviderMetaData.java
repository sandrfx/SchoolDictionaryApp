package ru.bilchuk.dictionary.data.datastores.providers;

import android.net.Uri;

public final class DictionaryProviderMetaData {

    public static final String AUTHORITY = "ru.bilchuk.dictionary";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String TRANSLATES_CONTENT_PATH = "translates";

    public static final Uri TRANSLATES_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, TRANSLATES_CONTENT_PATH);

    public static final String TRANSLATION_CONTENT_TYPE = AUTHORITY + "." + TRANSLATES_CONTENT_PATH;
}
