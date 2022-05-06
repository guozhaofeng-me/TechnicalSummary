package titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.guozhf.baseuimodule.R;

import utils.DensityUtil;

/**
 * Created by zephyr on 2017/11/14.
 * TilteBar，代替Toolbar
 */

public class TitleBar extends FrameLayout {
    private RelativeLayout mTitleLayout;
    private TextView mTitle;
    private ImageView mLeftIcon;
    private ImageView mRightIcon;
    private FrameLayout mCustomTitleLayout;
    private OnLeftDisplayClickListener mLeftDisplayClickListener;
    private OnRightDisplayClickListener mRightDisplayClickListener;
    private TextView mRightTv;
    private OnRightTextClickListener mRightTextClickListener;
    private Context mContext;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this, true);
        mTitle = (TextView) layout.findViewById(R.id.title);
        mLeftIcon = (ImageView) layout.findViewById(R.id.iv_left_display);
        mRightIcon = (ImageView) layout.findViewById(R.id.iv_right_display);
        mCustomTitleLayout = ((FrameLayout) layout.findViewById(R.id.custom_title));
        mRightTv = ((TextView) layout.findViewById(R.id.tv_right));
        mTitleLayout = ((RelativeLayout) layout.findViewById(R.id.title_bar));

        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            int titleRes = attributes.getResourceId(R.styleable.TitleBar_aTitle, 0);
            int leftIconRes = attributes.getResourceId(R.styleable.TitleBar_aLeftIcon, 0);
            int rightIconRes = attributes.getResourceId(R.styleable.TitleBar_aRightIcon, 0);
            int rightText = attributes.getResourceId(R.styleable.TitleBar_aRightText, 0);
            int customTitle = attributes.getResourceId(R.styleable.TitleBar_aCustomTitle, 0);
            int titleBgRes = attributes.getResourceId(R.styleable.TitleBar_aTitleBg, 0);
            if (titleBgRes != 0) {
                mTitleLayout.setBackgroundResource(titleBgRes);
            }
            if (titleRes != 0)
                mTitle.setText(titleRes);
            if (leftIconRes != 0)
                mLeftIcon.setImageResource(leftIconRes);
            if (rightIconRes != 0) {
                mRightIcon.setVisibility(VISIBLE);
                mRightIcon.setImageResource(rightIconRes);
            }
            if (rightText != 0) {
                mRightTv.setVisibility(VISIBLE);
                mRightTv.setText(rightText);
            }
            if (customTitle != 0) {
                mTitle.setVisibility(GONE);
                mCustomTitleLayout.setVisibility(VISIBLE);
                LayoutInflater.from(context).inflate(customTitle, mCustomTitleLayout, true);
            }
            setBackgroundColor(Color.WHITE);
            setFitsSystemWindows(true);
            attributes.recycle();
        }

        mLeftIcon.setOnClickListener(v -> {
            if (mLeftDisplayClickListener != null)
                mLeftDisplayClickListener.onLeftClick();
        });

        mRightIcon.setOnClickListener(v -> {
            if (mRightDisplayClickListener != null)
                mRightDisplayClickListener.onRightClick();
        });

        mRightTv.setOnClickListener(v -> {
            if (mRightTextClickListener != null)
                mRightTextClickListener.onRightTextClick();
        });
    }

    public void setTitle(@StringRes int titleRes) {
        mTitle.setText(titleRes);
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) return;
        mTitle.setText(title);
    }

    public void setTitleWidth(int width) {
        if (width > 0) {
            mTitle.post(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTitle.getLayoutParams();
                    params.width = DensityUtil.dip2px(mContext, width);
                    mTitle.setLayoutParams(params);
                }
            });
        }
    }

    public void setLeftIcon(@DrawableRes int iconRes) {
        mLeftIcon.setImageResource(iconRes);
    }

    public void setLeftIcon(Drawable drawable) {
        if (drawable == null) return;
        setRightIconVisible();
        setRightTextGone();
        mLeftIcon.setImageDrawable(drawable);
    }

    public void setRightIcon(@DrawableRes int iconRes) {
        setRightIconVisible();
        setRightTextGone();
        mRightIcon.setImageResource(iconRes);
    }

    public void setRightText(@StringRes int titleRes) {
        setRightTextVisible();
        setRightIconGone();
        mRightTv.setText(titleRes);
    }

    public void setRightText(String text) {
        if (TextUtils.isEmpty(text)) return;
        setRightTextVisible();
        setRightIconGone();
        mRightTv.setText(text);
    }

    public void setRightTextColor(String text) {
        if (TextUtils.isEmpty(text)) return;
        setRightTextVisible();
        setRightIconGone();
        mRightTv.setText(text);
        mRightTv.setTextColor(ContextCompat.getColor(mContext, R.color.color_0084CF));
    }

    public void setRightTextColor(@ColorRes int color) {
        mRightTv.setTextColor(getResources().getColor(color));
    }

    public void setLeftIconVisible() {
        mLeftIcon.setVisibility(VISIBLE);
    }

    public void setLeftIconGone() {
        mLeftIcon.setVisibility(GONE);
    }

    public void setRightIconVisible() {
        mRightIcon.setVisibility(VISIBLE);
    }

    public void setRightIconGone() {
        mRightIcon.setVisibility(GONE);
    }

    public void setRightTextVisible() {
        mRightTv.setVisibility(VISIBLE);
    }

    public void setRightTextGone() {
        mRightTv.setVisibility(GONE);
    }

    public void setCustomTitleLayout(View view) {
        if (view == null) return;
        mTitle.setVisibility(GONE);
        mCustomTitleLayout.setVisibility(VISIBLE);
        mCustomTitleLayout.addView(view);
    }


    public void setOnLeftDisplayClickListener(OnLeftDisplayClickListener leftDisplayClickListener) {
        mLeftDisplayClickListener = leftDisplayClickListener;
    }

    public void setOnRightDisplayClickListener(OnRightDisplayClickListener rightDisplayClickListener) {
        mRightDisplayClickListener = rightDisplayClickListener;
    }

    public void setRightTextClickListener(OnRightTextClickListener rightTextClickListener) {
        mRightTextClickListener = rightTextClickListener;
    }

    public interface OnLeftDisplayClickListener {
        void onLeftClick();
    }

    public interface OnRightDisplayClickListener {
        void onRightClick();
    }

    public interface OnRightTextClickListener {
        void onRightTextClick();
    }
}
