package jp.nfcgroup.tabekuranavi;

import jp.nfcgroup.tabekuranavi.fragment.StoreDialogFragment;
import jp.nfcgroup.tabekuranavi.view.MapGestureSurfaceView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MapActivity extends BaseActivity implements OnClickListener{
   
	MapGestureSurfaceView _mapGesturefaceView; 
	Bitmap _bmShop;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        
        _mapGesturefaceView=(MapGestureSurfaceView)this.findViewById(R.id.surfaceView);         
    }
    
    
	public boolean onTouchEvent(MotionEvent event)
	{
		
		if( event.getPointerCount() == 1){
			//ドラッグ
			switch(event.getAction() & MotionEvent.ACTION_MASK)
			{
				case MotionEvent.ACTION_DOWN:
					
		    	    RectF[] hitRects = _mapGesturefaceView._shopHitRects;
		    	    int paddingTop = 200;//TODO padding調整
		    	    
		    		for (int i = 0; i < hitRects.length; i++) {
		    			if(hitRects[i].contains((int)event.getX(), (int)event.getY() - paddingTop) == true){
		    				//ダイアログ表示
		    				
		    				StoreDialogFragment sdialog = StoreDialogFragment.newInstance(i);
		    				sdialog.show(getFragmentManager(), "dialog");
		    		
		    				return super.onTouchEvent(event);
		        		}
					}
		    		
		     		//ドラッグ開始
	        		_mapGesturefaceView.startDrag(event);
	       				
					break;
		
				//ドラッグ中
				case MotionEvent.ACTION_MOVE:
					_mapGesturefaceView.moveDrag(event);
					break;
					
				//ドラッグ終了
				case	MotionEvent.ACTION_UP:
					_mapGesturefaceView.endDrag(event);
					break;
			}
		}else{
		    //ピンチイン・アウト
			switch(event.getAction() & MotionEvent.ACTION_MASK)
			{
				//ピンチ開始
				case MotionEvent.ACTION_POINTER_DOWN:
					_mapGesturefaceView.startPinch(event);
					break;
		
				//ピンチ中
				case MotionEvent.ACTION_MOVE:
					_mapGesturefaceView.movePinch(event);
					break;
		
				//ピンチ終了
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
					_mapGesturefaceView.endPinch(event);
					break;
			 }
		}
		return super.onTouchEvent(event);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

   public void onClick(View v) {
	   
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
