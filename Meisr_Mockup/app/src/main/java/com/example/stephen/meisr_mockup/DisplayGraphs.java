package com.example.stephen.meisr_mockup;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by chasefeaster on 4/14/18.
 * Finished by kevin
 * This class takes the scores generated in DisplayModule for the module selected and puts them on a graph
 * Important note the scores are representative of the age of the child based what was entered in CreateAccount
 */

public class DisplayGraphs extends AppCompatActivity {

    String Jsonarray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_graphs);

        Intent myIntent = getIntent();
        final String mod = myIntent.getStringExtra("Module");
        final ArrayList<String> scorefull = myIntent.getStringArrayListExtra("Scorefull");
        final ArrayList<String> scoreage = myIntent.getStringArrayListExtra("Scoreage");
        final String token = myIntent.getStringExtra("Token");
        Jsonarray = myIntent.getStringExtra("JSONARRAY");
        System.out.println("DG Intents");
        System.out.println(Jsonarray);


        System.out.println("sCORE IN DISPLAY GRAPH");
        System.out.println(scorefull);
        System.out.println(scoreage);

        TextView title = findViewById(R.id.textView28);
        title.setText(mod);

        GraphView graph = findViewById(R.id.graph);

        int numvals = scorefull.size();
        int offset = 0;

        if(numvals >= 10){
            offset = 10;
        }else{
            offset = numvals;
        }
        DataPoint[] addsf = new DataPoint[offset];


        int counter = 0;
        for(int i =scorefull.size()-offset; i<scorefull.size(); i++) {
            System.out.println("i: ");
            System.out.println(i);


            float val = Float.parseFloat(scorefull.get(i))*100;
            double val2 = (counter + 1);
            addsf[counter] = new DataPoint(val2, val);
            //addsf[counter] = new DataPoint(counter, 1);
            counter++;
        }
        System.out.println(counter);

        DataPoint[] addsa = new DataPoint[offset];
        counter = 0;
        for(int i =scoreage.size()-offset; i<scoreage.size(); i++) {
            float val = Float.parseFloat(scoreage.get(i))*100;
            addsa[counter] = new DataPoint(counter, val);
            //addsa[counter] = new DataPoint(counter, 1);
            counter++;
        }
        System.out.println(counter);
        System.out.println(addsf);
        System.out.println(addsa);


        BarGraphSeries<DataPoint> s2 = new BarGraphSeries<>(addsa);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(addsf);
        series.setColor(Color.BLUE);
        series.setTitle("Up to FULL");
        graph.addSeries(series);

        s2.setColor(Color.RED);
        s2.setTitle("Up to Age");

        System.out.println("objects");
        System.out.println(series);
        System.out.println(s2);

        graph.addSeries(s2);

        //series.setSpacing(25);
        //s2.setSpacing(25);

        graph.getViewport().setMaxX(10);
        graph.getViewport().setMinX(0);
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMaxY(100);
        graph.getViewport().setMinY(0);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getGridLabelRenderer().setVerticalAxisTitle("Percent of 3s");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Last 10 Surveys");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        /*int titlelen = mod.length();
        graph.setTitle(mod);
        if(titlelen <=20) {
            graph.setTitleTextSize(100);
        }else{
            graph.setTitleTextSize(50);

        }*/

        final Button back = findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                Intent nextScreen = new Intent(view.getContext(), DisplayModule.class);
                nextScreen.putExtra("Token", token);
                System.out.println("DGBACK BUTTON");
                System.out.println(Jsonarray);
                nextScreen.putExtra("JSONARRAY", Jsonarray);

                startActivityForResult(nextScreen, 0);



                //query login information from database
            }
        });
    }


}
