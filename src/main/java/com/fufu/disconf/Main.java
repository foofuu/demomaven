package com.fufu.disconf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujunfu on 2018/5/28.
 */
public class Main {
    private static String WEB_DB = "E:\\163\\cms-publish\\cms-publish-web\\src\\main\\resources-common\\channeldb.properties";
    private static String WEB_HOSTS = "E:\\163\\cms-publish\\cms-publish-web\\src\\main\\resources-common\\hosts";
    private static String WEBSERVICE_DB = "/home/appops/cms-publish/cms-publish-webservice/src/main/resources-common/channeldb.properties";
    private static String WEBSERVICE_HOSTS = "/home/appops/cms-publish/cms-publish-webservice/src/main/resources-common/hosts";

    public static void main(String[] args) throws Exception {
        DisconfInfo disconfInfo = DisconfOperator.getDisconfInfo();
        DisconfOperator.checkDisconfInfo(disconfInfo);

        DisconfOperator disconfOperator = new DisconfOperator();
        disconfOperator.login(disconfInfo);

        boolean isAppExist = disconfOperator.checkAppExist(disconfInfo);
        if(!isAppExist){
            throw new RuntimeException(disconfInfo.getEnv() + " 应用不存在");
        }

        boolean isEnvExist = disconfOperator.checkEnvExist(disconfInfo);
        if(!isEnvExist){
            throw new RuntimeException(disconfInfo.getEnv() + " 环境不存在");
        }

        List<FileStream> fileStreamList = new ArrayList<>();
        InputStream inputStreamDb = new FileInputStream(new File(WEB_DB));
        FileStream fileStreamDb = new FileStream();
        fileStreamDb.setFileName("channeldb.properties");
        fileStreamDb.setInputStream(inputStreamDb);
        fileStreamDb.setFileCode(160);

        InputStream inputStreamHosts = new FileInputStream(new File(WEB_HOSTS));
        FileStream fileStreamHosts = new FileStream();
        fileStreamHosts.setFileName("hosts");
        fileStreamHosts.setInputStream(inputStreamHosts);
        fileStreamHosts.setFileCode(159);

        fileStreamList.add(fileStreamDb);
        fileStreamList.add(fileStreamHosts);

        disconfOperator.uploadDisconfFileConf(disconfInfo, fileStreamList);


        disconfOperator.uploadDisconfFileConf(disconfInfo, fileStreamList);
    }
}
