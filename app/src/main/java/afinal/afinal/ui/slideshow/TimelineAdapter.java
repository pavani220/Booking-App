package afinal.afinal.ui.slideshow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.afinal.afinal.R;
import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {

    private List<String> timelineItems;

    public TimelineAdapter(List<String> timelineItems) {
        this.timelineItems = timelineItems;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_item, parent, false);
        return new TimelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        holder.locationText.setText(timelineItems.get(position));
    }

    @Override
    public int getItemCount() {
        return timelineItems.size();
    }

    public static class TimelineViewHolder extends RecyclerView.ViewHolder {

        TextView locationText;

        public TimelineViewHolder(View itemView) {
            super(itemView);
            locationText = itemView.findViewById(R.id.locationText);
        }
    }
}

