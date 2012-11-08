package jp.nfcgroup.tabekuranavi.model;

import java.util.ArrayList;

import jp.nfcgroup.tabekuranavi.model.database.TabekuraDatabase;
import jp.nfcgroup.tabekuranavi.model.vo.DishVO;
import jp.nfcgroup.tabekuranavi.model.vo.StoreVO;
import android.content.Context;
import android.database.Cursor;

public class StoresData {

	private static StoresData mStoresData = new StoresData();
	private static boolean mInitFlag = false;
	
	private ArrayList<StoreVO> mStores = new ArrayList<StoreVO>();
	
	private StoresData() {
	}
	
	/**
	 * インスタンス取得
	 * @return
	 */
	public static final StoresData getInstance() {
		return mStoresData;
	}
	
	/**
	 * 全店舗および商品の情報を取得
	 * @param context
	 * @return
	 */
	public ArrayList<StoreVO> getAllStore(Context context) {
		if(!mInitFlag) initialize(context);
		return mStores;
	}
	
	/**
	 * データベースから店舗情報を作成する
	 * @param context
	 */
	private void initialize(Context context) {
		StoreVO svo = new StoreVO();
		DishVO dvo = new DishVO();
		int storeId = 0;
		
		// データベースから店舗情報を取得
		TabekuraDatabase db = new TabekuraDatabase(context);
		Cursor c = db.fetchAllStores();
		
		// 店舗情報を初期化
		mStores.clear();
		
		if(c.moveToFirst()) {
			do {
				// 店舗IDを取得
				storeId = c.getInt(c.getColumnIndex("dish_shop_id"));
				
				// 店舗情報を作成
				if(svo.id != storeId) {
					if(!c.isFirst()) {
						mStores.add(svo);
					}
					svo.id = storeId;
					svo.name = c.getString(c.getColumnIndex("shop_name"));
					svo.subTitle = c.getString(c.getColumnIndex("shop_subtitle"));
					svo.weight = 0;
					
					svo.dishes.clear();
					dvo.id = c.getInt(c.getColumnIndex("dish_id"));
					dvo.name = c.getString(c.getColumnIndex("dish_name"));
					dvo.priceTo = c.getInt(c.getColumnIndex("dish_price_to"));
					dvo.priceFrom = c.getInt(c.getColumnIndex("dish_price_from"));
					svo.dishes.add(dvo);
				} else {
					dvo.id = c.getInt(c.getColumnIndex("dish_id"));
					dvo.name = c.getString(c.getColumnIndex("dish_name"));
					dvo.priceTo = c.getInt(c.getColumnIndex("dish_price_to"));
					dvo.priceFrom = c.getInt(c.getColumnIndex("dish_price_from"));
					svo.dishes.add(dvo);
				}
			} while(c.moveToNext());
			
			// 店舗情報の作成済フラグをセット
			mInitFlag = true;
		}
	}
}
