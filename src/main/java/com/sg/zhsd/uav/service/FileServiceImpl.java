package com.sg.zhsd.uav.service;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.util.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.zhsd.uav.config.FileProperties;
import com.sg.zhsd.uav.data.dto.FileDto;
import com.sg.zhsd.uav.mapper.FileMapper;
import com.sg.zhsd.uav.utils.C;
import com.sg.zhsd.uav.utils.model.ModelListener;
import com.sg.zhsd.uav.utils.model.ReadModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDto> implements IFileService{


    @Autowired
    FileProperties fileProperties;

    /**
     * 上传资源文件
     * @param file
     * @return
     */
    @Override
    public R uploadFile(MultipartFile file) throws IOException {


        //文件Id
        String fileId = UUID.randomUUID().toString();
        //文件md5加密
        String md5Value = DigestUtils.md5Hex(file.getBytes());
        //文件名称
        String fileName = file.getOriginalFilename();
        //上传路径
        String filePath = fileProperties.getRootPath();
        //上传人
        String uploadUserName = "";
        //上传时间
        String uploadTime = DateUtils.format(new Date());

        //上传文件
        File targetFile = new File(filePath, md5Value);

        if (!targetFile.exists()) {
            System.err.println(targetFile.getParentFile());
            if(!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }else{
                boolean mkdir = targetFile.getParentFile().mkdir();
                System.out.println(mkdir);
            }
        }

        //上传
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件上传失败");
            return R.failed("文件上传失败");
        }

        //将上传文件存入数据库
        FileDto sourceFile = new FileDto();
        sourceFile.setFileId(fileId);
        sourceFile.setFileMd5Value(md5Value);
        sourceFile.setFileName(fileName);
        sourceFile.setUploadUserName(uploadUserName);
        sourceFile.setUploadDate(uploadTime);
        save(sourceFile);

        return R.ok(sourceFile);
    }


    /**
     * 资源文件下载
     * @param fileId
     * @return
     */
    @Override
    public R downLoadFile(String fileId,HttpServletResponse response) {

        //数据库查询文件信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("file_id",fileId);

        FileDto resultFile = getOne(queryWrapper);

        String path = fileProperties.getRootPath()+File.separator+resultFile.getFileMd5Value();
        File file = new File(path);

        try {

            downloadFile(file,resultFile.getFileName(),response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return R.failed("下载文件失败");
        }
        return R.ok("下载成功!");
    }


    /**
     * 文件列表查询
     * @return
     */
    @Override
    public List<FileDto> getFileList() {

        List<FileDto> fileDtoList = null;
        try {
            QueryWrapper<FileDto> queryWrapper = new QueryWrapper<>();
            fileDtoList = list(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileDtoList;
    }

    @Override
    public Object importExcel(MultipartFile file){

        try {

            ModelListener listener = new ModelListener();
            ExcelReader reader = EasyExcelFactory.read(file.getInputStream(), ReadModel.class, listener).headRowNumber(1).build();
            reader.read();

            //读取成功,插入数据
            List<ReadModel> insertDataList = listener.getInsertDataList();
            List<FileDto> fileDtoList = C.copyList(insertDataList, FileDto.class);
            saveBatch(fileDtoList);

        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("读取excel失败!");
        }
        return R.ok("操作成功!");
    }


    /**
     * *************************************私有方法*********************************
     */
    /**
     * @param file
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void downloadFile(File file, String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        if (file.exists()) {

            String name = StringUtils.isBlank(fileName) ? file.getName() : fileName;

            response.setContentType("application/force-download");// 设置强制下载不打开
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(name, "utf-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
