package com.trantuandung.technictest.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trantuandung.technictest.R;
import com.trantuandung.technictest.database.DBHelper;
import com.trantuandung.technictest.server.ItemsRequester;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.view.adapter.BooksAdapter;
import com.trantuandung.technictest.enums.UserAdapterType;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    private UserAdapterType userAdapterType = UserAdapterType.NORMAL;
    DBHelper mDbHelper;
    BooksAdapter bookAdapter;


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
                    bookList = mDbHelper.getBookList();
                    bookAdapter = new BooksAdapter(mDbHelper,bookList);
                    break;
                default:
                    try {
                        bookList = itemsRequester.getAllBook();
                        bookAdapter = new BooksAdapter(mDbHelper,bookList);
                    } catch (Exception e) {
                        Toast.makeText(this, getResources().getText(R.string.error_technical_problem_happened),Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onResume getAllBook error " + e.getMessage() , e);
                    }
                    break;
            }
            final GridLayoutManager mLayoutManager = new GridLayoutManager(this,4);
            contentListView.setLayoutManager(mLayoutManager);
            contentListView.setAdapter(bookAdapter);
            contentListView.setHasFixedSize(true);
        }
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
}
