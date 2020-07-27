import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class WordFrequencyGame {
    private final String SPLIT_SYMBOL="\\s+";
    private final String LINE_BREAK_SYMBOL="\n";
    private final String BLANK_SPACE_SYMBOL=" ";
    private final String CALCULATE_ERROR="Calculate Error";
    public String getResult(String WordInfo) {
        if (WordInfo.split(SPLIT_SYMBOL).length == 1) {
            return WordInfo + " 1";
        } else {
            try {
                List<WordInfo> wordInfos=createWordsInfos(WordInfo);
                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner(LINE_BREAK_SYMBOL);
                for (WordInfo w : wordInfos) {
                    String s = w.getValue() + BLANK_SPACE_SYMBOL + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    List<WordInfo> createWordsInfos(String WordInfo){
        String[] words = WordInfo.split(SPLIT_SYMBOL);

        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfos.add(wordInfo);
        }
        Map<String, List<WordInfo>> wordInfosMap = getListMap(wordInfos);

        List<WordInfo> handledWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfosMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            handledWordInfos.add(wordInfo);
        }
        return handledWordInfos;
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfos) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
