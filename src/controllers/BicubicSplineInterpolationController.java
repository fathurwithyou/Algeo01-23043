package src.controllers;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.bicubicSplineInterpolation.BicubicSplineInterpolation;
import src.views.bicubicSplineInterpolation.BicubicSplineInterpolationView;

public class BicubicSplineInterpolationController {
    private BicubicSplineInterpolationView view = new BicubicSplineInterpolationView();
    private Matrix matrix;
    public Double x, y, result;
    public void main() {
        BicubicSplineInterpolation model = new BicubicSplineInterpolation();
        Tuple3<Matrix, Double, Double> input = view.getInput();
        matrix = input.getItem1(); 
        x = input.getItem2(); 
        y = input.getItem3();
        model.fit(matrix, x, y);
        result = model.predict(x, y);
        view.printPrediction(result);
    } 
}
