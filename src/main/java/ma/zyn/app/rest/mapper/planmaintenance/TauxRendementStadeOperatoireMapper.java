package  ma.zyn.app.rest.mapper.planmaintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.scenario.ScenarioFluxMapper;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.ws.dto.planmaintenance.TauxRendementStadeOperatoireDto;

@Component
public class TauxRendementStadeOperatoireMapper {

    @Autowired
    private ScenarioFluxMapper scenarioFluxMapper ;
    private boolean scenarioFlux;

    public  TauxRendementStadeOperatoireMapper() {
        initObject(true);
    }

    public TauxRendementStadeOperatoire toItem(TauxRendementStadeOperatoireDto dto) {
        if (dto == null) {
            return null;
        } else {
        TauxRendementStadeOperatoire item = new TauxRendementStadeOperatoire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getJour()))
                item.setJour(DateUtil.stringEnToDate(dto.getJour()));
            if(StringUtil.isNotEmpty(dto.getTauxRendementGlobal()))
                item.setTauxRendementGlobal(dto.getTauxRendementGlobal());
            if(dto.getScenarioFlux() != null && dto.getScenarioFlux().getId() != null){
                item.setScenarioFlux(new ScenarioFlux());
                item.getScenarioFlux().setId(dto.getScenarioFlux().getId());
                item.getScenarioFlux().setLibelle(dto.getScenarioFlux().getLibelle());
            }




        return item;
        }
    }


    public TauxRendementStadeOperatoireDto toDto(TauxRendementStadeOperatoire item) {
        if (item == null) {
            return null;
        } else {
            TauxRendementStadeOperatoireDto dto = new TauxRendementStadeOperatoireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getJour()!=null)
                dto.setJour(DateUtil.dateTimeToString(item.getJour()));
            if(StringUtil.isNotEmpty(item.getTauxRendementGlobal()))
                dto.setTauxRendementGlobal(item.getTauxRendementGlobal());
            if(this.scenarioFlux && item.getScenarioFlux()!=null) {
                dto.setScenarioFlux(scenarioFluxMapper.toDto(item.getScenarioFlux())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.scenarioFlux = value;
    }

    public List<TauxRendementStadeOperatoire> toItem(List<TauxRendementStadeOperatoireDto> dtos) {
        List<TauxRendementStadeOperatoire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TauxRendementStadeOperatoireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TauxRendementStadeOperatoireDto> toDto(List<TauxRendementStadeOperatoire> items) {
        List<TauxRendementStadeOperatoireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TauxRendementStadeOperatoire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TauxRendementStadeOperatoireDto dto, TauxRendementStadeOperatoire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getScenarioFlux() == null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(new ScenarioFlux());
        }else if (t.getScenarioFlux() != null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(null);
            t.setScenarioFlux(new ScenarioFlux());
        }
        if (dto.getScenarioFlux() != null)
        scenarioFluxMapper.copy(dto.getScenarioFlux(), t.getScenarioFlux());
    }

    public List<TauxRendementStadeOperatoire> copy(List<TauxRendementStadeOperatoireDto> dtos) {
        List<TauxRendementStadeOperatoire> result = new ArrayList<>();
        if (dtos != null) {
            for (TauxRendementStadeOperatoireDto dto : dtos) {
                TauxRendementStadeOperatoire instance = new TauxRendementStadeOperatoire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ScenarioFluxMapper getScenarioFluxMapper(){
        return this.scenarioFluxMapper;
    }
    public void setScenarioFluxMapper(ScenarioFluxMapper scenarioFluxMapper ){
        this.scenarioFluxMapper = scenarioFluxMapper;
    }
    public boolean  isScenarioFlux(){
        return this.scenarioFlux;
    }
    public void  setScenarioFlux(boolean scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }
}
