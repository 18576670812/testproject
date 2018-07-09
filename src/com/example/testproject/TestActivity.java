/**
*
* ━━━━━━神兽出没━━━━━━
* 　　　┏┓　　　┏┓
* 　　┏┛┻━━━┛┻┓
* 　　┃　　　　　　　┃
* 　　┃　　　━　　　┃
* 　　┃　┳┛　┗┳　┃
* 　　┃　　　　　　　┃
* 　　┃　　　┻　　　┃
* 　　┃　　　　　　　┃
* 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
* 　　　　┃　　　┃    神兽保佑,代码无bug
* 　　　　┃　　　┃
* 　　　　┃　　　┗━━━┓
* 　　　　┃　　　　　　　┣┓
* 　　　　┃　　　　　　　┏┛
* 　　　　┗┓┓┏━┳┓┏┛
* 　　　　　┃┫┫　┃┫┫
* 　　　　　┗┻┛　┗┻┛
*
* ━━━━━━感觉萌萌哒━━━━━━
*/
package com.example.testproject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.Vibrator;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import android.util.TypedValue;

public class TestActivity extends ActionBarActivity {
	static final String TAG = "Test";
	static final boolean DBG = true;
	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			// do nothing;
		}
		
	};
	
	public Handler handler = null;
	
	private void registerReceiver() {
		if (mReceiver != null) {
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction("com.android.dialer.ACTION_CALL");
			this.registerReceiver(mReceiver, intentFilter);
		}
	}

	private void setWindowFlag() {
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp = getWindow().getAttributes();
		lp.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
		lp.flags |= WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
		lp.flags |= WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
		getWindow().setAttributes(lp);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		setWindowFlag();
		
		if(DBG) {
			logi("onCreate()");
		}
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		testLogPrinter();
		
		handler = new Handler(getMainLooper()) {
			public void handleMessage(Message msg) {
				Log.d("[Handler]", "received Message ID: " + msg.what);
				this.notify();
			}
		};
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onResume()");
		}
		super.onResume();
		//setWindowFlag();
		registerReceiver();
		sendMessage();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onStop()");
		}
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onDestroy()");
		}
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onNewIntent()");
		}
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onPause()");
		}
		super.onPause();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onStart()");
		}
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		if(DBG) {
			logi("onRestart()");
		}
		super.onRestart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	static void logi(String msg) {
		Log.i(TAG, msg);
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment  implements View.OnClickListener{
		private Button mIncreaseVoiceButton = null;
		private Button mDecreaseVoiceButton = null;
		private Button mIncreaseDtmfButton = null;
		private Button mDecreaseDtmfButton = null;
		private Button mIncreaseRingButton = null;
		private Button mDecreaseRingButton = null;
		private Button mIncreaseSystemButton = null;
		private Button mDecreaseSystemButton = null;
		private Button mIncreaseMusicButton = null;
		private Button mDecreaseMusicButton = null;
		private Button mIncreaseAlarmButton = null;
		private Button mDecreaseAlarmButton = null;
		private Button mIncreaseNotificationButton = null;
		private Button mDecreaseNotificationButton = null;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if(DBG) {
				logi("onCreateView()");
			}
			
			View rootView = inflater.inflate(R.layout.fragment_test, container,
					false);
			
			initView(rootView);
			
			return rootView;
		}
		
		private void initView(View parent) {
			if(DBG) {
				logi("initView(" + parent + ")");
			}
			if(parent != null) {
				mIncreaseVoiceButton = (Button)parent.findViewById(R.id.increase_voice);
				mDecreaseVoiceButton = (Button)parent.findViewById(R.id.decrease_voice);
				
				mIncreaseDtmfButton = (Button)parent.findViewById(R.id.increase_dtmf);
				mDecreaseDtmfButton = (Button)parent.findViewById(R.id.decrease_dtmf);
				
				mIncreaseRingButton = (Button)parent.findViewById(R.id.increase_ring);
				mDecreaseRingButton = (Button)parent.findViewById(R.id.decrease_ring);
				
				mIncreaseSystemButton = (Button)parent.findViewById(R.id.increase_system);
				mDecreaseSystemButton = (Button)parent.findViewById(R.id.decrease_system);
				
				mIncreaseMusicButton = (Button)parent.findViewById(R.id.increase_music);
				mDecreaseMusicButton = (Button)parent.findViewById(R.id.decrease_music);
				
				mIncreaseAlarmButton = (Button)parent.findViewById(R.id.increase_alarm);
				mDecreaseAlarmButton = (Button)parent.findViewById(R.id.decrease_alarm);
				
				mIncreaseNotificationButton = (Button)parent.findViewById(R.id.increase_notification);
				mDecreaseNotificationButton = (Button)parent.findViewById(R.id.decrease_notification);
			} else {
				logi("root view is null !!!");
			}
			
			if(mIncreaseVoiceButton != null && mDecreaseVoiceButton != null) {
				mIncreaseVoiceButton.setOnClickListener(this);
				mDecreaseVoiceButton.setOnClickListener(this);
			}
					
			if(mIncreaseDtmfButton != null && mDecreaseDtmfButton != null) {
				mIncreaseDtmfButton.setOnClickListener(this);
				mDecreaseDtmfButton.setOnClickListener(this);
			}
				
			if(mIncreaseRingButton != null && mDecreaseRingButton != null) {
				mIncreaseRingButton.setOnClickListener(this);
				mDecreaseRingButton.setOnClickListener(this);
			}
				
			if(mIncreaseSystemButton != null && mDecreaseSystemButton != null) {
				mIncreaseSystemButton.setOnClickListener(this);
				mDecreaseSystemButton.setOnClickListener(this);
			}
			
			if(mIncreaseMusicButton != null && mDecreaseMusicButton != null) {
				mIncreaseMusicButton.setOnClickListener(this);
				mDecreaseMusicButton.setOnClickListener(this);
			}
			
			if(mIncreaseAlarmButton != null && mDecreaseAlarmButton != null) {
				mIncreaseAlarmButton.setOnClickListener(this);
				mDecreaseAlarmButton.setOnClickListener(this);
			}
			
			if(mIncreaseNotificationButton != null && mDecreaseNotificationButton != null) {
				mIncreaseNotificationButton.setOnClickListener(this);
				mDecreaseNotificationButton.setOnClickListener(this);
			}
		}
		
		@Override
		public void onClick(View v) {
			if(DBG) {
				logi("onClick(" + v + ")");
			}
			// TODO Auto-generated method stub
			if(v != null) {
				switch (v.getId()) {
				case R.id.increase_voice:
					adjustVolume(AudioManager.STREAM_VOICE_CALL, true);
					break;
					
				case R.id.increase_dtmf:
					adjustVolume(AudioManager.STREAM_DTMF, true);
					break;
					
				case R.id.increase_ring:
					adjustVolume(AudioManager.STREAM_RING, true);
					break;
					
				case R.id.increase_system:
					adjustVolume(AudioManager.STREAM_SYSTEM, true);
					break;
					
				case R.id.increase_music:
					adjustVolume(AudioManager.STREAM_MUSIC, true);
					break;
					
				case R.id.increase_alarm:
					adjustVolume(AudioManager.STREAM_ALARM, true);
					break;
					
				case R.id.increase_notification:
					adjustVolume(AudioManager.STREAM_NOTIFICATION, true);
					break;
				
				case R.id.decrease_voice:
					adjustVolume(AudioManager.STREAM_VOICE_CALL, false);
					break;
					
				case R.id.decrease_dtmf:
					adjustVolume(AudioManager.STREAM_DTMF, false);
					break;
					
				case R.id.decrease_ring:
					adjustVolume(AudioManager.STREAM_RING, false);
					break;
					
				case R.id.decrease_system:
					adjustVolume(AudioManager.STREAM_SYSTEM, false);
					break;
					
				case R.id.decrease_music:
					adjustVolume(AudioManager.STREAM_MUSIC, false);
					break;
					
				case R.id.decrease_alarm:
					adjustVolume(AudioManager.STREAM_ALARM, false);
					break;
					
				case R.id.decrease_notification:
					adjustVolume(AudioManager.STREAM_NOTIFICATION, false);
					break;
				}
			}
		}
		
		void adjustVolume(int streamType, boolean isIncrease) {
			if(DBG) {
				logi("adjustVolume(" + streamType + ", "+ isIncrease + ")");
			}
			
			//testVibrator(getActivity());
			
			//testClearBundle();
			
			//testWindowFlags();
			
			//testObject();
			
            // testUpdateResourceConfiguration(getActivity());

			// testAlertDialog(getActivity());
			
			//testOrientationEventListener(getActivity());
			
			// testUninitObject(getActivity());
			
			// testNotificationLED(getActivity(), true);
			
			// testStringSplite(getActivity(), "");

			// testStringSubMethod("");
			
			//testCall(getActivity(), "12345");
			
			//testCallPrivileged(getActivity(), "67890");
			
			//testFileInterface(getActivity());
			
			//testLinkedHashMap(getActivity());
			
			//testList(getActivity());
			
			//testSubString(getActivity());
			
			//testHashMap(getActivity());
			
			//testString(getActivity());

			testSynchronized();
			
			AudioManager mAudioManager = 
					(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

			int adjustStep = 1;
			
			if(!isStreamTypeValid(streamType)) {
				logi("invalid stream type. do nothing.");
				return;
			}
			
			if(mAudioManager != null) {
				int currentVolume = mAudioManager.getStreamVolume(streamType);
				int maxVolume = mAudioManager.getStreamMaxVolume(streamType);
				
				if(DBG) {
					logi(getStreamTypeString(streamType) + " currentVolume = " + currentVolume);
					logi(getStreamTypeString(streamType) + " maxVolume = " + maxVolume);
				}
				
				if(isIncrease) {
					if(currentVolume + adjustStep <= maxVolume) {
						mAudioManager.setStreamVolume(streamType, (currentVolume + adjustStep), 0);
					} else {
						if(DBG) {
							logi("current " + getStreamTypeString(streamType) + " volume is maximum");
						} else {
							Toast.makeText(getActivity(), "Current " + getStreamTypeString(streamType) 
									+ " volume is maximum", Toast.LENGTH_SHORT).show();
						}
					}
				} else {
					if(currentVolume - adjustStep >= 0) {
						mAudioManager.setStreamVolume(streamType, (currentVolume - adjustStep), 0);
					} else {
						if(DBG) {
							logi("current " + getStreamTypeString(streamType) + " volume is minimum");
						} else {
							Toast.makeText(getActivity(), "Current " + getStreamTypeString(streamType) 
									+ " volume is minimum", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
				if(!DBG) {
					StringBuilder sb = new StringBuilder();
					
					sb.append("Current " + getStreamTypeString(streamType) + " volume = " 
							+ mAudioManager.getStreamVolume(streamType) + "\n");
					sb.append("Maximum " + getStreamTypeString(streamType) +  " volume = " 
							+ mAudioManager.getStreamMaxVolume(streamType));
					
					Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		}
		
		String getStreamTypeString(int streamType) {
			if(isStreamTypeValid(streamType)) {
				switch(streamType) {
				case AudioManager.STREAM_ALARM:
					return "alarm";
					
				case AudioManager.STREAM_DTMF:
					return "dtmf";
					
				case AudioManager.STREAM_RING:
					return "ring";
					
				case AudioManager.STREAM_VOICE_CALL:
					return "voice call";
					
				case AudioManager.STREAM_SYSTEM:
					return "system";
					
				case AudioManager.STREAM_MUSIC:
					return "music";
					
				case AudioManager.STREAM_NOTIFICATION:
					return "notification";
					
				default:
					return "unknown";
				}
			} else {
				return "unknown";
			}
		}
		
		boolean isStreamTypeValid(int streamType) {
			logi("isStreamTypeValid(" + streamType + ")");
			
			switch( streamType) {
			case AudioManager.STREAM_ALARM:
			case AudioManager.STREAM_DTMF:
			case AudioManager.STREAM_RING:
			case AudioManager.STREAM_VOICE_CALL:
			case AudioManager.STREAM_SYSTEM:
			case AudioManager.STREAM_MUSIC:
			case AudioManager.STREAM_NOTIFICATION:
				return true;
			
			default:
				return false;
			}
		}
		
		void testTypeConvert() {
			int[] mIntArray;
			Object ret = responseInts();
			
			mIntArray = (int[])ret;
			
			for(int value : mIntArray) {
				logi("value: " + value);
			}
		}
		
	    private Object responseInts() {
	        int response[];

	        response = new int[3];

	        for (int i = 0 ; i < 3 ; i++) {
	            response[i] = i;
	        }

	        return response;
	    }
	    
	    void testVibrator(Context context){
	    	long[] pattern = {100, 250, 250, 250};
	    	Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
	    	
	    	if(vibrator != null) {
	    		vibrator.vibrate(pattern, -1);
	    	}
	    }
	    
	    void testClearBundle() {
	    	if(DBG) {
				logi("testClearBundle()");
			}
	    	Bundle mBundle = new Bundle();
	    	mBundle.putInt("id", 100);
	    	mBundle.clear();
	    	mBundle.putString("pau", "tel:123456");
	    	
	    	int[] mHashMaps = new int[0];
	    	logi("testClearBundle(), mHashMaps.length = " + mHashMaps.length);
	    }
	    
	    void testWindowFlags() {
	    	logi("testWindowFlags()");
	    	Message msg = mHandler.obtainMessage(MSG_SHOW_ALERT_DIALOG);
	    	mHandler.sendMessageDelayed(msg, 5000);
	    }
	    
	    private final static int MSG_SHOW_ALERT_DIALOG = 0x01;
	    private final static int MSG_SEND_NOTIFICATION = 0x02;
	    private final static int MSG_CLEAR_NOTIFICATION = 0x03;
	    private final static int MSG_RELEASE_WAKE_LOCK = 0x04;
	    
	    private Handler mHandler = new Handler(){
	    	public void handleMessage(Message msg) {
	    		logi("handleMessage()");
	    		switch(msg.what) {
	    		case MSG_SHOW_ALERT_DIALOG:
		    		AlertDialog mAlertDialog = new AlertDialog.Builder(getActivity())
		    		    .setPositiveButton("OK",null)
		    		    .setNegativeButton("CANCEL", null)
		    		    .setCancelable(true)
		    		    .create();
		    		
		    		mAlertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		    		mAlertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		    		
		    		StringBuilder mSB = new StringBuilder();
		    		mSB.insert(0, "随便显示点什么吧");
		    		mAlertDialog.setMessage(mSB.toString());
		    		mAlertDialog.show();
		    		break;
		    	
	    		case MSG_SEND_NOTIFICATION:
	    		case MSG_CLEAR_NOTIFICATION:
	    			if(mWakeLock != null) {
	    				mWakeLock.acquire();
	    			}
	    			Intent intent = (Intent)msg.obj;
	    			getActivity().sendBroadcast(intent);
	    			
	    			Message message = obtainMessage(MSG_RELEASE_WAKE_LOCK);
	    			sendMessage(message);
	    			break;
	    		
	    		case MSG_RELEASE_WAKE_LOCK:
	    			if(mWakeLock != null && mWakeLock.isHeld()) {
	    				mWakeLock.release();
	    			}
	    			break;
	    		}
	    	}
	    };
	    
	    public class Details {
    		String mNumber;
    		String mId;
    		
    		public Details(){
    			
    		}
    		
    		public Details(String id, String number){
    			mNumber = number;
    			mId = id;
    		}
    	}
	    
	    void testObject() {

	    	class Call {
    			int mState;
    			Details mDetail;
    			
	    		public Call(int state, Details details){
	    			mDetail = new Details();
	    			mState = state;
	    			mDetail.mNumber = details.mNumber;
	    			mDetail.mId = details.mId;
	    		}
	    		
	    		public void update(int state){
	    			mState = state;
	    		}
	    		
	    		public void update(Details detail){
	    			mDetail.mId = detail.mId;
	    			mDetail.mNumber = detail.mNumber;
	    		}
	    		
	    		public void update(int state, Details detail){
	    			mState = state;
	    			mDetail.mId = detail.mId;
	    			mDetail.mNumber = detail.mNumber;
	    		}
	    		
	    		public String toString(){
	    			return "mState: " + mState + ", number: " + mDetail.mNumber + ", mId: " + mDetail.mId;
	    		}
	    	}
	    	
	    	Call call = new Call(1, new Details("CALLID_1", "11111"));
	    	Call mCallCopy1 = call;
	    	Call mCallCopy2 = call;
	    	
	    	mCallCopy1.update(2);
	    	Log.d("Call", "Cpoy1 == Copy2 ? " + (mCallCopy1==mCallCopy2));
	    	Log.d("Call", "Cpoy1:" + mCallCopy1);
	    	Log.d("Call", "Cpoy2:" + mCallCopy2);
	    	Log.d("Call", "--------------------------------------------------------------");
	    	
	    	mCallCopy2.update(new Details("CALLID_2", "2222"));
	    	Log.d("Call", "Cpoy1 == Copy2 ? " + (mCallCopy1==mCallCopy2));
	    	Log.d("Call", "Cpoy1:" + mCallCopy1);
	    	Log.d("Call", "Cpoy2:" + mCallCopy2);
	    	Log.d("Call", "--------------------------------------------------------------");
	    	
	    	call.update(new Details("CALLID_3", "3333"));
	    	Log.d("Call", "Cpoy1 == Copy2 ? " + (mCallCopy1==mCallCopy2));
	    	Log.d("Call", "Cpoy1:" + mCallCopy1);
	    	Log.d("Call", "Cpoy2:" + mCallCopy2);
	    }
	    
	    void testAlertDialog(Context context) {
			DialogInterface.OnCancelListener cancelListener = new
					DialogInterface.OnCancelListener() {
						
						@Override
						public void onCancel(DialogInterface arg0) {
							// TODO Auto-generated method stub

							Log.d("DialogInterface", "onCancel()");
							Toast.makeText(getActivity(), "onCancel()", Toast.LENGTH_SHORT).show();
						}
					};
			DialogInterface.OnDismissListener dismissListener = new
					DialogInterface.OnDismissListener() {
						
						@Override
						public void onDismiss(DialogInterface arg0) {
							// TODO Auto-generated method stub

							Log.d("DialogInterface", "onDismiss()");
							Toast.makeText(getActivity(), "onDismiss()", Toast.LENGTH_SHORT).show();
						}
					};
	    	AlertDialog.Builder build = new AlertDialog.Builder(context);
	    	AlertDialog dialog = build.setTitle(R.string.dialog_title)
	    	.setMessage("dialog message")
	    	.setPositiveButton(R.string.positive, null)
	    	.setNegativeButton(R.string.negative, null)
	    	.setNeutralButton(R.string.neutral, null)
	    	.setCancelable(false)
	    	.create();
	    	
	    	dialog.setOnDismissListener(dismissListener);
	    	dialog.setOnCancelListener(cancelListener);
	    	dialog.show();
	    	Button button1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
	    	Button button2 = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
	    	Button button3 = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
	    	if(button1 != null && button2 != null && button3 != null) {
	    		button1.setEllipsize(TextUtils.TruncateAt.END);
	    		button1.setSingleLine();
	    		button1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
	    		button2.setSingleLine();
	    		button3.setSingleLine();
	    	} else {
	    		Toast.makeText(context, "dialog neutral button is not exist", Toast.LENGTH_LONG).show();
	    	}
	    }
	    
	    void testUpdateResourceConfiguration(Context context) {
	    	Configuration config = context.getResources().getConfiguration();
	    	config.mcc = 460;
	    	config.mnc = Configuration.MNC_ZERO;
	    	context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
	    	//Toast.makeText(context, R.integer.config_bsf_port, Toast.LENGTH_LONG).show();
	    	Toast.makeText(context, R.bool.config_device_volte_available, Toast.LENGTH_SHORT).show();
	    	//Toast.makeText(context, R.bool.config_device_vt_available, Toast.LENGTH_SHORT).show();
	    	Toast.makeText(context, R.bool.config_device_wfc_available, Toast.LENGTH_SHORT).show();
	    }
	    
	    void testOrientationEventListener(Context context) {
	    	Presenter presenter = Presenter.getInstance(context);
	    	presenter.enable();
	    }
	    
	    void testUninitObject(Context context) {
	    	Presenter presenter = null;
	    	Toast.makeText(context, "Uninit Object address: " + presenter, Toast.LENGTH_LONG).show();
	    }
	    
	    void testTraversalHashMap(Context context) {
	    	HashMap<String, String> mHashMap = new HashMap<>();
	    	mHashMap.put("call_1", "The first call");
	    	mHashMap.put("call_2", "The second call");
	    	mHashMap.put("call_3", "The third call");
	    	
	    	StringBuilder sb = new StringBuilder();
	    	for(String value : mHashMap.values()) {
	    		sb.append(value + "\n");
	    	}
	    	
	    	Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
	    }
	    
	    PowerManager.WakeLock mWakeLock = null;
	    PowerManager mPowerManager = null;
	    @SuppressWarnings("deprecation")
		void testNotificationLED(Context context, Boolean start) {
	    	Log.d("TestActivity", "testNotificationLed(" + start + ")");
	    	
	    	if(mPowerManager == null) {
	    		mPowerManager = (PowerManager)getActivity().getSystemService("power");
	    	}
	    	
	    	if(mWakeLock == null) {
	    		mWakeLock = mPowerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "Test");
	    	}
	    	Message msg = null;
	    	Presenter.getInstance(context);
	    	if(start) {
	    		Intent intent = new Intent(Presenter.ACTION_START_FLASH_LED);
	    		msg = mHandler.obtainMessage(MSG_SEND_NOTIFICATION, intent);
	    	} else {
	    		Intent intent = new Intent(Presenter.ACTION_STOP_FLASH_LED);
	    		msg = mHandler.obtainMessage(MSG_CLEAR_NOTIFICATION, intent);
	    	}
	    	mHandler.sendMessageDelayed(msg, 5000);
	    }
	    
	    void testStringSplite(Context context, String str) {
	        if(str != null)	{
	        	String[] spliteResult = str.split(",");
	        	StringBuilder sb = new StringBuilder();
	        	for(String string : spliteResult) {
	        		sb.append(string + " ");
	        	}
	        	Toast.makeText(context, "length:" + spliteResult.length +
	        			", content{" + sb.toString() + "}", Toast.LENGTH_LONG).show();
	        }
	    }
	    
	    void testStringSubMethod(String str) {
	    	String string = "nafxjktm001.ims.mnc009.mcc510.pub.3gppnetwork.org/";
	    	if ("http".equals(string.substring(0, string.lastIndexOf(':')))
	                || "https".equals(
	                		string.substring(0, string.lastIndexOf(':')))) {
	             if (string.charAt(string.length() - 1) == '/') {
	            	 string = string.substring(0, string.length() - 1);
	             }
	         }
	    }
	    
	    void testCall(Context context, String number) {
	        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", number, null));
	        context.startActivity(intent);
	    }
	    
	    void testCallPrivileged(Context context, String number) {
	        Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", number, null));
	        context.startActivity(intent);
	    }
	    
	    void testFileInterface(Context context) {
	    	Log.d("TestActivity", "testFileInterface()");
	    	File outFileCE = context.getDatabasePath("internal.db");

	    	Log.d("TestActivity", "outFileCE: " + outFileCE.getPath());
	    	String description = "";
	    	if(outFileCE.exists()) {
	    		description = "internal.db exists";
	    	} else {
	    		description = "internal.db not exists";
	    	}
	    	
	    	if(!outFileCE.isDirectory()) {
	    		description = description + " and it isn't directory";
	    	} else {
	    		description = description + " and it's directory";
	    	}
	        Toast.makeText(context, description, Toast.LENGTH_LONG).show();
	    }
	    
	    private void testLinkedHashMap(Context context) {
	    	LinkedHashMap linkHashMap = new LinkedHashMap<String, String>();
	    	
	    	for(int i=0; i<5; i++) {
	    		linkHashMap.put("key", "value" + i);
	    	}
	    	
	    	Toast.makeText(context, "linkHashMap.size = " + linkHashMap.size(), Toast.LENGTH_SHORT).show();
	    	
	    	Iterator<Entry<String, String>> iterator = linkHashMap.entrySet().iterator();
	    	while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                Log.i("TestActivity", "Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
	    }
	    
	    private void testList(Context context) {
	        ArrayList<String> mList = new ArrayList<String>();
	        for(int i=0; i<4; i++) {
	        	mList.add("str" + (i%2));
	        }
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<mList.size(); i++) {
	        	sb.append(mList.get(i));
	        }
	        Toast.makeText(context, "mList.size = " + mList.size() + ": " + sb.toString(), Toast.LENGTH_SHORT).show();
	    }
	    
	    private void testSubString(Context context) {
	    	String str = "4047251511000";
	    	int mncLength = -1;
	    	Toast.makeText(context, str.substring(0, 3 + mncLength), Toast.LENGTH_LONG).show();
	    }
	    
	    private void testHashMap(Context context) {
	    	class Call {
	    		String callId;
	    		public Call(String id) {
	    			callId = id;
	    		}
	    		
	    		public boolean isConference() {
	    			return callId.equalsIgnoreCase("conference");
	    		}
	    		
	    		public String toString() {
	    			return "Call: " + callId;
	    		}
	    	}
	    	
	    	class CallsManager {
		    	Map<Call, Integer> mClccIndexMap = new HashMap<>();
		    	final Set<Call> mCalls = Collections.newSetFromMap(new ConcurrentHashMap<Call, Boolean>(8, 0.9f, 1)); 
		    	
		    	public Collection<Call> getCalls() {
	    		     return Collections.unmodifiableCollection(mCalls);
	    		}
		    	
		    	public void addCall(Call call) {
    				Log.i("TestHashMap", "addCall: " + call);
		    		mCalls.add(call);
		    	}
		    	
		    	public void removeCall(Call call) {
    				Log.i("TestHashMap", "removeCall: " + call);
		    		mCalls.remove(call);
		    		mClccIndexMap.remove(call);
		    	}
		    	
		    	public int getIndexForCall(Call call){
		    		Log.i("TestHashMap", "getIndexForCall(" + call + ") enter");
	    			if(mClccIndexMap.containsKey(call)) {
	    				return (int)mClccIndexMap.get(call);
	    			}
	    			
	    			int i = 1;
	    			for(; ;) {
	    			    boolean contains = mClccIndexMap.containsValue(i);
    				    Log.i("TestHashMap", "mClccIndexMap contains " + i + " ? " + contains);
	    			    if(contains){
	    				    i++;
	    				    continue;
	    			    }
	    			    break;
	    			}
	    			
	    			mClccIndexMap.put(call, i);
	    			return i;
	    		}
		    	
		    	public void clear() {
    				Log.i("TestHashMap", "mClccIndexMap clean!!!");
		    		mClccIndexMap.clear();
		    	}
	    	}
	    	
	    	CallsManager mCallsManager = new CallsManager();
	    	
	    	Call mo1 = new Call("mo1");
	    	mCallsManager.addCall(mo1);
	    	mCallsManager.getIndexForCall(mo1);
	    	
	    	Call mo2 = new Call("mo2");
	    	mCallsManager.addCall(mo2);
	    	mCallsManager.getIndexForCall(mo2);

	    	mCallsManager.removeCall(mo1);
            
            Call part1 = new Call("part1");
	    	mCallsManager.addCall(part1);
    		
            Call part2 = new Call("part2");
	    	mCallsManager.addCall(part2);

            Call conference = new Call("conference");
	    	mCallsManager.addCall(conference);

            Collection<Call> calls = mCallsManager.getCalls();
            StringBuilder sb = new StringBuilder();
            sb.append("send list call ID: ");
            for(Call call : calls) {
            	Log.i("TestHashMap", call + " is conference: " + call.isConference());
            	if(!call.isConference()) {
            		sb.append(mCallsManager.getIndexForCall(call));
            	}
            }
            
			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
	    }
	    
	    private void testString(Context context) {
	    	String str1 = "*31#123456789";
	    	
	    	if(str1.startsWith("*31#")) {
	    		str1 = str1.substring("*31#".length());
	    	}
	    	
	    	Toast.makeText(context, "after format:" + str1, Toast.LENGTH_SHORT).show();
	    }
	    
	    private void testSynchronized() {
	    	class TestObject {
	    		final int COUNT = 10000;
	    		public TestObject() {
	    			Log.i("TestObject", "Test Constructor");
	    		}
	    		
	    		public void start() {
	    			Log.i("TestObject", "start(" + this + ") enter");
	    			synchronized (this) {
	    				try {
	    					for(int i=0; i<COUNT; i++) {
	    						Log.i("TestObject", "output debug log info.");
	    					}
	    				} catch (Exception e) {		
	    				}
	    			}
	    			Log.i("TestObject", "start(" + this + ") exit");
	    		}
	    		
	    		public void close() {
	    			Log.i("TestObject", "Close() enter");
	    			synchronized (this) {
	    				Log.i("TestObject", "Close(" + this + ")");
	    			}
	    		}
	    	}
	    	
	    	TestObject test = new TestObject();
	    	HandlerThread thread = new HandlerThread("sub thread");
	    	thread.start();
	    	Handler mHandler = new Handler(thread.getLooper()) {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					((TestObject)msg.obj).close();
				}
	    	};

	    	mHandler.sendMessage(mHandler.obtainMessage(1, test));
	    	test.start();
	    }
	}
	
	private void testLogPrinter() {
		getMainLooper().setMessageLogging(new LogPrinter(Log.DEBUG, "Main-Looper"));
	}
	
	private void sendMessage() {
		HandlerThread mSubThread = new HandlerThread("sub thread");
		mSubThread.start();
		Handler subHandler = new Handler(mSubThread.getLooper()) {
			public void handleMessage() {
				for(int what=0; what<10; what++) {
					try {
						Log.d("[SubHandler]", "send Message ID: " + what);
						handler.obtainMessage(what++).sendToTarget();
						handler.wait();
					} catch (Exception ex) {
						// do nothing;
					}
			    }
			}
		};
		subHandler.obtainMessage().sendToTarget();
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//super.finish();
		moveTaskToBack(true);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		moveTaskToBack(true);
	}
	
	

}
