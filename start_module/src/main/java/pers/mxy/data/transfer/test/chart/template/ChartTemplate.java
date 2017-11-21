package pers.mxy.data.transfer.test.chart.template;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/9.
 */
public class ChartTemplate {

    private ChartTemplate(){}

    private String chartName;
    private String chartType;
    private int width;
    private int height;
    private List<GraphicsInfo> graphicsInfoList;

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
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

    public List<GraphicsInfo> getGraphicsInfoList() {
        return graphicsInfoList;
    }

    public void setGraphicsInfoList(List<GraphicsInfo> graphicsInfoList) {
        this.graphicsInfoList = graphicsInfoList;
    }
}
