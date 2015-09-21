package com.example.mapa_miejsa;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MapaActivity extends Activity  implements OnMapReadyCallback {
	
	  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map;
		private SqlHelper sql;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		
		
		 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
			        .getMap();
			    
			    if (map!=null){
			    	sql = new SqlHelper(this);
			    	Cursor k = sql.getProducts();
					while (k.moveToNext()) {
						Log.d("id wiersza","id "+k.getInt(0));
						Log.d("title","id "+k.getString(1));
						Log.d("x","id "+k.getDouble(3));
						Log.d("y","id "+k.getDouble(4));
						 LatLng latLng = new LatLng(k.getDouble(3), k.getDouble(4));
						 Marker newMark = map.addMarker(new MarkerOptions().position(latLng)
						          .title(k.getString(1)).snippet(k.getString(1))
						          );
					}
			    }
			    	
			     /* Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
			          .title("Hamburg"));
			      Marker kiel = map.addMarker(new MarkerOptions()
			          .position(KIEL)
			          .title("Kiel")
			          .snippet("Kiel is cool")
			          .icon(BitmapDescriptorFactory
			           //   .fromResource(R.drawable.ic_launcher)));
			    }*/
			    
			    
			    // Move the camera instantly to hamburg with a zoom of 15.
			    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

			    // Zoom in, animating the camera.
			    //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa, menu);
		Log.d("marker","marker");
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
	
	@Override
	public void onMapReady(GoogleMap map) {
		Log.d("marker1","marker1");
		map.addMarker(new MarkerOptions()
			.position(new LatLng(10, 10))
			.visible(true)
			.title("Marker"));
		Log.d("marker3","marker3");
	}
}
