package com.example.multipledatasource.config.schedule;

import com.example.multipledatasource.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FixedCronJob {

    private final UserService userService;


    @Scheduled(cron = "0/15 * * * * *")
    public void test() {
        log.warn("test");
        String uuid = UUID.randomUUID().toString();
        userService.insert(uuid, null, null);
    }

}
