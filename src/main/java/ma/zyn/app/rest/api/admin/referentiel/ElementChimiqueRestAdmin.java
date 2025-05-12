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

import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;
import ma.zyn.app.ws.mapper.referentiel.ElementChimiqueMapper;
import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/elementChimique/")
public class ElementChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all elementChimiques")
    @GetMapping("")
    public ResponseEntity<List<ElementChimiqueDto>> findAll(){
        ResponseEntity<List<ElementChimiqueDto>> res = null;
        List<ElementChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ElementChimiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all elementChimiques")
    @GetMapping("optimized")
    public ResponseEntity<List<ElementChimiqueDto>> findAllOptimized(){
        ResponseEntity<List<ElementChimiqueDto>> res = null;
        List<ElementChimique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ElementChimiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a elementChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ElementChimiqueDto> findById(@PathVariable Long id) {
        ElementChimique t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ElementChimiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a elementChimique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ElementChimiqueDto> findByLibelle(@PathVariable String libelle) {
	    ElementChimique t = service.findByReferenceEntity(new ElementChimique(libelle));
        if (t != null) {
            mapper.init(true);
            ElementChimiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  elementChimique")
    @PostMapping("")
    public ResponseEntity<ElementChimiqueDto> save(@RequestBody ElementChimiqueDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ElementChimique myT = mapper.toItem(dto);
            ElementChimique t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ElementChimiqueDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  elementChimique")
    @PutMapping("")
    public ResponseEntity<ElementChimiqueDto> update(@RequestBody ElementChimiqueDto dto){
        ResponseEntity<ElementChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ElementChimique t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ElementChimique updated = service.update(t);
            ElementChimiqueDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of elementChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<ElementChimiqueDto>> delete(@RequestBody List<ElementChimiqueDto> dtos){
        ResponseEntity<List<ElementChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ElementChimique> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified elementChimique")
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


    @Operation(summary = "Finds a elementChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ElementChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        ElementChimique loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ElementChimiqueDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ElementChimiqueDto> findDtos(List<ElementChimique> list){
        mapper.initObject(true);
        List<ElementChimiqueDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ElementChimiqueDto> getDtoResponseEntity(ElementChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ElementChimiqueRestAdmin(ElementChimiqueAdminService service, ElementChimiqueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ElementChimiqueAdminService service;
    private final ElementChimiqueMapper mapper;





}
