package pers.mxy.data.transfer.test.chart.template;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
public class GraphicsInfo {
    private int x;
    private int y;
    private int width;
    private int height;
    private int[] color;
    private List<TextInfo> textInfoList;
    private String imageUrl;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public List<TextInfo> getTextInfoList() {
        return textInfoList;
    }

    public void setTextInfoList(List<TextInfo> textInfoList) {
        this.textInfoList = textInfoList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
