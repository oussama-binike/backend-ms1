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

import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeDemandeMapper;
import ma.zyn.app.ws.dto.referentiel.TypeDemandeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeDemande/")
public class TypeDemandeRestAdmin {




    @Operation(summary = "Finds a list of all typeDemandes")
    @GetMapping("")
    public ResponseEntity<List<TypeDemandeDto>> findAll(){
        ResponseEntity<List<TypeDemandeDto>> res = null;
        List<TypeDemande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeDemandes")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeDemandeDto>> findAllOptimized(){
        ResponseEntity<List<TypeDemandeDto>> res = null;
        List<TypeDemande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeDemande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeDemandeDto> findById(@PathVariable Long id) {
        TypeDemande t = service.findById(id);
        if (t != null) {
            TypeDemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeDemande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeDemandeDto> findByLibelle(@PathVariable String libelle) {
	    TypeDemande t = service.findByReferenceEntity(new TypeDemande(libelle));
        if (t != null) {
            TypeDemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeDemande")
    @PostMapping("")
    public ResponseEntity<TypeDemandeDto> save(@RequestBody TypeDemandeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeDemande myT = mapper.toItem(dto);
            TypeDemande t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeDemandeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeDemande")
    @PutMapping("")
    public ResponseEntity<TypeDemandeDto> update(@RequestBody TypeDemandeDto dto){
        ResponseEntity<TypeDemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeDemande t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeDemande updated = service.update(t);
            TypeDemandeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeDemande")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeDemandeDto>> delete(@RequestBody List<TypeDemandeDto> dtos){
        ResponseEntity<List<TypeDemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeDemande> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeDemande")
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


    @Operation(summary = "Finds a typeDemande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeDemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeDemande loaded =  service.findWithAssociatedLists(id);
        TypeDemandeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeDemandeDto> findDtos(List<TypeDemande> list){
        List<TypeDemandeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeDemandeDto> getDtoResponseEntity(TypeDemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeDemandeRestAdmin(TypeDemandeAdminService service, TypeDemandeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeDemandeAdminService service;
    private final TypeDemandeMapper mapper;





}
