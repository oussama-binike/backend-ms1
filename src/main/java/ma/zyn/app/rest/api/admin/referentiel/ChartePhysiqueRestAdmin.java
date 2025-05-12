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

import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.service.facade.admin.referentiel.ChartePhysiqueAdminService;
import ma.zyn.app.ws.mapper.referentiel.ChartePhysiqueMapper;
import ma.zyn.app.ws.dto.referentiel.ChartePhysiqueDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/chartePhysique/")
public class ChartePhysiqueRestAdmin {




    @Operation(summary = "Finds a list of all chartePhysiques")
    @GetMapping("")
    public ResponseEntity<List<ChartePhysiqueDto>> findAll(){
        ResponseEntity<List<ChartePhysiqueDto>> res = null;
        List<ChartePhysique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ChartePhysiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all chartePhysiques")
    @GetMapping("optimized")
    public ResponseEntity<List<ChartePhysiqueDto>> findAllOptimized(){
        ResponseEntity<List<ChartePhysiqueDto>> res = null;
        List<ChartePhysique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ChartePhysiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a chartePhysique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ChartePhysiqueDto> findById(@PathVariable Long id) {
        ChartePhysique t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ChartePhysiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a chartePhysique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ChartePhysiqueDto> findByLibelle(@PathVariable String libelle) {
	    ChartePhysique t = service.findByReferenceEntity(new ChartePhysique(libelle));
        if (t != null) {
            mapper.init(true);
            ChartePhysiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  chartePhysique")
    @PostMapping("")
    public ResponseEntity<ChartePhysiqueDto> save(@RequestBody ChartePhysiqueDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ChartePhysique myT = mapper.toItem(dto);
            ChartePhysique t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ChartePhysiqueDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  chartePhysique")
    @PutMapping("")
    public ResponseEntity<ChartePhysiqueDto> update(@RequestBody ChartePhysiqueDto dto){
        ResponseEntity<ChartePhysiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ChartePhysique t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ChartePhysique updated = service.update(t);
            ChartePhysiqueDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of chartePhysique")
    @PostMapping("multiple")
    public ResponseEntity<List<ChartePhysiqueDto>> delete(@RequestBody List<ChartePhysiqueDto> dtos){
        ResponseEntity<List<ChartePhysiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ChartePhysique> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified chartePhysique")
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


    @Operation(summary = "Finds a chartePhysique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ChartePhysiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        ChartePhysique loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ChartePhysiqueDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ChartePhysiqueDto> findDtos(List<ChartePhysique> list){
        mapper.initObject(true);
        List<ChartePhysiqueDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ChartePhysiqueDto> getDtoResponseEntity(ChartePhysiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ChartePhysiqueRestAdmin(ChartePhysiqueAdminService service, ChartePhysiqueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ChartePhysiqueAdminService service;
    private final ChartePhysiqueMapper mapper;





}
