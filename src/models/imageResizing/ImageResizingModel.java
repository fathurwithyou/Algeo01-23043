package src.models.imageResizing;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.bicubicSplineInterpolation.BicubicSplineInterpolation;
import java.awt.image.BufferedImage;

public class ImageResizingModel {
    
    private BicubicSplineInterpolation spline = new BicubicSplineInterpolation();

    public Tuple3<Matrix, Matrix, Matrix> convertImageToMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        Matrix redMatrix = new Matrix(height, width);
        Matrix greenMatrix = new Matrix(height, width);
        Matrix blueMatrix = new Matrix(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;
                
                redMatrix.set(i, j, (double) red);
                greenMatrix.set(i, j, (double) green);
                blueMatrix.set(i, j, (double) blue);
            }
        }
        return new Tuple3<>(redMatrix, greenMatrix, blueMatrix);
    }

    public BufferedImage convertMatrixToImage(Tuple3<Matrix, Matrix, Matrix> matrices) {
        Matrix redMatrix = matrices.getItem1();
        Matrix greenMatrix = matrices.getItem2();
        Matrix blueMatrix = matrices.getItem3();

        int height = redMatrix.getRowCount();
        int width = redMatrix.getColumnCount();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = Math.max(0, Math.min(255, redMatrix.get(i, j).intValue()));
                int green = Math.max(0, Math.min(255, greenMatrix.get(i, j).intValue()));
                int blue = Math.max(0, Math.min(255, blueMatrix.get(i, j).intValue()));

                int rgb = (red << 16) | (green << 8) | blue;
                outputImage.setRGB(j, i, rgb);
            }
        }

        return outputImage;
    }

    public BufferedImage resizeImage(BufferedImage inputImage, int newWidth, int newHeight) {
        Tuple3<Matrix, Matrix, Matrix> inputMatrices = convertImageToMatrix(inputImage);  
        
        Matrix redOutputMatrix = new Matrix(newHeight, newWidth);
        Matrix greenOutputMatrix = new Matrix(newHeight, newWidth);
        Matrix blueOutputMatrix = new Matrix(newHeight, newWidth);
        
        double scaleX = (double) inputMatrices.getItem1().getColumnCount() / newWidth;
        double scaleY = (double) inputMatrices.getItem1().getRowCount() / newHeight;
        
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                double x = j * scaleX;
                double y = i * scaleY;
    
                spline.fit(inputMatrices.getItem1(), x, y);
                double redInterpolatedValue = Math.max(0, Math.min(255, spline.predict(x - Math.floor(x), y - Math.floor(y))));
                redOutputMatrix.set(i, j, redInterpolatedValue);

                spline.fit(inputMatrices.getItem2(), x, y);
                double greenInterpolatedValue = Math.max(0, Math.min(255, spline.predict(x - Math.floor(x), y - Math.floor(y))));
                greenOutputMatrix.set(i, j, greenInterpolatedValue);

                spline.fit(inputMatrices.getItem3(), x, y);
                double blueInterpolatedValue = Math.max(0, Math.min(255, spline.predict(x - Math.floor(x), y - Math.floor(y))));
                blueOutputMatrix.set(i, j, blueInterpolatedValue);
            }
        }
        return convertMatrixToImage(new Tuple3<>(redOutputMatrix, greenOutputMatrix, blueOutputMatrix));
    }
}
