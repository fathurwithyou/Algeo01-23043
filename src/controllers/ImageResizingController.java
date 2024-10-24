package src.controllers;

import src.views.Pprint;
import src.views.imageResizing.ImageResizingViews;
import src.models.imageResizing.ImageResizingModel;
import java.awt.image.BufferedImage;

public class ImageResizingController {

    private ImageResizingViews view = new ImageResizingViews();
    private ImageResizingModel model = new ImageResizingModel();
    private Pprint pprint = new Pprint();

    public void main() {
        int choice = view.getChoice(); 

        if (choice == 1) {
            BufferedImage inputImage = view.getInputFromFile();

            if (inputImage != null) {
                int newWidth = view.getNewWidth();
                int newHeight = view.getNewHeight();
                BufferedImage processedImage = model.resizeImage(inputImage, newWidth, newHeight);
                
                pprint.showResult();
                view.saveImageToFile(processedImage, "gambar_hasil");
                view.showCompletionMessage();
            }
        } else {
            pprint.thanks();
        }
    }
}
