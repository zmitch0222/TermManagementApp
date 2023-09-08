package com.mobile.zackmitchellc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.zackmitchellc196.Entities.Course;
import com.mobile.zackmitchellc196.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitleView;
        private final TextView courseStartView;
        private final TextView courseEndView;
        private final TextView courseStatusView;

        private CourseViewHolder(View itemview) {
            super(itemview);
            courseTitleView = itemview.findViewById(R.id.textViewCourseTitle);
            courseStartView = itemview.findViewById(R.id.textViewCourseStart);
            courseEndView = itemview.findViewById(R.id.textViewCourseEnd);
            courseStatusView = itemview.findViewById(R.id.textViewCourseStatus);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("id", current.getCourseID());
                    intent.putExtra("title", current.getCourseTitle());
                    intent.putExtra("start", current.getCourseStart());
                    intent.putExtra("end", current.getCourseEnd());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("name", current.getInstructorName());
                    intent.putExtra("phone", current.getInstructorPhoneNumber());
                    intent.putExtra("email", current.getInstructorEmail());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("notes", current.getNotes());
                    context.startActivity(intent);
                }
            });
        }
    }

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String title = current.getCourseTitle();
            String start = current.getCourseStart();
            String end = current.getCourseEnd();
            String status = current.getStatus();
            holder.courseTitleView.setText(title);
            holder.courseStartView.setText(start);
            holder.courseEndView.setText(end);
            holder.courseStatusView.setText(status);
        } else {
            holder.courseTitleView.setText("Course Title null");
            holder.courseStartView.setText("Course Start null");
            holder.courseEndView.setText("Course End null");
            holder.courseStatusView.setText("Course Status null");
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

}