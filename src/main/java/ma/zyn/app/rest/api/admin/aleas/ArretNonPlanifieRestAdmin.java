package  ma.zyn.app.api.facade.admin.aleas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import ma.zyn.app.service.facade.admin.aleas.ArretNonPlanifieAdminService;
import ma.zyn.app.ws.mapper.aleas.ArretNonPlanifieMapper;
import ma.zyn.app.ws.dto.aleas.ArretNonPlanifieDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/arretNonPlanifie/")
public class ArretNonPlanifieRestAdmin {




    @Operation(summary = "Finds a list of all arretNonPlanifies")
    @GetMapping("")
    public ResponseEntity<List<ArretNonPlanifieDto>> findAll(){
        ResponseEntity<List<ArretNonPlanifieDto>> res = null;
        List<ArretNonPlanifie> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ArretNonPlanifieDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all arretNonPlanifies")
    @GetMapping("optimized")
    public ResponseEntity<List<ArretNonPlanifieDto>> findAllOptimized(){
        ResponseEntity<List<ArretNonPlanifieDto>> res = null;
        List<ArretNonPlanifie> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ArretNonPlanifieDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a arretNonPlanifie by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ArretNonPlanifieDto> findById(@PathVariable Long id) {
        ArretNonPlanifie t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ArretNonPlanifieDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a arretNonPlanifie by code")
    @GetMapping("code/{code}")
    public ResponseEntity<ArretNonPlanifieDto> findByCode(@PathVariable String code) {
	    ArretNonPlanifie t = service.findByReferenceEntity(new ArretNonPlanifie(code));
        if (t != null) {
            mapper.init(true);
            ArretNonPlanifieDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  arretNonPlanifie")
    @PostMapping("")
    public ResponseEntity<ArretNonPlanifieDto> save(@RequestBody ArretNonPlanifieDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ArretNonPlanifie myT = mapper.toItem(dto);
            ArretNonPlanifie t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ArretNonPlanifieDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  arretNonPlanifie")
    @PutMapping("")
    public ResponseEntity<ArretNonPlanifieDto> update(@RequestBody ArretNonPlanifieDto dto){
        ResponseEntity<ArretNonPlanifieDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ArretNonPlanifie t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ArretNonPlanifie updated = service.update(t);
            ArretNonPlanifieDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of arretNonPlanifie")
    @PostMapping("multiple")
    public ResponseEntity<List<ArretNonPlanifieDto>> delete(@RequestBody List<ArretNonPlanifieDto> dtos){
        ResponseEntity<List<ArretNonPlanifieDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ArretNonPlanifie> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified arretNonPlanifie")
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


    @Operation(summary = "Finds a arretNonPlanifie and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ArretNonPlanifieDto> findWithAssociatedLists(@PathVariable Long id) {
        ArretNonPlanifie loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ArretNonPlanifieDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ArretNonPlanifieDto> findDtos(List<ArretNonPlanifie> list){
        mapper.initObject(true);
        List<ArretNonPlanifieDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ArretNonPlanifieDto> getDtoResponseEntity(ArretNonPlanifieDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ArretNonPlanifieRestAdmin(ArretNonPlanifieAdminService service, ArretNonPlanifieMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ArretNonPlanifieAdminService service;
    private final ArretNonPlanifieMapper mapper;





}
