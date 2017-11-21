package pers.mxy.data.transfer.test.chart.tool;

import pers.mxy.data.transfer.test.chart.template.ChartTemplate;
import pers.mxy.data.transfer.test.chart.template.GraphicsInfo;
import pers.mxy.data.transfer.test.chart.template.TextInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
public enum DrawTool {

    INSTANCE;

    private DrawTool() {}

    public void draw(ChartTemplate chartTemplate) throws IOException {
        BufferedImage image = getImage(chartTemplate.getWidth(), chartTemplate.getHeight(), chartTemplate.getGraphicsInfoList());
        drawImage(image, chartTemplate.getChartType(), chartTemplate.getChartName());
    }

    private BufferedImage getImage(int width, int height, List<GraphicsInfo> graphicsInfoList) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (GraphicsInfo graphicsInfo : graphicsInfoList) {
            formatGraphics(image.createGraphics(), graphicsInfo);
        }

        return image;
    }

    private void formatGraphics(Graphics graphics, GraphicsInfo graphicsInfo) throws IOException {
        if (graphicsInfo.getColor() != null && graphicsInfo.getColor().length > 0) {
            graphics.setColor(new Color(graphicsInfo.getColor()[0], graphicsInfo.getColor()[1], graphicsInfo.getColor()[2]));
        }
        graphics.fillRect(graphicsInfo.getX(), graphicsInfo.getY(),
                graphicsInfo.getWidth(), graphicsInfo.getHeight());

        if (graphicsInfo.getTextInfoList() != null &&graphicsInfo.getTextInfoList().size() > 0) {
            formatText(graphics, graphicsInfo.getTextInfoList());
        }
        if (graphicsInfo.getImageUrl() != null && graphicsInfo.getImageUrl().length() > 0) {
            formatImage(graphics, graphicsInfo);
        }
    }

    private void formatText(Graphics graphics, List<TextInfo> textInfoList) {
        for (TextInfo textInfo : textInfoList) {
            graphics.setColor(new Color(textInfo.getFontColor()[0], textInfo.getFontColor()[1],
                    textInfo.getFontColor()[2]));
            Font font = new Font(textInfo.getFontType(), textInfo.getFontStyle(), textInfo.getFontSize());
            graphics.setFont(font);
            graphics.drawString(textInfo.getContent(), textInfo.getFontX(), textInfo.getFontY());
        }
    }

    private void formatImage(Graphics graphics, GraphicsInfo graphicsInfo) throws IOException {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(graphicsInfo.getImageUrl()));
            if(bimg!=null){
                graphics.drawImage(bimg, graphicsInfo.getX(), graphicsInfo.getY(),
                        graphicsInfo.getWidth(), graphicsInfo.getHeight(), null);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            graphics.dispose();
        }
    }

    private void drawImage(BufferedImage image, String chartType, String chartName) throws IOException {
        ImageIO.write(image, chartType, new File(chartName));
    }
}
