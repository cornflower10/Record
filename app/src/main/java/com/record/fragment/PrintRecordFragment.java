package com.record.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.activity.ErrorActivity;
import com.record.activity.MainActivity;
import com.record.adapter.OfficeItemAdapter;
import com.record.moudle.entity.OfficeItem;
import com.record.moudle.moudleDao.ErrorView;

import java.util.ArrayList;
import java.util.List;

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
    private List<OfficeItem> list;

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
        initData();
        return view;

    }

    private void initData() {
        list = new ArrayList<>();
       String [] names = getResources().getStringArray(R.array.name);
        for (int i = 0; i <names.length ; i++) {
            OfficeItem off = new OfficeItem(names[i]);
            switch (i){
                case 0:
                    off.setResource(R.mipmap.task_blue);
                    break;

                case 1:
                    off.setResource(R.mipmap.apply_orange);
                    break;
                case 2:
                    off.setResource(R.mipmap.approval_red);
                    break;
                case 3:
                    off.setResource(R.mipmap.clock_blue);
                    break;
                case 4:
                    off.setResource(R.mipmap.record_orange);
                    break;
                case 5:
                    off.setResource(R.mipmap.report_red);
                    break;
                default:
                    break;

            }
            list.add(off);

        }
        OfficeItemAdapter adapter = new OfficeItemAdapter(R.layout.office_item,list);
        GridLayoutManager gridLay = new GridLayoutManager(mContext,3);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in  =new Intent(mContext, ErrorActivity.class);
                startActivity(in);

            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(gridLay);
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
