package com.example.mapa_miejsa;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity  implements LocationListener{

	private EditText nazwa;
	private EditText opis;
	private Button zapisz;
	private Button mapa;
	private SqlHelper sql;
	private LocationManager loc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		loc = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		nazwa = (EditText) findViewById(R.id.nazwa);
		opis = (EditText) findViewById(R.id.opis);
		zapisz = (Button) findViewById(R.id.zapisz);
		mapa = (Button) findViewById(R.id.mapa);
		
		sql = new SqlHelper(this);
		
		mapa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				reakcja();
			}
		});
		
		zapisz.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View v) {
				LokalizacjaEntity lokalizacja = new LokalizacjaEntity();
				lokalizacja.setTitle(nazwa.getText().toString());
				lokalizacja.setDescription(opis.getText().toString());
				boolean isProvider = loc.isProviderEnabled(LocationManager.GPS_PROVIDER);
				if(!isProvider){
					reakcjaWlaczGps();
					return;
				}
				Criteria criteria = new Criteria();
				//String bestProvider = loc.getBestProvider(criteria, false);
				String bestProvider = LocationManager.GPS_PROVIDER;
				Log.d("provider",bestProvider);
				Location location = loc.getLastKnownLocation(bestProvider);
/*				GPSTracker tracker = new GPSTracker(this);
			    if (tracker.canGetLocation() == false) {
			        tracker.showSettingsAlert();
			    } else {
			        latitude = tracker.getLatitude();
			        longitude = tracker.getLongitude();
			    }*/
				
					if(location != null){
						Log.d("x","tes"+location.getLatitude());
						Log.d("y","tes2"+location.getLongitude());
						lokalizacja.setX(location.getLatitude());
						lokalizacja.setY(location.getLongitude());
				
						sql.addLoc(lokalizacja);
						
						reakcja();
					}else{
						Toast.makeText(getApplicationContext(), "Nie mo¿na pobraæ lokalizacji", Toast.LENGTH_LONG).show();
					}
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	
	public void reakcja(){
		Intent  i = new Intent(this,MapaActivity.class);
		startActivity(i);
	}
	public void reakcjaWlaczGps(){
		Intent  i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(i);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		opis.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
		
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
}
