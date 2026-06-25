package llm.iteration3.thingsboard.dto;

public class TelemetryResponse {
    private final String entityKey;
    private final int count;

    public TelemetryResponse(String entityKey, int count) {
        this.entityKey = entityKey;
        this.count = count;
    }

    public String getEntityKey() {
        return entityKey;
    }

    public int getCount() {
        return count;
    }
}
