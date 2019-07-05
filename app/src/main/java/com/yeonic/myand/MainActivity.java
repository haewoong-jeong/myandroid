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
import com.naver.maps.map.overlay.PolylineOverlay;
import com.naver.maps.map.util.MarkerIcons;
import java.util.List;

import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    boolean check = true;
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
        LatLng jbu= new LatLng(35.844434,127.129299);

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
        marker4.setPosition(jbu);
        marker4.setMap(naverMap);



        final InfoWindow knu_info = new InfoWindow();
        knu_info.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산대학교";
            }
        });

        final InfoWindow kch_info = new InfoWindow();
        kch_info.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산시청";
            }
        });

        final InfoWindow wku_info = new InfoWindow();
        wku_info.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원광대학교";
            }
        });

        final InfoWindow jbu_info = new InfoWindow();
        jbu_info.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "전북대학교";
            }
        });


        final InfoWindow Lal = new InfoWindow();
        Lal.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {

                String point =Lal.getPosition().latitude+" , "+Lal.getPosition().longitude ;

                return point;
            }
        });

        naverMap.setOnMapLongClickListener((point, crood)->{
            LatLng la = crood;
            Lal.setPosition(la);
            Lal.open(naverMap);
        });

        knu_info.open(marker1);
        kch_info.open(marker2);
        wku_info.open(marker3);
        jbu_info.open(marker4);

        PolylineOverlay polyline = new PolylineOverlay();
        polyline.setCoords(Arrays.asList(knu,kch,wku,jbu));
        polyline.setMap(naverMap);
        polyline.setWidth(11);
        polyline.setColor(Color.RED);



        Button btn = findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                if(check==true) {

                    knu_info.close();
                    wku_info.close();
                    kch_info.close();
                    jbu_info.close();
                    polyline.setMap(null);
                    marker1.setMap(null);
                    marker2.setMap(null);
                    marker3.setMap(null);
                    marker4.setMap(null);

                    check =false;
                }
                else if(check==false)
                {
                    polyline.setMap(naverMap);
                    marker1.setMap(naverMap);
                    marker2.setMap(naverMap);
                    marker3.setMap(naverMap);
                    marker4.setMap(naverMap);
                    knu_info.open(marker1);
                    kch_info.open(marker2);
                    wku_info.open(marker3);
                    jbu_info.open(marker4);

                    check = true;
                }


            }

        });

    }

}