package com.trantuandung.technictest.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.trantuandung.technictest.R;
import com.trantuandung.technictest.database.DBHelper;
import com.trantuandung.technictest.listener.AmountListener;
import com.trantuandung.technictest.listener.BadgeCountListener;
import com.trantuandung.technictest.listener.CommercialOfferCallBack;
import com.trantuandung.technictest.server.CommercialOffer;
import com.trantuandung.technictest.server.ItemsRequester;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.view.BadgeView;
import com.trantuandung.technictest.view.adapter.BooksAdapter;
import com.trantuandung.technictest.view.adapter.BooksCatalogAdapter;
import com.trantuandung.technictest.enums.UserAdapterType;
import com.trantuandung.technictest.view.adapter.BooksPannierAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AmountListener, BadgeCountListener{
    private final static String TAG = MainActivity.class.getSimpleName();
    private UserAdapterType userAdapterType = UserAdapterType.NORMAL;
    DBHelper mDbHelper;
    BooksAdapter bookAdapter;
    private BadgeView tvBadgeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.main_layout);
        userAdapterType = UserAdapterType.NORMAL;

        this.mDbHelper = new DBHelper(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        TextView t = (TextView) findViewById(R.id.mainToolbarTotalSuggestedPrice);
        t.setPaintFlags(t.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        findViewById(R.id.mainToolbarContinueShopping).setOnClickListener(this);
        findViewById(R.id.mainToolbarCart).setOnClickListener(this);

        initView();
    }

    private void initView() {
        makeVisibilityButtonOnToolbar();
        final RecyclerView contentListView = (RecyclerView) findViewById(R.id.mainContent);
        if (contentListView != null) {
            ItemsRequester itemsRequester = new ItemsRequester();
            List<Book> bookList;
            switch (userAdapterType){
                case PANNIER:
                    setCartAmount();

                    bookList = mDbHelper.getBookList();
                    bookAdapter = new BooksPannierAdapter(this, mDbHelper, bookList);
                    break;
                default:
                    try {
                        updateBadgeView();
                        bookList = itemsRequester.getAllBook();
                        bookAdapter = new BooksCatalogAdapter(this, mDbHelper, bookList);
                    } catch (Exception e) {
                        Toast.makeText(this, getResources().getText(R.string.error_technical_problem_happened),Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "initView PANNIER error " + e.getMessage() , e);
                    }
                    break;
            }
            final GridLayoutManager mLayoutManager = new GridLayoutManager(this,1);
            contentListView.setLayoutManager(mLayoutManager);
            contentListView.setAdapter(bookAdapter);
            contentListView.setHasFixedSize(true);

            final float cardViewWidth = getResources().getDimension(R.dimen.bookItem_width);
            contentListView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            contentListView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int viewWidth = contentListView.getMeasuredWidth();

                            int newSpanCount = (int) Math.floor(viewWidth / cardViewWidth);
                            if(newSpanCount < 1){
                                newSpanCount = 1;
                            }
                            mLayoutManager.setSpanCount(newSpanCount);
                            mLayoutManager.requestLayout();
                        }
                    });

        }
    }

    @Override
    public void setCartAmount(){
        TextView mainToolbarTotalSuggestedPrice = (TextView) findViewById(R.id.mainToolbarTotalSuggestedPrice);
        if(mainToolbarTotalSuggestedPrice != null){
            mainToolbarTotalSuggestedPrice.setText(String.format(getResources().getString(R.string.main_toolbar_price),mDbHelper.totalCart()));
        }

        CommercialOffer commercialOffer = new CommercialOffer(mDbHelper);
        commercialOffer.getOffert(new CommercialOfferCallBack() {
            @Override
            public void success(int amount) {
                TextView mainToolbarTotalPrice = (TextView) findViewById(R.id.mainToolbarTotalPrice);
                if(mainToolbarTotalPrice != null){
                    mainToolbarTotalPrice.setText(String.format(getResources().getString(R.string.main_toolbar_price),amount));
                }
            }

            @Override
            public void failure(String messageError) {
                Toast.makeText(MainActivity.this, getResources().getText(R.string.error_technical_problem_happened),Toast.LENGTH_SHORT).show();
                Log.e(TAG, "setCartAmount error " + messageError);
            }
        });
    }

    private void makeVisibilityButtonOnToolbar(){
        boolean isOnPannier = userAdapterType == UserAdapterType.PANNIER;
        findViewById(R.id.mainToolbarCart).setVisibility(isOnPannier ? View.GONE : View.VISIBLE);
        findViewById(R.id.mainToolbarTotal).setVisibility(isOnPannier ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainToolbarContinueShopping:
                userAdapterType = UserAdapterType.NORMAL;
                initView();
                break;
            case R.id.mainToolbarCart:
                userAdapterType = UserAdapterType.PANNIER;
                initView();
                break;
        }

    }

    @Override
    public void updateBadgeView(){
        final int count = mDbHelper.getBookList().size();
        final int marginHorzontal = 8;
        final int nbCharater = ("" + count).length();

        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            @Override
            public void run() {
                if (tvBadgeView == null) {
                    tvBadgeView = new BadgeView(MainActivity.this, findViewById(R.id.mainToolbarCartBadge));
                }

                tvBadgeView.setText(count+"");
                Log.i(TAG, "updateBadgeView nb book = " + count);

                if (nbCharater == 1)
                    tvBadgeView.setBadgeMarginHorizontal(marginHorzontal);
                else if (nbCharater > 1 && nbCharater < 4)
                    tvBadgeView.setBadgeMarginHorizontal(marginHorzontal - nbCharater * 4);
                else
                    tvBadgeView.setBadgeMarginHorizontal(marginHorzontal - 3 * 4);

                tvBadgeView.show();
            }
        });
    }
}
