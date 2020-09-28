package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.sossolution.serviceonway.Adapter.CustomExpandableListAdapter;
import com.sossolution.serviceonway.Class.ExpandableListDataPump;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Terms_Condition_Activity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms__condition_);
        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(i) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(i) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(i)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(i)).get(
                                i1), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }
}
