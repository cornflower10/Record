package com.record.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.record.App;
import com.record.R;
import com.record.activity.MainActivity;
import com.record.activity.PrintRecordActivity;
import com.record.activity.UserInfoActivity;
import com.record.moudle.entity.User;
import com.record.moudle.moudleDao.ErrorView;
import com.record.moudle.moudleDao.LawCaseMoudleImpl;
import com.record.moudle.moudleDao.LawCaseMoulde;
import com.record.moudle.moudleDao.UserMoulde;
import com.record.moudle.moudleDao.UserMouldeImpl;
import com.record.utils.Constants;
import com.record.utils.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class AccountFragment extends BaseFragment implements ErrorView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_change)
    TextView tvChange;

    private String mParam1;
    private String mParam2;
    private AlertDialog.Builder builder;
    private LawCaseMoulde lawCase;
    private UserMoulde userMoulde;
    private User user;

    public AccountFragment() {
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
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (null != toolbar) {
            ((MainActivity) mContext).setSupportActionBar(toolbar);
            ((MainActivity) mContext).getSupportActionBar().setTitle("");
            titleName.setText(getString(R.string.account));
        }
        userMoulde = new UserMouldeImpl(this);
        lawCase = new LawCaseMoudleImpl(this);

        initDialog();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = userMoulde.seletcUser();
        if (null != user) {
            tvName.setText(user.getName());
            tvNo.setText(user.getUserNo());
            tvChange.setText("修改");
        } else {
            tvName.setText("");
            tvNo.setText("");
            tvChange.setText("设置");
        }
    }

    private void initDialog() {
        builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("将清除本地所有数据，是否继续？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FileUtils.deleteFile(Constants.pathDir + Constants.dir);
                lawCase.deleteAll();
                tvName.setText("");
                tvNo.setText("");
                tvChange.setText("设置");
            }
        });
        builder.setNegativeButton("否", null);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_print_record,R.id.rl_company_info, R.id.rl_traffic_accident_photo, R.id.rl_traffic_accident_law, R.id.rl_talk, R.id.rl_update, R.id.rl_clear_cache, R.id.rl_about, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_print_record:
                Intent intentP = new Intent(mContext, PrintRecordActivity.class);
                startActivity(intentP);
                break;
            case R.id.rl_company_info:
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_traffic_accident_photo:
                break;
            case R.id.rl_traffic_accident_law:
                break;
            case R.id.rl_talk:
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_clear_cache:
                if (null != builder) {
                    builder.show();
                }
                break;
            case R.id.rl_about:
                break;
            case R.id.tv_exit:
                App.getInstance().getForegroundCallbacks().AppExit();
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ((MainActivity) mContext).showToast(msg);
    }

    @OnClick(R.id.rl_change)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, UserInfoActivity.class);
        startActivity(intent);
    }
}
