package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-3-2.
 */

public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements SkinCompatSupportable {
    private int mContentScrimResId = INVALID_ID;
    private int mStatusBarScrimResId = INVALID_ID;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CollapsingToolbarLayout, defStyleAttr,
                R.style.Widget_Design_CollapsingToolbar);
        mContentScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_contentScrim, INVALID_ID);
        mStatusBarScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_statusBarScrim, INVALID_ID);
        a.recycle();
        applyContentScrimResource();
        applyStatusBarScrimResource();
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
    }

    private void applyStatusBarScrimResource() {
        mStatusBarScrimResId = SkinCompatHelper.checkResourceId(mStatusBarScrimResId);
        if (mStatusBarScrimResId != INVALID_ID) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mStatusBarScrimResId);
            if (drawable != null) {
                setStatusBarScrim(drawable);
            }
        }
    }

    private void applyContentScrimResource() {
        mContentScrimResId = SkinCompatHelper.checkResourceId(mContentScrimResId);
        if (mContentScrimResId != INVALID_ID) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mContentScrimResId);
            if (drawable != null) {
                setContentScrim(drawable);
            }
        }
    }

    @Override
    public void applySkin() {
        applyContentScrimResource();
        applyStatusBarScrimResource();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

}
