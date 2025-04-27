package ${package};

import io.mybatis.mapper.BaseMapper;

import ${project.attrs.basePackage}.model.${it.name.className};

/**
 * ${it.name} - ${it.comment}
 *
 * @author 系统自动生成
 */
@org.apache.ibatis.annotations.Mapper
public interface ${it.name.className}Mapper extends BaseMapper<${it.name.className}, Long> {

}