package sg.edu.rp.c346.id20011806.homeworkdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Activity> activityList;

    public ActivityAdapter(Context context, int resource, ArrayList<Activity> activities) {
        super(context, resource, activities);

        parent_context = context;
        layout_id = resource;
        activityList = activities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
       // TextView tvEntry = rowView.findViewById(R.id.textViewEntry);
        TextView tvDesc = rowView.findViewById(R.id.textViewActivityDesc);
        TextView tvStartDate = rowView.findViewById(R.id.textViewDate);
        TextView tvDueDate = rowView.findViewById(R.id.textViewDueDate);

        // Obtain the Android Version information based on the position
        Activity currentActivity = activityList.get(position);

        // Set values to the TextView to display the corresponding information
       // tvEntry.setText("Entry: " + currentActivity.getId());
        tvDesc.setText("Description: " + currentActivity.getDescription());
        tvStartDate.setText("Date added: " + currentActivity.getAddDate());
        tvDueDate.setText("Due date: " + currentActivity.getDueDate());

        return rowView;
    }
}
