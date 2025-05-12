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
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;

import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import ma.zyn.app.ws.dto.referentiel.ConsommationSpecifiqueDto;

@Component
public class ConsommationSpecifiqueMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private ConsommableMapper consommableMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    private boolean consommable;
    private boolean stadeOperatoire;
    private boolean unite;

    public  ConsommationSpecifiqueMapper() {
        initObject(true);
    }

    public ConsommationSpecifique toItem(ConsommationSpecifiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        ConsommationSpecifique item = new ConsommationSpecifique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getValeur()))
                item.setValeur(dto.getValeur());
            if(StringUtil.isNotEmpty(dto.getDateConsommationSpecifique()))
                item.setDateConsommationSpecifique(DateUtil.stringEnToDate(dto.getDateConsommationSpecifique()));
            if(dto.getConsommable() != null && dto.getConsommable().getId() != null){
                item.setConsommable(new Consommable());
                item.getConsommable().setId(dto.getConsommable().getId());
                item.getConsommable().setLibelle(dto.getConsommable().getLibelle());
            }

            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;




        return item;
        }
    }


    public ConsommationSpecifiqueDto toDto(ConsommationSpecifique item) {
        if (item == null) {
            return null;
        } else {
            ConsommationSpecifiqueDto dto = new ConsommationSpecifiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getValeur()))
                dto.setValeur(item.getValeur());
            if(item.getDateConsommationSpecifique()!=null)
                dto.setDateConsommationSpecifique(DateUtil.dateTimeToString(item.getDateConsommationSpecifique()));
            if(this.consommable && item.getConsommable()!=null) {
                dto.setConsommable(consommableMapper.toDto(item.getConsommable())) ;

            }
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

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
        this.stadeOperatoire = value;
        this.unite = value;
    }

    public List<ConsommationSpecifique> toItem(List<ConsommationSpecifiqueDto> dtos) {
        List<ConsommationSpecifique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ConsommationSpecifiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ConsommationSpecifiqueDto> toDto(List<ConsommationSpecifique> items) {
        List<ConsommationSpecifiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ConsommationSpecifique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ConsommationSpecifiqueDto dto, ConsommationSpecifique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getConsommable() == null  && dto.getConsommable() != null){
            t.setConsommable(new Consommable());
        }else if (t.getConsommable() != null  && dto.getConsommable() != null){
            t.setConsommable(null);
            t.setConsommable(new Consommable());
        }
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if (dto.getConsommable() != null)
        consommableMapper.copy(dto.getConsommable(), t.getConsommable());
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
    }

    public List<ConsommationSpecifique> copy(List<ConsommationSpecifiqueDto> dtos) {
        List<ConsommationSpecifique> result = new ArrayList<>();
        if (dtos != null) {
            for (ConsommationSpecifiqueDto dto : dtos) {
                ConsommationSpecifique instance = new ConsommationSpecifique();
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
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public boolean  isConsommable(){
        return this.consommable;
    }
    public void  setConsommable(boolean consommable){
        this.consommable = consommable;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
}
