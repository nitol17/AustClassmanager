package com.nitol.aust.cse.austclassmanager;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewLab extends Fragment {

    View v3;
    WebView wb2;

    ProfileDatabaseHelper myDb;
    ClassRoutineHelper cR;
    SwipeRefreshLayout mySwipeRefreshLayout;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String currentUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v3 = inflater.inflate(R.layout.webview_lab, container,false);
        wb2 = (WebView) v3.findViewById(R.id.webView_lab);
        mySwipeRefreshLayout = (SwipeRefreshLayout) v3.findViewById(R.id.swiperefresh);

        myDb = new ProfileDatabaseHelper(getContext());
        cR = new ClassRoutineHelper();

        getAllData();

        return v3;

    }


    public void getAllData() {

        Cursor result = myDb.getAllData();

        String student_department = "", student_year = "", student_semester = "",
                student_section = "", student_name = "";

        result.moveToFirst();
        while (!result.isAfterLast()) {

            if (result.getString(result.getColumnIndex("NAME")) != null) {
                student_name += result.getString(result.getColumnIndex("NAME"));
                student_name += "\n";
            }
            result.moveToNext();

            result.moveToFirst();
            if (result.getString(result.getColumnIndex("DEPARTMENT")) != null) {
                student_department += result.getString(result.getColumnIndex("DEPARTMENT"));
                student_department += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("YEAR")) != null) {
                student_year += result.getString(result.getColumnIndex("YEAR"));
                student_year += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SEMESTER")) != null) {
                student_semester += result.getString(result.getColumnIndex("SEMESTER"));
                student_semester += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SECTION")) != null) {
                student_section += result.getString(result.getColumnIndex("SECTION"));
                student_section += "\n";
            }
            result.moveToNext();
        }


        myDept = student_department.trim();
        myYear = student_year.trim();
        mySemester = student_semester.trim();
        mySection = student_section.trim();

        wb2.setInitialScale(1);
        wb2.getSettings().setJavaScriptEnabled(true);
        wb2.getSettings().setLoadWithOverviewMode(true);
        wb2.getSettings().setUseWideViewPort(true);
        wb2.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wb2.setScrollbarFadingEnabled(false);
        wb2.setWebViewClient(new WebViewClient());
        wb2.getSettings().setBuiltInZoomControls(true);
        wb2.getSettings().setUseWideViewPort(true);
        wb2.getSettings().setLoadWithOverviewMode(true);
        wb2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        String routine = cR.getRoutine(myDept,myYear,mySemester,mySection);

        if( (routine.trim().equals("cse_1_1_a")) || (routine.trim().equals("cse_1_1_b")) ||
                (routine.trim().equals("cse_1_1_c")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_1_1.php");
            currentUrl = "http://aust.edu/result/cse_s_1_1.php";
        }

        else if( (routine.trim().equals("cse_1_2_a")) || (routine.trim().equals("cse_1_2_b")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_1_2.php");
            currentUrl = "http://aust.edu/result/cse_s_1_2.php";
        }

        else if( (routine.trim().equals("cse_2_1_a")) || (routine.trim().equals("cse_2_1_b"))
                || (routine.trim().equals("cse_2_1_c")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_2_1.php");
            currentUrl = "http://aust.edu/result/cse_s_2_1.php";
        }

        else if( (routine.trim().equals("cse_2_2_a")) || (routine.trim().equals("cse_2_2_b")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_2_2.php");
            currentUrl = "http://aust.edu/result/cse_s_2_2.php";
        }

        else if( (routine.trim().equals("cse_3_1_a")) || (routine.trim().equals("cse_3_1_b"))
                || (routine.trim().equals("cse_3_1_c")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_3_1.php");
            currentUrl = "http://aust.edu/result/cse_s_3_1.php";
        }

        else if( (routine.trim().equals("cse_3_2_a")) || (routine.trim().equals("cse_3_2_b")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_3_2.php");
            currentUrl = "http://aust.edu/result/cse_s_3_2.php";
        }

        else if( (routine.trim().equals("cse_4_1_a")) || (routine.trim().equals("cse_4_1_b"))
                || (routine.trim().equals("cse_4_1_c")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_4_1.php");
            currentUrl = "http://aust.edu/result/cse_s_4_1.php";
        }

        else if( (routine.trim().equals("cse_4_2_a")) || (routine.trim().equals("cse_4_2_b")) ){

            wb2.loadUrl("http://aust.edu/result/cse_s_4_2.php");
            currentUrl = "http://aust.edu/result/cse_s_4_2.php";
        }

        wb2.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wb2.loadUrl(currentUrl);
                        wb2.setWebViewClient(new MyWebViewClient());
                    }
                }
        );


    }

    public class MyWebViewClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {
            mySwipeRefreshLayout.setRefreshing(false);
            currentUrl = url;
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains(currentUrl)){

            }
            return true;
        }
    }
}
