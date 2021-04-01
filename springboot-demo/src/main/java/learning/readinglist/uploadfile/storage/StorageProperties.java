package learning.readinglist.uploadfile.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:配置文件存储路径
 * @Author LinJia
 * @Date 2020/9/3 16:20 
 * @Param 
 * @return 
 **/
@ConfigurationProperties("storage")
@Component
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "D:\\learning\\upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
