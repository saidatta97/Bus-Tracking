package com.example.bustracking;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

//import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>{

    Context context;
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, final int position, @NonNull final model model)
    {
        holder.busNo.setText(model.getBusNumber());
        holder.startLocation.setText(model.getBusLocation());
        holder.destination.setText(model.getBusDestination());
        holder.startTime.setText(model.getBusStartTime());
        holder.reachTime.setText(model.getBusReachTime());
        // Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        context=holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("RestrictedApi")
                Intent intent=new Intent(getApplicationContext(),eachBusLoc.class);
                intent.putExtra("busNo",model.getBusNumber());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.busNo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),eachBusLoc.class);
                intent.putExtra("busNo",model.getBusNumber());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

//                Toast.makeText(getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT).show();

//                context.startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.busrow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
//        CircleImageView img;
        TextView busNo,startLocation,destination,startTime,reachTime;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            //img=(CircleImageView)itemView.findViewById(R.id.img1);
            busNo=(TextView)itemView.findViewById(R.id.busNo1);
            startLocation=(TextView)itemView.findViewById(R.id.startLocation1);
            destination=(TextView)itemView.findViewById(R.id.destination1);
            startTime=(TextView)itemView.findViewById(R.id.startTime1);
            reachTime=(TextView)itemView.findViewById(R.id.reachTime1);
        }
    }
}
