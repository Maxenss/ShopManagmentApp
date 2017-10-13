package com.company.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Report {
    ArrayList<Sale> mSaleArrayList;

    public Report(ArrayList<Sale> mSaleArrayList) {
        this.mSaleArrayList = mSaleArrayList;
        writeToFile();
    }

    public Report() {
        mSaleArrayList = new ArrayList<>();
        writeToFile();
    }

    public ArrayList<Sale> getSaleArrayList() {
        return mSaleArrayList;
    }

    public void setSaleArrayList(ArrayList<Sale> mSaleArrayList) {
        this.mSaleArrayList = mSaleArrayList;
    }

    public String toJSON() {
        String resultJSON = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            resultJSON = mapper.writeValueAsString(mSaleArrayList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(resultJSON);
        return resultJSON;
    }

    public void writeToFile(){
        try(FileWriter writer = new FileWriter("C:\\reports\\report.txt", false))
        {
            // запись всей строки
            String text = toJSON();
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
