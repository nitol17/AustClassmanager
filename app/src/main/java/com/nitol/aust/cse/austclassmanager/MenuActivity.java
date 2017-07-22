package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DatabaseHelper myDb;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        myDb = new DatabaseHelper(this);


        getAllData();

    }


    public void classRoutine(View view) {
        Intent intent = new Intent(MenuActivity.this, ClassRoutineActivity.class);
        startActivity(intent);
    }

    public void cgpaCalculator(View view){
        Intent intent = new Intent(MenuActivity.this, CgpaCalculator.class);
        startActivity(intent);
    }

    public  void quizReminder(View view){
        Intent intent = new Intent(MenuActivity.this, QuizReminder.class);
        startActivity(intent);
    }

    public  void Settings(View view){
        Intent intent= new Intent(MenuActivity.this, Settings.class);
        startActivity(intent);
    }

    public void Result(View view){
        Intent intent= new Intent(MenuActivity.this, ResultCheckActivity.class);
        startActivity(intent);
    }

    public void classDetails(View view){
        Intent intent= new Intent(MenuActivity.this, ClassDetails.class);
        startActivity(intent);
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

        myName = student_name.trim();
        myDept = student_department.trim();
        myYear = student_year.trim();
        mySemester = student_semester.trim();
        mySection = student_section.trim();

        View hView =  navigationView.getHeaderView(0);

        TextView nav_name = (TextView)hView.findViewById(R.id.header_name);
        nav_name.setText(myName);

        TextView nav_dept = (TextView)hView.findViewById(R.id.header_dept);
        nav_dept.setText(myDept);

        TextView nav_year = (TextView)hView.findViewById(R.id.header_year);
        nav_year.setText(myYear);

        TextView nav_semester = (TextView)hView.findViewById(R.id.header_semester);
        nav_semester.setText(mySemester);

        TextView nav_section = (TextView)hView.findViewById(R.id.header_Section);
        nav_section.setText(mySection);


    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        switch (id){

            case R.id.home:

                break;

            case R.id.routine:
                Intent intent = new Intent(MenuActivity.this, ClassRoutineActivity.class);
                startActivity(intent);


                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(MenuActivity.this, CgpaCalculator.class);
                startActivity(intent2);

                break;

            case R.id.my_details:
                Toast.makeText(getApplicationContext(),"Class Details",Toast.LENGTH_SHORT).show();

                break;

            case R.id.quiz:
                Toast.makeText(getApplicationContext(),"Quiz Reminder",Toast.LENGTH_SHORT).show();

                break;

            case R.id.result:
                Intent intent3 = new Intent(MenuActivity.this, ResultCheckActivity.class);
                startActivity(intent3);

                break;

            case R.id.profile:
                Intent intent4 = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent4);

                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();

                break;

            case R.id.settings:
                Intent intent5 = new Intent(MenuActivity.this, Settings.class);
                startActivity(intent5);

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_tool,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.tool_settings){
            Intent intent3 = new Intent(MenuActivity.this, Settings.class);
            startActivity(intent3);

        }
        else if(id == R.id.tool_about){

            Toast.makeText(getApplicationContext(),"This is About !", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }


}

