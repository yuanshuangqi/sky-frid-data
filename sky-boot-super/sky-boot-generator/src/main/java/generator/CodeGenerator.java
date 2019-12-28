package generator;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import generator.model.SkyGeneratorConfig;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
  public static void main(String[] args) throws Exception {
    String entity = "Order";
    String entityName = "订单";
    String entityPath = "com.ylj.sky.boot.manager.model." + entity.toLowerCase();
    String modelPath = "E:\\angular\\sky-boot-super\\sky-boot-manager\\src\\main\\java\\com\\ylj\\sky\\boot\\manager\\model\\" + entity.toLowerCase();
    SkyGeneratorConfig config = new SkyGeneratorConfig(modelPath,entityPath,entity,entityName);
   generatorManager(config);
  }
  @Test
  public void test1()throws Exception{
    String entity = "Order";
    String entityName = "订单";
    String entityPath = "com.ylj.sky.boot.manager.model." + entity.toLowerCase();
    String modelPath = "E:\\angular\\sky-boot-view\\src\\views\\" + entity.toLowerCase();
    SkyGeneratorConfig config = new SkyGeneratorConfig(modelPath,entityPath,entity,entityName);
    generatorView(config);
  }
  /**
   * manage代码生成
   * @param config 代码生成配置
   */
  public static void generatorManager(SkyGeneratorConfig config) throws Exception{
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
    cfg.setDirectoryForTemplateLoading(new File("E:\\angular\\sky-boot-super\\sky-boot-generator\\src\\templates"));
    cfg.setDefaultEncoding("utf-8");
    Map<String,Object> dataModel = new HashMap<>();
    dataModel.put("entity",config.getEntity());
    dataModel.put("entityPath",config.getEntityPath());
    dataModel.put("entityName",config.getEntityName());
    // 生成controller
    Template template = cfg.getTemplate("controller.ftl");
    String controllerPath = config.getModelPath() + "\\controller";
    FileUtil.mkdir(new File(controllerPath));
    template.process(dataModel,new FileWriter(new File( controllerPath+ "\\" +config.getEntity()+"Controller.java")));
    // 生成iservice
    //创建service路径
    Template iService = cfg.getTemplate("iservice.ftl");
    String iServicePath = config.getModelPath() + "\\service";
    FileUtil.mkdir(new File(iServicePath));
    iService.process(dataModel,new FileWriter(new File(iServicePath+"\\I"+config.getEntity()+"Service.java")));
    // 生成serviceImpl
    Template serviceImpl = cfg.getTemplate("serviceImpl.ftl");
    String serviceImplPath = config.getModelPath() + "\\service\\impl";
    FileUtil.mkdir(new File(serviceImplPath));
    serviceImpl.process(dataModel,new FileWriter(new File(serviceImplPath + "\\" + config.getEntity() + "ServiceImpl.java")));
  //生成repository
    Template repository = cfg.getTemplate("repository.ftl");
    String repositoryPath = config.getModelPath() + "\\repository";
    FileUtil.mkdir(new File(repositoryPath));
    repository.process(dataModel,new FileWriter(new File(repositoryPath + "\\" + config.getEntity() + "Repository.java")));
   // 生成search
    Template search = cfg.getTemplate("search.ftl");
    String searchPath = config.getModelPath() + "\\vo";
    FileUtil.mkdir(new File(searchPath));
    search.process(dataModel,new FileWriter(new File(searchPath + "\\" + config.getEntity() + "Search.java")));
    // 生成entity
    Template entity = cfg.getTemplate("entity.ftl");
    String entityPath = config.getModelPath() + "\\entity";
    FileUtil.mkdir(new File(entityPath));
    entity.process(dataModel,new FileWriter(new File(entityPath + "\\" + config.getEntity() + ".java")));
    //生成query
    Template query = cfg.getTemplate("query.ftl");
    String queryPath = config.getModelPath() + "\\query";
    FileUtil.mkdir(new File(queryPath));
    query.process(dataModel,new FileWriter(new File(queryPath + "\\" + config.getEntity() + "DynamicQueryFactory.java")));
  }

  /**
   * 前台代码生成
   * @param config
   */
  public static void generatorView(SkyGeneratorConfig config) throws Exception{
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
    cfg.setDirectoryForTemplateLoading(new File("E:\\angular\\sky-boot-super\\sky-boot-generator\\src\\templates\\view"));
    cfg.setDefaultEncoding("utf-8");
    Map<String,Object> dataModel = new HashMap<>();
    dataModel.put("entity",config.getEntity());
    dataModel.put("entityPath",config.getEntityPath());
    dataModel.put("entityName",config.getEntityName());
    //生成index
    Template index = cfg.getTemplate("index.ftl");
    String controllerPath = config.getModelPath();
    FileUtil.mkdir(new File(controllerPath));
    index.process(dataModel,new FileWriter(new File( controllerPath+ "\\index.vue")));
    // 生成add
    Template add = cfg.getTemplate("add.ftl");
    String addPath = config.getModelPath() + "\\add";
    FileUtil.mkdir(new File(addPath));
    add.process(dataModel,new FileWriter(new File( addPath+ "\\index.vue")));
    // 生成edit
    Template edit = cfg.getTemplate("edit.ftl");
    String editPath = config.getModelPath() + "\\edit";
    FileUtil.mkdir(new File(editPath));
    edit.process(dataModel,new FileWriter(new File( editPath+ "\\index.vue")));
  }
}
