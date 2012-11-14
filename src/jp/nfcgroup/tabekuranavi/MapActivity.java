package jp.nfcgroup.tabekuranavi;

import jp.nfcgroup.tabekuranavi.view.MapGestureSurfaceView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MapActivity extends BaseActivity implements OnClickListener{
   
	MapGestureSurfaceView _mapGesturefaceView; 
	Bitmap _bmShop;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	Log.v("tag","MapActiveity onCrate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
            
        //MapGestureSurfaceView mapGestureSurfaceView = (MapGestureSurfaceView)this.findFragmentById(R.id.surfaceView1);

        Log.v("tag","MapGestureSurfaceView buttonSet");
        _mapGesturefaceView=(MapGestureSurfaceView)this.findViewById(R.id.surfaceView1);        
        //mapGesturefaceView.imgbutton.setOnClickListener(this);

       
        //fragment.findViewById(R.id.fragment_map)
        //fragment.ge

       /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        */
       
        /*
        MapFragment fragment = new MapFragment();
        fragmentTransaction.add(R.id.fragment_map, fragment);
        fragmentTransaction.commit();
        */ 
    }
    
    public boolean onTouchEvent(MotionEvent event) {
    	
    	Log.v("onTouchEvent","////////");
    	
    	RectF[] shopButtonRects = _mapGesturefaceView.shopButtonRects; 
        
	    int iAction = event.getAction();
	    int paddingTop = 110;  
        switch(iAction){
        	case MotionEvent.ACTION_DOWN:
        		for (int i = 0; i < shopButtonRects.length; i++) {
        			if(shopButtonRects[i].contains((int)event.getX(), (int)event.getY() - paddingTop) == true){
        	    		
            			//ボタンが存在する範囲がクリックされた場合
            			//bButton = bButtonOn;//へこんだ画像にする。
            			//pText.setColor(0xffff0000);//文字を赤色にする。
            			Log.v("onTouchEvent","MotionEvent.ACTION_DOWN");
    	            
            			//いちおう、Toastもやってみる。
            			//Toast.makeText(context, "Hello!", Toast.LENGTH_SHORT).show();
            			return true;
            		}
				}
	       	break;
	     }
        
        return super.onTouchEvent(event);
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

   public void onClick(View v) {
	   
	   Log.v("tag","onClick");
	   
       switch(v.getId()){
           case R.id.button1:
               Intent intent = new Intent(this.getApplicationContext(),ListActivity.class);
               startActivity(intent);
               break;
       }
   }
    
    @Override
    protected void onUpdateTags(String tagId) {
        // TODO 自動生成されたメソッド・スタブ
        
    }
    
    @Override
    protected void onUpdateViews() {
        // TODO 自動生成されたメソッド・スタブ
        
    }
}
