package cn.kgc57.demo.uploadfilesspringboot.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;
public class FileUpload {
    /**
     * 文件上传处理
     *
     * @param file
     * @return
     */
    public static String writeUploadFile(MultipartFile file,String module) {
        String filename = file.getOriginalFilename();
        String realpath = "D:/rentHouse/" +  module +"/";
        File fileDir = new File(realpath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }

        filename = Math.abs(file.getOriginalFilename().hashCode()) + RandonUtils.createRandomString( 4 ) + "." + extname;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return "/" + filename;
    }
}
