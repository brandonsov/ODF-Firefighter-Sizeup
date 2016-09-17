package com.firefightergarrett.odffirefightersizeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireReportActivity extends AppCompatActivity {

    public static final String MESSAGES_CHILD = "messages";

    // Firebase instance variables
    private DatabaseReference firebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Report, MessageViewHolder> firebaseAdapter;
    private RecyclerView messageRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Report report;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView fireNumber,fireName,commander,latAndLong,
                spreadPotential,incidentSize,slope;

        public MessageViewHolder(View v) {
            super(v);
            fireName = (TextView) itemView.findViewById(R.id.minFireName);
            fireNumber = (TextView) itemView.findViewById(R.id.minFireNumber);
            commander = (TextView) itemView.findViewById(R.id.minCommander);
            latAndLong = (TextView) itemView.findViewById(R.id.minLatAndLong);
            spreadPotential = (TextView) itemView.findViewById(R.id.minSpreadPotential);
            incidentSize = (TextView) itemView.findViewById(R.id.minIncidentSize);
            slope = (TextView) itemView.findViewById(R.id.minSlope);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_report);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        report = (Report)bundle.getSerializable("report");

        messageRecyclerView = (RecyclerView) findViewById(R.id.messageRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        // New child entries
        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAdapter = new FirebaseRecyclerAdapter<Report,
                MessageViewHolder>(
                Report.class,
                R.layout.minimized_report,
                MessageViewHolder.class,
                firebaseDatabaseReference.child(MESSAGES_CHILD)) {

            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder,
                                              Report report, int position) {
                viewHolder.fireName.setText("Fire name: " + report.getFireName());
                viewHolder.latAndLong.setText("Latitude and longitude: " + report.getLatAndLong());
                viewHolder.fireNumber.setText("Fire Number: " + report.getFireNumber());
                viewHolder.commander.setText("Incident commander: " + report.getCommander());
                viewHolder.spreadPotential.setText("Spread potential: " + report.getSpreadPotential());
                viewHolder.slope.setText("Slope: " + report.getSlope());
                viewHolder.incidentSize.setText("Incident size: " + report.getIncidentSize());
            }
        };

        firebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    messageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(firebaseAdapter);

        firebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(report);
//`        newReport.setText("");
    }




}
