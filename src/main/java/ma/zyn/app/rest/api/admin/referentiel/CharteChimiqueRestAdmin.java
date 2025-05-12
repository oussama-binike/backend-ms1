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

import ma.zyn.app.bean.core.referentiel.CharteChimique;
import ma.zyn.app.service.facade.admin.referentiel.CharteChimiqueAdminService;
import ma.zyn.app.ws.mapper.referentiel.CharteChimiqueMapper;
import ma.zyn.app.ws.dto.referentiel.CharteChimiqueDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/charteChimique/")
public class CharteChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all charteChimiques")
    @GetMapping("")
    public ResponseEntity<List<CharteChimiqueDto>> findAll(){
        ResponseEntity<List<CharteChimiqueDto>> res = null;
        List<CharteChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<CharteChimiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all charteChimiques")
    @GetMapping("optimized")
    public ResponseEntity<List<CharteChimiqueDto>> findAllOptimized(){
        ResponseEntity<List<CharteChimiqueDto>> res = null;
        List<CharteChimique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<CharteChimiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a charteChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CharteChimiqueDto> findById(@PathVariable Long id) {
        CharteChimique t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            CharteChimiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a charteChimique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CharteChimiqueDto> findByLibelle(@PathVariable String libelle) {
	    CharteChimique t = service.findByReferenceEntity(new CharteChimique(libelle));
        if (t != null) {
            mapper.init(true);
            CharteChimiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  charteChimique")
    @PostMapping("")
    public ResponseEntity<CharteChimiqueDto> save(@RequestBody CharteChimiqueDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            CharteChimique myT = mapper.toItem(dto);
            CharteChimique t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CharteChimiqueDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  charteChimique")
    @PutMapping("")
    public ResponseEntity<CharteChimiqueDto> update(@RequestBody CharteChimiqueDto dto){
        ResponseEntity<CharteChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CharteChimique t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CharteChimique updated = service.update(t);
            CharteChimiqueDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of charteChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<CharteChimiqueDto>> delete(@RequestBody List<CharteChimiqueDto> dtos){
        ResponseEntity<List<CharteChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<CharteChimique> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified charteChimique")
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

    @Operation(summary = "find by elementChimique code")
    @GetMapping("elementChimique/code/{code}")
    public List<CharteChimiqueDto> findByElementChimiqueCode(@PathVariable String code){
        return findDtos(service.findByElementChimiqueCode(code));
    }
    @Operation(summary = "delete by elementChimique code")
    @DeleteMapping("elementChimique/code/{code}")
    public int deleteByElementChimiqueCode(@PathVariable String code){
        return service.deleteByElementChimiqueCode(code);
    }

    @Operation(summary = "Finds a charteChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CharteChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        CharteChimique loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        CharteChimiqueDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CharteChimiqueDto> findDtos(List<CharteChimique> list){
        mapper.initObject(true);
        List<CharteChimiqueDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CharteChimiqueDto> getDtoResponseEntity(CharteChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CharteChimiqueRestAdmin(CharteChimiqueAdminService service, CharteChimiqueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CharteChimiqueAdminService service;
    private final CharteChimiqueMapper mapper;





}
