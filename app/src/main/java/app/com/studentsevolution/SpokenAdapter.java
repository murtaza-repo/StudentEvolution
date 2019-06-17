package app.com.studentsevolution;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SpokenAdapter extends RecyclerView.Adapter<SpokenAdapter.PlayViewHolder> {


    private List<PlaylistItems> items = new ArrayList<>();
    private OnSubCateListener monSubCateListener;

    public SpokenAdapter(OnSubCateListener onSubCateListener) {
        this.items = new ArrayList<>();
        this.monSubCateListener = onSubCateListener;
    }

    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.playlist_items, viewGroup, false);
        return new PlayViewHolder(view, monSubCateListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayViewHolder PlayViewHolder, int i) {

        PlaylistItems item = items.get(i);
        PlayViewHolder.play_title.setText(item.getTitle());
        Picasso.get()
                .load(item.getDefaultImage())
                .placeholder(R.drawable.logo)
                .into(PlayViewHolder.play_image);

    }

    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

    public String getPlaylistId(int i){

        return items.get(i).getPlaylistId();
    }

    public List<PlaylistItems> getItems() {
        if (items == null) {
            return new ArrayList<>();
        }

        return items;
    }

    public void add(PlaylistItems playlistItems) {
        items.add(playlistItems);
        //notifyItemInserted(Items.size() - 1);
        notifyDataSetChanged();
    }

    public void addAll(List<PlaylistItems> playlistItems) {
        for (PlaylistItems playlistItems1 : playlistItems) {
            add(playlistItems1);
        }
    }

    public class PlayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView play_image;
        private TextView play_title;
        OnSubCateListener onSubCateListener;

        public PlayViewHolder(@NonNull View itemView, OnSubCateListener onSubCateListener) {
            super(itemView);
            play_image = itemView.findViewById(R.id.play_image);
            play_title = itemView.findViewById(R.id.play_title);
            this.onSubCateListener = onSubCateListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSubCateListener.OnSubCateClick(getAdapterPosition());
        }
    }

    public interface OnSubCateListener{
        void OnSubCateClick(int position);
    }
}
