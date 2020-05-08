package com.jiuair.cloud.oatools.mapper;

import com.jiuair.cloud.oatools.entity.docDetailContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author LF
 * @Date 2020/4/27
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （必须）
 */
@Repository
public interface flieSaveMapper {
    List<docDetailContent> fileDetail();
}
