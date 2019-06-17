package app.com.studentsevolution;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ComViewHolder> {

    private ArrayList<String> comCategoryList;
    private OnCateListener monCateListener;


    public CategoryAdapter(ArrayList<String> comCategoryList, OnCateListener onCateListener) {
        this.comCategoryList = comCategoryList;
        this.monCateListener = onCateListener;
    }

    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.category_item, viewGroup, false);
        return new ComViewHolder(view, monCateListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder comViewHolder, int i) {

        comViewHolder.Cat_title.setText(comCategoryList.get(i));

    }

    @Override
    public int getItemCount() {
        return comCategoryList.size();
    }

    public class ComViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView Cat_title;
        OnCateListener onCateListener;

        public ComViewHolder(@NonNull View itemView, OnCateListener onCateListener) {
            super(itemView);
            Cat_title = itemView.findViewById(R.id.Cat_title);
            this.onCateListener =onCateListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCateListener.OnCateClick(getAdapterPosition());
        }
    }

    public interface OnCateListener{
        void OnCateClick(int position);
    }
}
