package jeancarlos.firebase04;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import jeancarlos.firebase04.Adapter.AnuncioAdapter;
import jeancarlos.firebase04.Adapter.RecyclerViewOnClickListener;
import jeancarlos.firebase04.Database.FirebaseHelper;
import jeancarlos.firebase04.Model.Anuncio;


public class AnuncioFragment extends Fragment implements RecyclerViewOnClickListener {

    private RecyclerView anuncioListView;
    private List<Anuncio> anuncioList;

    FirebaseHelper firebaseHelper;

    public AnuncioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anuncio, container, false);
        anuncioListView = (RecyclerView) view.findViewById(R.id.rv_lista);
        anuncioListView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        anuncioListView.setLayoutManager(llm);

        Button btnNovaReceita = (Button) view.findViewById(R.id.btnCadastrar);
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewAnuncioActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregaDados();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void carregaDados(){
        anuncioList = new ArrayList<Anuncio>();
        //anuncioList.add(new Anuncio("qwerwer", "12", (float) 4.0));

        AnuncioAdapter adapter = new AnuncioAdapter(getActivity(), anuncioList);
        adapter.setRecyclerViewOnClickListener(this);
        /* O LayoutManager é responsável por medir e posicionar as visualizações de itens dentro do
        Recycler View.
        Essa forma de trabalhar funciona de forma um pouco diferente quando estamos utilizando o
        Recycler View dentro de um fragmento. */
        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        anuncioListView.setLayoutManager(mManager);
        anuncioListView.setItemAnimator(new DefaultItemAnimator());
        anuncioListView.setAdapter(adapter);

        firebaseHelper = new FirebaseHelper(getActivity(), this, anuncioListView);
        firebaseHelper.refreshData();
        //firebaseHelper.adapter.setRecyclerViewOnClickListener(this);
    }

    @Override
    public void onClickListener(View view, int position) {
        AnuncioAdapter adapter = (AnuncioAdapter) anuncioListView.getAdapter();
        Anuncio anuncio = adapter.getAnuncio(position);

        Intent intent = new Intent(getActivity(), NewAnuncioActivity.class);
        intent.putExtra("goAnuncio", anuncio);
        startActivity(intent);
    }
}
