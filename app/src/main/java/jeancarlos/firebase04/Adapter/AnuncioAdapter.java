package jeancarlos.firebase04.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jeancarlos.firebase04.Model.Anuncio;
import jeancarlos.firebase04.R;

import static java.lang.Float.parseFloat;

/**
 * Created by Jean Carlos on 12/06/2017.
 */

public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.MyViewHolder>  {

    private List<Anuncio> anuncioList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public AnuncioAdapter(Context context, List<Anuncio> anuncioList) {
        this.anuncioList = anuncioList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Anuncio getAnuncio(int position){
        return anuncioList.get(position);
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener){
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_lista, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescricao.setText(anuncioList.get(position).getDescricao());
        holder.tvPreco.setText(String.valueOf(anuncioList.get(position).getPreco()));
    }

    @Override
    public int getItemCount() {
        return anuncioList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvDescricao, tvPreco;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvDescricao = (TextView) itemView.findViewById(R.id.item_descricao);
            tvPreco = (TextView) itemView.findViewById(R.id.item_preco);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListener != null) {
                recyclerViewOnClickListener.onClickListener(v, getPosition());
            }
        }
    }

    public void remove(Anuncio anuncio){

    }
}
