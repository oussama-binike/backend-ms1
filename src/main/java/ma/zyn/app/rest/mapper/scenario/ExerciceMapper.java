package  ma.zyn.app.rest.mapper.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StatusExerciceMapper;
import ma.zyn.app.bean.core.referentiel.StatusExercice;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.ws.dto.scenario.ExerciceDto;

@Component
public class ExerciceMapper {

    @Autowired
    private StatusExerciceMapper statusExerciceMapper ;
    private boolean statusExercice;

    public  ExerciceMapper() {
        initObject(true);
    }

    public Exercice toItem(ExerciceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Exercice item = new Exercice();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMois()))
                item.setMois(dto.getMois());
            if(StringUtil.isNotEmpty(dto.getAnnee()))
                item.setAnnee(dto.getAnnee());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getDateRetrospective()))
                item.setDateRetrospective(DateUtil.stringEnToDate(dto.getDateRetrospective()));
            if(this.statusExercice && dto.getStatusExercice()!=null)
                item.setStatusExercice(statusExerciceMapper.toItem(dto.getStatusExercice())) ;




        return item;
        }
    }


    public ExerciceDto toDto(Exercice item) {
        if (item == null) {
            return null;
        } else {
            ExerciceDto dto = new ExerciceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMois()))
                dto.setMois(item.getMois());
            if(StringUtil.isNotEmpty(item.getAnnee()))
                dto.setAnnee(item.getAnnee());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(item.getDateRetrospective()!=null)
                dto.setDateRetrospective(DateUtil.dateTimeToString(item.getDateRetrospective()));
            if(this.statusExercice && item.getStatusExercice()!=null) {
                dto.setStatusExercice(statusExerciceMapper.toDto(item.getStatusExercice())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.statusExercice = value;
    }

    public List<Exercice> toItem(List<ExerciceDto> dtos) {
        List<Exercice> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ExerciceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ExerciceDto> toDto(List<Exercice> items) {
        List<ExerciceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Exercice item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ExerciceDto dto, Exercice t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStatusExercice() == null  && dto.getStatusExercice() != null){
            t.setStatusExercice(new StatusExercice());
        }else if (t.getStatusExercice() != null  && dto.getStatusExercice() != null){
            t.setStatusExercice(null);
            t.setStatusExercice(new StatusExercice());
        }
        if (dto.getStatusExercice() != null)
        statusExerciceMapper.copy(dto.getStatusExercice(), t.getStatusExercice());
    }

    public List<Exercice> copy(List<ExerciceDto> dtos) {
        List<Exercice> result = new ArrayList<>();
        if (dtos != null) {
            for (ExerciceDto dto : dtos) {
                Exercice instance = new Exercice();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StatusExerciceMapper getStatusExerciceMapper(){
        return this.statusExerciceMapper;
    }
    public void setStatusExerciceMapper(StatusExerciceMapper statusExerciceMapper ){
        this.statusExerciceMapper = statusExerciceMapper;
    }
    public boolean  isStatusExercice(){
        return this.statusExercice;
    }
    public void  setStatusExercice(boolean statusExercice){
        this.statusExercice = statusExercice;
    }
}
