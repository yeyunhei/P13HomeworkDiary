package sg.edu.rp.c346.id20011806.homeworkdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Subject> subjectList;

    DBHelperActivity dbh = new DBHelperActivity(getContext());

    public SubjectAdapter(Context context, int resource, ArrayList<Subject> subjects) {
        super(context, resource, subjects);

        parent_context = context;
        layout_id = resource;
        subjectList = subjects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvNumOfActivity = rowView.findViewById(R.id.textViewActivityNum);
        TextView tvDesc = rowView.findViewById(R.id.textViewDesc);

        // Obtain the Android Version information based on the position
        Subject currentSubject = subjectList.get(position);

        int size = dbh.getAllActivities(currentSubject.getId()).size();

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentSubject.getName());
        tvNumOfActivity.setText("Amount of incomplete activities: " + size);
        tvDesc.setText(currentSubject.getDesc());

        return rowView;
    }
}
