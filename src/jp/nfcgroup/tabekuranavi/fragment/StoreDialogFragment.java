package jp.nfcgroup.tabekuranavi.fragment;

import java.util.ArrayList;

import jp.nfcgroup.tabekuranavi.R;
import jp.nfcgroup.tabekuranavi.adapter.StoreDialogAdapter;
import jp.nfcgroup.tabekuranavi.model.vo.DishVO;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StoreDialogFragment extends DialogFragment {
	int mNum;
	
	public static StoreDialogFragment newInstance(int num) {
		StoreDialogFragment f = new StoreDialogFragment();
		
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);
		
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mNum = getArguments().getInt("num");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().setTitle("中華・泰平楽");
		View view = inflater.inflate(R.layout.fragment_dialog, container, false);
		
		ListView dialogList = (ListView)view.findViewById(R.id.dialog_list);
		ArrayList<DishVO> items = new ArrayList<DishVO>();
		DishVO dish1 = new DishVO();
		dish1.name = "マーボー丼";
		dish1.price = 2;
		items.add(dish1);
		DishVO dish2 = new DishVO();
		dish2.name = "コーンスープ";
		dish2.price = 1;
		items.add(dish2);
		ListAdapter adapter = new StoreDialogAdapter(getActivity(), R.layout.dialog_row, items);
		dialogList.setAdapter(adapter);
		return view;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity(), R.style.StoreListTheme);
		return dialog;
	}
}
