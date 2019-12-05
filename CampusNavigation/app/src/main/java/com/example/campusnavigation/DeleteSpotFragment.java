package com.example.campusnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;

public class DeleteSpotFragment extends Fragment {


    Button button_delete;
    NiceSpinner delete_spot;
    Graph graph = Graph.getInstance();
    LinkedList<String> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_spot_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        delete_spot = getActivity().findViewById(R.id.delete_spot);
        data = new LinkedList<>(Arrays.asList(Graph.getInstance().getPlace()));
        delete_spot.attachDataSource(data);

        button_delete = getActivity().findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (graph.Nodes.size() < 3) {
                    Toast.makeText(getActivity(), "别删了，再删就没了啊，哥哥！", Toast.LENGTH_SHORT).show();
                } else {
                    graph.DeleteNode(delete_spot.getText().toString());
                    Toast.makeText(getActivity(), "\"" + delete_spot.getText().toString() + "\"" + "已删除", Toast.LENGTH_SHORT).show();
                    data = new LinkedList<>(Arrays.asList(Graph.getInstance().getPlace()));
                    delete_spot.attachDataSource(data);
                }
            }
        });

    }


}
