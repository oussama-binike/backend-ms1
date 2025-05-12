package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.StatusExercice;
import ma.zyn.app.ws.dto.referentiel.StatusExerciceDto;

@Component
public class StatusExerciceMapper {



    public StatusExercice toItem(StatusExerciceDto dto) {
        if (dto == null) {
            return null;
        } else {
        StatusExercice item = new StatusExercice();
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


    public StatusExerciceDto toDto(StatusExercice item) {
        if (item == null) {
            return null;
        } else {
            StatusExerciceDto dto = new StatusExerciceDto();
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



    public List<StatusExercice> toItem(List<StatusExerciceDto> dtos) {
        List<StatusExercice> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StatusExerciceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StatusExerciceDto> toDto(List<StatusExercice> items) {
        List<StatusExerciceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StatusExercice item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StatusExerciceDto dto, StatusExercice t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<StatusExercice> copy(List<StatusExerciceDto> dtos) {
        List<StatusExercice> result = new ArrayList<>();
        if (dtos != null) {
            for (StatusExerciceDto dto : dtos) {
                StatusExercice instance = new StatusExercice();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
