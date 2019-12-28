package generator.model;

import lombok.Data;

@Data
public class SkyGeneratorConfig {

    private String entityPath;//类路径
    private String entity; //类
    private String entityName;// 类名
    private String modelPath;//基本路径

    public SkyGeneratorConfig(String modelPath,String entityPath, String entity, String entityName) {
        this.modelPath = modelPath;
        this.entityPath = entityPath;
        this.entity = entity;
        this.entityName = entityName;
    }
}
