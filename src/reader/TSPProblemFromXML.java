package reader;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tester.E;
import tester.OptionsHash;

/**
 * @author jose
 *
 *  Creates a TSP problem from an XML file.
 *  XML file uses TSPLIB format.
 */
public class TSPProblemFromXML{
    
    public TSPProblemFromXML(File file) throws Exception{
        Document dom = this.parseXML(file);
        parseDocument(dom);
    }
    
    private Document parseXML(File file){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document dom = null;
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dom;
    }
    
    private void parseDocument(Document dom) throws Exception{
        Element docEle = dom.getDocumentElement();
        
        String name = MyXML.getStringContent(docEle, E.name);
        String source = MyXML.getStringContent(docEle, E.source);
        String description = MyXML.getStringContent(docEle, E.description);
        Integer doublePrecision = Integer.parseInt(MyXML.getStringContent(docEle, E.doublePrecision));
        Integer ignoredDigits = Integer.parseInt(MyXML.getStringContent(docEle, E.ignoredDigits));
        
        Element graphEle = (Element) MyXML.getSubNode(docEle, "graph");
        OptionsHash distanceMatrix = parseGraphElement(graphEle);
        
        if (Integer.valueOf(distanceMatrix.getIndispensable(E.numOfCities)) <= 1) 
            throw new Exception("Matrix must have 2 or more nodes.");
    }
    
    /**
    * Matrix size after which matrix-reading progress is shown (since it will take a non-trivial amount of time).
    */
    static int MATRIX_SIZE_OUTPUT_THRESHOLD = 20;
    
    /**
     * 
     * @param graphEle Graph Element of TSP XML.
     * @return HashMap with distances.
     * @throws Exception
     */
    private OptionsHash parseGraphElement(Element graphEle) throws Exception{
        NodeList vertexEleList = MyXML.getSubNodeList(graphEle, "vertex");
        
        // DistanceMatrix distMat = new DistanceMatrix(vertexEleList.getLength());
        OptionsHash distMat2 = new OptionsHash();
        int numOfCities = vertexEleList.getLength();
        distMat2.put(E.numOfCities, String.valueOf(numOfCities));
        
        boolean showReadProgress = (numOfCities > MATRIX_SIZE_OUTPUT_THRESHOLD) ? true : false;
        
        for(int i = 0; i < numOfCities; i++){
            
            if (showReadProgress) System.out.println(String.format("Reading vertex %d of %d", i+1, numOfCities));
            
            NodeList edgeEleList = MyXML.getSubNodeList((Element)vertexEleList.item(i), "edge");
            
            for (int j = 0; j < edgeEleList.getLength(); j++){
                Element e = (Element)edgeEleList.item(j);
                double cost = Double.parseDouble(e.getAttribute("cost"));
                int endNode = Integer.parseInt(e.getTextContent());
                // distMat.set(i, endNode, cost);
                distMat2.put(String.format("%d,%d", i, endNode), String.valueOf(cost));
            }
            
            // Whatever the value, exist or not: set self distance to zero.
            // distMat.set(i, i, 0);
            distMat2.put(String.format("%d,%d", i, i), "0");
            
        }
        return distMat2;
    }
}
