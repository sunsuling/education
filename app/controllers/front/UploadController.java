package controllers.front;

import com.google.common.io.Files;
import play.Configuration;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.SCPService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/8.
 */
@Transactional
public class UploadController extends Controller {

    protected File  exlcelFile;
    protected String fileType;
    private final static List<String> types = new ArrayList<>();
    {
        types.add("XLS");
        types.add("XLSX");
        types.add("xlsx");
        types.add("xls");
    }
    public Result uploadImage() {
        Logger.info("上传文件");
        String server = Configuration.root().getString("nginx.server");
        String user = Configuration.root().getString("nginx.user");
        String password = Configuration.root().getString("nginx.password");
        String imagePath = Configuration.root().getString("nginx.imagePath");
        Http.RequestBody body = request().body();
        try {
            if (body.asMultipartFormData() != null) {
                Http.MultipartFormData multipartFormData = body.asMultipartFormData();
                List<Http.MultipartFormData.FilePart> files = multipartFormData.getFiles();

                if (files != null) {
                    String remoteDir = null;
                    for (Http.MultipartFormData.FilePart file : files) {
                        File orFile = file.getFile();
                        String key = file.getKey();
                        String fileName = file.getFilename();
                        Logger.info("[fileFile.getName()]" + fileName + " key = " + file.getKey());
                        if("image".equals(key)) {
                            remoteDir = imagePath;
                        }
                        SCPService.uploadFile(server, user, password, remoteDir, orFile, fileName);
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("上传文件失败", e);
        }
        return ok("File uploaded successfully");
    }


    public String uploadExcel() {
        Logger.info("上传文件");
        Http.RequestBody body = request().body();
        try {
            if (body.asMultipartFormData() != null) {
                Http.MultipartFormData multipartFormData = body.asMultipartFormData();
                List<Http.MultipartFormData.FilePart> files = multipartFormData.getFiles();
                if (files != null) {
                    for (Http.MultipartFormData.FilePart file : files) {
                        File orFile = file.getFile();
                        String fileName = file.getFilename();
                        Logger.info("[fileFile.getName()]" + fileName + " key = " + file.getKey());
                        Logger.info("[fileFile.type]:" + Files.getFileExtension(fileName));
                        fileType = Files.getFileExtension(fileName);
                        if(!types.contains(fileType)){
                            return "上传文件格式不正确";
                        }
                        exlcelFile = orFile;
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("上传文件失败", e);
        }
        return "ok";
    }
}
