package com.kbe.application.services.csv;


import com.kbe.application.models.Product;
import com.kbe.application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CSVExportService {

    @Autowired
    private ProductService productService;

    public void exportCsvToFolder(){
        String path = System.getProperty("java.io.tmpdir");
        File file = new File(path, "products.csv");

        List<Product> products = productService.getProducts();

        try{
            FileWriter fileWriter = new FileWriter(file);
            ICsvBeanWriter csvWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"productId", "name", "priceEuro", "manufacturer", "displaySizeInches", "color",
                                  "refreshRateHz", "weightKg", "reactionTimeMs", "displayInterface", "resolution"};
            String[] nameMapping = {"productId", "name", "priceEuro", "manufacturer", "displaySizeInches", "color",
                                    "refreshRateHz", "weightKg", "reactionTimeMs", "displayInterface", "resolution"};

            csvWriter.writeHeader(csvHeader);

            for(Product product : products){
                csvWriter.write(product, nameMapping);
            }
            csvWriter.close();
        }catch (IOException e){
            e.getStackTrace();
        }
    }
}