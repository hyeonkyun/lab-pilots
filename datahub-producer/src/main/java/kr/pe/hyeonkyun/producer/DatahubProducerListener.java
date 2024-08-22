package kr.pe.hyeonkyun.producer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:server.properties")
public class DatahubProducerListener {

	@Value("${SERVER_PORT}")
    private int serverPort;
	
//	@Autowired
//    DataProcessManager dataProcessManager;
	
	/**
     * DatahubService 리스너 구동
     * @throws IOException
     */
    public void startThread() throws Exception {
        InetSocketAddress sAdder = new InetSocketAddress(serverPort);
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                Executors.defaultThreadFactory());
        AsynchronousServerSocketChannel serverSock = AsynchronousServerSocketChannel.open(channelGroup);
        serverSock.bind(sAdder);

        log.info("------------------ {} START -------------------", "DatahubProducerListener");
        log.info(" SERVER ADDRESS: {}, {} ", sAdder, serverSock);
        log.info("================== {} END ===================", "DatahubProducerListener");

        //start to accept the connection from client
        serverSock.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
        	
        	@Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
        		//dataProcessManager.getDataProcess(protocolType).socketRead(result); 
            	serverSock.accept(null, this);
        	}

            @Override
            public void failed(Throwable exc, Void attachment) {
                log.error("Fail to accept a connection: {}", exc);
            }
        });
    }    
	
}
