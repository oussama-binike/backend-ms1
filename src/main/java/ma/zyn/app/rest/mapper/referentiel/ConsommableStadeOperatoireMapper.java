package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.ConsommableMapper;
import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.referentiel.Consommable;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.ws.dto.referentiel.ConsommableStadeOperatoireDto;

@Component
public class ConsommableStadeOperatoireMapper {

    @Autowired
    private ConsommableMapper consommableMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    private boolean stadeOperatoire;
    private boolean consommable;

    public  ConsommableStadeOperatoireMapper() {
        initObject(true);
    }

    public ConsommableStadeOperatoire toItem(ConsommableStadeOperatoireDto dto) {
        if (dto == null) {
            return null;
        } else {
        ConsommableStadeOperatoire item = new ConsommableStadeOperatoire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(dto.getConsommable() != null && dto.getConsommable().getId() != null){
                item.setConsommable(new Consommable());
                item.getConsommable().setId(dto.getConsommable().getId());
                item.getConsommable().setLibelle(dto.getConsommable().getLibelle());
            }




        return item;
        }
    }


    public ConsommableStadeOperatoireDto toDto(ConsommableStadeOperatoire item) {
        if (item == null) {
            return null;
        } else {
            ConsommableStadeOperatoireDto dto = new ConsommableStadeOperatoireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
            if(this.consommable && item.getConsommable()!=null) {
                dto.setConsommable(consommableMapper.toDto(item.getConsommable())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
        this.consommable = value;
    }

    public List<ConsommableStadeOperatoire> toItem(List<ConsommableStadeOperatoireDto> dtos) {
        List<ConsommableStadeOperatoire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ConsommableStadeOperatoireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ConsommableStadeOperatoireDto> toDto(List<ConsommableStadeOperatoire> items) {
        List<ConsommableStadeOperatoireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ConsommableStadeOperatoire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ConsommableStadeOperatoireDto dto, ConsommableStadeOperatoire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getConsommable() == null  && dto.getConsommable() != null){
            t.setConsommable(new Consommable());
        }else if (t.getConsommable() != null  && dto.getConsommable() != null){
            t.setConsommable(null);
            t.setConsommable(new Consommable());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getConsommable() != null)
        consommableMapper.copy(dto.getConsommable(), t.getConsommable());
    }

    public List<ConsommableStadeOperatoire> copy(List<ConsommableStadeOperatoireDto> dtos) {
        List<ConsommableStadeOperatoire> result = new ArrayList<>();
        if (dtos != null) {
            for (ConsommableStadeOperatoireDto dto : dtos) {
                ConsommableStadeOperatoire instance = new ConsommableStadeOperatoire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ConsommableMapper getConsommableMapper(){
        return this.consommableMapper;
    }
    public void setConsommableMapper(ConsommableMapper consommableMapper ){
        this.consommableMapper = consommableMapper;
    }
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isConsommable(){
        return this.consommable;
    }
    public void  setConsommable(boolean consommable){
        this.consommable = consommable;
    }
}
