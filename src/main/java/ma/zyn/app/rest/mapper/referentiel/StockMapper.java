package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.TypeStockMapper;
import ma.zyn.app.bean.core.referentiel.TypeStock;
import ma.zyn.app.ws.mapper.referentiel.CategorieStockMapper;
import ma.zyn.app.bean.core.referentiel.CategorieStock;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.ws.dto.referentiel.StockDto;

@Component
public class StockMapper {

    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private TypeStockMapper typeStockMapper ;
    @Autowired
    private CategorieStockMapper categorieStockMapper ;
    private boolean stadeOperatoire;
    private boolean typeStock;
    private boolean categorieStock;

    public  StockMapper() {
        initObject(true);
    }

    public Stock toItem(StockDto dto) {
        if (dto == null) {
            return null;
        } else {
        Stock item = new Stock();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getAutonomie()))
                item.setAutonomie(dto.getAutonomie());
            if(StringUtil.isNotEmpty(dto.getCapacite()))
                item.setCapacite(dto.getCapacite());
            if(StringUtil.isNotEmpty(dto.getNombreRepere()))
                item.setNombreRepere(dto.getNombreRepere());
            if(StringUtil.isNotEmpty(dto.getRepereDebut()))
                item.setRepereDebut(dto.getRepereDebut());
            if(StringUtil.isNotEmpty(dto.getRepereFin()))
                item.setRepereFin(dto.getRepereFin());
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.typeStock && dto.getTypeStock()!=null)
                item.setTypeStock(typeStockMapper.toItem(dto.getTypeStock())) ;

            if(this.categorieStock && dto.getCategorieStock()!=null)
                item.setCategorieStock(categorieStockMapper.toItem(dto.getCategorieStock())) ;




        return item;
        }
    }


    public StockDto toDto(Stock item) {
        if (item == null) {
            return null;
        } else {
            StockDto dto = new StockDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getAutonomie()))
                dto.setAutonomie(item.getAutonomie());
            if(StringUtil.isNotEmpty(item.getCapacite()))
                dto.setCapacite(item.getCapacite());
            if(StringUtil.isNotEmpty(item.getNombreRepere()))
                dto.setNombreRepere(item.getNombreRepere());
            if(StringUtil.isNotEmpty(item.getRepereDebut()))
                dto.setRepereDebut(item.getRepereDebut());
            if(StringUtil.isNotEmpty(item.getRepereFin()))
                dto.setRepereFin(item.getRepereFin());
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
            if(this.typeStock && item.getTypeStock()!=null) {
                dto.setTypeStock(typeStockMapper.toDto(item.getTypeStock())) ;

            }
            if(this.categorieStock && item.getCategorieStock()!=null) {
                dto.setCategorieStock(categorieStockMapper.toDto(item.getCategorieStock())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
        this.typeStock = value;
        this.categorieStock = value;
    }

    public List<Stock> toItem(List<StockDto> dtos) {
        List<Stock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StockDto> toDto(List<Stock> items) {
        List<StockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Stock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StockDto dto, Stock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getTypeStock() == null  && dto.getTypeStock() != null){
            t.setTypeStock(new TypeStock());
        }else if (t.getTypeStock() != null  && dto.getTypeStock() != null){
            t.setTypeStock(null);
            t.setTypeStock(new TypeStock());
        }
        if(t.getCategorieStock() == null  && dto.getCategorieStock() != null){
            t.setCategorieStock(new CategorieStock());
        }else if (t.getCategorieStock() != null  && dto.getCategorieStock() != null){
            t.setCategorieStock(null);
            t.setCategorieStock(new CategorieStock());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getTypeStock() != null)
        typeStockMapper.copy(dto.getTypeStock(), t.getTypeStock());
        if (dto.getCategorieStock() != null)
        categorieStockMapper.copy(dto.getCategorieStock(), t.getCategorieStock());
    }

    public List<Stock> copy(List<StockDto> dtos) {
        List<Stock> result = new ArrayList<>();
        if (dtos != null) {
            for (StockDto dto : dtos) {
                Stock instance = new Stock();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public TypeStockMapper getTypeStockMapper(){
        return this.typeStockMapper;
    }
    public void setTypeStockMapper(TypeStockMapper typeStockMapper ){
        this.typeStockMapper = typeStockMapper;
    }
    public CategorieStockMapper getCategorieStockMapper(){
        return this.categorieStockMapper;
    }
    public void setCategorieStockMapper(CategorieStockMapper categorieStockMapper ){
        this.categorieStockMapper = categorieStockMapper;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isTypeStock(){
        return this.typeStock;
    }
    public void  setTypeStock(boolean typeStock){
        this.typeStock = typeStock;
    }
    public boolean  isCategorieStock(){
        return this.categorieStock;
    }
    public void  setCategorieStock(boolean categorieStock){
        this.categorieStock = categorieStock;
    }
}
