package com.example.deathnote.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.deathnote.DataClass.CrimesData;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DatabaseName = "Database.db";
    private static final int DatabaseVersion = 1;
    Context mContext;

    public SQLiteHelper(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLContract.FeedEntry.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLContract.FeedEntry.DELETE_QUERY);
        onCreate(db);
    }

    public void InsertData(CrimesData data){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLContract.FeedEntry.Id,data.getId());
        contentValues.put(SQLContract.FeedEntry.Persistent_key,data.getPersistent_id());
        contentValues.put(SQLContract.FeedEntry.Location_type,data.getLocation_type());
        contentValues.put(SQLContract.FeedEntry.Month,data.getMonth());
        contentValues.put(SQLContract.FeedEntry.Outcome_status,data.getOutcome_category());
        contentValues.put(SQLContract.FeedEntry.Outcome_date,data.getOutcome_date());
        contentValues.put(SQLContract.FeedEntry.Category,data.getCategory());
        contentValues.put(SQLContract.FeedEntry.Latitude,data.getLatitude());
        contentValues.put(SQLContract.FeedEntry.Longitude,data.getLongitude());
        db.insert(SQLContract.FeedEntry.TableName,null,contentValues);
        Toast.makeText(mContext,"Added To Favs",Toast.LENGTH_SHORT).show();
    }

    public Cursor getFavourites(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(SQLContract.FeedEntry.GET_QUERY,null);
        return res;
    }

    public void DeleteByID(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Toast.makeText(mContext,"Removing from favourites",Toast.LENGTH_SHORT).show();
        int result = db.delete(SQLContract.FeedEntry.TableName, SQLContract.FeedEntry.Id+" = ?",new String[] {id});
        if(result>0)
            Toast.makeText(mContext,"Deleted from favourites",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext,"Not found in favourites",Toast.LENGTH_SHORT).show();
    }
}
