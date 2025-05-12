package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.EntiteMapper;
import ma.zyn.app.bean.core.referentiel.Entite;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Panneau;
import ma.zyn.app.ws.dto.referentiel.PanneauDto;

@Component
public class PanneauMapper {

    @Autowired
    private EntiteMapper entiteMapper ;
    private boolean entite;

    public  PanneauMapper() {
        initObject(true);
    }

    public Panneau toItem(PanneauDto dto) {
        if (dto == null) {
            return null;
        } else {
        Panneau item = new Panneau();
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
            if(this.entite && dto.getEntite()!=null)
                item.setEntite(entiteMapper.toItem(dto.getEntite())) ;




        return item;
        }
    }


    public PanneauDto toDto(Panneau item) {
        if (item == null) {
            return null;
        } else {
            PanneauDto dto = new PanneauDto();
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
            if(this.entite && item.getEntite()!=null) {
                dto.setEntite(entiteMapper.toDto(item.getEntite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.entite = value;
    }

    public List<Panneau> toItem(List<PanneauDto> dtos) {
        List<Panneau> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PanneauDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PanneauDto> toDto(List<Panneau> items) {
        List<PanneauDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Panneau item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PanneauDto dto, Panneau t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEntite() == null  && dto.getEntite() != null){
            t.setEntite(new Entite());
        }else if (t.getEntite() != null  && dto.getEntite() != null){
            t.setEntite(null);
            t.setEntite(new Entite());
        }
        if (dto.getEntite() != null)
        entiteMapper.copy(dto.getEntite(), t.getEntite());
    }

    public List<Panneau> copy(List<PanneauDto> dtos) {
        List<Panneau> result = new ArrayList<>();
        if (dtos != null) {
            for (PanneauDto dto : dtos) {
                Panneau instance = new Panneau();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EntiteMapper getEntiteMapper(){
        return this.entiteMapper;
    }
    public void setEntiteMapper(EntiteMapper entiteMapper ){
        this.entiteMapper = entiteMapper;
    }
    public boolean  isEntite(){
        return this.entite;
    }
    public void  setEntite(boolean entite){
        this.entite = entite;
    }
}
