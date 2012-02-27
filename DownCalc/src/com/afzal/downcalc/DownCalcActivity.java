package com.afzal.downcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DownCalcActivity extends Activity {
	static EditText txtspeed;
	static EditText txtsize;
	static EditText txttime;
	
	static Spinner spnspeed;
	static Spinner spnsize;
	static Spinner spntime;
	
	final static int kib = 1;
	final static int mib = 1024;
	final static int gib = 1048576;
	
	final static int kibs = 1;
	final static int mibs = 1024;
	final static int gibs = 1048576;
	
	final static int sec = 1;
	final static int min = 60;
	final static int hour = 3600;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        txtspeed = (EditText) findViewById(R.id.speed);
        txtsize = (EditText) findViewById(R.id.size);
        txttime = (EditText) findViewById(R.id.time);
        
        spnspeed = (Spinner) findViewById(R.id.speedunit);
        spnsize = (Spinner) findViewById(R.id.sizeunit);
        spntime = (Spinner) findViewById(R.id.timeunit);
        
		String sizeunit = spnsize.getSelectedItem().toString();
		String timeunit = spntime.getSelectedItem().toString();
		
        txtspeed.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String speed = txtspeed.getText().toString();
				String size = txtsize.getText().toString();
				String time = txttime.getText().toString();
				
				if (!size.equals("") && !speed.equals("")){ // size is not null
					Double intspeed = Double.parseDouble(speed);
					Log.d("DC", intspeed.toString());
					Double intsize = Double.parseDouble(size);
					Log.d("DC", intsize.toString());
					Double inttime = intsize/intspeed;
					Log.d("DC", inttime.toString());
					txttime.setText(Double.toString(inttime));
				} else if (!time.equals("") && !speed.equals("")){ // time is not null
					txtsize.setText("size set");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    
}