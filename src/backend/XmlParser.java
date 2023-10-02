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
    public static void parse(String path) throws SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xml = db.parse(new File(path));
            xml.getDocumentElement().normalize();

            System.out.println("Root Element: " + xml.getDocumentElement().getNodeName());
            
            NodeList list = xml.getElementsByTagName("SubTexture");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    String name = e.getAttribute("name");
                    int x = Integer.parseInt(e.getAttribute("x"));
                    int y = Integer.parseInt(e.getAttribute("y"));
                    int width = Integer.parseInt(e.getAttribute("width"));
                    int height = Integer.parseInt(e.getAttribute("height"));
                    int frameX = Integer.parseInt(e.getAttribute("frameX"));
                    int frameY = Integer.parseInt(e.getAttribute("frameY"));
                    int frameWidth = Integer.parseInt(e.getAttribute("frameWidth"));
                    int frameHeight = Integer.parseInt(e.getAttribute("frameHeight"));
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


/*
  private static final String FILENAME = "/users/mkyong/staff.xml";

  public static void main(String[] args) {

      // Instantiate the Factory
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(FILENAME));

          // optional, but recommended
          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          // get <staff>
          NodeList list = doc.getElementsByTagName("staff");

          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // get staff's attribute
                  String id = element.getAttribute("id");

                  // get text
                  String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                  String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                  String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

                  NodeList salaryNodeList = element.getElementsByTagName("salary");
                  String salary = salaryNodeList.item(0).getTextContent();

                  // get salary's attribute
                  String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                  System.out.println("Current Element :" + node.getNodeName());
                  System.out.println("Staff Id : " + id);
                  System.out.println("First Name : " + firstname);
                  System.out.println("Last Name : " + lastname);
                  System.out.println("Nick Name : " + nickname);
                  System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }

  }
*/
}