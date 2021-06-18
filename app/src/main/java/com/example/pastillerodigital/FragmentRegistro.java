package com.example.pastillerodigital;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegistro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegistro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView mlsPastilla;
    private List<Modelo> mlist = new ArrayList<>();
    ListAdapter mAdaprer;

    public FragmentRegistro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegistro.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegistro newInstance(String param1, String param2) {
        FragmentRegistro fragment = new FragmentRegistro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_registro,container,false);

        mlsPastilla= view.findViewById(R.id.lvLista);
        mlist.removeAll(mlist);

        mlist.add(new Modelo(R.drawable.cita,"Añadir cita","Controle sus citas medicas y reciba recordatorios a tiempo"));
        mlist.add(new Modelo(R.drawable.pastilla,"Añadir medicamentos","Recordatorio para el consumo de medicamentos"));
        mAdaprer =new Adapter(getContext(),R.layout.plantilla,mlist);
        mlsPastilla.setAdapter(mAdaprer);

        //saber en que posicion se encuentra la lista para abrir las activities
        mlsPastilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent = new Intent(view.getContext(),RegistrarCita.class);
                    startActivity(intent);
                }
                else if (position==1)
                {
                    Intent intent = new Intent(view.getContext(),RegistraPastilla.class);
                    startActivity(intent);
                }


            }
        });

        return view;



    }



}