package com.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shop.adapter.ShoppingCarAdapter;
import com.shop.bean.ShoppingCarDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_titlebar_center) TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_edit) TextView tvTitlebarRight;
    @BindView(R.id.elv_shopping_car) ExpandableListView elvShoppingCar;
    @BindView(R.id.iv_select_all) ImageView ivSelectAll;
    @BindView(R.id.ll_select_all) LinearLayout llSelectAll;
    @BindView(R.id.btn_order) Button btnOrder;
    @BindView(R.id.btn_delete) Button btnDelete;
    @BindView(R.id.tv_total_price) TextView tvTotalPrice;
    @BindView(R.id.rl_total_price) RelativeLayout rlTotalPrice;
    @BindView(R.id.rl) RelativeLayout rl;
    @BindView(R.id.iv_no_contant) ImageView ivNoContant;
    @BindView(R.id.rl_no_contant) RelativeLayout rlNoContant;
    @BindView(R.id.tv_titlebar_update) TextView tvTitlebarLeft;

    //模拟的购物车数据（实际开发中使用后台返回的数据）
    private String shoppingCarData = "{\n" +
            "    \"code\": 200,\n" +
            "    \"datas\": [\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"111111\",\n" +
            "                    \"goods_image\": \"https://img2.momoshop.com.tw/goodsimg/0006/381/341/6381341_B.jpg?t=1582040396\",\n" +
            "                    \"goods_name\": \"鋼木多用收納工作電腦桌\",\n" +
            "                    \"goods_num\": \"1\",\n" +
            "                    \"goods_price\": \"18.00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"1\",\n" +
            "            \"store_name\": \"一号小书店\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private List<ShoppingCarDataBean.DatasBean> datas;
    private Context context;
    private ShoppingCarAdapter shoppingCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = this;

        initExpandableListView();
        initData();

    }
    private void initExpandableListView(){
        shoppingCarAdapter = new ShoppingCarAdapter(context, llSelectAll, ivSelectAll, btnOrder, btnDelete, rlTotalPrice, tvTotalPrice);
        elvShoppingCar.setAdapter(shoppingCarAdapter);

    }
/*
    @OnClick({R.id.tv_titlebar_update, R.id.tv_titlebar_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_update://刷新数据
                initData();
                break;
            case R.id.tv_titlebar_edit://编辑
                String edit = tvTitlebarRight.getText().toString().trim();
                if (edit.equals("编辑")) {
                    tvTitlebarRight.setText("完成");
                    rlTotalPrice.setVisibility(View.GONE);
                    btnOrder.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.VISIBLE);
                } else {
                    tvTitlebarRight.setText("编辑");
                    rlTotalPrice.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }*/
    private void initData(){
        //使用Gson解析购物车数据，
        //ShoppingCarDataBean为bean类，Gson按照bean类的格式解析数据
        /**
         * 实际开发中，通过请求后台接口获取购物车数据并解析
         */

        Gson gson = new Gson();
        ShoppingCarDataBean shoppingCarDataBean = gson.fromJson(shoppingCarData, ShoppingCarDataBean.class); //strint to cardata class
         datas = shoppingCarDataBean.getDatas();
        initExpandableListViewData(datas);
     /*   System.out.println("store number="+datas.size());
        if(datas.size()>0)
        {
            final ShoppingCarDataBean.DatasBean datasBean = datas.get(0);
            System.out.println("store id="+datasBean.getStore_id()+"_store name="+datasBean.getStore_name());
            //ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = datasBean.getGoods().get(0);

            for (int i = 0; i < datasBean.getGoods().size(); i++) {
                ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = datasBean.getGoods().get(i);
                System.out.println("_gooods id="+goodsBean.getGoods_id()+"_gooods name="+goodsBean.getGoods_name());

            }
        }*/

    }
    private void initExpandableListViewData(List<ShoppingCarDataBean.DatasBean> datas) {
        if (datas != null && datas.size() > 0) {
            //刷新数据时，保持当前位置
            shoppingCarAdapter.setData(datas);

            //使所有组展开
            for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                elvShoppingCar.expandGroup(i);
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    return true;
                }
            });

            tvTitlebarRight.setVisibility(View.VISIBLE);
            tvTitlebarRight.setText("编辑");
            rlNoContant.setVisibility(View.GONE);
            elvShoppingCar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            rlTotalPrice.setVisibility(View.VISIBLE);
            btnOrder.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
        } else {
            tvTitlebarRight.setVisibility(View.GONE);
            rlNoContant.setVisibility(View.VISIBLE);
            elvShoppingCar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    }
}

