package ${package};

import org.springframework.stereotype.Service;
import io.mybatis.service.AbstractService;
import ${project.attrs.basePackage}.model.${it.name.className};
import ${project.attrs.basePackage}.mapper.${it.name.className}Mapper;

/**
 * ${it.name} - ${it.comment}
 *
 * @author 系统自动生成
 */
@Service
public class  ${it.name.className}Service extends AbstractService<${it.name.className}, Long, ${it.name.className}Mapper> {

}
