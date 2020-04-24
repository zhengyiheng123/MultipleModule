package com.example.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.baselibrary.R;
import com.example.baselibrary.loading.VaryViewHelperController;
import com.example.baselibrary.utils.PromptManager;
import com.example.baselibrary.utils.SystemBarTintManager;


/**
 * Created by yuepeng on 2017/12/12.
 */

public abstract class BaseDataBindingActivity<B extends ViewDataBinding, T extends BasePresenter> extends FragmentActivity implements View.OnClickListener, BaseView {
    //    private Unbinder unbinder;
    public B mViewBinding;

    public T mPresenter;
    public Activity mContext = null;
    /**
     * loading view controller
     */
    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        BaseAppManager.getInstance().addActivity(this);
        setTranslucentStatus(isApplyStatusBarTranslucency());
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            mViewBinding = DataBindingUtil.setContentView(this, getContentViewLayoutID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        initViewsAndEvents();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        unbinder = ButterKnife.bind(this);
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetached();
        }
        BaseAppManager.getInstance().removeActivity(this);
//        unbinder.unbind();
        super.onDestroy();

    }

    protected abstract T createPresenter();

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();

    /**
     * get loading target view
     */
    protected abstract View getLoadingTargetView();

    /**
     * is applyStatusBarTranslucency
     *
     * @return
     */
    protected abstract boolean isApplyStatusBarTranslucency();

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    public void popBackStack() {
        finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    public void gotoActivity(Class targetActivity) {
        gotoActivity(targetActivity, null, false);
    }

    public void gotoActivity(Class targetActivity, Bundle bundle) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void gotoActivity(Class targetActivity, boolean finishActivity) {
        gotoActivity(targetActivity, null, true);
    }

    public void gotoActivity(Class targetActivity, Bundle bundle, boolean finishActivity) {
        gotoActivity(targetActivity, bundle);
        if (finishActivity) {
            finish();
        }
    }

    public void gotoFragmentActivity(Class targetActivity, String targetPage) {
        gotoFragmentActivity(targetActivity, targetPage, null);
    }

    public void gotoFragmentActivity(Class targetActivity, String targetPage, Bundle bundle) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("targetPage", targetPage);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }


    public void gotoActivityForResult(Class targetActivity, int requestCode) {
        gotoActivityForResult(targetActivity, null, requestCode);
    }

    public void gotoActivityForResult(Class targetActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void gotoFragmentActivityForResult(Class targetActivity, String targetPage, int requestCode) {
        gotoFragmentActivityForResult(targetActivity, targetPage, null, requestCode);
    }


    public void gotoFragmentActivityForResult(Class targetActivity, String targetPage, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("targetPage", targetPage);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        PromptManager.showCustomToast(getApplicationContext(), text);
    }

    public void showToast(int textId) {
        PromptManager.showCustomToast(getApplicationContext(), getString(textId));
    }

    /**
     * toggle show loading
     */

    protected void toggleShowLoading() {
        toggleShowLoading("");
    }

    /**
     * toggle show loading
     */
    protected void toggleShowLoading(String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        mVaryViewHelperController.showLoading(msg);
    }

    /**
     * toggle show empty
     */
    protected void toggleShowEmpty() {
        toggleShowEmpty("");
    }

    /**
     * toggle show empty
     */
    protected void toggleShowEmpty(String msg) {
        toggleShowEmpty(msg, "", null);
    }

    /**
     * toggle show empty
     */
    protected void toggleShowEmpty(String msg, int imgId) {
        toggleShowEmpty(msg, "", imgId, null);
    }


    /**
     * toggle show empty
     */
    protected void toggleShowEmpty(String msg, String btnMsg, View.OnClickListener onClickListener) {
        toggleShowEmpty(msg, btnMsg, 0, onClickListener);
    }

    /**
     * toggle show empty
     */
    protected void toggleShowEmpty(String msg, String btnMsg, int imgId, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        mVaryViewHelperController.showEmpty(msg, imgId, btnMsg, onClickListener);
    }

    /**
     * toggle show error
     */
    protected void toggleShowError(String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        mVaryViewHelperController.showError(msg, onClickListener);

    }

    /**
     * toggle show network error
     */
    protected void toggleNetworkError(View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        mVaryViewHelperController.showNetworkError(onClickListener);
    }

    /**
     * toggle restore
     */
    protected void toggleRestore() {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        mVaryViewHelperController.restore();
    }


    /**
     * use SytemBarTintManager
     *
     * @param tintDrawable
     */
    protected void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }

    }

    /**
     * set status bar translucency
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
}
