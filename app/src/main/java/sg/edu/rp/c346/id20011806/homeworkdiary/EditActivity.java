package sg.edu.rp.c346.id20011806.homeworkdiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etName, etDesc;
    // RadioButton radio1,radio2,radio3,radio4,radio5;
    Button btnUpdate, btnDelete, btnCancel;
    Subject subjectdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = findViewById(R.id.etSubjTitle);
        etDesc = findViewById(R.id.etSubjDesc);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        subjectdata = (Subject) i.getSerializableExtra("subjectdata");

        etName.setText(subjectdata.getName());
        etDesc.setText(subjectdata.getDesc());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                subjectdata.setName(etName.getText().toString());
                subjectdata.setDesc(etDesc.getText().toString());
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
                dbh.updateSubject(subjectdata);
                Toast.makeText(EditActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the subject: " + subjectdata.getName() + "?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbh.deleteSubject(subjectdata.getId());
                        Toast.makeText(EditActivity.this, "Delete successful",
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

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

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
        });
    }
}