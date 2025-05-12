package  ma.zyn.app.rest.mapper.navire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.ws.dto.navire.DestinationNavireDto;

@Component
public class DestinationNavireMapper {



    public DestinationNavire toItem(DestinationNavireDto dto) {
        if (dto == null) {
            return null;
        } else {
        DestinationNavire item = new DestinationNavire();
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


    public DestinationNavireDto toDto(DestinationNavire item) {
        if (item == null) {
            return null;
        } else {
            DestinationNavireDto dto = new DestinationNavireDto();
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



    public List<DestinationNavire> toItem(List<DestinationNavireDto> dtos) {
        List<DestinationNavire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DestinationNavireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DestinationNavireDto> toDto(List<DestinationNavire> items) {
        List<DestinationNavireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DestinationNavire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DestinationNavireDto dto, DestinationNavire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DestinationNavire> copy(List<DestinationNavireDto> dtos) {
        List<DestinationNavire> result = new ArrayList<>();
        if (dtos != null) {
            for (DestinationNavireDto dto : dtos) {
                DestinationNavire instance = new DestinationNavire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
