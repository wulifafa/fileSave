package com.jiuair.cloud.oatools.service;

import com.jiuair.cloud.oatools.mapper.flieSaveMapper;

import com.jiuair.cloud.oatools.tools.url2flie;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author LF
 * @Date 2020/4/27
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （必须）
 */
@Service
public class FilesaveService {

    @Autowired
    flieSaveMapper flieSaveMapper;
    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 通过url 下载文件到指定路径
     * @throws Exception
     */
    public void file() throws Exception {
        String fileurl=null;
        for (int i = 0; i <flieSaveMapper.fileDetail().size(); i++) {
            String place="D:/tmp/oa";
            fileurl=flieSaveMapper.fileDetail().get(i).getFILEREALPATH()
                    .replace("/oa/weaver/ecology",""); //url
            String filename= fileurl.substring(fileurl.lastIndexOf("/")).replace(".zip","");
            String typeName=flieSaveMapper.fileDetail().get(i).getIMAGEFILENAME();
            String type=typeName.substring(typeName.lastIndexOf("."));
            url2flie.downloadFile("http://oa.9air.com:8081"+fileurl,place,filename+".zip");
            String zipName=url2flie.zipUncompress(place+filename+".zip",place+"/"+filename);
            //有的文件有后缀
            if(zipName.contains(".")){
                System.out.println("文件解压自带类型");
            }else
            url2flie.fileNew(place+filename+"/"+filename,place+filename+"/"+filename+type);

        }
    }

    /**
     * 文件转存到MongoDB girdfs
     * @throws FileNotFoundException
     */
    public void uploadfile2Mongo() throws FileNotFoundException {
        String filename;
        for (int i = 0; i < flieSaveMapper.fileDetail().size(); i++) {
            String local="D:/tmp/oa";
            String fileurl=flieSaveMapper.fileDetail().get(i).getFILEREALPATH()
                    .replace("/oa/weaver/ecology",""); //url
            filename= fileurl.substring(fileurl.lastIndexOf("/")).replace(".zip","");

            String typeName=flieSaveMapper.fileDetail().get(i).getIMAGEFILENAME();
            String type=typeName.substring(typeName.lastIndexOf("."));
            File file=new File(local+"/"+filename+filename+type);
            InputStream content = new FileInputStream(file);
            DBObject metadata = new BasicDBObject("userId",filename.replace("/",""));
            gridFsTemplate.store(content, file.getName(),"file/files",metadata);
        }
    }
}
