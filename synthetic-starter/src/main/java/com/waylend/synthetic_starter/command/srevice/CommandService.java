package com.waylend.synthetic_starter.command.srevice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.waylend.synthetic_starter.command.model.CommandRequest;
import com.waylend.synthetic_starter.command.model.Prioryty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandService {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void executeCommand(CommandRequest command, Runnable task) {
        if (command.getPrioryty() == Prioryty.CRITICAL) {
            log.info("Выполняется CRITICAL команда :{}", command.getDescription());
            task.run();
        } else {
            executor.submit(()->
            {
                log.info("Обрабатывается COMMON команду:", command.getDescription());
                task.run();
            });
        }
    }

}
