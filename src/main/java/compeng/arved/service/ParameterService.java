package compeng.arved.service;

import compeng.arved.domain.Parameter;

import java.util.List;

public interface ParameterService {
    Parameter save(Parameter parameter);
    Parameter findParameterByParamId(String paramId);
    List<Parameter> findAll();
}
