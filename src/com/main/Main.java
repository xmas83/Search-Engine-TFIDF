package com.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    static ArrayList<String> dataList;
    static String searchText;
    static ArrayList<Double> tf_list;
    static double idf;
    static ArrayList<Double> tfidf_list;


    public static void main(String[] args) throws FileNotFoundException, IOException {

        dataList = new ArrayList<>();

        File folder = new File("document ");
        File[] listFiles = folder.listFiles();

        // read documents
        for (File file : listFiles) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tempString = "";
            String st = "";
            while ((st = br.readLine()) != null) {
                tempString += st + " ";
            }
            dataList.add(tempString);
        }

        // read search text
        while (true) {
            System.out.print("Please enter your text: ");
            Scanner sc = new Scanner(System.in);
            searchText = sc.nextLine();

            if (searchText.equals("exit") || searchText.equals("quit"))
                break;

            // tf
            tf_list = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).contains(searchText)) {
                    String[] temp = dataList.get(i).split(" ");
                    int totalCount = temp.length;
                    int currentCount = 0;
                    for (int j = 0; j < temp.length; j++)
                        if (temp[j].contains(searchText))
                            currentCount++;
                    tf_list.add(currentCount * 1.0 / totalCount);
                } else {
                    tf_list.add(0.0);
                }
            }

            // idf
            int count = 0;
            for (int i = 0; i < dataList.size(); i++)
                if (dataList.get(i).contains(searchText))
                    count++;
            idf = Math.log(0.5 + dataList.size() * 1.0 / count);

            // tf * idf
            ArrayList<Double> tempList = new ArrayList<>();
            tfidf_list = new ArrayList<>();
            for (int i = 0; i < tf_list.size(); i++) {
                tfidf_list.add(tf_list.get(i) * idf);
                tempList.add(tf_list.get(i) * idf);
            }

            // sort
            Collections.sort(tempList);
            Collections.reverse(tempList);

            // search process
            ArrayList<Integer> resultList = new ArrayList<>();
            if (idf == Double.POSITIVE_INFINITY)
                System.out.println("There is no document contains " + searchText);
            else {
                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i) != 0) {
                        for (int j = 0; j < tfidf_list.size(); j++) {
                            if (tfidf_list.get(j).equals(tempList.get(i)) && !resultList.contains(j)) {
                                System.out.print(listFiles[j].getName() + ", ");
                                resultList.add(j);
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}

