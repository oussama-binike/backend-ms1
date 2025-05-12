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

import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.service.facade.admin.referentiel.TypeWagonAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeWagonMapper;
import ma.zyn.app.ws.dto.referentiel.TypeWagonDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeWagon/")
public class TypeWagonRestAdmin {




    @Operation(summary = "Finds a list of all typeWagons")
    @GetMapping("")
    public ResponseEntity<List<TypeWagonDto>> findAll(){
        ResponseEntity<List<TypeWagonDto>> res = null;
        List<TypeWagon> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeWagonDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeWagons")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeWagonDto>> findAllOptimized(){
        ResponseEntity<List<TypeWagonDto>> res = null;
        List<TypeWagon> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeWagonDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeWagon by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeWagonDto> findById(@PathVariable Long id) {
        TypeWagon t = service.findById(id);
        if (t != null) {
            TypeWagonDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeWagon by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeWagonDto> findByLibelle(@PathVariable String libelle) {
	    TypeWagon t = service.findByReferenceEntity(new TypeWagon(libelle));
        if (t != null) {
            TypeWagonDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeWagon")
    @PostMapping("")
    public ResponseEntity<TypeWagonDto> save(@RequestBody TypeWagonDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeWagon myT = mapper.toItem(dto);
            TypeWagon t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeWagonDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeWagon")
    @PutMapping("")
    public ResponseEntity<TypeWagonDto> update(@RequestBody TypeWagonDto dto){
        ResponseEntity<TypeWagonDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeWagon t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeWagon updated = service.update(t);
            TypeWagonDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeWagon")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeWagonDto>> delete(@RequestBody List<TypeWagonDto> dtos){
        ResponseEntity<List<TypeWagonDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeWagon> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeWagon")
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


    @Operation(summary = "Finds a typeWagon and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeWagonDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeWagon loaded =  service.findWithAssociatedLists(id);
        TypeWagonDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeWagonDto> findDtos(List<TypeWagon> list){
        List<TypeWagonDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeWagonDto> getDtoResponseEntity(TypeWagonDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeWagonRestAdmin(TypeWagonAdminService service, TypeWagonMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeWagonAdminService service;
    private final TypeWagonMapper mapper;





}
