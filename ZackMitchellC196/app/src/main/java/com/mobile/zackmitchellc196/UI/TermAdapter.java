package com.mobile.zackmitchellc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.zackmitchellc196.Entities.Term;
import com.mobile.zackmitchellc196.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTitleView;
        private final TextView termStartView;
        private final TextView termEndView;

        private TermViewHolder(View itemview) {
            super(itemview);
            termTitleView = itemview.findViewById(R.id.textViewTermTitle);
            termStartView = itemview.findViewById(R.id.textViewTermStart);
            termEndView = itemview.findViewById(R.id.textViewTermEnd);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("id", current.getTermID());
                    intent.putExtra("title", current.getTermTitle());
                    intent.putExtra("start", current.getTermStart());
                    intent.putExtra("end", current.getTermEnd());
                    context.startActivity(intent);
                }
            });
        }
    }

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term current = mTerms.get(position);
            String title = current.getTermTitle();
            String start = current.getTermStart();
            String end = current.getTermEnd();
            holder.termTitleView.setText(title);
            holder.termStartView.setText(start);
            holder.termEndView.setText(end);
        } else {
            holder.termTitleView.setText("Term Title null");
            holder.termStartView.setText("Term Start null");
            holder.termEndView.setText("Term End null");
        }
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

    public void setTerms(List<Term> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

}
