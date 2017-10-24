package com.record.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by 灌云县公安局 李秉键 on 2017/7/26.
 */

public class BaseFragment extends Fragment {
    public Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
