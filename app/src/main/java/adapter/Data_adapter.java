package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ilab.user.e_lora.R;
import model.Data_model;
import model.Payload_elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data_adapter extends RecyclerView.Adapter<Data_adapter.DataViewHolder> {

    private ArrayList<Data_model> payload;
    private int rowLayout;
    private Context context;


    public static class DataViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView altitude_txtview, temp_textview, humidity_txtview, time_txtview;

        public DataViewHolder( View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.data_list_item_layout);
            altitude_txtview=itemView.findViewById(R.id.altitude_value);
            temp_textview = itemView.findViewById(R.id.temp_value);
            humidity_txtview =itemView.findViewById(R.id.humidity_value);
            time_txtview = itemView.findViewById(R.id.time_value);

        }
    }

    public Data_adapter(ArrayList<Data_model> payload, int rowLayout, Context context) {
        this.payload = payload;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new DataViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, int i) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


        dataViewHolder.altitude_txtview.setText(new Long(payload.get(i).getPayload().getAltitude()).toString());
        dataViewHolder.temp_textview.setText(new Long(payload.get(i).getPayload().getTemperature()).toString());
        dataViewHolder.humidity_txtview.setText(new Long(payload.get(i).getPayload().getHumidityy()).toString());
        dataViewHolder.time_txtview.setText(dateFormat.format(payload.get(i).getMetadata().getTime()));
    }

    @Override
    public int getItemCount() {
        return payload.size();
    }
}
