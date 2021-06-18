package com.example.pastillerodigital;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Entity;
import androidx.room.Index;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPromedio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPromedio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LineChart lineChart;


    public FragmentPromedio() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPromedio.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPromedio newInstance(String param1, String param2) {
        FragmentPromedio fragment = new FragmentPromedio();
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
        //instanciamiento de una vista para el fragments
        View view = inflater.inflate(R.layout.fragment_promedio, container, false);
        lineChart = view.findViewById(R.id.line_chart);
        //pie da pagina para diferenciar los datos de la grafica
        LineDataSet lineDataSet = new LineDataSet(dataValues1(),"Data set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        //almacena los datos de la grafica
        dataSets.add(lineDataSet);

        LineData data= new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
        return view;

    }
    private  ArrayList<Entry> dataValues1()
    {
        //llenar coordenadas donde iran los datos
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        {
            dataVals.add(new Entry(0, 20));
            dataVals.add(new Entry(1, 24));
            dataVals.add(new Entry(2, 2));
            dataVals.add(new Entry(3, 10));
            dataVals.add(new Entry(4, 28));

            return dataVals;

        }
    }
}