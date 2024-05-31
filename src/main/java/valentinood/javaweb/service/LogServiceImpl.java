package valentinood.javaweb.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.LogEntry;
import valentinood.javaweb.repository.SpringDataLogRepository;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {

    private SpringDataLogRepository logRepository;

    @Override
    public List<LogEntry> getLogs() {
        return logRepository.findAll();
    }

    @Override
    public List<LogEntry> getLogs(String username) {
        return logRepository.findByUsername(username);
    }

    @Override
    public void saveLogs(LogEntry log) {
        logRepository.save(log);
    }

    @Override
    public void log(LogEntry log) {
        saveLogs(log);
        System.out.println(log.getMessage());
    }

    @Override
    public void log(HttpServletRequest request, String message) {
        String username;
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
            } else {
                username = principal.getName();
            }
        } else {
            username = "*anonymous user*";
        }

        LogEntry entry = new LogEntry();
        entry.setUsername(username);
        entry.setTime(Instant.now());
        entry.setIp(request.getRemoteAddr());
        entry.setMessage(message);

        log(entry);
    }
}
