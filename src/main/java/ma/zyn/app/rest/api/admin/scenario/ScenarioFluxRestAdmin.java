package  ma.zyn.app.api.facade.admin.scenario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService;
import ma.zyn.app.ws.mapper.scenario.ScenarioFluxMapper;
import ma.zyn.app.ws.dto.scenario.ScenarioFluxDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/scenarioFlux/")
public class ScenarioFluxRestAdmin {




    @Operation(summary = "Finds a list of all scenarioFluxs")
    @GetMapping("")
    public ResponseEntity<List<ScenarioFluxDto>> findAll(){
        ResponseEntity<List<ScenarioFluxDto>> res = null;
        List<ScenarioFlux> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<ScenarioFluxDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all scenarioFluxs")
    @GetMapping("optimized")
    public ResponseEntity<List<ScenarioFluxDto>> findAllOptimized(){
        ResponseEntity<List<ScenarioFluxDto>> res = null;
        List<ScenarioFlux> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<ScenarioFluxDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a scenarioFlux by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ScenarioFluxDto> findById(@PathVariable Long id) {
        ScenarioFlux t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ScenarioFluxDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a scenarioFlux by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ScenarioFluxDto> findByLibelle(@PathVariable String libelle) {
	    ScenarioFlux t = service.findByReferenceEntity(new ScenarioFlux(libelle));
        if (t != null) {
            mapper.init(true);
            ScenarioFluxDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  scenarioFlux")
    @PostMapping("")
    public ResponseEntity<ScenarioFluxDto> save(@RequestBody ScenarioFluxDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ScenarioFlux myT = mapper.toItem(dto);
            ScenarioFlux t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ScenarioFluxDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  scenarioFlux")
    @PutMapping("")
    public ResponseEntity<ScenarioFluxDto> update(@RequestBody ScenarioFluxDto dto){
        ResponseEntity<ScenarioFluxDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ScenarioFlux t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ScenarioFlux updated = service.update(t);
            ScenarioFluxDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of scenarioFlux")
    @PostMapping("multiple")
    public ResponseEntity<List<ScenarioFluxDto>> delete(@RequestBody List<ScenarioFluxDto> dtos){
        ResponseEntity<List<ScenarioFluxDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ScenarioFlux> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified scenarioFlux")
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

    @Operation(summary = "find by exercice id")
    @GetMapping("exercice/id/{id}")
    public List<ScenarioFluxDto> findByExerciceId(@PathVariable Long id){
        return findDtos(service.findByExerciceId(id));
    }
    @Operation(summary = "delete by exercice id")
    @DeleteMapping("exercice/id/{id}")
    public int deleteByExerciceId(@PathVariable Long id){
        return service.deleteByExerciceId(id);
    }
    @Operation(summary = "find by statusScenarioFlux code")
    @GetMapping("statusScenarioFlux/code/{code}")
    public List<ScenarioFluxDto> findByStatusScenarioFluxCode(@PathVariable String code){
        return findDtos(service.findByStatusScenarioFluxCode(code));
    }
    @Operation(summary = "delete by statusScenarioFlux code")
    @DeleteMapping("statusScenarioFlux/code/{code}")
    public int deleteByStatusScenarioFluxCode(@PathVariable String code){
        return service.deleteByStatusScenarioFluxCode(code);
    }

    @Operation(summary = "Finds a scenarioFlux and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ScenarioFluxDto> findWithAssociatedLists(@PathVariable Long id) {
        ScenarioFlux loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ScenarioFluxDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ScenarioFluxDto> findDtos(List<ScenarioFlux> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<ScenarioFluxDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ScenarioFluxDto> getDtoResponseEntity(ScenarioFluxDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ScenarioFluxRestAdmin(ScenarioFluxAdminService service, ScenarioFluxMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ScenarioFluxAdminService service;
    private final ScenarioFluxMapper mapper;





}
