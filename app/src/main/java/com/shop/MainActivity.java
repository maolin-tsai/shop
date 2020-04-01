package com.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;


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
    private String shoppingCarData = "{\n" +"}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
