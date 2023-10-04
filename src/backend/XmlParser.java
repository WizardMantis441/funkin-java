package src.backend;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class XmlParser {
    public static String parse(String path, String want) throws SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder(); // my hatred for xml grows.
            Document xml = db.parse(new File(path));
            xml.getDocumentElement().normalize();
            NodeList list = xml.getElementsByTagName("SubTexture");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    switch (want) {
                        case "name": return e.getAttribute("name");
                        case "x": return e.getAttribute("x");
                        case "y": return e.getAttribute("y");
                        case "width": return e.getAttribute("width");
                        case "height": return e.getAttribute("height");
                        case "frameX": return e.getAttribute("frameX");
                        case "frameY": return e.getAttribute("frameY");
                        case "frameWidth": return e.getAttribute("frameWidth");
                        case "frameHeight": return e.getAttribute("frameHeight");
                        default: System.out.println("XMLPARSER: unknown want.");
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return want;
    }
}