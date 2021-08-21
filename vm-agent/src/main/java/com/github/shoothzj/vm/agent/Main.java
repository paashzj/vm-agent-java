package com.github.shoothzj.vm.agent;

import com.github.shoothzj.javatool.module.OperationSystem;
import com.github.shoothzj.javatool.util.LogUtil;
import com.github.shoothzj.javatool.util.OsUtil;
import com.github.shoothzj.vm.agent.command.VmCommand;
import com.github.shoothzj.vm.agent.config.VmConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello vm agent");
        final OperationSystem operationSystem = OsUtil.getOs();
        if (operationSystem.equals(OperationSystem.MAC)) {
            LogUtil.configureLog();
        }
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new DefaultThreadFactory("command-runner"));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shutdown");
            executorService.shutdownNow();
            System.exit(0);
        }));
        final Injector injector = Guice.createInjector(new GuiceModule());
        final VmCommand vmCommand = injector.getInstance(VmCommand.class);
        executorService.scheduleWithFixedDelay(vmCommand, 0, VmConfig.FIX_DELAY_SECONDS, TimeUnit.SECONDS);

    }

}
