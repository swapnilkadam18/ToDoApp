package com.swapnil.todoapp.view.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.swapnil.todoapp.R;
import com.swapnil.todoapp.model.persistence.pojo.TodoEntity;

import java.util.ArrayList;
import java.util.List;

public class DashBoardAdapter extends
        RecyclerView.Adapter<DashBoardAdapter.ViewHolder> {

    private List<TodoEntity> dataList = new ArrayList<>();
    private OnItemClickListener clickListener;

    public DashBoardAdapter() {

    }

    public void updateList(List<TodoEntity> updatedList) {
        dataList.clear();
        dataList.addAll(updatedList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.todo_list_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoEntity item = dataList.get(position);
        holder.bind(item);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(TodoEntity selectedData);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView title;
        private final TextView desc;
        private final TextView time;
        private final TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.tvTitle);
            desc = itemView.findViewById(R.id.tvDesc);
            time = itemView.findViewById(R.id.tvTime);
            date = itemView.findViewById(R.id.tvDate);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            clickListener.onItemClick(dataList.get(pos));
        }


        public void bind(final TodoEntity model) {
            title.setText(model.getTitle());
            desc.setText(model.getDescription());
            date.setText(model.getDate());
            time.setText(model.getTime());
        }
    }

}
