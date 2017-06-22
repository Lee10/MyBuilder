package ${packageName}

<#list packageList as item>
import ${item};
</#list>

/**
 * Created by lee on ${.now?string["yyyy/MM/dd HH:mm:ss"]}.
 * ${tableComment}
 */
public class ${className?cap_first} {

<#list columns as col>
	/** ${col.columnComment} **/
	private ${col.javaType} ${col.propertyName};
</#list>

<#list columns as col>
	public void set${col.propertyName? cap_first}(${col.javaType} ${col.propertyName}){
		this.${col.propertyName} = ${col.propertyName};
	}
	
	public ${col.javaType} get${col.propertyName? cap_first}(){
		return this.${col.propertyName};
	}
	
</#list>
}