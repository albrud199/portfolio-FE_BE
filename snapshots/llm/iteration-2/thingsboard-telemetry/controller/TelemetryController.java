package llm.iteration2.thingsboard.controller;

import java.util.Map;
import llm.iteration2.thingsboard.service.TelemetryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plugins/telemetry")
public class TelemetryController {
    private final TelemetryService service = new TelemetryService();

    @PostMapping("/{type}/{id}/timeseries")
    public Map<String, Object> saveTelemetry(@PathVariable String type,
                                             @PathVariable String id,
                                             @RequestBody Map<String, Object> body) {
        return service.save(type, id, body);
    }
}
