package com.itis.android.vkapp.model.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.model.CommentItem;
import com.itis.android.vkapp.model.WallItem;
import com.itis.android.vkapp.model.view.counter.LikeCounterViewModel;
import com.itis.android.vkapp.mvp.view.PostFooterView;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentFooterViewModel extends BaseViewModel {

    private int mId;

    private long mDateLong;

    private LikeCounterViewModel mLikes;


    public CommentFooterViewModel(CommentItem commentItem) {
        this.mId = commentItem.getId();
        this.mDateLong = commentItem.getDate();
        this.mLikes = new LikeCounterViewModel(commentItem.getLikes());
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }

    @Override
    public BaseViewModel.LayoutTypes getType() {
        return LayoutTypes.CommentFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentFooterHolder(view);
    }

    public int getId() {
        return mId;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public static class CommentFooterHolder extends BaseViewHolder<CommentFooterViewModel> implements PostFooterView {

        @BindView(R.id.tv_date)
        public TextView tvDate;

        @BindView(R.id.rl_likes)
        public View rlLikes;

        @BindView(R.id.tv_likes_count)
        public TextView tvLikesCount;

        @BindView(R.id.tv_likes_icon)
        public TextView tvLikesIcon;

        @Inject
        Typeface mGoogleFontTypeface;

        private Resources mResources;

        private Context mContext;

        public CommentFooterHolder(View itemView) {
            super(itemView);
            App.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mResources = mContext.getResources();

            tvLikesIcon.setTypeface(mGoogleFontTypeface);
        }

        @Override
        public void bindViewHolder(CommentFooterViewModel item) {

            tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

            bindLikes(item.getLikes());
        }

        private void bindLikes(LikeCounterViewModel likes) {
            tvLikesCount.setText(String.valueOf(likes.getCount()));
            tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
            tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));
        }

        @Override
        public void unbindViewHolder() {
            rlLikes.setOnClickListener(null);
            tvDate.setText(null);
            tvLikesCount.setText(null);
        }

        @Override
        public void like(LikeCounterViewModel likes) {

        }

        @Override
        public void openComments(WallItem wallItem) {

        }
    }
}
