package lib.model.abstraction.graph;


import lib.util.exception.BaseException;
import lib.util.exception.InconsistentGraphException;
import lib.util.exception.NoSuchGraphElementException;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Benefical when:
 * Number of vertex is known.
 * No vertex addition or removal operation required
 * Graph is directed
 */
public class ListyGraph extends ArrayList<IVertex> implements IGraph {

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isMaxSize() {
        return (double) this.size == (double) (this.order) * (this.order - 1) / 2;
    }

    @Override
    public IVertex getMinimumIndegreeVertex() {
        return this.minimumIndegreeVertex;
    }

    // indegree map
    int[] indegreeMap = null;
    // #of vertices
    private int order = 0;
    // #of edges
    // max n(n-1)/2
    private int size = 0;
    private IVertex minDegreeVertex;
    private IVertex maxDegreeVertex;
    private IVertex minimumIndegreeVertex;
    private IVertex maximumIndegreeVertex;

    // validation conditions
    private Predicate<IVertex> higherVerticeIdThanNumberOfVertex = a -> a.getId() > order - 1;
    private Predicate<IEdge> higherEdgeVerticeIdThanNumberOfVertex = a -> a.getId() > order - 1;
    private Predicate<int[][]> graphLengthDifferentThanNumberOfVertex = a -> a.length != order;


    public ListyGraph(int[][] adjList, int numberOfVertices) {
        super(numberOfVertices);
        this.order = numberOfVertices;
        this.indegreeMap = new int[order];
        this.initializeAdjList(adjList);
        this.setIndegree();
        this.setExtremumIndegree();
    }

    /**
     * Initializes graph in to an arraylists. Index of the arraylist elements are the same as the getId
     * method of the Vertice objects.
     *
     * @param graph1 as adjacency list without weight
     * @throws InconsistentGraphException
     */
    private void initializeAdjList(int[][] graph1) throws InconsistentGraphException {

        BaseException.exceptionValidator(graph1, graphLengthDifferentThanNumberOfVertex,
                new InconsistentGraphException("graphLengthDifferentThanNumberOfVertex", null)
                        .AddDataPair("NumOfVertex", order)
                        .AddDataPair("LengthGraph", graph1.length)
                        .throwIt()
                        .logIt()
        );

        IntStream.range(0, order).forEach(vtxid -> { // O(V)
            final IVertex vertex = new Vertex(vtxid);

            BaseException.exceptionValidator(vertex, this.higherVerticeIdThanNumberOfVertex,
                    new InconsistentGraphException("higherVerticeIdThanNumberOfVertex", null)
                            .AddDataPair("BadVertice", vertex.getId())
                            .AddDataPair("NumOfVertex", order)
                            .throwIt()
                            .logIt()
            );

            IntStream.range(0, graph1[vtxid].length).forEach(edgeidx -> { //O(E)
                IEdge edge = new Edge(graph1[vtxid][edgeidx], 1);

                BaseException.exceptionValidator(edge, higherEdgeVerticeIdThanNumberOfVertex,
                        new InconsistentGraphException("higherEdgeVerticeIdThanNumberOfVertex", null)
                                .AddDataPair("BadEdgeId", edge.getId())
                                .AddDataPair("BadEdgeWeight", edge.getWeight())
                                .throwIt()
                                .logIt()
                );

                this.indegreeMap[edge.getId()]++;// update indegree map
                vertex.getNbours().add(edge); // O(1)
            });
            //TODO: handle multiple extremum degree values
            this.minDegreeVertex = this.minDegreeVertex == null ? vertex : this.minDegreeVertex.getDegree() < vertex.getDegree() ? this.minDegreeVertex : vertex;
            this.maxDegreeVertex = this.maxDegreeVertex == null ? vertex : this.maxDegreeVertex.getDegree() > vertex.getDegree() ? this.maxDegreeVertex : vertex;
            this.size = this.size + vertex.getDegree();// Updating graph size
            this.add(vtxid, vertex); // O(1) with initial capacity
        });

    }

