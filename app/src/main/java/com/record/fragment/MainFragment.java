package com.record.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.record.R;
import com.record.activity.DocListTypeActivity;
import com.record.activity.ErrorActivity;
import com.record.activity.MainActivity;
import com.record.utils.Constants;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.banner_view)
    BannerLayout bannerView;
    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String mParam1;
    private String mParam2;

    public MainFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.home_banner01);
        list.add(R.mipmap.home_banner01);
        list.add(R.mipmap.home_banner01);
        list.add(R.mipmap.home_banner01);
        bannerView.setViewRes(list);
        bannerView.stopAutoPlay();

        if (null != toolbar) {
            ((MainActivity)mContext).setSupportActionBar(toolbar);
            ((MainActivity)mContext).getSupportActionBar().setTitle("");
            titleName.setText("公安笔录");
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_make_doc, R.id.ll_always_doc_setting, R.id.ll_null_doc, R.id.ll_cacul, R.id.ll_alarm, R.id.ll_ptoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_make_doc:
                ((MainActivity) mContext).mStartActivity(DocListTypeActivity.class);
                break;
            case R.id.ll_always_doc_setting:
                Intent intent = new Intent(mContext,DocListTypeActivity.class);
                intent.putExtra(Constants.TYPE,Constants.DOC_SETTING);
                startActivity(intent);
                break;
            case R.id.ll_null_doc:
                ((MainActivity) mContext).mStartActivity(DocListTypeActivity.class);
                break;
            case R.id.ll_cacul:
                ((MainActivity) mContext).mStartActivity(ErrorActivity.class);
                break;
            case R.id.ll_alarm:
                ((MainActivity) mContext).mStartActivity(ErrorActivity.class);
                break;
            case R.id.ll_ptoto:
                break;
        }
    }
}
