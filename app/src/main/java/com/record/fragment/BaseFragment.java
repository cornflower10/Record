package com.record.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by xiejingbao on 2017/7/26.
 */

public class BaseFragment extends Fragment {
    public Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
