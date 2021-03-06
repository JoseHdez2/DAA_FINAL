package reader.tsp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import convenience.E;
import convenience.opthash.HashGraphDef;
import convenience.opthash.HashProbDef;
import convenience.opthash.OptionsHash;
import reader.MyXML;

/**
 * @author jose
 *
 *  Creates a TSP problem from an XML file.
 *  XML file uses TSPLIB format.
 */
public abstract class GraphProblemFromXML{
    
    public static HashGraphDef graphDefFromXML(File file) throws Exception{
        Document dom = GraphProblemFromXML.parseXML(file);
        return parseDocument(dom);
    }
    
    private static Document parseXML(File file){
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
    
    private static HashGraphDef parseDocument(Document dom) throws Exception{
        Element docEle = dom.getDocumentElement();
        
        String name = MyXML.getStringContent(docEle, E.name);
        String source = MyXML.getStringContent(docEle, E.source);
        String description = MyXML.getStringContent(docEle, E.description);
        Integer doublePrecision = Integer.parseInt(MyXML.getStringContent(docEle, E.doublePrecision));
        Integer ignoredDigits = Integer.parseInt(MyXML.getStringContent(docEle, E.ignoredDigits));
        
        Element graphEle = (Element) MyXML.getSubNode(docEle, "graph");
        HashGraphDef distanceMatrix = parseGraphElement(graphEle);
        
        distanceMatrix.put(E.name, name);
        distanceMatrix.put(E.source, source);
        distanceMatrix.put(E.description, description);
        distanceMatrix.put(E.doublePrecision, String.valueOf(doublePrecision));
        distanceMatrix.put(E.ignoredDigits, String.valueOf(ignoredDigits));
        
        if (Integer.valueOf(distanceMatrix.getIndispensable(E.numOfCities)) <= 1) 
            throw new Exception("Matrix must have 2 or more nodes.");
        return distanceMatrix;
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
    private static HashGraphDef parseGraphElement(Element graphEle) throws Exception{
        NodeList vertexEleList = MyXML.getSubNodeList(graphEle, "vertex");
        
        // DistanceMatrix distMat = new DistanceMatrix(vertexEleList.getLength());
        HashGraphDef distMat2 = new HashGraphDef();
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
