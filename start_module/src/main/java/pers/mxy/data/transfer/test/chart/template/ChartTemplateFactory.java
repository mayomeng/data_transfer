package pers.mxy.data.transfer.test.chart.template;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/13.
 */
public class ChartTemplateFactory {
    public static ChartTemplate getWandashanCharTemplate(String imageUrl, String phone, String office, String store, String qrCodeUrl) throws IOException {
        String templateContent = FileUtils.readFileToString(new File("D:\\goProject\\data_transfer\\start_module\\src\\main\\java\\pers\\mxy\\data\\transfer\\test\\wandashan.json"),"utf-8").trim().replaceAll("\\r\\n", "");

        templateContent = templateContent.replace("{imageUrl}", imageUrl)
                .replace("{phone}", phone)
                .replace("{office}", office)
                .replace("{store}", store)
                .replace("{qrCodeUrl}", qrCodeUrl);
        System.out.println(templateContent);

        ChartTemplate chartTemplate = JSON.parseObject(templateContent, ChartTemplate.class);
        return chartTemplate;
    }
}
