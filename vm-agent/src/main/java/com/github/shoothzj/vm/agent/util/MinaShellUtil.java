package com.github.shoothzj.vm.agent.util;

import com.github.shoothzj.vm.agent.config.VmConfig;
import com.github.shoothzj.vm.agent.module.SshShellResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;

import java.io.ByteArrayOutputStream;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class MinaShellUtil implements IShellUtil {

    private final int timeoutMs = VmConfig.SSH_TIMEOUT_SECONDS * 1000;

    @Override
    public SshShellResult execCommand(String host, int port,
                                      String username, String password, String command) throws Exception {
        final SshShellResult shellResult = new SshShellResult();
        final SshClient sshClient = SshClient.setUpDefaultClient();
        sshClient.start();
        try (ClientSession session = sshClient.connect(username, host, port)
                .verify(timeoutMs, TimeUnit.MILLISECONDS).getClientSession()) {
            session.addPasswordIdentity(password);
            session.auth().verify(timeoutMs, TimeUnit.MILLISECONDS);
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 ByteArrayOutputStream errStream = new ByteArrayOutputStream();
                 ClientChannel channel = session.createChannel(Channel.CHANNEL_EXEC, command)) {
                channel.setOut(outputStream);
                channel.setErr(errStream);
                try {
                    channel.open().verify(5, TimeUnit.SECONDS);
                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), timeoutMs);
                    shellResult.setOutputContent(outputStream.toString());
                    shellResult.setErrorContent(errStream.toString());
                } finally {
                    channel.close(false);
                }
            }
            log.info("exec result is {}", shellResult);
        }
        return shellResult;
    }

}