    /**
     * Initializes graph in to an arraylists. Index of the arraylist elements are the same as the getId
     * method of the Vertice objects.
     *
     * @param graph1 as adjacency matrix without weight
     * @throws InconsistentGraphException
     */
    private void initializeAdjMtx(int[][] graph1) throws InconsistentGraphException {

        BaseException.exceptionValidator(graph1, graphLengthDifferentThanNumberOfVertex,
                new InconsistentGraphException("graphLengthDifferentThanNumberOfVertex", null)
                        .AddDataPair("NumOfVertex", order)
                        .AddDataPair("LengthGraph", graph1.length)
                        .throwIt()
                        .logIt()
        );

        IntStream.range(0, order).forEach(vtxid -> { // O(V)
            final IVertex vertex = new Vertex(vtxid);

            BaseException.exceptionValidator(vertex, higherVerticeIdThanNumberOfVertex,
                    new InconsistentGraphException("higherVerticeIdThanNumberOfVertex", null)
                            .AddDataPair("BadVertice", vertex.getId())
                            .AddDataPair("NumOfVertex", order)
                            .throwIt()
                            .logIt()
            );

            IntStream.range(0, graph1[vtxid].length).forEach(edgeidx -> { //O(E)
                IEdge edge = new Edge(edgeidx, graph1[vtxid][edgeidx]);

                BaseException.exceptionValidator(edge, higherEdgeVerticeIdThanNumberOfVertex,
                        new InconsistentGraphException("higherEdgeVerticeIdThanNumberOfVertex", null)
                                .AddDataPair("BadEdgeId", edge.getId())
                                .AddDataPair("BadEdgeWeight", edge.getWeight())
                                .throwIt()
                                .logIt()
                );

                this.indegreeMap[edge.getId()]++;// update indegree map
                vertex.getNbours().add(edge); // O(1)
            });
            //TODO: handle multiple extremum degree values
            this.minDegreeVertex = this.minDegreeVertex == null ? vertex : this.minDegreeVertex.getDegree() < vertex.getDegree() ? this.minDegreeVertex : vertex;
            this.maxDegreeVertex = this.maxDegreeVertex == null ? vertex : this.maxDegreeVertex.getDegree() > vertex.getDegree() ? this.maxDegreeVertex : vertex;
            this.size = this.size + vertex.getDegree();// Updating graph size
            this.add(vtxid, vertex); // O(1) with initial capacity
        });
    }

    /**
     * Sets indegree values
     */
    private void setIndegree() {
        // set indegree values

        IntStream.range(0, order).forEach(v -> {
            this.get(v).setIndegree(this.indegreeMap[v]);
        });
    }

    /**
     * Set max and min indegree vertices
     */
    private void setExtremumIndegree() {
        // set min-max degree vertices

        IntStream.range(0, order).forEach(v -> {
            this.minimumIndegreeVertex = this.minimumIndegreeVertex == null ? this.get(v) : this.get(v).getIndegree() < this.minimumIndegreeVertex.getIndegree() ? this.get(v) : this.minimumIndegreeVertex;
            this.maximumIndegreeVertex = this.maximumIndegreeVertex == null ? this.get(v) : this.get(v).getIndegree() > this.maximumIndegreeVertex.getIndegree() ? this.get(v) : this.maximumIndegreeVertex;

        });
    }

    /**
     * Adds an edge to given vertex id vertice
     *
     * @param vId  as vertex id
     * @param edge as edge element
     * @return true if edge did not exists already
     */
    public boolean addEdge(int vId, IEdge edge) {

        //TODO: update size order degree indegree

        BaseException.exceptionValidator(edge, this.higherEdgeVerticeIdThanNumberOfVertex,
                new InconsistentGraphException("higherEdgeVerticeIdThanNumberOfVertex", null)
                        .AddDataPair("BadEdgeId", edge.getId())
                        .AddDataPair("BadEdgeWeight", edge.getWeight())
                        .throwIt()
                        .logIt()
        );


        IVertex vertex = null;

        try {

            vertex = this.get(vId);

        } catch (IndexOutOfBoundsException ex) {

            new NoSuchGraphElementException("VertexIdDoesNotExists", ex, false, true, true)
                    .AddDataPair("nonExistingVertexId", vId)
                    .Act();
        }

        return vertex.getNbours().add(edge);
    }

    /**
     * O(1) -- directed
     * Removes given edge from given vertex id
     *
     * @param vId  as vertex id
     * @param edge as edge element
     * @return true if edge element exists
     */
    public boolean removeEdge(int vId, IEdge edge) {

        //TODO: update size order degree indegree

        BaseException.exceptionValidator(edge, this.higherEdgeVerticeIdThanNumberOfVertex,
                new InconsistentGraphException("higherEdgeVerticeIdThanNumberOfVertex", null)
                        .AddDataPair("BadEdgeId", edge.getId())
                        .AddDataPair("BadEdgeWeight", edge.getWeight())
                        .throwIt()
                        .logIt()
        );

        IVertex vertex = null;

        try {

            vertex = this.get(vId);

        } catch (IndexOutOfBoundsException ex) {

            new NoSuchGraphElementException("VertexIdDoesNotExists", ex)
                    .AddDataPair("nonExistingVertexId", vId)
                    .AddDataPair("edgeId", edge.getId())
                    .AddDataPair("edgeWeight", edge.getWeight())
                    .throwIt()
                    .logIt()
                    .Act();
        }

        return vertex.getNbours().remove(edge);
    }


}
