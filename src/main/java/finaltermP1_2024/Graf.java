package finaltermP1_2024;

import sk.upjs.paz.graph.Graph;

public class Graf {


    public static int calculateNetGains(Graph graph) {
        Map<Vertex, Integer> netGains = new HashMap<>();

        // Process each vertex
        for (Vertex v : graph.getVertices()) {
            int netGain = 0;
            for (Edge e : graph.getOutEdges(v)) {
                netGain += e.getWeight();
            }
            for (Edge e : graph.getInEdges(v)) {
                netGain -= e.getWeight();
            }
            netGains.put(v, netGain);
        }

        // Count the number of thieves with a positive net gain
        int countOfProfitableThieves = 0;
        for (int gain : netGains.values()) {
            if (gain > 0) {
                countOfProfitableThieves++;
            }
        }

        return countOfProfitableThieves;
    }
}
