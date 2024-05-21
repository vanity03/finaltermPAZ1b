package finaltermP1_2023;
import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class SuperMario {

    // copypaste kod z cvicenia
    public static Set<Edge> prim(Graph g, Vertex s) {
        Map<Vertex, Double> dlzky = g.createVertexMap(Double.POSITIVE_INFINITY);
        Map<Vertex, Vertex> predchodcovia = g.createVertexMap(null);
        Map<Vertex, Boolean> nevybavene = g.createVertexMap(true);


        dlzky.put(s, 0.0);

        while (nevybavene.containsValue(true)) {
            Vertex min = null;
            for (Map.Entry<Vertex, Double> entry : dlzky.entrySet()) {
                if (nevybavene.get(entry.getKey())) {
                    if (min == null || entry.getValue() < dlzky.get(min)) {
                        min = entry.getKey();
                    }
                }
            }
            Vertex v = min;
            nevybavene.put(v, false);
            for (Vertex w : v.getNeighbours()) {
                double vaha = g.getEdge(v, w).getWeight();
                if (nevybavene.get(w) && vaha < dlzky.get(w)) {
                    predchodcovia.put(w, v);
                    dlzky.put(w, vaha);

                }
            }
        }


        Set<Edge> vysledok = new HashSet<>();
        for (Map.Entry<Vertex, Vertex> vertexVertexEntry : predchodcovia.entrySet()) {
            if (vertexVertexEntry.getValue() != null) {
                vysledok.add(g.getEdge(vertexVertexEntry.getKey(), vertexVertexEntry.getValue()));
            }
        }
        double cena=cena(dlzky);
        System.out.println("cena bez VIP: " + cena);


        return vysledok;
    }

    public static double cena (Map<Vertex, Double> dlzky) {
        double pocitadlo = 0;
        for (Double v : dlzky.values()) {
            pocitadlo += v;
        }
        return pocitadlo;
    }


    public static void main(String[] args) {
        Graph g = Graph.createFromFile("src/main/java/finaltermP1_2023/graf.txt");
        System.out.println(g);

        System.out.println(prim(g, g.getVertex("A")));
    }

}
