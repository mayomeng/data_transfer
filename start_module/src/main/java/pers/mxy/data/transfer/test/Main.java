package pers.mxy.data.transfer.test;

import org.thymeleaf.util.DateUtils;
import pers.mxy.data.transfer.test.chart.template.ChartTemplate;
import pers.mxy.data.transfer.test.chart.template.ChartTemplateFactory;
import pers.mxy.data.transfer.test.chart.tool.DrawTool;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        DrawTool.INSTANCE.draw(ChartTemplateFactory.getWandashanCharTemplate("C:\\\\Users\\\\Administrator\\\\Desktop\\\\temp\\\\test.png",
                "024-88319845", "XXXXX", "XXXX", "C:\\\\Users\\\\Administrator\\\\Desktop\\\\temp\\\\qrcode.png"));

/*        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-11-01 09:31:53");
        System.out.println(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(date).toString()));*/
    }
}
