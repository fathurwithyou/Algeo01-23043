package src.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.bicubicSplineInterpolation.BicubicSplineInterpolation;
import src.views.Menu;
import src.views.bicubicSplineInterpolation.BicubicSplineInterpolationView;
import src.views.Pprint;

public class BicubicSplineInterpolationController {
    private BicubicSplineInterpolationView view = new BicubicSplineInterpolationView();
    private Pprint pprint = new Pprint();
    private Matrix matrix;
    private Tuple3<Matrix, Double, Double> input;
    public Double x, y, result;

    public void getInput() {
        view.showHeader();
        int method = new Menu().getMethod();
        if (method == 1) {
            input = view.getInputFromFile();
        } else {
            input = view.getInput();
        }
        matrix = input.getItem1();
        x = input.getItem2();
        y = input.getItem3();
    }

    public void main() {
        BicubicSplineInterpolation model = new BicubicSplineInterpolation();
        getInput();
        model.fit(matrix, input.getItem2(), input.getItem3());
        result = model.predict(x, y);
        pprint.showResult();
        view.printPrediction(result, x, y);
    }
}
