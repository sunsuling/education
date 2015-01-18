package controllers.front;

import com.google.common.io.Files;
import play.Configuration;
import play.Logger;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/8.
 */

public class UploadController extends Controller {

    protected File  exlcelFile;
    protected String fileType;
    private final static List<String> types = new ArrayList<String>();
    {
        types.add("XLS");
        types.add("XLSX");
        types.add("xlsx");
        types.add("xls");
    }
    public Result uploadImage() {
        Logger.info("上传文件");

        return ok("File uploaded successfully");
    }


    public String uploadExcel() {
        Logger.info("上传文件");

        return "ok";
    }
}
