package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.referentiel.UniteMapper;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.ws.mapper.referentiel.ConsommableStadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.ws.dto.referentiel.ConsommableDto;

@Component
public class ConsommableMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private ConsommableStadeOperatoireMapper consommableStadeOperatoireMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    private boolean unite;
    private boolean consommableStadeOperatoires;

    public  ConsommableMapper() {
        init(true);
    }

    public Consommable toItem(ConsommableDto dto) {
        if (dto == null) {
            return null;
        } else {
        Consommable item = new Consommable();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;


            if(this.consommableStadeOperatoires && ListUtil.isNotEmpty(dto.getConsommableStadeOperatoires()))
                item.setConsommableStadeOperatoires(consommableStadeOperatoireMapper.toItem(dto.getConsommableStadeOperatoires()));


        return item;
        }
    }


    public ConsommableDto toDto(Consommable item) {
        if (item == null) {
            return null;
        } else {
            ConsommableDto dto = new ConsommableDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(this.unite && item.getUnite()!=null) {
                dto.setUnite(uniteMapper.toDto(item.getUnite())) ;

            }
        if(this.consommableStadeOperatoires && ListUtil.isNotEmpty(item.getConsommableStadeOperatoires())){
            consommableStadeOperatoireMapper.init(true);
            consommableStadeOperatoireMapper.setConsommable(false);
            dto.setConsommableStadeOperatoires(consommableStadeOperatoireMapper.toDto(item.getConsommableStadeOperatoires()));
            consommableStadeOperatoireMapper.setConsommable(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.consommableStadeOperatoires = value;
    }
    public void initObject(boolean value) {
        this.unite = value;
    }

    public List<Consommable> toItem(List<ConsommableDto> dtos) {
        List<Consommable> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ConsommableDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ConsommableDto> toDto(List<Consommable> items) {
        List<ConsommableDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Consommable item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ConsommableDto dto, Consommable t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
        if (dto.getConsommableStadeOperatoires() != null)
            t.setConsommableStadeOperatoires(consommableStadeOperatoireMapper.copy(dto.getConsommableStadeOperatoires()));
    }

    public List<Consommable> copy(List<ConsommableDto> dtos) {
        List<Consommable> result = new ArrayList<>();
        if (dtos != null) {
            for (ConsommableDto dto : dtos) {
                Consommable instance = new Consommable();
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
    public ConsommableStadeOperatoireMapper getConsommableStadeOperatoireMapper(){
        return this.consommableStadeOperatoireMapper;
    }
    public void setConsommableStadeOperatoireMapper(ConsommableStadeOperatoireMapper consommableStadeOperatoireMapper ){
        this.consommableStadeOperatoireMapper = consommableStadeOperatoireMapper;
    }
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
    public boolean  isConsommableStadeOperatoires(){
        return this.consommableStadeOperatoires ;
    }
    public void  setConsommableStadeOperatoires(boolean consommableStadeOperatoires ){
        this.consommableStadeOperatoires  = consommableStadeOperatoires ;
    }
}
