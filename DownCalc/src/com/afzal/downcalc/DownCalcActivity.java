package com.afzal.downcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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
	
	static Button btnclear;
	
	private double speedunit = 0;
    private int timeunit = 0;
    private double sizeunit = 0;
    
    static final int SIZE = 0;
    static final int TIME = 1;
    static final int SPEED = 2;
    
    
	
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
        
        btnclear = (Button) findViewById(R.id.clear);
        
        /** Clear button */
        btnclear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtsize.setText("");
				txttime.setText("");
				txtspeed.setText("");
			}
		});
        
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
        
		/** EditText change stuff */
        txttime.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				updateSpeed();
				updateSize();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				updateUnits();
			}
			
			@Override
			public void afterTextChanged(Editable s) {

			}
		});
        
        txtspeed.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				updateTime();
				updateSize();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				updateUnits();
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
        
        txtsize.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				updateTime();
				updateSpeed();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				updateUnits();
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
        
        /** Spinner update stuff */
        spntime.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				updateUnit(TIME);
				if (radtime.isChecked()) {
					updateTime();
				} else {
					updateSize();
					updateSpeed();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
        
        spnsize.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				updateUnit(SIZE);
				if (radsize.isChecked()) {
					updateSize();
				} else { 
					updateTime();
					updateSpeed();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
        
        spnspeed.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				updateUnit(SPEED);
				if (radspeed.isChecked()) {
					updateSpeed();
				} else {
					updateSize();
					updateTime();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    
    private void updateUnits() {
    	updateUnit(SIZE);
    	updateUnit(TIME);
    	updateUnit(SPEED);
    }
    
    private void updateUnit(int unit) {
    	switch (unit) {
    		case SIZE:
    			if (spnsize.getSelectedItemPosition() < 3) {
    				sizeunit = (double) Math.pow(1024, spnsize.getSelectedItemPosition());
    			} else {
    				sizeunit = (double) (Math.pow(1024, (spnsize.getSelectedItemPosition() % 3)) / 8);
    			}
    			break;
    		case TIME:
    			timeunit = (int) Math.pow(60, spntime.getSelectedItemPosition());
    			break;
    		case SPEED:
    			if (spnspeed.getSelectedItemPosition() < 3) {
    				speedunit = (double) Math.pow(1024, spnspeed.getSelectedItemPosition());
    			} else {
    				speedunit = (double) ((Math.pow(1024, (spnspeed.getSelectedItemPosition()) % 3)) / 8);
    			} 
    			break;
    		default:
    			break;
    	}
    }
    
    private void updateTime() {
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
    }
    
    private void updateSize() {
		double speed = 0;
		double size = 0;
		double time = 0;
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
    
    private void updateSpeed() {
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
    }
    
    private double calcSpeed(double size, double time, double speedunit, double sizeunit, int timeunit) {
    	double speed = 0;
    	size = size * sizeunit;
    	time = time * timeunit;
    	speed = size/time;
    	speed = speed/speedunit;
    	return speed;
    }
    
    private double calcSize(double speed, double time, double speedunit, double sizeunit, int timeunit) {
    	double size = 0;
    	speed = speed * speedunit;
    	time = time * timeunit;
    	size = speed * time;
    	size = size/sizeunit;
    	return size;
    }

    private double calcTime(double speed, double size, double speedunit, double sizeunit, int timeunit) {
    	double time = 0;
    	speed = speed * speedunit;
    	size = size * sizeunit;
    	time = size/speed;
    	time = time/timeunit;
    	return time;
    }
    
    
}