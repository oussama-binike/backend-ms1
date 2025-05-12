package  ma.zyn.app.rest.mapper.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.ws.dto.reclamation.ActionEntrepriseDto;

@Component
public class ActionEntrepriseMapper {



    public ActionEntreprise toItem(ActionEntrepriseDto dto) {
        if (dto == null) {
            return null;
        } else {
        ActionEntreprise item = new ActionEntreprise();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public ActionEntrepriseDto toDto(ActionEntreprise item) {
        if (item == null) {
            return null;
        } else {
            ActionEntrepriseDto dto = new ActionEntrepriseDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }



    public List<ActionEntreprise> toItem(List<ActionEntrepriseDto> dtos) {
        List<ActionEntreprise> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ActionEntrepriseDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ActionEntrepriseDto> toDto(List<ActionEntreprise> items) {
        List<ActionEntrepriseDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ActionEntreprise item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ActionEntrepriseDto dto, ActionEntreprise t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ActionEntreprise> copy(List<ActionEntrepriseDto> dtos) {
        List<ActionEntreprise> result = new ArrayList<>();
        if (dtos != null) {
            for (ActionEntrepriseDto dto : dtos) {
                ActionEntreprise instance = new ActionEntreprise();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
