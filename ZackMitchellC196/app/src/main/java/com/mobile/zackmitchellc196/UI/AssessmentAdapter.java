package com.mobile.zackmitchellc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.zackmitchellc196.Entities.Assessment;
import com.mobile.zackmitchellc196.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView AssessmentTitleView;
        private final TextView AssessmentTypeView;
        private final TextView AssessmentStartView;
        private final TextView AssessmentEndView;

        private AssessmentViewHolder(View itemview) {
            super(itemview);
            AssessmentTitleView = itemview.findViewById(R.id.textViewAssessmentTitle);
            AssessmentTypeView = itemview.findViewById(R.id.textViewAssessmentType);
            AssessmentStartView = itemview.findViewById(R.id.textViewAssessmentStart);
            AssessmentEndView = itemview.findViewById(R.id.textViewAssessmentEnd);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("id", current.getAssessmentID());
                    intent.putExtra("title", current.getAssessmentTitle());
                    intent.putExtra("type", current.getAssessmentType());
                    intent.putExtra("start", current.getAssessmentStart());
                    intent.putExtra("end", current.getAssessmentEnd());
                    intent.putExtra("courseID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String title = current.getAssessmentTitle();
            String type = current.getAssessmentType();
            String start = current.getAssessmentStart();
            String end = current.getAssessmentEnd();
            holder.AssessmentTitleView.setText(title);
            holder.AssessmentTypeView.setText(type);
            holder.AssessmentStartView.setText(start);
            holder.AssessmentEndView.setText(end);
        } else {
            holder.AssessmentTitleView.setText("Assessment Title null");
            holder.AssessmentTypeView.setText("Assessment Type null");
            holder.AssessmentStartView.setText("Assessment Start null");
            holder.AssessmentEndView.setText("Assessment End null");
        }
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public void setAssessment(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

}


