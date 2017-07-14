<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.I${modelName}Dao">

	<resultMap id="objMap" type="${packageName}.model.${modelName}">
	<#list columns as col>
		<result property="${col.propertyName}" column="${col.columnName}" jdbcType="${col.columnType}"/>
	</#list>
	</resultMap>

	<sql id="allField">
	<#list columns as col>
		${col.columnName}<#if col_has_next>,</#if>
	</#list>
	</sql>

	<sql id="conditions">
	<#list columns as col>
			<if test="${col.propertyName} != null">and ${col.columnName} = ${r"#{"}${col.propertyName}}</if>
		<#if col.javaType == "String">
			<#if dbType == "mysql">
			<if test="${col.propertyName}Like != null">and ${col.columnName} = concat("%", ${r"#{"}${col.propertyName}Like}, "%")</if>
			<#elseif dbType == "oracle">
			<if test="${col.propertyName}Like != null">and ${col.columnName} = ("%"||${r"#{"}${col.propertyName}Like}||"%")</if>
			</#if>
		</#if>
		<#if col.javaType == "Date">
			<if test="${col.propertyName}Gte != null">and ${col.columnName} >= ${r"#{"}${col.propertyName}Gte}</if>
			<if test="${col.propertyName}Lte != null"><![CDATA[ and ${col.columnName} <= ${r"#{"}${col.propertyName}Lte}]]></if>
		</#if>
	</#list>
	</sql>

	<select id="select" parameterType="hashmap" resultMap="objMap">
		select
		<include refid="allField"/>
		from ${tableName}
		<where>
			<include refid="conditions"/>
		</where>
	</select>

	<select id="count" parameterType="hashmap" resultType="Long">
		select
			count(*)
		from ${tableName}
		<where>
			<include refid="conditions"/>
		</where>
	</select>

	<insert id="insert" parameterType="${modelName?uncap_first}">
		insert into ${tableName}(
		<include refid="allField"/>
		)values(
			<#list columns as col>
			${r"#{"}${col.columnName}, jdbcType="${col.columnType}"}<#if col_has_next>,</#if>
			</#list>
		)
	</insert>

	<update id="update" parameterType="${modelName?uncap_first}">
		update ${tableName}
		<set>
			<#list columns as col>
			<if test="${col.propertyName} != null">${col.columnName} = ${r"#{"}${col.propertyName}, jdbcType="${col.columnType}"}<#if col_has_next>,</#if></if>
			</#list>
		</set>
	</update>

	<delete id="delete" parameterType="hashmap">
		delete from ${tableName}
		<where>
			<include refid="conditions"/>
		</where>
	</delete>

</mapper>