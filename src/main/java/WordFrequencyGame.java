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
    public String getResult(String sentence) {
        if (sentence.split(SPLIT_SYMBOL).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<Sentence> wordInfos=createWordsInfos(sentence);
                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner(LINE_BREAK_SYMBOL);
                for (Sentence w : wordInfos) {
                    String s = w.getValue() + BLANK_SPACE_SYMBOL + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    List<Sentence> createWordsInfos(String sentence){
        String[] words = sentence.split(SPLIT_SYMBOL);

        List<Sentence> wordInfos = new ArrayList<>();
        for (String word : words) {
            Sentence wordInfo = new Sentence(word, 1);
            wordInfos.add(wordInfo);
        }
        Map<String, List<Sentence>> map = getListMap(wordInfos);

        List<Sentence> list = new ArrayList<>();
        for (Map.Entry<String, List<Sentence>> entry : map.entrySet()) {
            Sentence Sentence = new Sentence(entry.getKey(), entry.getValue().size());
            list.add(Sentence);
        }
        return list;
    }

    private Map<String, List<Sentence>> getListMap(List<Sentence> wordInfos) {
        Map<String, List<Sentence>> map = new HashMap<>();
        for (Sentence wordInfo : wordInfos) {
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
