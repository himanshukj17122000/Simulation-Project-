package cellsociety;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLReader {
    private static String rows;
    private static String columns;
    private static String top;
    private static String bottom;
    private static String left;
    private static String right;
    private static String levels;
    private static String probCatch;
    public static void main(String argv[]) {
        try {
            File fXmlFile = new File("/Users/himanshu/Desktop/ALL/simulation_team17/resources/file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("gridlayout");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    rows= getTagValue("rows", eElement);
                    columns=getTagValue("columns", eElement);
                    left= getTagValue("rows", eElement);
                    right=getTagValue("columns", eElement);
                    bottom= getTagValue("rows", eElement);
                    top=getTagValue("columns", eElement);
                    levels= getTagValue("maxlevels", eElement);
                    if(getTagValue("probCatch", eElement)!=null){
                        probCatch=getTagValue("probCatch", eElement);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getRows(){return Integer.parseInt(rows);}

    public int getProbCatch(){
        if(probCatch!=null) {
        return Integer.parseInt(probCatch);
    } return -1;
    }

    public Boolean getLeft(){
        if(left.equals("No")){
            return false;
        }
        return true;
    }

    public Boolean getRight(){
        if(right.equals("No")){
            return false;
        }
        return true;
    }

    public boolean getTop(){
        if(top.equals("No")){
            return false;
        }
        return true;
    }
    public int levels(){
        return Integer.parseInt(levels);
    }

    public boolean getBottom(){
        if(bottom.equals("No")){
            return false;
        }
        return true;
    }

    public int getColumns(){
        return Integer.parseInt(columns);
    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}
