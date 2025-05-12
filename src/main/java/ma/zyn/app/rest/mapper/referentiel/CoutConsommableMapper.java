package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.UniteMapper;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.ws.mapper.referentiel.ConsommableMapper;
import ma.zyn.app.bean.core.referentiel.Consommable;

import ma.zyn.app.bean.core.referentiel.Consommable;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import ma.zyn.app.ws.dto.referentiel.CoutConsommableDto;

@Component
public class CoutConsommableMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private ConsommableMapper consommableMapper ;
    private boolean consommable;
    private boolean unite;

    public  CoutConsommableMapper() {
        initObject(true);
    }

    public CoutConsommable toItem(CoutConsommableDto dto) {
        if (dto == null) {
            return null;
        } else {
        CoutConsommable item = new CoutConsommable();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getCoutUnitaire()))
                item.setCoutUnitaire(dto.getCoutUnitaire());
            if(dto.getConsommable() != null && dto.getConsommable().getId() != null){
                item.setConsommable(new Consommable());
                item.getConsommable().setId(dto.getConsommable().getId());
                item.getConsommable().setLibelle(dto.getConsommable().getLibelle());
            }

            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;




        return item;
        }
    }


    public CoutConsommableDto toDto(CoutConsommable item) {
        if (item == null) {
            return null;
        } else {
            CoutConsommableDto dto = new CoutConsommableDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCoutUnitaire()))
                dto.setCoutUnitaire(item.getCoutUnitaire());
            if(this.consommable && item.getConsommable()!=null) {
                dto.setConsommable(consommableMapper.toDto(item.getConsommable())) ;

            }
            if(this.unite && item.getUnite()!=null) {
                dto.setUnite(uniteMapper.toDto(item.getUnite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.consommable = value;
        this.unite = value;
    }

    public List<CoutConsommable> toItem(List<CoutConsommableDto> dtos) {
        List<CoutConsommable> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CoutConsommableDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CoutConsommableDto> toDto(List<CoutConsommable> items) {
        List<CoutConsommableDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CoutConsommable item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CoutConsommableDto dto, CoutConsommable t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getConsommable() == null  && dto.getConsommable() != null){
            t.setConsommable(new Consommable());
        }else if (t.getConsommable() != null  && dto.getConsommable() != null){
            t.setConsommable(null);
            t.setConsommable(new Consommable());
        }
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if (dto.getConsommable() != null)
        consommableMapper.copy(dto.getConsommable(), t.getConsommable());
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
    }

    public List<CoutConsommable> copy(List<CoutConsommableDto> dtos) {
        List<CoutConsommable> result = new ArrayList<>();
        if (dtos != null) {
            for (CoutConsommableDto dto : dtos) {
                CoutConsommable instance = new CoutConsommable();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public UniteMapper getUniteMapper(){
        return this.uniteMapper;
    }
    public void setUniteMapper(UniteMapper uniteMapper ){
        this.uniteMapper = uniteMapper;
    }
    public ConsommableMapper getConsommableMapper(){
        return this.consommableMapper;
    }
    public void setConsommableMapper(ConsommableMapper consommableMapper ){
        this.consommableMapper = consommableMapper;
    }
    public boolean  isConsommable(){
        return this.consommable;
    }
    public void  setConsommable(boolean consommable){
        this.consommable = consommable;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
}
