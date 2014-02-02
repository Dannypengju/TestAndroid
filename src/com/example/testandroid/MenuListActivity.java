package com.example.testandroid;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuListActivity extends ListFragment{
	
	private String[] data = new String[]{"aaa页面", "bbb页面", "cc页面"};

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(this.getActivity(), data);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new A_Activity();
			break;
			
		case 1:
			newContent = new B_Activity();
			break;
			
		case 2:
			newContent = new C_Activity();
			break;
		}
		if(newContent != null){
			switchFragment(newContent);
		}
	}
	
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity ra = (MainActivity) getActivity();
			ra.switchContent(fragment);
		}
	}


	public class SampleAdapter extends BaseAdapter {
		
		private LayoutInflater mInflater;
		private String[] mData;

		public SampleAdapter(Context context, String[] data) {
			mInflater = LayoutInflater.from(context);
			mData = data;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.row, null);
			}
			TextView name = (TextView)convertView.findViewById(R.id.tv_name);
			name.setText(getItem(position));
			return convertView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.length;
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return mData[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

	}
}
