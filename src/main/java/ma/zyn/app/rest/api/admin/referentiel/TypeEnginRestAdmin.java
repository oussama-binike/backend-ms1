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

import ma.zyn.app.bean.core.referentiel.TypeEngin;
import ma.zyn.app.service.facade.admin.referentiel.TypeEnginAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeEnginMapper;
import ma.zyn.app.ws.dto.referentiel.TypeEnginDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeEngin/")
public class TypeEnginRestAdmin {




    @Operation(summary = "Finds a list of all typeEngins")
    @GetMapping("")
    public ResponseEntity<List<TypeEnginDto>> findAll(){
        ResponseEntity<List<TypeEnginDto>> res = null;
        List<TypeEngin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeEngins")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeEnginDto>> findAllOptimized(){
        ResponseEntity<List<TypeEnginDto>> res = null;
        List<TypeEngin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeEngin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeEnginDto> findById(@PathVariable Long id) {
        TypeEngin t = service.findById(id);
        if (t != null) {
            TypeEnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeEngin by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeEnginDto> findByLibelle(@PathVariable String libelle) {
	    TypeEngin t = service.findByReferenceEntity(new TypeEngin(libelle));
        if (t != null) {
            TypeEnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeEngin")
    @PostMapping("")
    public ResponseEntity<TypeEnginDto> save(@RequestBody TypeEnginDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeEngin myT = mapper.toItem(dto);
            TypeEngin t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeEnginDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeEngin")
    @PutMapping("")
    public ResponseEntity<TypeEnginDto> update(@RequestBody TypeEnginDto dto){
        ResponseEntity<TypeEnginDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeEngin t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeEngin updated = service.update(t);
            TypeEnginDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeEngin")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeEnginDto>> delete(@RequestBody List<TypeEnginDto> dtos){
        ResponseEntity<List<TypeEnginDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeEngin> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeEngin")
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


    @Operation(summary = "Finds a typeEngin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeEnginDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeEngin loaded =  service.findWithAssociatedLists(id);
        TypeEnginDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeEnginDto> findDtos(List<TypeEngin> list){
        List<TypeEnginDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeEnginDto> getDtoResponseEntity(TypeEnginDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeEnginRestAdmin(TypeEnginAdminService service, TypeEnginMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeEnginAdminService service;
    private final TypeEnginMapper mapper;





}
