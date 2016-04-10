package com.trantuandung.technictest.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.trantuandung.technictest.R;
import com.trantuandung.technictest.server.ItemsRequester;
import com.trantuandung.technictest.model.Book;
import com.trantuandung.technictest.view.adapter.BooksAdapter;
import com.trantuandung.technictest.enums.UserAdapterType;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private UserAdapterType userAdapterType = UserAdapterType.NORMAL;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();

    }

    private void initView() {
        final RecyclerView contentListView = (RecyclerView) findViewById(R.id.main_content);
        if (contentListView != null) {
            ItemsRequester itemsRequester = new ItemsRequester();
            BooksAdapter bookAdapter = null;
            switch (userAdapterType){
                case PANNIER:
                    break;
                default:
                    try {
                        List<Book> bookList = itemsRequester.getAllBook();
                        bookAdapter = new BooksAdapter(bookList);
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
}
