package jeancarlos.firebase04;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.renderscript.Float3;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import jeancarlos.firebase04.Database.FirebaseHelper;
import jeancarlos.firebase04.Model.Anuncio;

public class NewAnuncioFragment extends Fragment {

    EditText etDescricao, etTamanho, etPreco;
    Button btnSalvar;
    Anuncio anuncio;

    public NewAnuncioFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_new_anuncio, container, false);

        etDescricao = (EditText) view.findViewById(R.id.et_descricao);
        etTamanho = (EditText) view.findViewById(R.id.et_tamanho);
        etPreco = (EditText) view.findViewById(R.id.et_preco);
        btnSalvar = (Button) view.findViewById(R.id.btn_salvar);

        anuncio = new Anuncio();

        Intent intent = getActivity().getIntent();
        anuncio = (Anuncio) intent.getSerializableExtra("goAnuncio");
        if (anuncio != null) {
            etDescricao.setText(anuncio.getDescricao());
            etTamanho.setText(anuncio.getTamanho());
            etPreco.setText(String.valueOf(anuncio.getPreco()));
            Button btnExcluir = (Button) view.findViewById(R.id.btn_excluir);
            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseHelper.deleteData(anuncio.getId());
                    getActivity().finish();
                }
            });


        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseHelper firebaseHelper = new FirebaseHelper(getContext());

                if (anuncio == null || anuncio.getId() == null) {
                    anuncio = new Anuncio(etDescricao.getText().toString(), etTamanho.getText().toString(), Float.parseFloat(etPreco.getText().toString()));
                    firebaseHelper.saveData(anuncio);
                }
                else{

                anuncio.setDescricao(etDescricao.getText().toString());
                anuncio.setTamanho(etTamanho.getText().toString());
                anuncio.setPreco(Float.parseFloat(etPreco.getText().toString()));



                firebaseHelper.saveData(anuncio);
                }
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
