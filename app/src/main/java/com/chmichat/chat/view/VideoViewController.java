package com.chmichat.chat.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chmichat.chat.R;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.L;

/**
 * 抖音
 * Created by xinyu on 2018/1/6.
 */

public class VideoViewController extends BaseVideoController {

    private ImageView thumb;
    public VideoViewController(@NonNull Context context) {
        super(context);
    }

    public VideoViewController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoViewController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_tiktok_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        thumb = mControllerView.findViewById(R.id.iv_thumb);
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);

        switch (playState) {
            case VideoView.STATE_IDLE:
                L.e("STATE_IDLE");
                thumb.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_PLAYING:
                L.e("STATE_PLAYING");
                thumb.setVisibility(GONE);
                break;
            case VideoView.STATE_PREPARED:
                L.e("STATE_PREPARED");
                break;
        }
    }

    public ImageView getThumb() {
        return thumb;
    }
}
