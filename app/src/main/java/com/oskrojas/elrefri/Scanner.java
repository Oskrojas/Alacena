package com.oskrojas.elrefri;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.widget.FrameLayout;


public class Scanner extends ActionBarActivity {

    private CameraPreview mPreview;
    private CameraManager mCameraManager;
    private HoverView mHoverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Display display = getWindowManager().getDefaultDisplay();
        mHoverView = (HoverView)findViewById(R.id.hover_view);
        mHoverView.update(display.getWidth(), display.getHeight());

        mCameraManager = new CameraManager(this);
        mPreview = new CameraPreview(this, mCameraManager.getCamera());
        mPreview.setArea(mHoverView.getHoverLeft(), mHoverView.getHoverTop(), mHoverView.getHoverAreaWidth(), display.getWidth());
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreview.onPause();
        mCameraManager.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mCameraManager.onResume();
        mPreview.setCamera(mCameraManager.getCamera());
    }
}