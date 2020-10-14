package com.desmart.eureka_server.EurekaListener;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenConfig {
    private final static Logger logger = LoggerFactory.getLogger(ListenConfig.class);

    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.error("服务{}下线", event.getServerId()+" "+ event.getAppName());
    }

    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("服务{}进行注册", instanceInfo.getAppName()+" "+  instanceInfo.getHostName()+ "(" +instanceInfo.getIPAddr()+":"+ instanceInfo.getPort() + ")");

    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("服务{}进行续约", instanceInfo.getAppName()+" "+  instanceInfo.getHostName()+ "(" +instanceInfo.getIPAddr()+":"+ instanceInfo.getPort() + ")");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("注册中心启动,{}", System.currentTimeMillis());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("注册中心服务端启动,{}", System.currentTimeMillis());
    }
}
