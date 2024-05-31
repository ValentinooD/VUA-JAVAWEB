package valentinood.javaweb.service;

import jakarta.servlet.http.HttpServletRequest;
import valentinood.javaweb.domain.LogEntry;

import java.util.List;

public interface LogService {
    List<LogEntry> getLogs();
    List<LogEntry> getLogs(String username);
    void saveLogs(LogEntry log);
    void log(LogEntry log);

    void log(HttpServletRequest request, String message);
}
