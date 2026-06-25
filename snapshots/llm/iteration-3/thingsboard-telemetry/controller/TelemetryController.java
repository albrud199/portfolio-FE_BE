package llm.iteration3.thingsboard.controller;

import java.util.List;
import llm.iteration3.thingsboard.dto.SaveTelemetryRequest;
import llm.iteration3.thingsboard.dto.TelemetryResponse;
import llm.iteration3.thingsboard.dto.SubscriptionRequest;
import llm.iteration3.thingsboard.model.TelemetryPoint;
import llm.iteration3.thingsboard.model.TelemetrySubscription;
import llm.iteration3.thingsboard.service.SubscriptionService;
import llm.iteration3.thingsboard.service.TelemetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plugins/telemetry")
public class TelemetryController {
    private final TelemetryService telemetryService;
    private final SubscriptionService subscriptionService;

    public TelemetryController(TelemetryService telemetryService, SubscriptionService subscriptionService) {
        this.telemetryService = telemetryService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{entityType}/{entityId}/timeseries")
    public TelemetryResponse save(@PathVariable String entityType,
                                  @PathVariable String entityId,
                                  @RequestBody SaveTelemetryRequest request) {
        return telemetryService.save(entityType, entityId, request);
    }

    @GetMapping("/{entityType}/{entityId}/values/timeseries")
    public List<TelemetryPoint> latest(@PathVariable String entityType, @PathVariable String entityId) {
        return telemetryService.latest(entityType, entityId);
    }

    @PostMapping("/ws/{sessionId}/subscribe")
    public TelemetrySubscription subscribe(@PathVariable String sessionId,
                                           @RequestBody SubscriptionRequest request) {
        return subscriptionService.subscribe(sessionId, request);
    }
}
