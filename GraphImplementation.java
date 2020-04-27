import java.util.*;
import java.util.Queue;

public class GraphImplementation implements Graph {

    protected int [][] adj;
    protected int vertices;
    protected int [] incident;

    /**
     * Constructs and returns a graph with the number of vertices
     * passed as the argument. Vertices have IDs, numbered 0, 1, …,
     * vertices-1. No edges exist between vertices at instantiation.
     * @param vertices
     */
    public GraphImplementation(int vertices){
        this.vertices = vertices;
        adj = new int[vertices][vertices];
        incident = new int[vertices];
    }

    /**
     * Adds a directed edge between two vertices from src to tar.
     * @param src
     * @param tar
     */
    public void addEdge(int src, int tar) throws Exception {
        adj[src][tar]++;
        incident[tar]++;
    }

    /**
     * Returns a List of vertex IDs, with each ID representing a vertex
     * which is the destination of the edge originating from the source
     * vertex, passed as the argument.
     * @param vertex
     * @return
     * @throws Exception
     */
    public List<Integer> neighbors(int vertex) throws Exception {

        List<Integer> list = new LinkedList<>();
        for (int v = 0; v < vertices; v++){
            /* if adj[vertex][v] == 1, this means that vertex --> v
             * and thus, is a 'child' of vertex*/
            if (adj[vertex][v] == 1){
                list.add(v);
            }
        }

        return list;
    }

    /**
     * Returns first vertex in incidents array with no incoming edges,
     * @param incidents
     * @return
     */
    protected int noIncidents (int [] incidents) {

        for (int i = 0; i < incidents.length; i++) {
            /* it has no incoming edges */
            if (incidents[i] == 0){
                return i;
            }
        }
        /* Unable to find a vertex with no incoming edges */
        return -1;
    }

    /**
     * Prints (to console) one ordering of vertices such that each
     * directed edge (v1, v2) from vertex v1 to vertex v2, v1 comes
     * before v2 in the ordering. If such an ordering is not possible (due
     * to cycles, for example), this function must indicate so, though it
     * may print a partial solution in so doing.
     * @return
     */
    public List<Integer> topologicalSort() {
        /* (1) SELECT ANY VERTEX, V, WITH NO INCOMING EDGES
         * (2) ADD V TO SCHEDULE
         * (3) VIRTUALLY REMOVE ALL OUTGOING EDGES FROM V
         */

        List<Integer> schedule = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            /* (1) */
            int vertex = noIncidents(incident);
            incident[vertex] -= 1;
            /* (2) */
            schedule.add(vertex);

            for (int j = 0; j < vertices; j++){
                /* (3) */
                if(adj[vertex][j] == 1) {
                    incident[j] -= 1;
                }
            }
        }

        return schedule;
    }

}
