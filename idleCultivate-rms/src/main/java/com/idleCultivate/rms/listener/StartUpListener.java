package com.idleCultivate.rms.listener;

import com.idleCultivate.rms.support.util.DataDictUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class StartUpListener implements ApplicationContextAware, ServletContextAware, InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    protected final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    DataDictUtil dataDictUtil;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("缓存初始化。。。");
        dataDictUtil.initialize();
        logger.info("缓存初始化完毕。。。");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }

    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
