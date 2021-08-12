package sg.edu.rp.c346.id20011806.homeworkdiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSecondActivity extends AppCompatActivity {

    EditText etDesc, etDue;
    // RadioButton radio1,radio2,radio3,radio4,radio5;
    Button btnUpdate, btnDelete, btnCancel;
    Activity activitydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_second);

        etDesc = findViewById(R.id.etActDesc);
        etDue = findViewById(R.id.etActDue);
        btnUpdate = findViewById(R.id.btnActUpdate);
        btnDelete = findViewById(R.id.btnActDelete);
        btnCancel = findViewById(R.id.btnActCancel);

       /* Intent i = getIntent();
        activitydata = (Activity) i.getSerializableExtra("activitydata");

        etDesc.setText(activitydata.getDescription());
        etDue.setText(activitydata.getDueDate());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperActivity dbh = new DBHelperActivity(EditSecondActivity.this);
                activitydata.setDescription(etDesc.getText().toString());
                activitydata.setDueDate(etDue.getText().toString());*/
               /*if (radio1.isChecked()) {
                   data.setStars(1);
               } else if (radio2.isChecked()) {
                   data.setStars(2);
               } else if (radio3.isChecked()) {
                   data.setStars(3);
               } else if (radio4.isChecked()) {
                   data.setStars(4);
               } else if (radio5.isChecked()) {
                   data.setStars(5);
               }*/
               /* dbh.updateActivity(activitydata);
                Toast.makeText(EditSecondActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSecondActivity.this);
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditSecondActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the activity: " + activitydata.getDescription() + "?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbh.deleteSubject(activitydata.getId());
                        Toast.makeText(EditSecondActivity.this, "Delete successful",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditSecondActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do not discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });*/

    }



}