package main.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.function.Function;
import main.integralCalculator.RunnableIntegralCalculator;

public class Controller {
    @FXML
    private TextField countN, countThreads;
    @FXML
    private TextFlow outputText;
    private double totalSum = 0;
    private int finished = 0;

    @FXML
    public void controller(){
        Function function = new Function();

        totalSum = 0;
        finished = 0;


        double a = 0.0;
        double b = 10.0;

        int n = Integer.parseInt(countN.getText());
        int numberOfThreads = Integer.parseInt(countThreads.getText());

        long startTime = System.currentTimeMillis();

        double delta = (b - a) / numberOfThreads;
        for(int i = 0; i < numberOfThreads; i++){
            double ai = a + i * delta;
            double bi = ai + delta;
            int ni = n / numberOfThreads;
            Thread.startVirtualThread(new RunnableIntegralCalculator(ai, bi, ni, function, this));
        }

        try {
            synchronized (this){
                while(finished < numberOfThreads){
                    wait();
                }
            }
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        printResult(startTime, endTime);
    }

    void printResult(long startTime, long endTime){
        outputText.getChildren().clear();
        Text resultText = new Text("Result = " + totalSum + "\n");
        Text timeText = new Text("Time = " + (endTime - startTime) + "ms\n");
        outputText.getChildren().addAll(resultText, timeText);
    }

    public synchronized void sendResult(double result){
        totalSum += result;
        finished++;
        notify();
    }
}
