package  ma.zyn.app.rest.mapper.camion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.ws.dto.camion.DestinationCamionDto;

@Component
public class DestinationCamionMapper {



    public DestinationCamion toItem(DestinationCamionDto dto) {
        if (dto == null) {
            return null;
        } else {
        DestinationCamion item = new DestinationCamion();
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



        return item;
        }
    }


    public DestinationCamionDto toDto(DestinationCamion item) {
        if (item == null) {
            return null;
        } else {
            DestinationCamionDto dto = new DestinationCamionDto();
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


        return dto;
        }
    }



    public List<DestinationCamion> toItem(List<DestinationCamionDto> dtos) {
        List<DestinationCamion> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DestinationCamionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DestinationCamionDto> toDto(List<DestinationCamion> items) {
        List<DestinationCamionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DestinationCamion item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DestinationCamionDto dto, DestinationCamion t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DestinationCamion> copy(List<DestinationCamionDto> dtos) {
        List<DestinationCamion> result = new ArrayList<>();
        if (dtos != null) {
            for (DestinationCamionDto dto : dtos) {
                DestinationCamion instance = new DestinationCamion();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
