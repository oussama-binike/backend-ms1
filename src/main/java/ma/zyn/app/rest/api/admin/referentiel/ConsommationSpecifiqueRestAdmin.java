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

import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import ma.zyn.app.service.facade.admin.referentiel.ConsommationSpecifiqueAdminService;
import ma.zyn.app.ws.mapper.referentiel.ConsommationSpecifiqueMapper;
import ma.zyn.app.ws.dto.referentiel.ConsommationSpecifiqueDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/consommationSpecifique/")
public class ConsommationSpecifiqueRestAdmin {




    @Operation(summary = "Finds a list of all consommationSpecifiques")
    @GetMapping("")
    public ResponseEntity<List<ConsommationSpecifiqueDto>> findAll(){
        ResponseEntity<List<ConsommationSpecifiqueDto>> res = null;
        List<ConsommationSpecifique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ConsommationSpecifiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all consommationSpecifiques")
    @GetMapping("optimized")
    public ResponseEntity<List<ConsommationSpecifiqueDto>> findAllOptimized(){
        ResponseEntity<List<ConsommationSpecifiqueDto>> res = null;
        List<ConsommationSpecifique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ConsommationSpecifiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a consommationSpecifique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConsommationSpecifiqueDto> findById(@PathVariable Long id) {
        ConsommationSpecifique t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ConsommationSpecifiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a consommationSpecifique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ConsommationSpecifiqueDto> findByLibelle(@PathVariable String libelle) {
	    ConsommationSpecifique t = service.findByReferenceEntity(new ConsommationSpecifique(libelle));
        if (t != null) {
            mapper.init(true);
            ConsommationSpecifiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  consommationSpecifique")
    @PostMapping("")
    public ResponseEntity<ConsommationSpecifiqueDto> save(@RequestBody ConsommationSpecifiqueDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ConsommationSpecifique myT = mapper.toItem(dto);
            ConsommationSpecifique t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ConsommationSpecifiqueDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  consommationSpecifique")
    @PutMapping("")
    public ResponseEntity<ConsommationSpecifiqueDto> update(@RequestBody ConsommationSpecifiqueDto dto){
        ResponseEntity<ConsommationSpecifiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ConsommationSpecifique t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ConsommationSpecifique updated = service.update(t);
            ConsommationSpecifiqueDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of consommationSpecifique")
    @PostMapping("multiple")
    public ResponseEntity<List<ConsommationSpecifiqueDto>> delete(@RequestBody List<ConsommationSpecifiqueDto> dtos){
        ResponseEntity<List<ConsommationSpecifiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ConsommationSpecifique> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified consommationSpecifique")
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

    @Operation(summary = "find by consommable code")
    @GetMapping("consommable/code/{code}")
    public List<ConsommationSpecifiqueDto> findByConsommableCode(@PathVariable String code){
        return findDtos(service.findByConsommableCode(code));
    }
    @Operation(summary = "delete by consommable code")
    @DeleteMapping("consommable/code/{code}")
    public int deleteByConsommableCode(@PathVariable String code){
        return service.deleteByConsommableCode(code);
    }
    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<ConsommationSpecifiqueDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }
    @Operation(summary = "find by unite code")
    @GetMapping("unite/code/{code}")
    public List<ConsommationSpecifiqueDto> findByUniteCode(@PathVariable String code){
        return findDtos(service.findByUniteCode(code));
    }
    @Operation(summary = "delete by unite code")
    @DeleteMapping("unite/code/{code}")
    public int deleteByUniteCode(@PathVariable String code){
        return service.deleteByUniteCode(code);
    }

    @Operation(summary = "Finds a consommationSpecifique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ConsommationSpecifiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        ConsommationSpecifique loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ConsommationSpecifiqueDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ConsommationSpecifiqueDto> findDtos(List<ConsommationSpecifique> list){
        mapper.initObject(true);
        List<ConsommationSpecifiqueDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ConsommationSpecifiqueDto> getDtoResponseEntity(ConsommationSpecifiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ConsommationSpecifiqueRestAdmin(ConsommationSpecifiqueAdminService service, ConsommationSpecifiqueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ConsommationSpecifiqueAdminService service;
    private final ConsommationSpecifiqueMapper mapper;





}
