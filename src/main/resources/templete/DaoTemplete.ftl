package ${packageName}.dao;

import java.util.List;
import java.util.Map;
import ${packageName}.model.${modelName};

public interface I${modelName}Dao {
	
	List<${modelName}> select(Map<String, Object> params);
	Long count(Map<String, Object> params);
	int insert(${modelName} ${modelName?uncap_first});
	int update(${modelName} ${modelName?uncap_first});
	int delete(Map<String, Object> params);
}