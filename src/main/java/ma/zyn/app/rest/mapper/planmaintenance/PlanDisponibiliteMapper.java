package  ma.zyn.app.rest.mapper.planmaintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.scenario.ScenarioFluxMapper;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.ws.dto.planmaintenance.PlanDisponibiliteDto;

@Component
public class PlanDisponibiliteMapper {

    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private ScenarioFluxMapper scenarioFluxMapper ;
    private boolean stadeOperatoire;
    private boolean scenarioFlux;

    public  PlanDisponibiliteMapper() {
        initObject(true);
    }

    public PlanDisponibilite toItem(PlanDisponibiliteDto dto) {
        if (dto == null) {
            return null;
        } else {
        PlanDisponibilite item = new PlanDisponibilite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getNombreHeureArret()))
                item.setNombreHeureArret(dto.getNombreHeureArret());
            if(StringUtil.isNotEmpty(dto.getDateArretDebut()))
                item.setDateArretDebut(DateUtil.stringEnToDate(dto.getDateArretDebut()));
            if(StringUtil.isNotEmpty(dto.getDateArretFin()))
                item.setDateArretFin(DateUtil.stringEnToDate(dto.getDateArretFin()));
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(dto.getScenarioFlux() != null && dto.getScenarioFlux().getId() != null){
                item.setScenarioFlux(new ScenarioFlux());
                item.getScenarioFlux().setId(dto.getScenarioFlux().getId());
                item.getScenarioFlux().setLibelle(dto.getScenarioFlux().getLibelle());
            }




        return item;
        }
    }


    public PlanDisponibiliteDto toDto(PlanDisponibilite item) {
        if (item == null) {
            return null;
        } else {
            PlanDisponibiliteDto dto = new PlanDisponibiliteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getNombreHeureArret()))
                dto.setNombreHeureArret(item.getNombreHeureArret());
            if(item.getDateArretDebut()!=null)
                dto.setDateArretDebut(DateUtil.dateTimeToString(item.getDateArretDebut()));
            if(item.getDateArretFin()!=null)
                dto.setDateArretFin(DateUtil.dateTimeToString(item.getDateArretFin()));
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
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
        this.stadeOperatoire = value;
        this.scenarioFlux = value;
    }

    public List<PlanDisponibilite> toItem(List<PlanDisponibiliteDto> dtos) {
        List<PlanDisponibilite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PlanDisponibiliteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PlanDisponibiliteDto> toDto(List<PlanDisponibilite> items) {
        List<PlanDisponibiliteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PlanDisponibilite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PlanDisponibiliteDto dto, PlanDisponibilite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getScenarioFlux() == null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(new ScenarioFlux());
        }else if (t.getScenarioFlux() != null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(null);
            t.setScenarioFlux(new ScenarioFlux());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getScenarioFlux() != null)
        scenarioFluxMapper.copy(dto.getScenarioFlux(), t.getScenarioFlux());
    }

    public List<PlanDisponibilite> copy(List<PlanDisponibiliteDto> dtos) {
        List<PlanDisponibilite> result = new ArrayList<>();
        if (dtos != null) {
            for (PlanDisponibiliteDto dto : dtos) {
                PlanDisponibilite instance = new PlanDisponibilite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public ScenarioFluxMapper getScenarioFluxMapper(){
        return this.scenarioFluxMapper;
    }
    public void setScenarioFluxMapper(ScenarioFluxMapper scenarioFluxMapper ){
        this.scenarioFluxMapper = scenarioFluxMapper;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isScenarioFlux(){
        return this.scenarioFlux;
    }
    public void  setScenarioFlux(boolean scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }
}
