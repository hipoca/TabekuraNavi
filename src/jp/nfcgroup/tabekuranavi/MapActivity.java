package jp.nfcgroup.tabekuranavi;

import jp.nfcgroup.tabekuranavi.fragment.StoreDialogFragment;
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
import android.widget.LinearLayout;

public class MapActivity extends BaseActivity implements OnClickListener{
   
	MapGestureSurfaceView _mapGesturefaceView; 
	Bitmap _bmShop;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        //LinearLayout	layout = new LinearLayout(this);
		//setContentView(layout);
        setContentView(R.layout.activity_map);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        
        _mapGesturefaceView=(MapGestureSurfaceView)this.findViewById(R.id.surfaceView);         
    }
    
    
    /*
    public boolean onTouchEvent(MotionEvent event) {
    
    	RectF[] shopButtonRects = _mapGesturefaceView.shopButtonRects; 
        
    	Log.v("touch","X" + (int)event.getX());
    	Log.v("touch","Y" + (int)event.getY());
    	
	    int iAction = event.getAction();
	    
	    //TODO padding調整
	    int paddingTop = 200;  
        switch(iAction){
        	case MotionEvent.ACTION_DOWN:
        		for (int i = 0; i < shopButtonRects.length; i++) {
        			if(shopButtonRects[i].contains((int)event.getX(), (int)event.getY() - paddingTop) == true){
        				Log.d("MyApp", "index" + i);
        				
        				StoreDialogFragment sdialog = StoreDialogFragment.newInstance(i);
        				sdialog.show(getFragmentManager(), "dialog");
        				
            			return true;
            		}
				}
	       	break;
	     }
        
        return super.onTouchEvent(event);
    }
    */
    
    
    
    
    
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
