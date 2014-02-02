package com.example.testandroid.testWeixin;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testandroid.R;

public class TestWeiXin extends Activity {

	private TextView mTvContact;
	private Button mBtnOpenWeixin;
	private static final String TAG = "TestWeiXin";
	private static String FRIEND_CRICLE = "vnd.android.cursor.item/vnd.com.tencent.mm.plugin.sns.timeline";
	private static String WX_CHAT = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile";
	public static final String[] DATA_PROJECTION = { Data.CONTACT_ID,
			Data.MIMETYPE, Data.DATA1, Data.DATA2, Data.DATA3 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		mTvContact = (TextView) findViewById(R.id.tv_contact_info);
		ArrayList<ContactInfo> rowContactIds = getRowContactId();
		for (ContactInfo info : rowContactIds) {
			ContactInfo result = getWXContactId(info.id, info.name);
			if(result != null && result.id != -1){
				mTvContact.append("result id is " + result.id + "\n");
				mTvContact.append("result name is " + result.name + "\n");
			}
		}
		mBtnOpenWeixin = (Button) findViewById(R.id.btn_open_weixin);
		mBtnOpenWeixin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 启动微信聊天
				Intent intent = new Intent("android.intent.action.VIEW", Uri
						.parse("content://com.android.contacts/data/459"));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		
		mTvContact.append(""+System.currentTimeMillis());

	}

	private ArrayList<ContactInfo> getRowContactId() {
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		Cursor c = getContentResolver().query(uri, new String[] { "_id", "display_name"},
				null, null, null);
		ArrayList<ContactInfo> ids = new ArrayList<ContactInfo>();
		if (c != null) {
			try{
				while (c.moveToNext()) {
					if(!c.isNull(0) && !c.isNull(1)){
						ContactInfo info = new ContactInfo();
						info.id = c.getLong(0);
						info.name = c.getString(1);
						ids.add(info);
					}
				}
			}finally{
				if(c != null){
					c.close();
				}
			}
			return ids;
		}
		return null;
	}

	// 查询data表
	public ContactInfo getWXContactId(long contact_id, String displayname) {
		ContactInfo info = new ContactInfo();
		Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI,
				contact_id);
		String dataDir = ContactsContract.Contacts.Data.CONTENT_DIRECTORY;
		Uri methodUri = Uri.withAppendedPath(contactUri, dataDir);
		/*Cursor c = getContentResolver().query(methodUri, DATA_PROJECTION, Data.MIMETYPE + "=?",
				new String[]{WX_CHAT}, null);*/
		Cursor c = getContentResolver().query(methodUri, DATA_PROJECTION, null, null, null);
		if (c != null && c.getCount() > 0) {
			try {
				c.moveToFirst();
				while(c.moveToNext()){
					String mimetype = c.getString(1);
					if(mimetype.equals(WX_CHAT)){
						String weixin_logo = c.getString(3);
						Log.d(TAG, "getWXContactId: weixin_logo " + weixin_logo);
						String weixin_type = c.getString(4);
						Log.d(TAG, "getWXContactId: weixin_type " + weixin_type);
						Log.d(TAG, "getWXContactId: mimetype " + mimetype);
						String phoneNumber = c.getString(2);
						Log.d(TAG, "getWXContactId: phoneNumber " + phoneNumber);
						info.id = c.getLong(0);
						info.name = displayname;
					}
				}
				return info;
			} finally {
				if(c != null)
				c.close();
			}
		}
		return null;
	}
	
	public static class ContactInfo{
		public long id = -1;
		public String name = null;
	}

}
