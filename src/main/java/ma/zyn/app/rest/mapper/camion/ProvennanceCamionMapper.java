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
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.ws.dto.camion.ProvennanceCamionDto;

@Component
public class ProvennanceCamionMapper {



    public ProvennanceCamion toItem(ProvennanceCamionDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProvennanceCamion item = new ProvennanceCamion();
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


    public ProvennanceCamionDto toDto(ProvennanceCamion item) {
        if (item == null) {
            return null;
        } else {
            ProvennanceCamionDto dto = new ProvennanceCamionDto();
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



    public List<ProvennanceCamion> toItem(List<ProvennanceCamionDto> dtos) {
        List<ProvennanceCamion> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProvennanceCamionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProvennanceCamionDto> toDto(List<ProvennanceCamion> items) {
        List<ProvennanceCamionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProvennanceCamion item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProvennanceCamionDto dto, ProvennanceCamion t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProvennanceCamion> copy(List<ProvennanceCamionDto> dtos) {
        List<ProvennanceCamion> result = new ArrayList<>();
        if (dtos != null) {
            for (ProvennanceCamionDto dto : dtos) {
                ProvennanceCamion instance = new ProvennanceCamion();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
