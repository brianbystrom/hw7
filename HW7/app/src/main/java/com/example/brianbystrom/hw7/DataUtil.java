/*
Assignment #: In Class 05
File Name: DataUtil.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.hw7;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class DataUtil {

    static public class DataSAXParser extends DefaultHandler {
        ArrayList<Data> dataList = new ArrayList();
        Data data;
        StringBuilder xmlInnerText;
        int counter = 0;

        static public ArrayList<Data> parseData(InputStream in) {

            DataSAXParser parser = new DataSAXParser();
            try {
                Xml.parse(in, Xml.Encoding.UTF_8, parser);
                return parser.getDataList();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }

        public ArrayList<Data> getDataList() {
            return dataList;
        }





        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            dataList = new ArrayList<Data>();
            xmlInnerText = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")) {
                data = new Data();
                counter++;
            } else if(localName.equals("image")) {
                if(counter > 0) {
                    data.setUrlToImage((attributes.getValue("href").toString()));
                }
            } else if(localName.equals("enclosure")) {
                if(counter > 0) {
                    data.setUrlToMp3((attributes.getValue("url").toString()));
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if(localName.equals("item")) {
                dataList.add(data);
                Log.d("UTIL", "OBJ ADDED");
            }

            if(data != null) {
                if(localName.equals("title")) {
                    data.setTitle(xmlInnerText.toString());
                    Log.d("UTIL", "TITLE" + xmlInnerText.toString());
                } else if(localName.equals("pubDate")) {
                    data.setPublished_date((xmlInnerText.toString()));
                    Log.d("UTIL", "PUB DATE" + xmlInnerText.toString());
                } else if(localName.equals("description")) {
                    data.setDescription(xmlInnerText.toString());
                    Log.d("UTIL", "DESC" + xmlInnerText.toString());
                }


            }

            xmlInnerText.setLength(0);

        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }



        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch, start, length);
        }


    }
}
