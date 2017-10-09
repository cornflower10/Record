package com.record.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.record.R;
import com.record.adapter.TempIncludeAdapter;
import com.record.moudle.entity.LawCase;
import com.record.moudle.entity.Level0Item;
import com.record.moudle.entity.TempInclude;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.LawCaseView;
import com.record.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryDocListActivity extends BaseActivity implements LawCaseView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private List<LawCase> list = new ArrayList<>();
    //    private LawCaseAdapter lawCaseAdapter;
    private TempIncludeAdapter tempIncludeAdapter;
    private LawCaseMoulde lawCaseMoulde;
    private List<MultiItemEntity> data = new ArrayList<>();
    private SparseArray<List<LawCase>> spa = new SparseArray();

    @Override
    public int setContentView() {
        return R.layout.activity_doc_list;
    }

    @Override
    public String setTitleName() {
        return "模板列表";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("确定");

        lawCaseMoulde = new LawCaseMoudleImpl(this);
        list = lawCaseMoulde.seletcAll();
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i).getType();
            List<LawCase> listLawCase = spa.get(list.get(i).getType());
            if (null == listLawCase) {
                List<LawCase> listL = new ArrayList<>();
                listL.add(list.get(i));
                spa.put(key, listL);
            } else {
                listLawCase.add(list.get(i));
            }
        }

        for (int j = 0; j < spa.size(); j++) {

            int key = spa.keyAt(j);
            Level0Item group = new Level0Item(Constants.SPARSEARRAY_NAME.get(key));
            List<LawCase> list = spa.get(key);
            for (int k = 0; k < list.size(); k++) {
                TempInclude tempInclude = new TempInclude();
                tempInclude.setTypeName(list.get(k).getLawCaseTitle());
                tempInclude.setLawCase(list.get(k));
                group.addSubItem(tempInclude);
            }
            data.add(group);

        }
        tempIncludeAdapter = new TempIncludeAdapter(data);
//        tempIncludeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////            LawCase lawCase = lawCaseMoulde.seletcById(list.get(position).getId());
////
////                Gson gson = new Gson();
////              String str =   lawCase.getLawCaseInfo().replaceAll("\\$","");
////                Receipt receipt = gson.fromJson(str, Receipt.class);
////                Intent intent = new  Intent(mContext,RecordDocInfoActivity.class);
////                intent.putExtra("Receipt",receipt);
////                setResult(RESULT_OK,intent);
////                finish();
//                if(data.get(position+1).isExpan()){
//                    data.get(position+1).setExpan(false);
//                }else {
//                    data.get(position+1).setExpan(true);
//                }
//                tempIncludeAdapter.notifyItemChanged(position+1);
//
//            }
//        });
        rv.setAdapter(tempIncludeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void onResume() {

        tempIncludeAdapter.notifyDataSetChanged();
        super.onResume();

    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @OnClick(R.id.tv_right)
    public void onViewClicked() {
    }
}
