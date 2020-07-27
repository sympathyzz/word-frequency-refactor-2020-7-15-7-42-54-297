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
                String[] words = sentence.split(SPLIT_SYMBOL);

                List<Input> wordInfo = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    wordInfo.add(input);
                }
                Map<String, List<Input>> map = getListMap(wordInfo);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfo = list;
                wordInfo.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner(LINE_BREAK_SYMBOL);
                for (Input w : wordInfo) {
                    String s = w.getValue() + BLANK_SPACE_SYMBOL + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
