package  ma.zyn.app.rest.mapper.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.LiaisonMapper;
import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.ws.mapper.scenario.ScenarioFluxMapper;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.ws.dto.stock.SuiviStockDto;

@Component
public class SuiviStockMapper {

    @Autowired
    private LiaisonMapper liaisonMapper ;
    @Autowired
    private ScenarioFluxMapper scenarioFluxMapper ;
    private boolean liaison;
    private boolean scenarioFlux;

    public  SuiviStockMapper() {
        initObject(true);
    }

    public SuiviStock toItem(SuiviStockDto dto) {
        if (dto == null) {
            return null;
        } else {
        SuiviStock item = new SuiviStock();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getVolumeReel()))
                item.setVolumeReel(dto.getVolumeReel());
            if(StringUtil.isNotEmpty(dto.getVolumeEstime()))
                item.setVolumeEstime(dto.getVolumeEstime());
            if(StringUtil.isNotEmpty(dto.getDateFlux()))
                item.setDateFlux(DateUtil.stringEnToDate(dto.getDateFlux()));
            if(StringUtil.isNotEmpty(dto.getRepereSourceDebut()))
                item.setRepereSourceDebut(dto.getRepereSourceDebut());
            if(StringUtil.isNotEmpty(dto.getRepereSourceFin()))
                item.setRepereSourceFin(dto.getRepereSourceFin());
            if(StringUtil.isNotEmpty(dto.getRepereDestinationDebut()))
                item.setRepereDestinationDebut(dto.getRepereDestinationDebut());
            if(StringUtil.isNotEmpty(dto.getRepereDestinationFin()))
                item.setRepereDestinationFin(dto.getRepereDestinationFin());
            if(StringUtil.isNotEmpty(dto.getPositionRouePelle()))
                item.setPositionRouePelle(dto.getPositionRouePelle());
            if(StringUtil.isNotEmpty(dto.getPositionStacker()))
                item.setPositionStacker(dto.getPositionStacker());
            if(this.liaison && dto.getLiaison()!=null)
                item.setLiaison(liaisonMapper.toItem(dto.getLiaison())) ;

            if(dto.getScenarioFlux() != null && dto.getScenarioFlux().getId() != null){
                item.setScenarioFlux(new ScenarioFlux());
                item.getScenarioFlux().setId(dto.getScenarioFlux().getId());
                item.getScenarioFlux().setLibelle(dto.getScenarioFlux().getLibelle());
            }




        return item;
        }
    }


    public SuiviStockDto toDto(SuiviStock item) {
        if (item == null) {
            return null;
        } else {
            SuiviStockDto dto = new SuiviStockDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getVolumeReel()))
                dto.setVolumeReel(item.getVolumeReel());
            if(StringUtil.isNotEmpty(item.getVolumeEstime()))
                dto.setVolumeEstime(item.getVolumeEstime());
            if(item.getDateFlux()!=null)
                dto.setDateFlux(DateUtil.dateTimeToString(item.getDateFlux()));
            if(StringUtil.isNotEmpty(item.getRepereSourceDebut()))
                dto.setRepereSourceDebut(item.getRepereSourceDebut());
            if(StringUtil.isNotEmpty(item.getRepereSourceFin()))
                dto.setRepereSourceFin(item.getRepereSourceFin());
            if(StringUtil.isNotEmpty(item.getRepereDestinationDebut()))
                dto.setRepereDestinationDebut(item.getRepereDestinationDebut());
            if(StringUtil.isNotEmpty(item.getRepereDestinationFin()))
                dto.setRepereDestinationFin(item.getRepereDestinationFin());
            if(StringUtil.isNotEmpty(item.getPositionRouePelle()))
                dto.setPositionRouePelle(item.getPositionRouePelle());
            if(StringUtil.isNotEmpty(item.getPositionStacker()))
                dto.setPositionStacker(item.getPositionStacker());
            if(this.liaison && item.getLiaison()!=null) {
                dto.setLiaison(liaisonMapper.toDto(item.getLiaison())) ;

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
        this.liaison = value;
        this.scenarioFlux = value;
    }

    public List<SuiviStock> toItem(List<SuiviStockDto> dtos) {
        List<SuiviStock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SuiviStockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SuiviStockDto> toDto(List<SuiviStock> items) {
        List<SuiviStockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SuiviStock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SuiviStockDto dto, SuiviStock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getLiaison() == null  && dto.getLiaison() != null){
            t.setLiaison(new Liaison());
        }else if (t.getLiaison() != null  && dto.getLiaison() != null){
            t.setLiaison(null);
            t.setLiaison(new Liaison());
        }
        if(t.getScenarioFlux() == null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(new ScenarioFlux());
        }else if (t.getScenarioFlux() != null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(null);
            t.setScenarioFlux(new ScenarioFlux());
        }
        if (dto.getLiaison() != null)
        liaisonMapper.copy(dto.getLiaison(), t.getLiaison());
        if (dto.getScenarioFlux() != null)
        scenarioFluxMapper.copy(dto.getScenarioFlux(), t.getScenarioFlux());
    }

    public List<SuiviStock> copy(List<SuiviStockDto> dtos) {
        List<SuiviStock> result = new ArrayList<>();
        if (dtos != null) {
            for (SuiviStockDto dto : dtos) {
                SuiviStock instance = new SuiviStock();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public LiaisonMapper getLiaisonMapper(){
        return this.liaisonMapper;
    }
    public void setLiaisonMapper(LiaisonMapper liaisonMapper ){
        this.liaisonMapper = liaisonMapper;
    }
    public ScenarioFluxMapper getScenarioFluxMapper(){
        return this.scenarioFluxMapper;
    }
    public void setScenarioFluxMapper(ScenarioFluxMapper scenarioFluxMapper ){
        this.scenarioFluxMapper = scenarioFluxMapper;
    }
    public boolean  isLiaison(){
        return this.liaison;
    }
    public void  setLiaison(boolean liaison){
        this.liaison = liaison;
    }
    public boolean  isScenarioFlux(){
        return this.scenarioFlux;
    }
    public void  setScenarioFlux(boolean scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }
}
