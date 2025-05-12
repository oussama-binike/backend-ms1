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
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.ws.dto.referentiel.StatusScenarioFluxDto;

@Component
public class StatusScenarioFluxMapper {



    public StatusScenarioFlux toItem(StatusScenarioFluxDto dto) {
        if (dto == null) {
            return null;
        } else {
        StatusScenarioFlux item = new StatusScenarioFlux();
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


    public StatusScenarioFluxDto toDto(StatusScenarioFlux item) {
        if (item == null) {
            return null;
        } else {
            StatusScenarioFluxDto dto = new StatusScenarioFluxDto();
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



    public List<StatusScenarioFlux> toItem(List<StatusScenarioFluxDto> dtos) {
        List<StatusScenarioFlux> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StatusScenarioFluxDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StatusScenarioFluxDto> toDto(List<StatusScenarioFlux> items) {
        List<StatusScenarioFluxDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StatusScenarioFlux item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StatusScenarioFluxDto dto, StatusScenarioFlux t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<StatusScenarioFlux> copy(List<StatusScenarioFluxDto> dtos) {
        List<StatusScenarioFlux> result = new ArrayList<>();
        if (dtos != null) {
            for (StatusScenarioFluxDto dto : dtos) {
                StatusScenarioFlux instance = new StatusScenarioFlux();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
