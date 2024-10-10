package src.controllers;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.bicubicSplineInterpolation.BicubicSplineInterpolation;
import src.views.bicubicSplineInterpolation.BicubicSplineInterpolationView;

public class BicubicSplineInterpolationController {
    private BicubicSplineInterpolationView view = new BicubicSplineInterpolationView();
    private BicubicSplineInterpolation model = new BicubicSplineInterpolation();

    public void main() {
            Tuple3<Matrix, Double, Double> input = view.getInput();

            Matrix Z = input.getItem1(); 
            Double a = input.getItem2(); 
            Double b = input.getItem3();

            model.fit(Z);
            Double result = model.predict(a, b);

            view.printPrediction(result);
        } 
    }
