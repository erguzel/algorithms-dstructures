package lib.util;

import lib.model.abstraction.IBinaryTreeNode;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataUtil {

    private static ALogger<DataUtil> LOGGER = new ALogger<>(DataUtil.class);

    // converts adj mtx list edges etc
    public static class Convertors {

        private static void emptyListAndNullEntryCheck(int[][] d2array) {
            boolean validatorBoolean = d2array.length == 0;
            if (validatorBoolean) {
                LOGGER.info("this 2darray can not be empty");
                System.exit(-1);
            }
            validatorBoolean = IntStream.range(0, d2array.length).anyMatch(a -> d2array[a] == null);
            if (validatorBoolean) {
                LOGGER.info("this 2darray can not have null entries");
                System.exit(-1);

            }
        }

        private static void isArrayElementsUnique(int[][] d2array) {
            boolean hasRepeatingElements = IntStream.range(0, d2array.length)
                    .filter(r -> Arrays.stream(d2array[r]).boxed().collect(Collectors.toSet()).size() != d2array[r].length)
                    .count() > 0;
            if (hasRepeatingElements) {
                LOGGER.info("this 2darray can not have non-unique entries");
                System.exit(-1);

            }
        }

        private static void emptyListAndNullEntryCheck(int[][][] d3array) {
            boolean validatorBoolean = d3array.length == 0;
            if (validatorBoolean) {
                LOGGER.info("EdgeList can not be empty");
                System.exit(-1);
            }
            validatorBoolean = IntStream.range(0, d3array.length).anyMatch(a -> d3array[a] == null);
            if (validatorBoolean) {
                LOGGER.info("EdgeList can not have null entries");
                System.exit(-1);
            }

            var streamBoolean = new Object() {
                boolean anyMatch = false;
            };

            IntStream.range(0, d3array.length).forEach(x -> {
                if (IntStream.range(0, d3array[x].length).anyMatch(a -> d3array[x][a] == null)) {
                    LOGGER.info("EdgeList can not have null entries");
                    System.exit(-1);
                }
            });
        }

        private static void emptyEdgeWeightPair(int[][][] d3adjListWeighted){
            boolean validator = IntStream.range(0,d3adjListWeighted.length)
                    .mapToObj(o->d3adjListWeighted[o])
                    .flatMap(Stream::of)
                    .anyMatch(k->k.length==0 || k.length>2);

            if(validator){
                LOGGER.info("AdjList edge-weight pair array can not be empty or longer than 2");
                System.exit(-1);
            }
        }

        /**
         * Converts adj list to adj matrix.
         * Can be used for non-weighted adj lists.
         * Can be used directed or undirected graphs.
         *
         * @param adjlist    of adjacent vertexes without weight information.
         * @param isDirected true if input graph is directed. In that case adds symmetric element to the result matrix
         * @return
         */
        public static int[][] convertAdjListToAdjMatrix(int[][] adjlist, boolean isDirected) {
            //validate
            emptyListAndNullEntryCheck(adjlist);
            isArrayElementsUnique(adjlist);
            //validate

            OptionalInt max = IntStream.range(0, adjlist.length)
                    .filter(x -> adjlist[x].length != 0)
                    .map(m -> IntStream.of(adjlist[m]).max().getAsInt())
                    .max();

            int numberofvertices = Math.max(max.isPresent() ? max.getAsInt() + 1 : 0, adjlist.length);

            //result
            int[][] res = new int[numberofvertices][numberofvertices];

            IntStream.range(0, adjlist.length).forEach(vertex -> {
                IntStream.range(0, adjlist[vertex].length)
                        .forEach(ed -> {
                            res[vertex][adjlist[vertex][ed]] = 1;
                            if (!isDirected) {
                                res[adjlist[vertex][ed]][vertex] = 1;
                            }
                        });
            });

            return res;

        }

        /**
         * Converts adj list to adj matrix.
         * Can be used for non-weighted adj lists.
         * Can be used directed or undirected graphs.
         * Prints result matrix
         *
         * @param adjlist    of adjacent vertexes without weight information.
         * @param isDirected true if input graph is directed. In that case adds symmetric element to the result matrix
         * @param isVerbose  souts result matrix
         * @return
         */
        public static int[][] convertAdjListToAdjMatrix(int[][] adjlist, boolean isDirected, boolean isVerbose) {

            int[][] res = convertAdjListToAdjMatrix(adjlist, isDirected);
            if (isVerbose)
                LOGGER.info("\n" + DataUtil.Printers.stringifyAdjacencyMatrix(res));
            return res;

        }


        /**
         * Converts adj list to adj matrix for weighted graphs.
         * Allows 0 weights, allows weights to itself including 0 weight
         * If weight is not provided, default weight is 0
         * Weights of unconnected vertexes are Integer.MAX_VAL
         * Can be used for weighted adj lists.
         * Can be used directed or undirected graphs.
         *
         * @param adjlist    of adjacent vertexes without weight information.
         * @param isDirected true if input graph is directed. In that case adds symmetric element to the result matrix
         * @return
         */
        public static int[][] convertAdjListToAdjMatrixWeighted(int[][][] adjlist, boolean isDirected) {
            //validate
            emptyListAndNullEntryCheck(adjlist);
            emptyEdgeWeightPair(adjlist);
            //validate


            OptionalInt max = IntStream.range(0, adjlist.length)
                    .filter(f -> adjlist[f].length != 0)
                    .mapToObj(a -> adjlist[a])
                    .flatMap(s -> Arrays.stream(s))
                    .flatMapToInt(kk -> IntStream.of(kk[0]))
                    .max();


            int numberofvertices = Math.max(max.isPresent() ? max.getAsInt() + 1 : 0, adjlist.length);

            int[][] res = new int[numberofvertices][numberofvertices];

            IntStream.range(0,numberofvertices).forEach(row->{
                IntStream.range(0,numberofvertices).forEach(col->{
                    res[row][col] = Integer.MAX_VALUE;
                });
            });

            IntStream.range(0, adjlist.length).filter(m -> adjlist[m].length > 0)
                    .forEach(vertex -> {
                        IntStream.range(0, adjlist[vertex].length)
                                .forEach(edgidx -> {
                                    int[] edgeweightpair = adjlist[vertex][edgidx];
                                    final int dest = edgeweightpair[0];
                                    final int weight = edgeweightpair.length==2?edgeweightpair[1]:0;
                                    res[vertex][dest] = weight;
                                    //res[vertex][dest] = vertex==dest && weight==Integer.MAX_VALUE ? 0: weight;

                                    if (!isDirected) {
                                        boolean conflictingEdgesInUndirectedGraph = res[vertex][dest]!=Integer.MAX_VALUE &&
                                                res[dest][vertex] != Integer.MAX_VALUE &&
                                                res[vertex][dest] !=res[dest][vertex];
                                        if (conflictingEdgesInUndirectedGraph) {
                                            LOGGER.info("Undirected graph has different weights between edges, invalid graph");
                                            System.exit(-1);
                                        }
                                        res[dest][vertex] = res[vertex][dest];// == 0 ? weight: res[dest][vertex];
                                    }
                                });
                    });

            IntStream.range(0,res.length).forEach(v->{
                IntStream.range(0,res[v].length)
                        .filter(k->v==k && res[v][k]==Integer.MAX_VALUE)
                        .forEach(ed->{
                            res[v][ed]=0;
                });
            });

            return res;
        }

        /**
         * Converts adj list to adj matrix for weighted graphs.
         * Allows 0 weights, allows weights to itself including 0 weight
         * Can be used for weighted adj lists. Default value of unconnected vertexes are Integer.MAX_VAL
         * Can be used directed or undirected graphs.
         * Prints result matrix
         *
         * @param adjlist  of adjacent vertexes with weight information.
         * @param isDirected true if input graph is directed. In that case adds symmetric element to the result matrix
         * @param isVerbose souts result data
         * @return
         */
        public static int [][] convertAdjListToAdjMatrixWeighted(int[][][] adjlist, boolean isDirected,boolean isVerbose){
            int[][] rese = convertAdjListToAdjMatrixWeighted(adjlist,isDirected);
            if(isVerbose)
                LOGGER.info("\n"+DataUtil.Printers.stringifyAdjacencyMatrix(rese));
            return rese;
        }

        public static int[][] convertAdjMatrixToAdjList(int[][] adjmtx, boolean isDirected) {
            // validate
            emptyListAndNullEntryCheck(adjmtx);
            IntStream.range(0, adjmtx.length).forEach(vt -> {
                IntStream.range(0, adjmtx.length).forEach(ed -> {
                    if (!(adjmtx[vt][ed] == 0 || adjmtx[vt][ed] == 1)) {
                        LOGGER.info("This method is not for weighted graph!!");
                        System.exit(-1);
                    }
                });
            });
            // validate

            List<Integer>[] result = new List[adjmtx.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new ArrayList<>();
            }


            IntStream.range(0, adjmtx.length).forEach(vertex -> {
                IntStream.range(0, adjmtx.length).filter(x -> adjmtx[vertex][x] != 0)
                        .forEach(ed -> {
                            result[vertex].add(ed);
                            if (!isDirected) {
                                result[ed].add(vertex);
                            }
                        });
            });


            int[][] res = new int[adjmtx.length][adjmtx.length];
            IntStream.range(0, res.length).forEach(x -> {
                res[x] = result[x].stream().mapToInt(a -> a).toArray();
            });

            return res;
        }

        public static int[][][] convertAdjMatrixToAdjListWeighted(int[][] adjmtx, boolean isDirected) {
            // validate
            emptyListAndNullEntryCheck(adjmtx);
            // validate

            List<int[]>[] result = new List[adjmtx.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new ArrayList<>();
            }

            IntStream.range(0, adjmtx.length).forEach(vertex -> {
                IntStream.range(0, adjmtx.length).filter(x -> adjmtx[vertex][x] != 0)
                        .forEach(ed -> {
                            result[vertex].add(new int[]{ed, adjmtx[vertex][ed]});
                            if (!isDirected) {
                                result[ed].add(new int[]{vertex, adjmtx[ed][vertex] == 0 ? 1 : adjmtx[ed][vertex]});
                            }
                        });
            });


            int[][][] res = new int[adjmtx.length][][];

            IntStream.range(0, adjmtx.length).forEach(vertex -> {
                res[vertex] = new int[result[vertex].size()][2];
                List<int[]> nbours = result[vertex];
                IntStream.range(0, nbours.size()).forEach(nb -> {
                    res[vertex][nb] = nbours.get(nb);
                });
            });

            return res;
        }

        public static int[][] convertAdjMatrixToEdgeList(int[][] adjmtx, boolean isDirected) {
            // validate
            emptyListAndNullEntryCheck(adjmtx);
            // validate

            List<int[]> result = new ArrayList<>();

            IntStream.range(0, adjmtx.length).forEach(vertex -> {
                IntStream.range(0, adjmtx.length).forEach(edge -> {
                    if (adjmtx[vertex][edge] != 0) {
                        result.add(new int[]{vertex, edge, adjmtx[vertex][edge]});
                        if (!isDirected) {
                            result.add(new int[]{edge, vertex, adjmtx[vertex][edge]});
                        }
                    }
                });
            });


            int[][] res = new int[result.size()][];

            IntStream.range(0, result.size()).forEach(index -> {
                res[index] = result.get(index);
            });

            return res;
        }

        public static int[][] convertEdgeListToAdjMatrix(int[][] edges, boolean isDirected) {
            // validate
            emptyListAndNullEntryCheck(edges);
            // validate

            var streamData = new Object() {
                int max = Integer.MIN_VALUE;
            };

            IntStream.range(0, edges.length).forEach(edge -> {
                IntStream.range(0, edges[edge].length).filter(x -> x < 2).forEach(edgeelement -> {
                    if (edges[edge][edgeelement] > streamData.max) {
                        streamData.max = edges[edge][edgeelement];
                    }
                });
            });

            int numberOfVertex = streamData.max + 1;
            // adjmtx
            int[][] result = new int[numberOfVertex][numberOfVertex];

            IntStream.range(0, edges.length).forEach(edge -> {
                int[] ed = edges[edge];
                int src = ed[0];
                int dst = ed[1];
                int wt = ed.length == 3 ? ed[2] : 1;
                result[src][dst] = wt;
                if (!isDirected) {
                    if (result[dst][src] != 0) {
                        LOGGER.info("Undirected graph override value not excepted. Invalid graph definition.");
                        System.exit(-1);
                    }
                    result[dst][src] = wt;
                }
            });

            return result;
        }

        // will be deprecated
        public static int[][] _convertEdgelistToAdjMtx(int[][] edges) {
            // {{1,2,1},{1,3,1}..}//

            int numOfVertices = Integer.MIN_VALUE;
            for (int i = 0; i < edges.length; i++) {

                if (edges[i].length > 0) {
                    if (edges[i][0] > numOfVertices) numOfVertices = edges[i][0];
                }
                if (edges[i].length > 1) {
                    if (edges[i][1] > numOfVertices) numOfVertices = edges[i][1];
                }

            }

            numOfVertices = numOfVertices + 1;

            int[][] res = new int[numOfVertices][numOfVertices];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = Integer.MAX_VALUE;
                try {
                    fridx = edges[i][0];
                } catch (Exception e) {
                    fridx = Integer.MAX_VALUE;
                }
                int toidx = Integer.MAX_VALUE;
                try {
                    toidx = edges[i][1];
                } catch (Exception e) {
                    toidx = Integer.MAX_VALUE;
                }
                int weight = 0;
                try {
                    weight = edges[i][2];
                } catch (Exception e) {
                    weight = 0;
                }

                if (fridx != Integer.MAX_VALUE && toidx != Integer.MAX_VALUE) {
                    res[fridx][toidx] = weight;
                }
            }

            return res;
        }

        public static int[][] _convertEdgelistToAdjMtx(int[][] edges, int paramfromidx, int paramtoidx, Object paramweightidx) {
            // {{1,2,1},{1,3,1}..}//

            int numOfVertices = Integer.MIN_VALUE;
            for (int i = 0; i < edges.length; i++) {

                if (edges[i].length > paramfromidx) {
                    if (edges[i][paramfromidx] > numOfVertices) numOfVertices = edges[i][paramfromidx];
                }
                if (edges[i].length > paramtoidx) {
                    if (edges[i][paramtoidx] > numOfVertices) numOfVertices = edges[i][paramtoidx];
                }

            }

            int[][] res = new int[numOfVertices][numOfVertices];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = Integer.MAX_VALUE;
                try {
                    fridx = edges[i][0];
                } catch (Exception e) {
                    fridx = Integer.MAX_VALUE;
                }
                int toidx = Integer.MAX_VALUE;
                try {
                    toidx = edges[i][1];
                } catch (Exception e) {
                    toidx = Integer.MAX_VALUE;
                }
                int weight = 0;
                try {
                    if (paramweightidx != null) {

                        weight = edges[i][(int) paramweightidx];
                    }
                } catch (Exception e) {
                    weight = 0;
                }

                if (fridx != Integer.MAX_VALUE && toidx != Integer.MAX_VALUE) {
                    res[fridx][toidx] = weight;
                }
            }

            return res;
        }

        public static int[][] _convertAdjMtxToEdgeList(int[][] adjMtx) {

            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < adjMtx.length; i++) {

                int from = i;
                for (int j = 0; j < adjMtx[i].length; j++) {
                    if (adjMtx[i][j] == 0) continue;
                    int to = j;
                    int weight = j == 0 ? Integer.MAX_VALUE : adjMtx[i][j];
                    int[] edge = {i, j, weight};
                    if (weight != Integer.MAX_VALUE)
                        edges.add(edge);
                }
            }


            int[][] res = new int[edges.size()][3];

            for (int i = 0; i < edges.size(); i++) {
                res[i] = edges.get(i);
            }

            return res;
        }

        public static int[][][] _convertAdjmtxToAdjList(int[][] adjmtx) {

            int[][][] adjList = new int[adjmtx.length][][];

            for (int i = 0; i < adjmtx.length; i++) {

                int[][] neighbours = null;

                int nbNumber = 0;

                for (int k = 0; k < adjmtx[i].length; k++) {

                    if (adjmtx[i][k] != 0) {
                        nbNumber++;
                    }
                }


                int insertIndex = 0;
                for (int j = 0; j < adjmtx[i].length; j++) {

                    if (adjmtx[i][j] != 0) {
                        int currentPointIndex = i;
                        int neighbourIndex = j;
                        int weight = adjmtx[i][j];
                        if (adjList[currentPointIndex] == null) {
                            adjList[currentPointIndex] = new int[nbNumber][2];
                        }
                        int idx =
                                adjList[i][insertIndex][0] = neighbourIndex;
                        adjList[i][insertIndex][1] = weight;
                        insertIndex++;
                    }
                }
            }

            return adjList;
        }

        public static int[][] _convertAdjListToAdjMtx(int[][][] adjlist) {

            int[][] res = new int[adjlist.length][adjlist.length];

            for (int i = 0; i < adjlist.length; i++) {

                for (int j = 0; j < adjlist[i].length; j++) {
                    int nbid = adjlist[i][j][0];
                    int weight = adjlist[i][j][1];

                    res[i][nbid] = weight;
                }

            }

            return res;

        }


    }

    //generates dummy data, array matrix etc
    public static class Generators {

        public static int[][] generateRandomMatrix(int rownum, int colnum, int low, int high) {

            int[][] res = new int[rownum][colnum];
            for (int i = 0; i < rownum; i++) {
                if (res[i] == null) res[i] = new int[colnum];
                for (int j = 0; j < colnum; j++) {
                    int val = (int) DataUtil.Generators.getRandom(low, high);
                    res[i][j] = val;
                }
            }

            return res;
        }

        public static int[] generateIntArray(int length, int low, int high) {

            int[] res = new int[length];

            for (int i = 0; i < length; i++) {

                res[i] = (int) getRandom(low, high);
            }

            return res;
        }

        public static int[][][] generateRandomGraph(int numberOfVertex) {

            StringBuilder sb = new StringBuilder();
            int[][][] graph = new int[numberOfVertex][][];
            int edgeNummer = 0;
            for (int i = 0; i < numberOfVertex; i++) {

                // number of edges


                if (graph[i] == null) {

                    edgeNummer = (int) DataUtil.Generators.getRandom(1, numberOfVertex - 1);
                    graph[i] = new int[edgeNummer][];
                }

                for (int j = 0; j < graph[i].length; j++) {
                    int noeighbourId = (int) DataUtil.Generators.getRandom(0, numberOfVertex - 1);
                    int weight = (int) DataUtil.Generators.getRandom(0, numberOfVertex - 1);
                    graph[i][j] = new int[]{noeighbourId, weight};
                    if (j == graph[i].length - 1) {
                        sb.append("{" + noeighbourId + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + noeighbourId + "," + weight + "},");
                    }
                }


            }


            //   System.out.println(sb.toString());
            LOGGER.info("RANDOM GRAPH:\n" + sb.toString());


            return graph;

        }

        public static int[][][] generateRandomGraph(int numberOfVertex, boolean silent) {

            StringBuilder sb = new StringBuilder();
            int[][][] graph = new int[numberOfVertex][][];
            int edgeNummer = 0;
            for (int i = 0; i < numberOfVertex; i++) {

                // number of edges


                if (graph[i] == null) {

                    edgeNummer = (int) DataUtil.Generators.getRandom(1, numberOfVertex - 1);
                    graph[i] = new int[edgeNummer][];
                }

                for (int j = 0; j < graph[i].length; j++) {
                    int noeighbourId = (int) DataUtil.Generators.getRandom(0, numberOfVertex - 1);
                    int weight = (int) DataUtil.Generators.getRandom(0, numberOfVertex - 1);
                    graph[i][j] = new int[]{noeighbourId, weight};
                    if (j == graph[i].length - 1) {
                        sb.append("{" + noeighbourId + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + noeighbourId + "," + weight + "},");
                    }
                }


            }

            if (!silent)
                LOGGER.info("RANDOM GRAPH:\n" + sb.toString());


            return graph;

        }

        public static double getRandom(double low, double high) {
            double rand = (int) Math.floor(Math.random() * (high - low + 1) + low);
            return rand;
        }


    }

    //print data
    //trees, graphs, matrices etc
    public static class Printers {

        public static void printBSTree(IBinaryTreeNode root) {
            List<List<String>> lines = new ArrayList<List<String>>();

            List<IBinaryTreeNode> level = new ArrayList<IBinaryTreeNode>();
            List<IBinaryTreeNode> next = new ArrayList<IBinaryTreeNode>();

            level.add(root);
            int nn = 1;

            int widest = 0;

            while (nn != 0) {
                List<String> line = new ArrayList<String>();

                nn = 0;

                for (IBinaryTreeNode n : level) {
                    if (n == null) {
                        line.add(null);

                        next.add(null);
                        next.add(null);
                    } else {
                        String aa = n.toString();
                        line.add(aa);
                        if (aa.length() > widest) widest = aa.length();

                        next.add(n.getLeft());
                        next.add(n.getRight());

                        if (n.getLeft() != null) nn++;
                        if (n.getRight() != null) nn++;
                    }
                }

                if (widest % 2 == 1) widest++;

                lines.add(line);

                List<IBinaryTreeNode> tmp = level;
                level = next;
                next = tmp;
                next.clear();
            }

            int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
            for (int i = 0; i < lines.size(); i++) {
                List<String> line = lines.get(i);
                int hpw = (int) Math.floor(perpiece / 2f) - 1;

                if (i > 0) {
                    for (int j = 0; j < line.size(); j++) {

                        // split node
                        char c = ' ';
                        if (j % 2 == 1) {
                            if (line.get(j - 1) != null) {
                                c = (line.get(j) != null) ? '┴' : '┘';
                            } else {
                                if (j < line.size() && line.get(j) != null) c = '└';
                            }
                        }
                        System.out.print(c);

                        // lines and spaces
                        if (line.get(j) == null) {
                            for (int k = 0; k < perpiece - 1; k++) {
                                System.out.print(" ");
                            }
                        } else {

                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? " " : "─");
                            }
                            System.out.print(j % 2 == 0 ? "┌" : "┐");
                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? "─" : " ");
                            }
                        }
                    }
                    System.out.println();
                }

                // print line of numbers
                for (int j = 0; j < line.size(); j++) {

                    String f = line.get(j);
                    if (f == null) f = "";
                    int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                    int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                    // a number
                    for (int k = 0; k < gap1; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(f);
                    for (int k = 0; k < gap2; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();

                perpiece /= 2;
            }
        }

        //
        //https://csacademy.com/app/graph_editor/
        public static String stringifyEdgeList(int[][] edgelist) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < edgelist.length; i++) {
                for (int j = 0; j < edgelist[i].length; j++) {
                    if (j != edgelist[i].length - 1) {
                        stringBuilder.append(edgelist[i][j] + " ");
                    } else {
                        stringBuilder.append(edgelist[i][j] + "\n");
                    }
                }
            }
            return stringBuilder.toString();
        }

        public static void PrintReport(ALogger logger, Map<Object, Object> data) {
            for (Map.Entry entry : data.entrySet()) {

                logger.info(entry.getKey() + ":" + entry.getValue());
            }
        }

        public static String stringfyAdjacencyList(int[][][] adjlist) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < adjlist.length; i++) {
                for (int j = 0; j < adjlist[i].length; j++) {
                    int nbour = adjlist[i][j][0];
                    int weight = adjlist[i][j][1];

                    adjlist[i][j] = new int[]{nbour, weight};

                    if (j == adjlist[i].length - 1) {
                        sb.append("{" + nbour + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + nbour + "," + weight + "},");
                    }
                }


            }

            return sb.toString();

        }

        public static String stringifyAdjacencyMatrix(int[][] adjmtx) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < adjmtx.length; i++) {
                for (int j = 0; j < adjmtx[i].length; j++) {
                    stringBuilder.append(adjmtx[i][j] + ",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        public static String stringifyArray(int[] arr) {

            return Arrays.stream(arr).boxed().collect(Collectors.toList()).toString();
        }
    }
}
