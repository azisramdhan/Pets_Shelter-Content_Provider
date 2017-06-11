package com.alfatihramadhan.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alfatih on 5/23/2017
 * Cannot be extended or implemented by other class
 * Contract class often contain constant associate with the database
 */

public final class PetContract {

    public static final String CONTENT_AUTHORITY = "com.alfatihramdhan.pets";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI,PATH_PETS);


    // Base Column is an interface that contains _ID and _COUNT
    public static abstract class PetEntry implements BaseColumns{

        public static final String TABLE_NAME = "pets";

        // column name
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        // pet gender category
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_UNKNOWN = 0;

    }

}
