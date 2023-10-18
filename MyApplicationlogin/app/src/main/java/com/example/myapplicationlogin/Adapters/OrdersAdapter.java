package com.example.myapplicationlogin.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.example.myapplicationlogin.DetailsActivity;
import com.example.myapplicationlogin.Model.MainModel;
import com.example.myapplicationlogin.Model.OrdersModel;
import com.example.myapplicationlogin.R;

import java.util.ArrayList;

import Sql.DbHelperfood;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewHolder> {
    ArrayList<OrdersModel> list ;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @androidx.annotation.NonNull
    @Override
    public viewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample , parent,false);
       return  new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull viewHolder holder, int position) {

        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNum.setText(model.getOrderNumber());
        holder.price.setText((model.getPrice()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                String orderNumber = model.getOrderNumber().trim(); // Remove leading/trailing spaces
                intent.putExtra("_itemid", Integer.parseInt(orderNumber));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Iten")
                        .setMessage("Are you sure to delete this item?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                DbHelperfood dbHelperfood = new DbHelperfood(context);
                                if(dbHelperfood.deleteOreder(model.getOrderNumber())>0){
                                    Toast.makeText(context,"Deleted.",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Error.",Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        }).show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
       ImageView orderImage;
       TextView soldItemName, orderNum, price;


        public  viewHolder(@NonNull View itemView){
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderimg);
            soldItemName = itemView.findViewById(R.id.orderName);
            orderNum = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.orderPrice1);
        }
    }
}
