package  ma.zyn.app.rest.mapper.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.reclamation.ReclamationMapper;
import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.ws.mapper.referentiel.ElementChimiqueMapper;
import ma.zyn.app.bean.core.referentiel.ElementChimique;

import ma.zyn.app.bean.core.reclamation.Reclamation;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.ws.dto.reclamation.ReclamationElementChimiqueDto;

@Component
public class ReclamationElementChimiqueMapper {

    @Autowired
    private ReclamationMapper reclamationMapper ;
    @Autowired
    private ElementChimiqueMapper elementChimiqueMapper ;
    private boolean reclamation;
    private boolean elementChimique;

    public  ReclamationElementChimiqueMapper() {
        initObject(true);
    }

    public ReclamationElementChimique toItem(ReclamationElementChimiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        ReclamationElementChimique item = new ReclamationElementChimique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getReclamation() != null && dto.getReclamation().getId() != null){
                item.setReclamation(new Reclamation());
                item.getReclamation().setId(dto.getReclamation().getId());
                item.getReclamation().setCode(dto.getReclamation().getCode());
            }

            if(this.elementChimique && dto.getElementChimique()!=null)
                item.setElementChimique(elementChimiqueMapper.toItem(dto.getElementChimique())) ;




        return item;
        }
    }


    public ReclamationElementChimiqueDto toDto(ReclamationElementChimique item) {
        if (item == null) {
            return null;
        } else {
            ReclamationElementChimiqueDto dto = new ReclamationElementChimiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.reclamation && item.getReclamation()!=null) {
                dto.setReclamation(reclamationMapper.toDto(item.getReclamation())) ;

            }
            if(this.elementChimique && item.getElementChimique()!=null) {
                dto.setElementChimique(elementChimiqueMapper.toDto(item.getElementChimique())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.reclamation = value;
        this.elementChimique = value;
    }

    public List<ReclamationElementChimique> toItem(List<ReclamationElementChimiqueDto> dtos) {
        List<ReclamationElementChimique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReclamationElementChimiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReclamationElementChimiqueDto> toDto(List<ReclamationElementChimique> items) {
        List<ReclamationElementChimiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ReclamationElementChimique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReclamationElementChimiqueDto dto, ReclamationElementChimique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getReclamation() == null  && dto.getReclamation() != null){
            t.setReclamation(new Reclamation());
        }else if (t.getReclamation() != null  && dto.getReclamation() != null){
            t.setReclamation(null);
            t.setReclamation(new Reclamation());
        }
        if(t.getElementChimique() == null  && dto.getElementChimique() != null){
            t.setElementChimique(new ElementChimique());
        }else if (t.getElementChimique() != null  && dto.getElementChimique() != null){
            t.setElementChimique(null);
            t.setElementChimique(new ElementChimique());
        }
        if (dto.getReclamation() != null)
        reclamationMapper.copy(dto.getReclamation(), t.getReclamation());
        if (dto.getElementChimique() != null)
        elementChimiqueMapper.copy(dto.getElementChimique(), t.getElementChimique());
    }

    public List<ReclamationElementChimique> copy(List<ReclamationElementChimiqueDto> dtos) {
        List<ReclamationElementChimique> result = new ArrayList<>();
        if (dtos != null) {
            for (ReclamationElementChimiqueDto dto : dtos) {
                ReclamationElementChimique instance = new ReclamationElementChimique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ReclamationMapper getReclamationMapper(){
        return this.reclamationMapper;
    }
    public void setReclamationMapper(ReclamationMapper reclamationMapper ){
        this.reclamationMapper = reclamationMapper;
    }
    public ElementChimiqueMapper getElementChimiqueMapper(){
        return this.elementChimiqueMapper;
    }
    public void setElementChimiqueMapper(ElementChimiqueMapper elementChimiqueMapper ){
        this.elementChimiqueMapper = elementChimiqueMapper;
    }
    public boolean  isReclamation(){
        return this.reclamation;
    }
    public void  setReclamation(boolean reclamation){
        this.reclamation = reclamation;
    }
    public boolean  isElementChimique(){
        return this.elementChimique;
    }
    public void  setElementChimique(boolean elementChimique){
        this.elementChimique = elementChimique;
    }
}
