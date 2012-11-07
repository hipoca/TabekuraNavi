package jp.nfcgroup.tabekuranavi;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;

import jp.nfcgroup.tabekuranavi.fragment.ListFragment;

public class ListActivity extends BaseActivity {
    
    private static final String TAG = "ListActivity";

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    
    protected void onUpdateViews(){
        FragmentManager manager = getFragmentManager();
        ListFragment list =  (ListFragment) manager.findFragmentById(R.id.fragment_list);
        
        list.updateViews();
    }
}
