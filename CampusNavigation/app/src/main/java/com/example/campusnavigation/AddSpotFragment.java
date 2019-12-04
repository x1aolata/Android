package com.example.campusnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;

public class AddSpotFragment extends Fragment {
    // Add
    Button button_add_submit;
    EditText edittext_addfrag_name;
    EditText edittext_addfrag_number;
    EditText edittext_addfrag_about;
    EditText edittext_addfrag_longitude;
    EditText edittext_addfrag_latitude;
    NiceSpinner start_addRoute, end_addRoute;
    EditText edittext_adddistance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_spot_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button_add_submit = getActivity().findViewById(R.id.button_add_submit);
        edittext_addfrag_name = getActivity().findViewById(R.id.edittext_addfrag_name);
        edittext_addfrag_number = getActivity().findViewById(R.id.edittext_addfrag_number);
        edittext_addfrag_about = getActivity().findViewById(R.id.edittext_addfrag_about);
        edittext_addfrag_longitude = getActivity().findViewById(R.id.edittext_addfrag_longitude);
        edittext_addfrag_latitude = getActivity().findViewById(R.id.edittext_addfrag_latitude);
        edittext_adddistance = getActivity().findViewById(R.id.edittext_adddistance);
        button_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edittext_addfrag_name.getText().equals("") ||
                        edittext_addfrag_number.getText().toString().equals("") ||
                        edittext_addfrag_longitude.getText().toString().equals("") ||
                        edittext_addfrag_latitude.getText().toString().equals("") ||
                        edittext_addfrag_about.getText().toString().equals("")
                ) {
                    Toast.makeText(getActivity(), "请输入正确的信息", Toast.LENGTH_SHORT).show();

                } else {
                    if (Double.valueOf(edittext_addfrag_longitude.getText().toString()) < 70 ||
                            Double.valueOf(edittext_addfrag_longitude.getText().toString()) > 138 ||
                            Double.valueOf(edittext_addfrag_latitude.getText().toString()) > 53 ||
                            Double.valueOf(edittext_addfrag_latitude.getText().toString()) < 0) {
                        Toast.makeText(getActivity(), "这还是中国吗？经纬度可以通过坐标拾取系统查询。。。", Toast.LENGTH_SHORT).show();
                    } else {
                        Node node = new Node(edittext_addfrag_name.getText().toString(),
                                edittext_addfrag_number.getText().toString(),
                                Double.valueOf(edittext_addfrag_longitude.getText().toString()),
                                Double.valueOf(edittext_addfrag_latitude.getText().toString()),
                                edittext_addfrag_about.getText().toString());
                        Graph.getInstance().Nodes.add(node);

                        Toast.makeText(getActivity(), edittext_addfrag_name.getText().toString() + "地点添加成功", Toast.LENGTH_SHORT).show();

                        LinkedList<String> data = new LinkedList<>(Arrays.asList(Graph.getInstance().getPlace()));
                        start_addRoute.attachDataSource(data);
                        end_addRoute.attachDataSource(data);
                    }

                }

            }
        });


        start_addRoute = getActivity().findViewById(R.id.start_addRoute);
        end_addRoute = getActivity().findViewById(R.id.end_addRoute);
        LinkedList<String> data = new LinkedList<>(Arrays.asList(Graph.getInstance().getPlace()));
        start_addRoute.attachDataSource(data);
        end_addRoute.attachDataSource(data);


        Button button = getActivity().findViewById(R.id.button_addRoute);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edittext_adddistance.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "不输入距离是添加不了的哦！", Toast.LENGTH_SHORT).show();
                }else
                {
                    int distance = Integer.valueOf(edittext_adddistance.getText().toString());

                    Graph.getInstance().MAP[Graph.getInstance().getIndex(end_addRoute.getText().toString())][Graph.getInstance().getIndex(start_addRoute.getText().toString())] = distance;

                    Graph.getInstance().MAP[Graph.getInstance().getIndex(start_addRoute.getText().toString())][Graph.getInstance().getIndex(end_addRoute.getText().toString())] = distance;
                    Toast.makeText(getActivity(), "添加路径成功", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}
