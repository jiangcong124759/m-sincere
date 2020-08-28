package com.sg.zhsd.uav.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.zhsd.uav.data.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IFileService extends IService<FileDto> {

    /**
     * 文件上传
     * @param file
     * @return
     */
    R uploadFile(MultipartFile file) throws IOException;


    /**
     * 文件下载
     * @param fileId
     * @return
     */
    R downLoadFile(String fileId, HttpServletResponse response);


    /**
     * 获取文件列表
     * @return
     */
    List<FileDto> getFileList();

    /**
     * 读取excel数据，并导入到数据库
     * @param file
     * @return
     */
    Object importExcel(MultipartFile file);
}
