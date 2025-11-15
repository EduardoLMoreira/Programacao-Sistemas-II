package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaRepo repo;

    @GetMapping
    public List<Empresa> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa create(@RequestBody Empresa e) { return repo.save(e); }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody Empresa e) {
        return repo.findById(id).map(existing -> {
            existing.setNome(e.getNome());
            existing.setCnpj(e.getCnpj());
            existing.setEmailContato(e.getEmailContato());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}