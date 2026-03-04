package core;

import java.util.Map;

/**
 * Trida questu
 * Obsahuje metody pro praci s vlastnistmi questu
 */
public class Quest {
    private String questId;
    private String questText;
    private String answer;
    private QuestState state = QuestState.NOT_STARTED;
    private Map<QuestState, String> hints;

    public Quest (String questId, String questText, String answer) {
        this.questId = questId;
        this.questText = questText;
        this.answer = answer;

        state = QuestState.NOT_STARTED;
    }

    //info
    public String getQuestId() {
        return questId;
    }
    public String getQuestText() {
        return questText;
    }
    public String getAnswer() {
        return answer;
    }

    //hinty (zatim nevyuzita)
    public void addHints (QuestState state, String hint) {
        hints.put(state, hint);
    }
    public String getHint (QuestState state) {
        return hints.get(state);
    }

    //quest state (zatim nevyuzita)
    public QuestState getState() {
        return state;
    }
    public void setState(QuestState state) {
        this.state = state;
    }
}
