package com.record.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.activity.MainActivity;
import com.record.activity.WordHtmlActivity;
import com.record.adapter.LawCaseAdapter;
import com.record.customview.CustomBottomSheet;
import com.record.moudle.entity.LawCase;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.LawCaseView;
import com.record.utils.TimeUtils;
import com.record.utils.WordUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PrintRecordFragment extends BaseFragment implements LawCaseView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String mParam1;
    private String mParam2;
    private LawCaseMoulde lawCaseMoulde;
    private List<LawCase> list;
    private LawCaseAdapter lawCaseAdapter;
    private  CustomBottomSheet customBottomSheet;
    private long id;

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
            titleName.setText("打印记录");
        }
        initData();
        initBottomSheet();
        return view;

    }

    private void initData() {
        lawCaseMoulde = new LawCaseMoudleImpl(this);
        list = lawCaseMoulde.seletcAll();
        lawCaseAdapter = new LawCaseAdapter(R.layout.print_record_item, list);
        lawCaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LawCase lawCase = lawCaseMoulde.seletcById(list.get(position).getId());
                try {
                    WordUtil.convert2Html(lawCase.getDocPath(),
                            Environment.getExternalStorageDirectory().getPath() + "/" +
                                    lawCase.getLawCaseTitle() + TimeUtils.currentTimeMillis() + ".html");

                    Intent intent = new Intent(mContext, WordHtmlActivity.class);
                    intent.putExtra("path", lawCase.getDocPath());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ((MainActivity) mContext).showToast(e.getMessage());
                }


            }
        });
        lawCaseAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                id = list.get(position).getId();
                showBottomSheet();
                return false;
            }
        });
        rv.setAdapter(lawCaseAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));

    }


    private void initBottomSheet(){
        customBottomSheet = new CustomBottomSheet(mContext);
       View view = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet,null);
        view.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lawCaseMoulde.deleteById(id);
                dismissBottomSheet();
                lawCaseAdapter.refresh(lawCaseMoulde.seletcAll());
            }
        });
        view.findViewById(R.id.tv_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, lawCaseMoulde.seletcById(id).getDocPath(), Toast.LENGTH_LONG).show();

            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissBottomSheet();
            }
        });
        customBottomSheet.setContentView(view);

    }
    private void showBottomSheet(){
        if(null!=customBottomSheet&&!customBottomSheet.isShowing()){
            customBottomSheet.show();
        }
    }

    private void dismissBottomSheet(){
        if(null!=customBottomSheet&&customBottomSheet.isShowing()){
            customBottomSheet.dismiss();
        }
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
