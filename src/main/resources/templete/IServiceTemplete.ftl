package ${packageName}.service;

import java.util.List;
import java.util.Map;
import ${packageName}.model.${modelName};

public interface I${modelName}Servcie {

	List<${modelName}> list(Map<String, Object> params);
	Long count(Map<String, Object> params);
	boolean add(${modelName} ${modelName?uncap_first});
	boolean update(${modelName} ${modelName?uncap_first});
	boolean remove(Map<String, Object> params);
}