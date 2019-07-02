package com.yeonic.myand;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;


import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        
        mapFragment.getMapAsync(this);
        
    }

    public void onMapReady(@NonNull NaverMap naverMap){

        naverMap.setMapType(NaverMap.MapType.Hybrid);

        LatLng knu = new LatLng(35.945482,126.682121);
        LatLng kch = new LatLng(35.967679,126.736828);
        LatLng wku = new LatLng(35.970614,126.954628);
        LatLng kbu= new LatLng(35.844434,127.129299);

        LatLng mid= new LatLng(35.865052,126.886189);

        CameraPosition cameraPosition = new CameraPosition(mid, 9);
        naverMap.setCameraPosition(cameraPosition);

        final Marker marker1 = new Marker();
        marker1.setPosition(knu);
        marker1.setMap(naverMap);

        Marker marker2 = new Marker();
        marker2.setIcon(MarkerIcons.RED);
        marker2.setPosition(kch);
        marker2.setMap(naverMap);

        Marker marker3 = new Marker();
        marker3.setIcon(MarkerIcons.BLUE);
        marker3.setPosition(wku);
        marker3.setMap(naverMap);

        Marker marker4 = new Marker();
        marker4.setIcon(MarkerIcons.YELLOW);
        marker4.setPosition(kbu);
        marker4.setMap(naverMap);


        final InfoWindow infoWindow = new InfoWindow();

        marker1.setTag("군산대학교");
        marker1.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker1);
            return true;
        });

        marker2.setTag("군산시청");
        marker2.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker2);
            return true;
        });

        marker3.setTag("원광대학교");
        marker3.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker3);
            return true;
        });

        marker4.setTag("전북대학교");
        marker4.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker4);
            return true;
        });


        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                // 정보 창이 열린 마커의 tag를 텍스트로 노출하도록 반환
                return (CharSequence)infoWindow.getMarker().getTag();
            }
        });





    }
    
}