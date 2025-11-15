package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
    @Autowired
    private EstudanteRepo repo;

    @GetMapping
    public List<Estudante> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudante create(@RequestBody Estudante s) { return repo.save(s); }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> update(@PathVariable Long id, @RequestBody Estudante s) {
        return repo.findById(id).map(existing -> {
            existing.setNome(s.getNome());
            existing.setEmail(s.getEmail());
            existing.setNascimento(s.getNascimento());
            existing.setAnoIngresso(s.getAnoIngresso());
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