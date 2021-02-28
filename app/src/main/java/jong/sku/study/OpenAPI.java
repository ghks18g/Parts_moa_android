package jong.sku.study;


import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class OpenAPI extends AsyncTask<Void, Void, List<Oil_Station>> {

    private String url;

    public List<Oil_Station> list_oil_station;

    public OpenAPI(String url) {
        this.url = url;
    }

    @Override
    protected List<Oil_Station> doInBackground(Void... params) {

        // parsing 할 url 지정 (API key 포함)
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try{
            dBuilder = dbFactory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }

        Document doc = null;

        try{
            doc = dBuilder.parse(url);
        }catch (IOException | SAXException e){
            e.printStackTrace();
        }

        //root tag

        doc.getDocumentElement().normalize();
        System.out.println("Root element : " + doc.getDocumentElement().getNodeName()); //root element result

        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("OIL");

        list_oil_station = new ArrayList<Oil_Station>();

        for (int tmp = 0; tmp < nList.getLength(); tmp++){
            Node nNode = nList.item(tmp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                Oil_Station oil_station = new Oil_Station(
                        getTagValue("OS_NM",eElement),
                        getTagValue("PRICE",eElement),
                        getTagValue("GIS_X_COOR",eElement),
                        getTagValue("GIS_Y_COOR",eElement),
                        getTagValue("DISTANCE",eElement),
                        getTagValue("POLL_DIV_CO",eElement),
                        getTagValue("UNI_ID",eElement)
                );
//                oil_station[tmp].setStation_name(""+getTagValue("OS_NM",eElement));
//                oil_station[tmp].setPrice(""+getTagValue("PRICE",eElement));
//                oil_station[tmp].setUser_to_station(""+getTagValue("DISTANCE",eElement));
//                oil_station[tmp].setGis_x(""+getTagValue("GIS_X_COOR",eElement));
//                oil_station[tmp].setGis_y(""+getTagValue("GIS_Y_COOR",eElement));
                list_oil_station.add(oil_station);
                Log.d("OPEN_API","주유소 코드: "+getTagValue("UNI_ID",eElement));
                Log.d("OPEN_API", "주유소명: " + getTagValue("OS_NM",eElement));
                Log.d("OPEN_API", "상표" + getTagValue("POLL_DIV_CO",eElement));
                Log.d("OPEN_API", "거리 : " + getTagValue("DISTANCE",eElement));
                Log.d("OPEN_API", "가격: " + getTagValue("PRICE",eElement));
                Log.d("OPEN_API", "x좌표: " + getTagValue("GIS_X_COOR",eElement));
                Log.d("OPEN_API", "y좌표: " + getTagValue("GIS_Y_COOR",eElement));
                Log.d("OPEN_API", "----------------------------------------");
            }
        }

        return list_oil_station;
    }

    @Override
    protected void onPostExecute(List<Oil_Station> list_oil_station) {
        super.onPostExecute(list_oil_station);
    }

    private String getTagValue(String tag, Element eElement){
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}

