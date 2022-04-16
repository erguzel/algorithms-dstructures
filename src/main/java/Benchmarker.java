import lib.model.ABinaryTree;
import lib.model.MyBSTree;
import lib.util.ALogger;
import lib.util.DataUtil;
import lib.util.SampleData;

import javax.xml.parsers.SAXParser;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Benchmarker {

    static ALogger<Benchmarker> LOGGER = new ALogger<>(Benchmarker.class);

    public static void main(String[] args) {

        int input [][] = SampleData.GraphOnlineRu.DUCK_DIR_WEG;

        LOGGER.info("\n"+ DataUtil.Printers.stringifyEdgeList(
                DataUtil.Convertors.convertAdjMatrixToEdgeList(input,true)
        ));

    }


}
