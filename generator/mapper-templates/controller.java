package ${package};

import io.mybatis.common.core.DataResponse;
import io.mybatis.common.core.RowsResponse;

import ${project.attrs.basePackage}.model.${it.name.className};
import ${project.attrs.basePackage}.service.${it.name.className}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${it.name} - ${it.comment}
 *
 * @author ${SYS['user.name']}
 */
@RestController
@RequestMapping("/${it.name.fieldName.s}")
@AllArgsConstructor
@Validated
public class ${it.name.className}Controller {

  @Autowired
  private ${it.name.className}Service ${it.name.fieldName}Service;

  @PostMapping
  public ResponseBean<${it.name.className}> save(@RequestBody ${it.name.className} ${it.name.fieldName}) {
    return ResponseBean.ok(${it.name.fieldName}Service.save( ${it.name.fieldName}));
  }

  @GetMapping
  public ResponseBean<${it.name.className}> findList(@RequestBody ${it.name.className} ${it.name.fieldName}) {
    return ResponseBean.ok(${it.name.fieldName}Service.findList( ${it.name.fieldName}));
  }

  @GetMapping(value = "/{id}")
  public ResponseBean<${it.name.className}> findById(@PathVariable("id") Long id) {
    return ResponseBean.ok(${it.name.fieldName}Service.findById(id));
  }

  @PutMapping(value = "/{id}")
  public ResponseBean<${it.name.className}> update(@PathVariable("id") Long id, @RequestBody ${it.name.className} ${it.name.fieldName}) {
    ${it.name.fieldName}.setId(id);
    return ResponseBean.ok(${it.name.fieldName}Service.update( ${it.name.fieldName}));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseBean<Boolean> deleteById(@PathVariable("id") Long id) {
    return ResponseBean.ok(${it.name.fieldName}Service.deleteById(id) == 1);
  }

}
