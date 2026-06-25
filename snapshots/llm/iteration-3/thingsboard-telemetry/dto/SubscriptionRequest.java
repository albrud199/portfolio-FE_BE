package llm.iteration3.thingsboard.dto;

public class SubscriptionRequest {
    private String entityType;
    private String entityId;
    private String keyFilter;

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
