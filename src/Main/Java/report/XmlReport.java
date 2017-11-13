package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlReport {

    /**
     * Creates XML file report.
     * @param filePath File path chosen in save file dialog.
     * @param inputData Inputted string data.
     */
    public static void saveXmlFile(String filePath, ArrayList<String> inputData) {
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        }
        Document doc = builder.newDocument();
        Node moveList = doc.createElement("movelist");
        doc.appendChild(moveList);
        for (String line:inputData) {
            Element move = doc.createElement("move");
            moveList.appendChild(move);
            move.setAttribute("number", Integer.toString(inputData.indexOf(line) + 1));
            move.setAttribute("from", line.substring(0, 2));
            move.setAttribute("to", line.substring(2));
        }
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            FileWriter fw = new FileWriter(filePath);
            transformer.transform(new DOMSource(doc), new StreamResult(fw));
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read move string array from XML file.
     * @param filePath File path chose in file open dialog.
     * @return null if something was wrong with chosen file else return string array.
     */
    public static ArrayList<String> readXmlFile(String filePath) {
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
        Document doc;
        try {
            doc = builder.parse(new File(filePath));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("move");
        ArrayList<String> result = new ArrayList<>();
        try {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node element = nodeList.item(i);
                NamedNodeMap nodeMap = element.getAttributes();
                String from = nodeMap.getNamedItem("from").getNodeValue();
                String to = nodeMap.getNamedItem("to").getNodeValue();
                result.add(from + to);
            }
        } catch (DOMException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
