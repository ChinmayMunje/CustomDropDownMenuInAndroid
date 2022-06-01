package com.gtappdevelopers.googlemapsroutes.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtappdevelopers.googlemapsroutes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoursesRVAdapter extends RecyclerView.Adapter<CoursesRVAdapter.CourseViewHolder> {
    private Context ctx;
    private List<DataModal> dataModalArrayList;

    public CoursesRVAdapter(Context ctx, List<DataModal> dataModalArrayList) {
        this.ctx = ctx;
        this.dataModalArrayList = dataModalArrayList;
    }

    @NonNull
    @Override
    public CoursesRVAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesRVAdapter.CourseViewHolder holder, int position) {
        DataModal modal = dataModalArrayList.get(position);
        Picasso.get().load(modal.getCourseImg()).into(holder.courseIV);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDurationTV.setText("Course Duration : " + modal.getCourseDuration() + " days");
        holder.coursePriceTV.setText("Course Price : " + modal.getCoursePrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, AddDataActivity.class);
                i.putExtra("type", "edit");
                i.putExtra("courseId", modal.getCourseId());
                i.putExtra("courseName", modal.getCourseName());
                i.putExtra("coursePrice", modal.getCoursePrice());
                i.putExtra("courseDuration", modal.getCourseDuration());
                i.putExtra("courseLink", modal.getCourseLink());
                i.putExtra("courseImg", modal.getCourseImg());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModalArrayList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private ImageView courseIV;
        private TextView courseNameTV, coursePriceTV, courseDurationTV;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourse);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            coursePriceTV = itemView.findViewById(R.id.idTVCoursePrice);
            courseDurationTV = itemView.findViewById(R.id.idTvCourseDuration);
        }
    }
}
