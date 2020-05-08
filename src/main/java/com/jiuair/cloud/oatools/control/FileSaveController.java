package com.jiuair.cloud.oatools.control;

import com.jiuair.cloud.oatools.service.FilesaveService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@RestController
public class FileSaveController {

    @Autowired
    FilesaveService filesaveService;

    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/getFile")
    public String setFile() throws Exception {
        filesaveService.file();
        return "ok";
    }


    @GetMapping("/upload")
    public String uploadFile() throws Exception {
        filesaveService.uploadfile2Mongo();
        return "success";
    }


    @GetMapping("/download/{userId}")
    public void getFiles(@PathVariable("userId") String userId, HttpServletResponse response) {
    try {
        GridFSFile gridfs = gridFsTemplate.findOne(Query.query(Criteria.where("metadata.userId").is(userId)));
        response.setHeader("Content-Disposition","attachment;filename=\""+gridfs.getFilename()+"\"");
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoTemplate.getDb());
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridfs.getObjectId());
        GridFsResource resource = new GridFsResource(gridfs,gridFSDownloadStream);
        InputStream inp = resource.getInputStream();
        OutputStream out = response.getOutputStream();
        IOUtils.copy(inp,out);
        } catch (Exception e) {
            e.printStackTrace();
            }
        }

}

