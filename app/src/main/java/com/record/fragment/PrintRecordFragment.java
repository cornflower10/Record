package com.record.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.record.R;
import com.record.activity.MainActivity;
import com.record.moudle.moudleDao.ErrorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PrintRecordFragment extends BaseFragment implements ErrorView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;

    private String mParam1;
    private String mParam2;

    public PrintRecordFragment() {
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
    public static PrintRecordFragment newInstance(String param1, String param2) {
        PrintRecordFragment fragment = new PrintRecordFragment();
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
        View view = inflater.inflate(R.layout.fragment_print_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (null != toolbar) {
            ((MainActivity) mContext).setSupportActionBar(toolbar);
            ((MainActivity) mContext).getSupportActionBar().setTitle("");
            titleName.setText("办公区");
        }
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onError(String msg) {
        ((MainActivity) mContext).showToast(msg);
    }
}
