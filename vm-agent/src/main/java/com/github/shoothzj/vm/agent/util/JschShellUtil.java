package com.github.shoothzj.vm.agent.util;

import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.vm.agent.module.SshShellResult;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class JschShellUtil implements IShellUtil {

    @Override
    public SshShellResult execCommand(String host, int port, String username, String password, String command) throws Exception {
        final SshShellResult shellResult = new SshShellResult();
        Session session = null;
        ChannelExec channel = null;
        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errStream = new ByteArrayOutputStream();
            channel.setOutputStream(outputStream);
            channel.setErrStream(errStream);
            channel.connect();
            while (!channel.isClosed()) {
                CommonUtil.sleep(TimeUnit.MILLISECONDS, 500);
            }
            shellResult.setOutputContent(outputStream.toString());
            shellResult.setErrorContent(errStream.toString());
            shellResult.setReturnCode(channel.getExitStatus());
            log.info("exec code is {}", shellResult.getReturnCode());
            log.info("exec err result is {}", shellResult.getErrorContent());
            log.info("exec output result is {}", shellResult.getOutputContent());
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return shellResult;
    }

}
