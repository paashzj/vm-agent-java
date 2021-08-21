package com.github.shoothzj.vm.agent.config;

import com.github.shoothzj.javatool.util.EnvUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class VmConfig {

    public static final String SSH_HOST = EnvUtil.getStringVar("ssh.host", "SSH_HOST", "localhost");

    public static final int SSH_PORT = EnvUtil.getIntVar("ssh.port", "SSH_PORT", 22);

    public static final String SSH_USER = EnvUtil.getStringVar("ssh.user", "SSH_USER", "root");

    public static final String SSH_PASSWORD = EnvUtil.getStringVar("ssh.password", "SSH_PASSWORD", "toor");

    public static final String SSH_CLIENT = EnvUtil.getStringVar("ssh.client", "SSH_CLIENT", "MINA");

    public static final int SSH_TIMEOUT_SECONDS = EnvUtil.getIntVar("ssh.timeout.seconds", "SSH_TIMEOUT_SECONDS", 5);

    public static final int FIX_DELAY_SECONDS = EnvUtil.getIntVar("fix.delay.seconds", "FIX_DELAY_SECONDS", 60);


}
