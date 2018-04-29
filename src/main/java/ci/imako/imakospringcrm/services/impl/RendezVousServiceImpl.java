package ci.imako.imakospringcrm.services.impl;

import ci.imako.imakospringcrm.domain.RendezVous;
import ci.imako.imakospringcrm.repositories.RendezVousRepository;
import ci.imako.imakospringcrm.services.IRendezVousService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class RendezVousServiceImpl implements IRendezVousService {

    private RendezVousRepository rendezVousRepository;

    @Autowired
    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public RendezVous save(RendezVous rendezVous) {
        return null;
    }

    @Override
    public Optional<RendezVous> readById(Long aLong) {
        return null;
    }

    @Override
    public List<RendezVous> readAll() {
        return null;
    }

    @Override
    public RendezVous update(RendezVous rendezVous) {
        return null;
    }

    @Override
    public void delete(RendezVous rendezVous) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
