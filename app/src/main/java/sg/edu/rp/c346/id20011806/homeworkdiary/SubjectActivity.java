package sg.edu.rp.c346.id20011806.homeworkdiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SubjectActivity extends AppCompatActivity {

    Button btnBack, btnAddAct;
    ListView lvActivities;
    ArrayList<Activity> alAct;
    ActivityAdapter ca;
    TextView tvCurrentSubj;
    Subject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        btnBack = findViewById(R.id.buttonBack);
        lvActivities = findViewById(R.id.lvActivities);
        btnAddAct = findViewById(R.id.buttonAddAct);
        tvCurrentSubj = findViewById(R.id.textViewCurrentSubj);

        Intent i = getIntent();
        data = (Subject) i.getSerializableExtra("data");
        tvCurrentSubj.setText("Current subject: " + data.getName());

        DBHelperActivity dbh =new DBHelperActivity(SubjectActivity.this);

        alAct = new ArrayList<Activity>();
        alAct = dbh.getAllActivities(data.getId());
        //Activity acty = new Activity("desc test", "start test", "due test");
        //alAct.add(acty);
        ca = new ActivityAdapter(this, R.layout.activity, alAct);
        lvActivities.setAdapter(ca);

        btnAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.insertactivity, null);

                final EditText etInputDescription = viewDialog.findViewById(R.id.etActDesc);
                final EditText etInputDue = viewDialog.findViewById(R.id.etDueDate);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(SubjectActivity.this);
                myBuilder.setView(viewDialog);  // Set the view of the dialog
                myBuilder.setTitle("Insert an Activity");
                myBuilder.setNegativeButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dataDesc = etInputDescription.getText().toString();
                        String dataDueDate = etInputDue.getText().toString();
                        Calendar c  = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String dataStartDate = sdf.format(c.getTime());
                        //String dataStartDate = "hi";
                        int dataSubjectId = data.getId();

                        DBHelperActivity dbh = new DBHelperActivity(SubjectActivity.this);
                        long inserted_id = dbh.insertActivity(dataDesc, dataStartDate, dataDueDate, dataSubjectId);

                        if (inserted_id != -1) {
                            Toast.makeText(SubjectActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                            alAct.clear();
                            alAct.addAll(dbh.getAllActivities(data.getId()));
                            ca.notifyDataSetChanged();
                        }
                    }
                });

                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}