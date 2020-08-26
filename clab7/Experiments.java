package src.clab7;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;


import static src.clab7.BST.AverageDepth;
import static src.clab7.RandomGenerator.getRandomInt;
import static src.clab7.ExperimentHelper.optimalAverageDepth;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> b= new BST<>();
        List<Double> OPTValues = new ArrayList<>();
        List<Double> MyValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i = 1; i<=5000;i++){
            b.add(getRandomInt(i));
            xValues.add(i);
            OPTValues.add(optimalAverageDepth(i));
            MyValues.add(AverageDepth(b));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("optimal", xValues, OPTValues);
        chart.addSeries("my tree", xValues, MyValues);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
        BST<Integer> b= new BST<>();
        List<Double> OPTValues = new ArrayList<>();
        List<Double> MyValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i = 1; i<=200;i++){
            b.add(getRandomInt(i));
        }
        OPTValues.add(optimalAverageDepth(b.size()));
        MyValues.add(AverageDepth(b));
        xValues.add(0);
        int tmp;
        for(int t=201;t<5000;t++){
            ExperimentHelper.changeInt(b);
            tmp=getRandomInt(t);
            while(true){
                if(!b.contains(tmp)){
                    b.add(tmp);
                    break;
                }
                tmp=getRandomInt(t);
            }
            xValues.add(t-200);
            OPTValues.add(optimalAverageDepth(b.size()));
            MyValues.add(AverageDepth(b));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("optimal", xValues, OPTValues);
        chart.addSeries("my tree", xValues, MyValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> b= new BST<>();
        List<Double> OPTValues = new ArrayList<>();
        List<Double> MyValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int i = 1; i<=200;i++){
            b.add(getRandomInt(i));
        }
        OPTValues.add(optimalAverageDepth(b.size()));
        MyValues.add(AverageDepth(b));
        xValues.add(0);
        int tmp;
        for(int t=201;t<400;t++){
            ExperimentHelper.randomInt(b);
            tmp=getRandomInt(t);
            while(true){
                if(!b.contains(tmp)){
                    b.add(tmp);
                    break;
                }
                tmp=getRandomInt(t);
            }
            xValues.add(t-201);
            OPTValues.add(optimalAverageDepth(b.size()));
            MyValues.add(AverageDepth(b));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("optimal", xValues, OPTValues);
        chart.addSeries("my tree", xValues, MyValues);

        new SwingWrapper(chart).displayChart();

    }

    public static void main(String[] args) {
        experiment1();//BST does not converge to 14, I cannot figure out the reason.
        experiment2();
        experiment3();
    }
}
