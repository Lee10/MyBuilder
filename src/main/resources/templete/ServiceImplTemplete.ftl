package ${packageName}.service.impl;

import java.util.List;
import java.util.Map;
import ${packageName}.model.${modelName};

@Service
public class ${modelName}ServiceImpl implements I${modelName}Service {

	@Resource
	private I${modelName}Dao ${modelName?uncap_first}Dao;

	@Override
	public List<${modelName}> list(Map<String, Object> params){

		List<${modelName}> dataList = new ArrayList<${modelName}>();
		try{
			dataList.addAll(this.${modelName?uncap_first}Dao.select(params));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public Long count(Map<String, Object> params){

		int row = 0;
		try{
			row = this.${modelName?uncap_first}Dao.count(params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public boolean add(${modelName} ${modelName?uncap_first}){
		if(${modelName?uncap_first} == null) return false;

		int row = 0;
		try{
			row = this.${modelName?uncap_first}Dao.insert(${modelName?uncap_first});
		}catch(Exception e){
			e.printStackTrace();
		}
		return row > 0;
	}

	@Override
	public boolean update(${modelName} ${modelName?uncap_first}){
		if(${modelName?uncap_first} == null) return false;

		int row = 0;
		try{
			row = this.${modelName?uncap_first}Dao.update(${modelName?uncap_first});
		}catch(Exception e){
			e.printStackTrace();
		}
		return row > 0;
	}

	@Override
	public boolean remove(Map<String, Object> params){

		int row = 0;
		try{
			row = this.${modelName?uncap_first}Dao.delete(params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return row > 0;
	}

}