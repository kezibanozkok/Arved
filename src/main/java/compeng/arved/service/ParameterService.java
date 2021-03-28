package compeng.arved.service;

import compeng.arved.domain.Parameter;

public interface ParameterService {
    Parameter save(Parameter parameter);
    Parameter findParameterByParamId(String paramId);
}
