/*

    javac -cp ".;jts-core-1.14.0.jar" jts_core_test.java && java -cp ".;jts-core-1.14.0.jar" jts_core_test

*/

import com.vividsolutions.jts.io.*;
import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.operation.polygonize.*;
import java.util.*;
  
class jts_core_test {

    public static void main(String[] args) {

        try {
            
            Polygon p = (Polygon) new WKTReader().read("POLYGON((384000 32000, 384000 384000, 960000 384000, 960000 32000, 384000 32000))"); 
            LinearRing l = (LinearRing) new WKTReader().read("LINEARRING(384000 32000, 384000 384000, 960000 384000, 960000 32000, 384000 32000)"); 
        
            Coordinate c = new Coordinate();
        
            Polygonizer polygonizer = new Polygonizer();
            polygonizer.add(l);
            Collection polygons = polygonizer.getPolygons();

            System.out.println(polygons.toString()); 
        
        } catch (Exception e) {
            
            System.err.println(e);
            
        }
    }

}