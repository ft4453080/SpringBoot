package com.highrock.utils.entity;

import java.io.Serializable;

/**
 * Created by user on 2017/8/29.
 */
public interface FileManagerConfig extends Serializable{


    public static final String FILE_DEFAULT_AUTHOR = "Zhangjian";

    public static final String PROTOCOL = "http://";

    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_ADDR = "172.16.1.47";

    public static final String TRACKER_NGNIX_PORT = "";

    public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";

}
