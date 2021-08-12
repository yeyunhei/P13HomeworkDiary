package sg.edu.rp.c346.id20011806.homeworkdiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSubject;
    Button btnInsertSubj;
    ArrayList<Subject> alSubj;
    SubjectAdapter ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSubject = findViewById(R.id.lvSubject);
        btnInsertSubj = findViewById(R.id.buttonInsertDialog);

        DBHelper dbh =new DBHelper(MainActivity.this);

        alSubj = new ArrayList<Subject>();
        alSubj = dbh.getAllSubjects();
        ca = new SubjectAdapter(this, R.layout.subject, alSubj);
        lvSubject.setAdapter(ca);

        btnInsertSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.insertsubject, null);

                final EditText etInputName = viewDialog.findViewById(R.id.etName);
                final EditText etInputDesc = viewDialog.findViewById(R.id.etDesc);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);  // Set the view of the dialog
                myBuilder.setTitle("Insert a Subject");
                myBuilder.setNegativeButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dataName = etInputName.getText().toString();
                        String dataDesc = etInputDesc.getText().toString();

                        DBHelper dbh = new DBHelper(MainActivity.this);
                        long inserted_id = dbh.insertSubject(dataName, dataDesc);

                        if (inserted_id != -1) {
                            Toast.makeText(MainActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                            alSubj.clear();
                            alSubj.addAll(dbh.getAllSubjects());
                            ca.notifyDataSetChanged();
                        }
                    }
                });

                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject data = alSubj.get(position);
                Intent i = new Intent(MainActivity.this, SubjectActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh =new DBHelper(MainActivity.this);
        alSubj.clear();
        alSubj.addAll(dbh.getAllSubjects());
        ca.notifyDataSetChanged();
    }
}