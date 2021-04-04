package compeng.arved.service;

import compeng.arved.domain.Parameter;
import compeng.arved.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService{

    private final ParameterRepository parameterRepository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public Parameter save(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    @Override
    public Parameter findParameterByParamId(String paramId) {
        return parameterRepository.findParameterByParamId(paramId);
    }

    @Override
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }
}
