package com.afzal.downcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class DownCalcActivity extends Activity {
	static EditText txtspeed;
	static EditText txtsize;
	static EditText txttime;
	
	static Spinner spnspeed;
	static Spinner spnsize;
	static Spinner spntime;
	
	static RadioButton radspeed;
	static RadioButton radsize;
	static RadioButton radtime;
	
	final static int kib = 1;
	final static int mib = 1024;
	final static int gib = 1048576;
	
	final static int kibs = 1;
	final static int mibs = 1024;
	final static int gibs = 1048576;
	
	final static int sec = 1;
	final static int min = 60;
	final static int hour = 3600;
	
	private int speedunit = 0;
    private int timeunit = 0;
    private int sizeunit = 0;
    
	
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
        
        radspeed = (RadioButton) findViewById(R.id.radspeed);
        radsize = (RadioButton) findViewById(R.id.radsize);
        radtime = (RadioButton) findViewById(R.id.radtime);
        
        /** Radio button logic */
        radspeed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// uncheck the other two
				radsize.setChecked(false);
				radtime.setChecked(false);
			}
		});
        
		radsize.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// uncheck the other two
				radspeed.setChecked(false);
				radtime.setChecked(false);
			}
		});

		radtime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// uncheck the other two
				radsize.setChecked(false);
				radspeed.setChecked(false);
			}
		});
        		
        txttime.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				double speed = 0;
				double size = 0;
				double time = 0;
				if (radspeed.isChecked()) { 
					try {
						size = Double.parseDouble(txtsize.getText().toString());
						time = Double.parseDouble(txttime.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (time != 0 && size != 0) {
							speed = calcSpeed(size, time, speedunit, sizeunit, timeunit);
							txtspeed.setText(Double.toString(speed));
						}
					}
				}
				
				if (radsize.isChecked()) {
					try {
						speed = Double.parseDouble(txtspeed.getText().toString());
						time = Double.parseDouble(txttime.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (time != 0 && speed != 0) {
							size = calcSize(speed, time, speedunit, sizeunit, timeunit);
							txtsize.setText(Double.toString(size));
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				speedunit = (int) Math.pow(1024, spnspeed.getSelectedItemPosition());
				sizeunit = (int)  Math.pow(1024, spnsize.getSelectedItemPosition());
				timeunit = (int) Math.pow(60, spntime.getSelectedItemPosition());
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        txtspeed.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				double speed = 0;
				double size = 0;
				double time = 0;
				if (radtime.isChecked()) { 
					try {
						speed = Double.parseDouble(txtspeed.getText().toString());
						size = Double.parseDouble(txtsize.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (size != 0 && speed != 0) {
							time = calcTime(speed, size, speedunit, sizeunit, timeunit);
							txttime.setText(Double.toString(time));
						}
					}
				}
				
				if (radsize.isChecked()) {
					try {
						speed = Double.parseDouble(txtspeed.getText().toString());
						time = Double.parseDouble(txttime.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (time != 0 && speed != 0) {
							size = calcSize(speed, time, speedunit, sizeunit, timeunit);
							txtsize.setText(Double.toString(size));
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				speedunit = (int) Math.pow(1024, spnspeed.getSelectedItemPosition());
				sizeunit = (int)  Math.pow(1024, spnsize.getSelectedItemPosition());
				timeunit = (int) Math.pow(60, spntime.getSelectedItemPosition());
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        txtsize.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				double speed = 0;
				double size = 0;
				double time = 0;
				if (radtime.isChecked()) { 
					try {
						speed = Double.parseDouble(txtspeed.getText().toString());
						size = Double.parseDouble(txtsize.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (size != 0 && speed != 0) {
							time = calcTime(speed, size, speedunit, sizeunit, timeunit);
							txttime.setText(Double.toString(time));
						}
					}
				}
				
				if (radspeed.isChecked()) { 
					try {
						size = Double.parseDouble(txtsize.getText().toString());
						time = Double.parseDouble(txttime.getText().toString());
					} catch (NumberFormatException e) {
						
					} finally {
						if (time != 0 && size != 0) {
							speed = calcSpeed(size, time, speedunit, sizeunit, timeunit);
							txtspeed.setText(Double.toString(speed));
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				speedunit = (int) Math.pow(1024, spnspeed.getSelectedItemPosition());
				sizeunit = (int)  Math.pow(1024, spnsize.getSelectedItemPosition());
				timeunit = (int) Math.pow(60, spntime.getSelectedItemPosition());
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private double calcSpeed(double size, double time, int speedunit, int sizeunit, int timeunit) {
    	double speed = 0;
    	size = size * sizeunit;
    	time = time * timeunit;
    	speed = size/time;
    	speed = speed/speedunit;
    	return speed;
    }
    
    private double calcSize(double speed, double time, int speedunit, int sizeunit, int timeunit) {
    	double size = 0;
    	speed = speed * speedunit;
    	time = time * timeunit;
    	size = speed * time;
    	size = size/sizeunit;
    	return size;
    }

    private double calcTime(double speed, double size, int speedunit, int sizeunit, int timeunit) {
    	double time = 0;
    	speed = speed * speedunit;
    	size = size * sizeunit;
    	time = size/speed;
    	time = time/timeunit;
    	return time;
    }
    
    
}