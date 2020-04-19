package com.example.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.R;
import com.example.baselibrary.loading.VaryViewHelperController;
import com.example.baselibrary.utils.PromptManager;


/**
 * Created by yuepeng on 2017/12/12.
 */

public abstract class BaseDataBindingFragment<B extends ViewDataBinding,T extends BasePresenter> extends Fragment implements View.OnClickListener, BaseView {
//    private Unbinder unbinder;
    public B mViewBinding;

    public T mPresenter;

    protected Activity mContext = null;
    /**
     * loading view controller
     */
    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            mViewBinding = DataBindingUtil.inflate(inflater, getContentViewLayoutID(), container, false);
            return mViewBinding.getRoot();
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        initViewsAndEvents();
        setListener();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    protected void setListener(){};

    @Override
    public void onDestroyView() {
//        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetached();
        }
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
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    /**
     * get the support fragment manager
     *
     * @return
     */
    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    public void popBackStack() {
        if (mContext != null) {
            mContext.finish();
            mContext.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
        }
    }

    public void gotoActivity(Class targetActivity) {
        gotoActivity(targetActivity, null, false);
    }

    public void gotoActivity(Class targetActivity, Bundle bundle) {
        Intent intent = new Intent(mContext, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        mContext.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void gotoActivity(Class targetActivity, boolean finishActivity) {
        gotoActivity(targetActivity, null, true);
    }

    public void gotoActivity(Class targetActivity, Bundle bundle, boolean finishActivity) {
        gotoActivity(targetActivity, bundle);
        if (finishActivity) {
            mContext.finish();
        }
    }

    public void gotoFragmentActivity(Class targetActivity, String targetPage) {
        gotoFragmentActivity(targetActivity, targetPage, null);
    }

    public void gotoFragmentActivity(Class targetActivity, String targetPage, Bundle bundle) {
        Intent intent = new Intent(mContext, targetActivity);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("targetPage", targetPage);
        intent.putExtras(bundle);
        startActivity(intent);
        mContext.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }


    public void gotoActivityForResult(Class targetActivity, int requestCode) {
        gotoActivityForResult(targetActivity, null, requestCode);
    }

    public void gotoActivityForResult(Class targetActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        mContext.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public void gotoFragmentActivityForResult(Class targetActivity, String targetPage, int requestCode) {
        gotoFragmentActivityForResult(targetActivity, targetPage, null, requestCode);
    }


    public void gotoFragmentActivityForResult(Class targetActivity, String targetPage, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, targetActivity);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("targetPage", targetPage);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        mContext.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        PromptManager.showCustomToast(getActivity().getApplicationContext(), text);
    }

    public void showToast(int text) {
        PromptManager.showCustomToast(getActivity().getApplicationContext(), getString(text));
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
}
