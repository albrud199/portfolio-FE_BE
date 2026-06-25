package llm.iteration3.thingsboard.model;

public class TelemetrySubscription {
    private final String sessionId;
    private final String entityType;
    private final String entityId;
    private final String keyFilter;

    public TelemetrySubscription(String sessionId, String entityType, String entityId, String keyFilter) {
        this.sessionId = sessionId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.keyFilter = keyFilter;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getKeyFilter() {
        return keyFilter;
    }
}
