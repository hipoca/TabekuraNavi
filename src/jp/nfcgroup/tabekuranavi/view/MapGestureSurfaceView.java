package jp.nfcgroup.tabekuranavi.view;

import jp.nfcgroup.tabekuranavi.R;
import android.R.array;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Rect;
import android.graphics.RectF;
//import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

@SuppressLint("DrawAllocation")
public class MapGestureSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private Bitmap _bmMap;
	private Float  _mapScale;
	private Matrix _matrix;
	public  RectF[] shopButtonRects;
	
	public MapGestureSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this); 

        _bmMap  = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        
        //店舗タッチ範囲を作成
        shopButtonRects = setShopButtonRect();
        
        _matrix = new Matrix();
    	_matrix.preScale(1.0f,1.0f);
		_matrix.postTranslate(0,0); //中心座標
		//_matrix.mapRect(testRect);
	}
	
	//todo レイアウト調整する
	public RectF[] setShopButtonRect(){
		
		RectF[] sBttonRects = new RectF[29];
		
		float rx = 0;
		float ry = 0;
		for (int i = 0; i < sBttonRects.length; i++) {
			
			if(i<9){
				rx = 10.0f + 45.0f*i;
			}
			else if(i<19){
				rx = 10.0f + 45.0f*(i-9);
				ry = 45.0f;
			}else{
				rx = 10.0f + 45.0f*(i-19);
				ry = 90.0f;
			}
			
			sBttonRects[i] = new RectF(rx, ry, 40.0f+rx, 40.0f+ry);
		}
		
		return sBttonRects;	
	}
	
	
	
	public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     
    	canvas.drawBitmap(_bmMap, _matrix, null);
		
    	//店舗ビットマップ作成
    	for (int i = 0; i < shopButtonRects.length; i++) {
    		
    		Paint paint = new Paint();
    		paint.setColor(Color.GREEN);
    		
    		_matrix.mapRect(shopButtonRects[i]);
    		canvas.drawRect(shopButtonRects[i], paint);		
		}
    }
	
   


	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}


	public void surfaceCreated(SurfaceHolder holder) {
       Canvas canvas = holder.lockCanvas();
		onDraw(canvas);
		holder.unlockCanvasAndPost(canvas);
	}


	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
