package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    private VagaRepo vagaRepo;
    @Autowired
    private EmpresaRepo empresaRepo;

    @GetMapping
    public List<Vaga> all() { return vagaRepo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> get(@PathVariable Long id) {
        return vagaRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vaga vaga) {
        // se veio empresa com id, assegure que existe
        if (vaga.getEmpresa() != null && vaga.getEmpresa().getId() != null) {
            Long eid = vaga.getEmpresa().getId();
            return empresaRepo.findById(eid).map(e -> {
                vaga.setEmpresa(e);
                Vaga saved = vagaRepo.save(vaga);
                return ResponseEntity.ok(saved);
            }).orElse(ResponseEntity.badRequest().body("Empresa não encontrada com id: " + eid));
        } else {
            // permitir criar vaga sem empresa; ou exigir empresa
            Vaga saved = vagaRepo.save(vaga);
            return ResponseEntity.ok(saved);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vaga v) {
        return vagaRepo.findById(id).map(existing -> {
            existing.setTitulo(v.getTitulo());
            existing.setDescricao(v.getDescricao());
            existing.setPublicacao(v.getPublicacao());
            existing.setAtivo(v.getAtivo());
            if (v.getEmpresa() != null && v.getEmpresa().getId() != null) {
                empresaRepo.findById(v.getEmpresa().getId()).ifPresent(existing::setEmpresa);
            }
            vagaRepo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!vagaRepo.existsById(id)) return ResponseEntity.notFound().build();
        vagaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}