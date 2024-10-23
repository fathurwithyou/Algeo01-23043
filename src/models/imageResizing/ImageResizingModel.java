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
        
        Matrix redMatrix = new Matrix(width, height);
        Matrix greenMatrix = new Matrix(width, height);
        Matrix blueMatrix = new Matrix(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;
                
                redMatrix.set(x, y, (double) red);
                greenMatrix.set(x, y, (double) green);
                blueMatrix.set(x, y, (double) blue);
            }
        }
        return new Tuple3<>(redMatrix, greenMatrix, blueMatrix);
    }

    public BufferedImage convertMatrixToImage(Tuple3<Matrix, Matrix, Matrix> matrices) {
        Matrix redMatrix = matrices.getItem1();
        Matrix greenMatrix = matrices.getItem2();
        Matrix blueMatrix = matrices.getItem3();

        int width = redMatrix.getRowCount();
        int height = redMatrix.getColumnCount();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int red = Math.max(0, Math.min(255, redMatrix.get(x, y).intValue()));
                int green = Math.max(0, Math.min(255, greenMatrix.get(x, y).intValue()));
                int blue = Math.max(0, Math.min(255, blueMatrix.get(x, y).intValue()));

                int rgb = (red << 16) | (green << 8) | blue;
                outputImage.setRGB(x, y, rgb);
            }
        }

        return outputImage;
    }

    public BufferedImage resizeImage(BufferedImage inputImage, int newWidth, int newHeight) {
        Tuple3<Matrix, Matrix, Matrix> inputMatrices = convertImageToMatrix(inputImage);  
        
        Matrix redOutputMatrix = new Matrix(newWidth, newHeight);
        Matrix greenOutputMatrix = new Matrix(newWidth, newHeight);
        Matrix blueOutputMatrix = new Matrix(newWidth, newHeight);
        
        double scaleX = (double) inputMatrices.getItem1().getRowCount() / newWidth;
        double scaleY = (double) inputMatrices.getItem1().getColumnCount() / newHeight;
        
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                double srcX = x * scaleX;
                double srcY = y * scaleY;
    
                spline.fit(inputMatrices.getItem1(), srcX, srcY);
                double redInterpolatedValue = Math.max(0, Math.min(255, spline.predict(srcX - Math.floor(srcX), srcY - Math.floor(srcY))));
                redOutputMatrix.set(x, y, redInterpolatedValue);

                spline.fit(inputMatrices.getItem2(), srcX, srcY);
                double greenInterpolatedValue = Math.max(0, Math.min(255, spline.predict(srcX - Math.floor(srcX), srcY - Math.floor(srcY))));
                greenOutputMatrix.set(x, y, greenInterpolatedValue);

                spline.fit(inputMatrices.getItem3(), srcX, srcY);
                double blueInterpolatedValue = Math.max(0, Math.min(255, spline.predict(srcX - Math.floor(srcX), srcY - Math.floor(srcY))));
                blueOutputMatrix.set(x, y, blueInterpolatedValue);
            }
        }
        return convertMatrixToImage(new Tuple3<>(redOutputMatrix, greenOutputMatrix, blueOutputMatrix));
    }
}