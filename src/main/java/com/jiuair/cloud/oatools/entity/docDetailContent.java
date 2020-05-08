package com.jiuair.cloud.oatools.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author LF
 * @Date 2020/4/27
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （必须）
 */


@Data
public class docDetailContent {

    private int IMAGEFILEID;

    private String IMAGEFILENAME;

    private String IMAGEFILETYPE;

    private byte[] IMAGEFILE;

    private int IMAGEFILEUSED;

    private String FILEREALPATH;

    private String ISZIP;

    private String ISENCRYPT;

    private String FILESIZE;

    private int DOWNLOADS;

    private String MINIIMGPATH;

    private String IMGSIZE;

    private String ISFTP;

    private int FTPCONFIGID;

    private int ISAESENCRYPT;

    private String AESCODE;

    private  String TOKENKEY;

    private  String STORAGESTATUS;

    private String COMEFROM;

    private int OBJID;

    private String OBJOTHERPARA;

    private String DELFILEREALPATH;

}
