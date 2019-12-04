package com.example.campusnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;

public class AlterSpotFragment extends Fragment {

    Button button_alterRoute;
    EditText edittext_alterfrag_name;
    EditText edittext_alterfrag_number;
    EditText edittext_alterfrag_about;
    EditText edittext_alterfrag_longitude;
    EditText edittext_alterfrag_latitude;
    NiceSpinner alter_spot;
    LinkedList<String> data;
    Graph graph = Graph.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alter_spot_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        alter_spot = getActivity().findViewById(R.id.alter_spot);
        edittext_alterfrag_name = getActivity().findViewById(R.id.edittext_alterfrag_name);
        edittext_alterfrag_number = getActivity().findViewById(R.id.edittext_alterfrag_number);
        edittext_alterfrag_about = getActivity().findViewById(R.id.edittext_alterfrag_about);
        edittext_alterfrag_longitude = getActivity().findViewById(R.id.edittext_alterfrag_longitude);
        edittext_alterfrag_latitude = getActivity().findViewById(R.id.edittext_alterfrag_latitude);


        data = new LinkedList<>(Arrays.asList(Graph.getInstance().getPlace()));
        alter_spot.attachDataSource(data);

        alter_spot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(), data.get(i), Toast.LENGTH_SHORT).show();

                edittext_alterfrag_name.setText(graph.Nodes.get(graph.getIndex(data.get(i))).getName());
                edittext_alterfrag_number.setText(graph.Nodes.get(graph.getIndex(data.get(i))).getNumber());
                edittext_alterfrag_about.setText(graph.Nodes.get(graph.getIndex(data.get(i))).getAbout());
                edittext_alterfrag_longitude.setText("" + graph.Nodes.get(graph.getIndex(data.get(i))).getLongitude());
                edittext_alterfrag_latitude.setText("" + graph.Nodes.get(graph.getIndex(data.get(i))).getLatitude());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), alter_spot.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        button_alterRoute = getActivity().findViewById(R.id.button_alterRoute);
        button_alterRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Node node = graph.Nodes.get(graph.getIndex(alter_spot.getText().toString()));
                node.setName(edittext_alterfrag_name.getText().toString());
                node.setNumber(edittext_alterfrag_number.getText().toString());
                node.setAbout(edittext_alterfrag_about.getText().toString());
                node.setLongitude(Double.valueOf(edittext_alterfrag_longitude.getText().toString()));
                node.setLatitude(Double.valueOf(edittext_alterfrag_latitude.getText().toString()));

            }
        });


    }
}
