package com.record.customview;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.record.R;
import com.record.adapter.PrintChooseAdapter;
import com.record.moudle.entity.PrintChoose;
import com.record.utils.ScreenHelper;
import com.record.utils.pakageUtils;

import java.util.ArrayList;
import java.util.List;


public class PrintChooseDialog extends Dialog {


    protected PrintChooseDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    protected PrintChooseDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    public static class Builder {
        private Dialog dialog;
        private Context context;
        private boolean cancelable;
        private String path;


        public Builder(Context context) {
            this.context = context;
        }



        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setPath(String  path) {
            this.path = path;
            return this;
        }
        /**
         * Create the custom dialog
         */
        public PrintChooseDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final PrintChooseDialog dialog = new PrintChooseDialog(context,
                    R.style.introduce_custom_dialog);
            View layout = inflater.inflate(R.layout.introduce_dialog, null);

            if(!cancelable){
                dialog.setCancelable(cancelable);
            }

            // set the confirm button
//            ImageView bt_close = (ImageView) layout.findViewById(R.id.iv_close);
//            bt_close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dimiss();
//                }
//            });
            final List<PrintChoose> printChooses = new ArrayList<>();
            PrintChoose printChoose = new PrintChoose();
            printChoose.setName(context.getResources().getString(R.string.canon));
            printChooses.add(printChoose);
            PrintChoose printC = new PrintChoose();
            printC.setName(context.getResources().getString(R.string.other));
            printChooses.add(printC);

            PrintChooseAdapter printChooseAdapter = new PrintChooseAdapter(R.layout.print_choose_item,printChooses);
            printChooseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://zsapp.faduit.com.cn:8070/bat/apk/canon.apk"));
//                    context.startActivity(intent);
                    pakageUtils.launchapp(context,printChooses.get(position).getName(),path);

                }
            });
            RecyclerView rv = (RecyclerView) layout.findViewById(R.id.rv);
            rv.setAdapter(printChooseAdapter);
            rv.setLayoutManager(new LinearLayoutManager(context));

            dialog.setContentView(layout);
            WindowManager.LayoutParams params =
                    dialog.getWindow().getAttributes();
            params.width = ScreenHelper.getScreenPixels(context).widthPixels*4/5;
            dialog.getWindow().setAttributes(params);
            return dialog;
        }

        public void show(){
            dialog =  create();
            dialog.show();

        }
        public void dimiss(){
            dialog.dismiss();
        }
    }


}
