package com.highrock.log;

import com.highrock.utils.LogProperties;
import com.highrock.utils.ProfileProperties;
import com.highrock.utils.S3Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by user on 2017/10/11.
 */
@Component
@EnableScheduling
public class BackupLogTask {
    @Autowired
    private LogProperties logProperties;
    @Autowired
    private ProfileProperties profileProperties;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0 0 1/1 * ?")//Every Day
    public void backUpLogToS3(){
        String dirPath = logProperties.getPath();
        File dir = new File(dirPath);
        if (!dir.exists()) {
            logger.info(dir + " not exists");
            return;
        }
        if(dir.isDirectory()){
            File files[] = dir.listFiles();
            for(File f:files){
                logger.info(f.getPath());
                S3Helper.upload(profileProperties.getS3LogBucket(),f.getName(),f.getPath());
            }
        }
    }
}
