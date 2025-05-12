package  ma.zyn.app.api.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.PointControle;
import ma.zyn.app.service.facade.admin.referentiel.PointControleAdminService;
import ma.zyn.app.ws.mapper.referentiel.PointControleMapper;
import ma.zyn.app.ws.dto.referentiel.PointControleDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/pointControle/")
public class PointControleRestAdmin {




    @Operation(summary = "Finds a list of all pointControles")
    @GetMapping("")
    public ResponseEntity<List<PointControleDto>> findAll(){
        ResponseEntity<List<PointControleDto>> res = null;
        List<PointControle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<PointControleDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all pointControles")
    @GetMapping("optimized")
    public ResponseEntity<List<PointControleDto>> findAllOptimized(){
        ResponseEntity<List<PointControleDto>> res = null;
        List<PointControle> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<PointControleDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a pointControle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PointControleDto> findById(@PathVariable Long id) {
        PointControle t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            PointControleDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a pointControle by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PointControleDto> findByLibelle(@PathVariable String libelle) {
	    PointControle t = service.findByReferenceEntity(new PointControle(libelle));
        if (t != null) {
            mapper.init(true);
            PointControleDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  pointControle")
    @PostMapping("")
    public ResponseEntity<PointControleDto> save(@RequestBody PointControleDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            PointControle myT = mapper.toItem(dto);
            PointControle t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                PointControleDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  pointControle")
    @PutMapping("")
    public ResponseEntity<PointControleDto> update(@RequestBody PointControleDto dto){
        ResponseEntity<PointControleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PointControle t = service.findById(dto.getId());
            mapper.copy(dto,t);
            PointControle updated = service.update(t);
            PointControleDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of pointControle")
    @PostMapping("multiple")
    public ResponseEntity<List<PointControleDto>> delete(@RequestBody List<PointControleDto> dtos){
        ResponseEntity<List<PointControleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<PointControle> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified pointControle")
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

    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<PointControleDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }

    @Operation(summary = "Finds a pointControle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PointControleDto> findWithAssociatedLists(@PathVariable Long id) {
        PointControle loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        PointControleDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<PointControleDto> findDtos(List<PointControle> list){
        mapper.initObject(true);
        List<PointControleDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<PointControleDto> getDtoResponseEntity(PointControleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public PointControleRestAdmin(PointControleAdminService service, PointControleMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final PointControleAdminService service;
    private final PointControleMapper mapper;





}
