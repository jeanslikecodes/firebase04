package jeancarlos.firebase04.Database;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import jeancarlos.firebase04.Adapter.AnuncioAdapter;
import jeancarlos.firebase04.Adapter.RecyclerViewOnClickListener;
import jeancarlos.firebase04.Model.Anuncio;

/**
 * Created by Jean Carlos on 11/06/2017.
 */

public class FirebaseHelper {


    Context context;
    RecyclerView listView;
    DatabaseReference rootRef;
    ArrayList<Anuncio> anuncioList= new ArrayList<>();
    AnuncioAdapter adapter;
    RecyclerViewOnClickListener recyclerViewOnClickListener;

    public FirebaseHelper(Context context, RecyclerViewOnClickListener recyclerViewOnClickListener, RecyclerView listView)
    {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
        this.context= context;
        this.listView= listView;

        rootRef= FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseHelper(Context context)
    {
        this.context = context;
        rootRef= FirebaseDatabase.getInstance().getReference("anuncio");
    }
    /*
    public  void saveData(String nome, String ingredientes, String modoPreparo, Integer tempoPreparo,
                                 Float nivelDificuldade, Integer quantidadePorcoes, String caminhoFoto) {

        String id = rootRef.push().getKey();
        Receita receita= new Receita(id, nome, ingredientes, modoPreparo, tempoPreparo,
                nivelDificuldade, quantidadePorcoes, caminhoFoto);
        rootRef.child("receita").push().setValue(receita);
    }

    public  void saveData(String nome, Float nivelDificuldade) {
        String id = rootRef.push().getKey();
        Receita receita= new Receita(id, nome, nivelDificuldade);
        rootRef.child(id).setValue(receita);

    }
    */
    public  void saveData(Anuncio anuncio) {
        if (anuncio.getId()!=null){
            rootRef.child(anuncio.getId()).setValue(anuncio);
        }
        else{

            String id = rootRef.push().getKey();
            anuncio.setId(id);
            rootRef.child(id).setValue(anuncio);
        }
    }

    public void refreshData()
    {
        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot){

        anuncioList.clear();

        for(DataSnapshot ds :dataSnapshot.getChildren()){
            Anuncio anuncio = new Anuncio();
            anuncio.setId(ds.getValue(Anuncio.class).getId());
            anuncio.setDescricao(ds.getValue(Anuncio.class).getDescricao());
            anuncio.setTamanho(ds.getValue(Anuncio.class).getTamanho());
            anuncio.setPreco(ds.getValue(Anuncio.class).getPreco());

            anuncioList.add(anuncio);
        }
        if(anuncioList.size()>0){
            adapter = new AnuncioAdapter(context, anuncioList);
            adapter.setRecyclerViewOnClickListener(recyclerViewOnClickListener);
            listView.setAdapter(adapter);
        }else {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }
    }


    public static void deleteData(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("anuncio").child(id);
        //removendo receita
        dR.removeValue();

    }

}
