package  ma.zyn.app.api.facade.admin.supply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.service.facade.admin.supply.SuiviProductionAdminService;
import ma.zyn.app.ws.mapper.supply.SuiviProductionMapper;
import ma.zyn.app.ws.dto.supply.SuiviProductionDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/suiviProduction/")
public class SuiviProductionRestAdmin {




    @Operation(summary = "Finds a list of all suiviProductions")
    @GetMapping("")
    public ResponseEntity<List<SuiviProductionDto>> findAll(){
        ResponseEntity<List<SuiviProductionDto>> res = null;
        List<SuiviProduction> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<SuiviProductionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all suiviProductions")
    @GetMapping("optimized")
    public ResponseEntity<List<SuiviProductionDto>> findAllOptimized(){
        ResponseEntity<List<SuiviProductionDto>> res = null;
        List<SuiviProduction> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<SuiviProductionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a suiviProduction by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SuiviProductionDto> findById(@PathVariable Long id) {
        SuiviProduction t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            SuiviProductionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a suiviProduction by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SuiviProductionDto> findByLibelle(@PathVariable String libelle) {
	    SuiviProduction t = service.findByReferenceEntity(new SuiviProduction(libelle));
        if (t != null) {
            mapper.init(true);
            SuiviProductionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  suiviProduction")
    @PostMapping("")
    public ResponseEntity<SuiviProductionDto> save(@RequestBody SuiviProductionDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            SuiviProduction myT = mapper.toItem(dto);
            SuiviProduction t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                SuiviProductionDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  suiviProduction")
    @PutMapping("")
    public ResponseEntity<SuiviProductionDto> update(@RequestBody SuiviProductionDto dto){
        ResponseEntity<SuiviProductionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SuiviProduction t = service.findById(dto.getId());
            mapper.copy(dto,t);
            SuiviProduction updated = service.update(t);
            SuiviProductionDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of suiviProduction")
    @PostMapping("multiple")
    public ResponseEntity<List<SuiviProductionDto>> delete(@RequestBody List<SuiviProductionDto> dtos){
        ResponseEntity<List<SuiviProductionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<SuiviProduction> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified suiviProduction")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by produit id")
    @GetMapping("produit/id/{id}")
    public List<SuiviProductionDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }
    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<SuiviProductionDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }
    @Operation(summary = "find by unite code")
    @GetMapping("unite/code/{code}")
    public List<SuiviProductionDto> findByUniteCode(@PathVariable String code){
        return findDtos(service.findByUniteCode(code));
    }
    @Operation(summary = "delete by unite code")
    @DeleteMapping("unite/code/{code}")
    public int deleteByUniteCode(@PathVariable String code){
        return service.deleteByUniteCode(code);
    }
    @Operation(summary = "find by moyen code")
    @GetMapping("moyen/code/{code}")
    public List<SuiviProductionDto> findByMoyenCode(@PathVariable String code){
        return findDtos(service.findByMoyenCode(code));
    }
    @Operation(summary = "delete by moyen code")
    @DeleteMapping("moyen/code/{code}")
    public int deleteByMoyenCode(@PathVariable String code){
        return service.deleteByMoyenCode(code);
    }

    @Operation(summary = "Finds a suiviProduction and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SuiviProductionDto> findWithAssociatedLists(@PathVariable Long id) {
        SuiviProduction loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        SuiviProductionDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<SuiviProductionDto> findDtos(List<SuiviProduction> list){
        mapper.initObject(true);
        List<SuiviProductionDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<SuiviProductionDto> getDtoResponseEntity(SuiviProductionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public SuiviProductionRestAdmin(SuiviProductionAdminService service, SuiviProductionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final SuiviProductionAdminService service;
    private final SuiviProductionMapper mapper;





}
