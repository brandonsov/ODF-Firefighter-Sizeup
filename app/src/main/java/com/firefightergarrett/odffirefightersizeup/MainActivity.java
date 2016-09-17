package com.firefightergarrett.odffirefightersizeup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String[] INCIDENT_SIZE_VALUES = {"Spot", "1/4-1/2 Acre", "1/2-1 Acre", "1-5 Acres", "5+ Acres"};
    private final String[] SLOPE_VALUES = {"Flat (~0%)", "1-20%", "20-40%", "40%+"};

    private CheckBox statusContained,statusCreeping,statusRunning,statusSpotting,
                    statusCrowning, statusSmoldering, fuelGrass, fuelSlash,fuelBrush,fuelTimber;
    private ArrayList<String> status,fuel;
    private TextView incidentSizeText, slopeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar incidentSize, slope;
        incidentSize = (SeekBar) findViewById(R.id.sbSizeProgress);
        slope = (SeekBar) findViewById(R.id.sbSlopeProgress);
        incidentSizeText = (TextView) findViewById(R.id.tvSizeProgress);
        slopeText = (TextView) findViewById(R.id.tvSlopeProgress);

        incidentSize.setOnSeekBarChangeListener(new SeekBarListener(incidentSizeText, INCIDENT_SIZE_VALUES));
        slope.setOnSeekBarChangeListener(new SeekBarListener(slopeText, SLOPE_VALUES));

        Button submitButton = (Button) findViewById(R.id.bSubmit);
        submitButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // SubmitButtonClicked sets creates initial values from user input

                EditText fireNumber = (EditText) findViewById(R.id.FireNumber);
                EditText fireName = (EditText) findViewById(R.id.FireName);
                EditText incidentCommander = (EditText) findViewById(R.id.Commander);
                EditText description = (EditText) findViewById(R.id.LegalDescription);
                EditText latAndLong = (EditText) findViewById(R.id.LatLong);

                //TODO get checkbox values
                GridLayout statusContainer = (GridLayout)findViewById(R.id.cbContainerStatus);
                LinearLayout fuelContainer = (LinearLayout) findViewById(R.id.cbContainerFuelType);

                //RadioGroup spreadSizes = (RadioGroup) findViewById(R.id.rbContainerSpread);
                //RadioButton spread = (RadioButton) spreadSizes.getFocusedChild();

                Report report = new Report.ReportBuilder(fireNumber.getText().toString(),fireName.getText().toString())
                        .setCommander(incidentCommander.getText().toString())
                        .setDescription(description.getText().toString())
                        .setLatAndLong(latAndLong.getText().toString())
                        .setIncidentSize(incidentSizeText.getText().toString())
                        .setSlope(slopeText.getText().toString())
//                        .setSpreadPotential(spread.getText().toString())
                        .build();

                Intent intent = new Intent(MainActivity.this, FireReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("report", report);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.nav_camera:
                // Handle the camera action

            case R.id.nav_gallery:

            case R.id.nav_slideshow:

            case R.id.nav_manage:

            case R.id.nav_share:

            case R.id.nav_send:

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
