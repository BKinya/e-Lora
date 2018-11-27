package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ilab.user.e_lora.R;
import model.Gateway_fields;
import retrofit2.http.PUT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewaysAdapater extends RecyclerView.Adapter<GatewaysAdapater.MyViewHolder> {

    private List<Gateway_fields> gateway_fields_list;
    private Context context;
    private int rowLayout;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView last_seen_textview, gateway_id_textview, rssi_textview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            last_seen_textview = itemView.findViewById(R.id.last_seen_value_txtview);
            gateway_id_textview = itemView.findViewById(R.id.gateway_id_value_txtview);
            rssi_textview = itemView.findViewById(R.id.signal_level_value_txtview);
        }
    }

    public GatewaysAdapater(List<Gateway_fields> gateway_fields_list, Context context, int rowLayout) {
        this.gateway_fields_list = gateway_fields_list;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gateways_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Gateway_fields gateway_fields = gateway_fields_list.get(i);
        myViewHolder.last_seen_textview.setText(dateToString(gateway_fields.getTime()));
        myViewHolder.gateway_id_textview.setText(gateway_fields.getGateway_id());
        myViewHolder.rssi_textview.setText(String.valueOf(gateway_fields.getRssi()));


    }

    @Override
    public int getItemCount() {
        return gateway_fields_list.size();
    }
public String dateToString( Date somedate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(somedate);

        return  date;
}

}
