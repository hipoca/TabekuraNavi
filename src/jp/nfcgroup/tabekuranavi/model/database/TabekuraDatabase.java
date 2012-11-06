package jp.nfcgroup.tabekuranavi.model.database;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TabekuraDatabase {
	
	private final Context mContext;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	public TabekuraDatabase(Context context) {
		mContext = context;
		setDatabase();
	}
	
	private void setDatabase() {
		mDbHelper = new DatabaseHelper(mContext);
		try {
			mDbHelper.createEmptyDatabase();
			mDb = mDbHelper.openTabekuraDatabase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		} catch (SQLException sqle) {
			throw sqle;
		}
	}
	
	private static final String TABLE_NAME_CATEGORY = "category_table";
	private static final String[] COLUMNS = {
		"category_id", "category_tag_id", "category_shop_id"
	};
	
	/**
	 * 指定したタグIDの店舗一覧を取得
	 */
	public Cursor findStores(int tagId) {
		// タグIDを指定して、店舗IDでグルーピングかつソートする
		Cursor cursor = mDb.query(TABLE_NAME_CATEGORY, COLUMNS,
				"where category_tag_id=" + tagId, null, "category_shop_id", null, "category_shop_id");
		return cursor;
	}

}
