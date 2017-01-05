package com.feicuiedu.hunttreasure.user;


import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feicuiedu.hunttreasure.R;
import com.feicuiedu.hunttreasure.commons.ActivityUtils;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * MediaPlayer播放
 * TextureView
 * SurfaceTexture渲染
 */
public class Mp4 extends Fragment implements TextureView.SurfaceTextureListener {
    private ActivityUtils activityUtils;

    private TextureView textureView;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activityUtils = new ActivityUtils(this);
        textureView = new TextureView(getContext());
        return textureView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //监听SurfaceTexture
        textureView.setSurfaceTextureListener(this);
    }

    @Override
    //准备完成
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, int width, int height) {
        /*
          找到资源
          MediaPlayer
          页面销毁停止播放
         */
        try {
            AssetFileDescriptor afd = getContext().getAssets().openFd("welcome.mp4");
            FileDescriptor fileDescriptor = afd.getFileDescriptor();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDescriptor, afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepareAsync();//异步
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Surface surface = new Surface(surfaceTexture);
                    mediaPlayer.setSurface(surface);
                    mediaPlayer.setLooping(true);//循环
                    mediaPlayer.seekTo(0);//从零开始
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            activityUtils.showToast("视频错误");
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {

        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
