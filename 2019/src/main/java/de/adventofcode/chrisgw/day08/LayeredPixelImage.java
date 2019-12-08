package de.adventofcode.chrisgw.day08;

import lombok.Data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Data
public class LayeredPixelImage {

    private final int width;
    private final int height;
    private final int[] imageData;


    public static LayeredPixelImage parseLayeredPixelImageStr(int width, int height, String imageDataStr) {
        int[] imageData = imageDataStr.chars().map(numberChar -> numberChar - '0').toArray();
        return new LayeredPixelImage(width, height, imageData);
    }


    public Stream<LayeredPixelImage> imageLayers() {
        return IntStream.range(0, imageLayerCount()).mapToObj(this::getImageLayer);
    }

    public LayeredPixelImage getImageLayer(int layerIndex) {
        int[] imageLayerData = imageLayerData(layerIndex);
        return new LayeredPixelImage(width, height, imageLayerData);
    }

    private int[] imageLayerData(int layerIndex) {
        int imageLayerSize = imageLayerSize();
        int fromIndex = imageLayerSize * layerIndex;
        int toIndex = fromIndex + imageLayerSize;
        return Arrays.copyOfRange(imageData, fromIndex, toIndex);
    }


    public int imageDataAt(int row, int column) {
        int index = (row * width) + column;
        return imageData[index];
    }

    public int pixelColorCount(int pixelColor) {
        return (int) Arrays.stream(imageData).filter(value -> value == pixelColor).count();
    }


    public int imageLayerCount() {
        return imageData.length / imageLayerSize();
    }

    public int imageLayerSize() {
        return height * width;
    }

    public boolean isMultyLayeredImage() {
        return imageLayerCount() > 1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String leftPadding = "           ";
        Iterator<LayeredPixelImage> pixelImageIterator = imageLayers().iterator();
        for (int layerIndey = 1; pixelImageIterator.hasNext(); layerIndey++) {
            sb.append("Layer ").append(String.format("%3d", layerIndey)).append(": ");
            LayeredPixelImage layeredPixelImage = pixelImageIterator.next();
            for (int row = 0; row < layeredPixelImage.getHeight(); row++) {
                for (int column = 0; column < layeredPixelImage.getWidth(); column++) {
                    int pixelData = layeredPixelImage.imageDataAt(row, column);
                    sb.append(pixelData);
                }
                sb.append("\n").append(leftPadding);
            }
            sb.setLength(sb.length() - leftPadding.length());
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


}
