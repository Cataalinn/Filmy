package tech.salroid.filmy.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;
import java.util.List;
import tech.salroid.filmy.DataClasses.MovieDetailsData;
import tech.salroid.filmy.R;
/**
 * Created by Home on 7/22/2016.
 */
public class MovieDetailsActivityAdapter extends RecyclerView.Adapter<MovieDetailsActivityAdapter.Ho> {

    private final Boolean ret_size;
    String ct_name, ct_desc, ct_profile, ct_id;
    private final LayoutInflater inflater;
    private ClickListener clickListener;

    List<MovieDetailsData> cast = new ArrayList<>();
    Context con;

    public MovieDetailsActivityAdapter(Context context, List<MovieDetailsData> cast, Boolean size) {
        inflater = LayoutInflater.from(context);
        con = context;
        this.cast = cast;
        this.ret_size = size;
    }

    @Override
    public Ho onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cast_custom_row, parent, false);
        Ho holder = new Ho(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Ho holder, int position) {

        ct_name = cast.get(position).getCast_name();
        ct_desc = cast.get(position).getCast_character();
        ct_profile = cast.get(position).getCast_profile();
        ct_id = cast.get(position).getCast_id();

        holder.cast_name.setText(ct_name);
        holder.cast_description.setText(ct_desc);

        Glide.with(con)
                .load(ct_profile)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .into(holder.cast_poster);
    }


    @Override
    public int getItemCount() {

        if (ret_size)
            return (cast.size() >= 5) ? 5 : cast.size();

        else
            return cast.size();

    }

    class Ho extends RecyclerView.ViewHolder {

        TextView cast_name, cast_description;
        CircularImageView cast_poster;

        public Ho(View itemView) {

            super(itemView);

            cast_name = (TextView) itemView.findViewById(R.id.cast_name);
            cast_description = (TextView) itemView.findViewById(R.id.cast_description);
            cast_poster = (CircularImageView) itemView.findViewById(R.id.cast_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.itemClicked(cast.get(getPosition()), getPosition());
                    }
                }
            });
        }
    }

    public interface ClickListener {

        public void itemClicked(MovieDetailsData setterGetter, int position);

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
