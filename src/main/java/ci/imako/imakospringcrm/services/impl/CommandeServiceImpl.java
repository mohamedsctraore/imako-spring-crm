package ci.imako.imakospringcrm.services.impl;

import ci.imako.imakospringcrm.domain.Commande;
import ci.imako.imakospringcrm.repositories.CommandeRepository;
import ci.imako.imakospringcrm.services.ICommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CommandeServiceImpl implements ICommandeService {

    private CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande save(Commande commande) {
        return null;
    }

    @Override
    public Optional<Commande> readById(Long aLong) {
        return null;
    }

    @Override
    public List<Commande> readAll() {
        return null;
    }

    @Override
    public Commande update(Commande commande) {
        return null;
    }

    @Override
    public void delete(Commande commande) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
